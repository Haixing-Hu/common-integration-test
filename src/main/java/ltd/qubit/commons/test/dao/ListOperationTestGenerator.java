////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2022 - 2024.
//    Haixing Hu, Qubit Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////
package ltd.qubit.commons.test.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import ltd.qubit.commons.sql.Criterion;
import ltd.qubit.commons.sql.SortRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListOperationTestGenerator<T> extends DaoOperationTestGenerator<T> {

  public ListOperationTestGenerator(final DaoTestGeneratorRegistry registry,
      final Class<T> modelType, final DaoMethodInfo methodInfo) {
    super(registry, modelType, methodInfo);
  }

  @Override
  protected void buildTests(final DaoDynamicTestBuilder builder) {
    listEmptyDatasetWithoutFilter(builder);
    listNonEmptyDatasetWithoutFilter(builder);
    //  TODO
  }

  @SuppressWarnings("unchecked")
  private List<T> doList(final Criterion<T> criterion, final SortRequest<T> sortRequest,
      final Integer limit, final Long offset) throws Throwable {
    final Object result = methodInfo.invoke(true, criterion, sortRequest, limit, offset);
    assertNotNull(result, "The result must not be null.");
    assertInstanceOf(List.class, result, "The result must be a list.");
    return (List<T>) result;
  }

  private void listEmptyDatasetWithoutFilter(final DaoDynamicTestBuilder builder) {
    final String displayName = getDisplayName("Empty " + modelName + " data set");
    builder.add(displayName, () -> {
      logger.info("Test {}: List the {} from an empty {} data set without filter and sort.",
          methodName, targetName, modelName);
      final List<T> result = doList(null, null, 100, 0L);
      assertEquals(0, result.size(), "The result must be an empty list.");
    });
  }

  private List<T> prepareRandomList(final List<T> existingModels) throws Throwable {
    final int tableSize = parameters.getTableSize();
    final int n = random.nextInt(0, tableSize);
    logger.info("Add {} {}s.", n, modelName);
    final List<T> models = beanCreator.prepare(n, modelInfo);
    // 注意：必须先准备好所有待添加的模型对象，并且记录下准备这些模型对象后目前数据库中
    // 该对象对应的表已经有多少对象，这是因为在准备某些对象时，可能需要往数据库中加入其他
    // 一些同类型的对象。例如对于下面两个类：
    //   class A {
    //     @Reference(entity = B.class, existing = false)
    //     private B child;
    //   }
    //   class B {
    //     @Reference(entity = A.class, property = "id")
    //     private Long parentId;
    //   }
    // 上述两个类的关系是常见的父子关系。在准备类B对象时，会先创建一个存在的A对象并将其加
    // 入数据库，而将A对象加入数据库时有可能会将A.child也加入数据库。这样在准备好B对象后，
    // 数据库中就会已经有一个B对象了。
    final List<T> existing = daoInfo.listAll();
    existingModels.clear();
    existingModels.addAll(existing);
    daoInfo.addAll(models);
    return models;
  }

  private void listNonEmptyDatasetWithoutFilter(final DaoDynamicTestBuilder builder) {
    final String displayName = getDisplayName("Non-empty " + modelName
        + " data set without filter");
    final int loops = parameters.getLoops();
    final int tableSize = parameters.getTableSize();
    builder.add(displayName, () -> {
      for (int i = 0; i < loops; ++i) {
        logger.info("Test {}: List the {} from a non-empty {} data set without filter: {} of {}",
            methodName, targetName, modelName, i + 1, loops);
        final List<T> existModels = new ArrayList<>();
        final List<T> models = prepareRandomList(existModels);
        final List<T> result = doList(null, null, null, 0L);
        assertEquals(existModels.size() + models.size(), result.size(),
            "The result must contain all the models.");
        final HashSet<Object> expectedSet = new HashSet<>();
        if (target == null) {
          expectedSet.addAll(existModels);
          expectedSet.addAll(models);
        } else {
          existModels.forEach(model -> expectedSet.add(target.getValue(model)));
          models.forEach(model -> expectedSet.add(target.getValue(model)));
        }
        for (final T obj : result) {
          assertTrue(expectedSet.contains(obj), "The result must contain all the models.");
        }
        daoInfo.clear();
      }
    });
  }
}
