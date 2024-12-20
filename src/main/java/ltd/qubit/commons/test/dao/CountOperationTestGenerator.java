////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2022 - 2024.
//    Haixing Hu, Qubit Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////
package ltd.qubit.commons.test.dao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CountOperationTestGenerator<T> extends DaoOperationTestGenerator<T> {

  public CountOperationTestGenerator(final DaoTestGeneratorRegistry registry,
      final Class<T> modelType, final DaoMethodInfo methodInfo) {
    super(registry, modelType, methodInfo);
  }

  @Override
 protected void buildTests(final DaoDynamicTestBuilder builder) {
    countEmptyTableWithNullCriterion(builder);
    countNonEmptyTableWithNullCriterion(builder);
    //  TODO
  }

  private void countEmptyTableWithNullCriterion(final DaoDynamicTestBuilder builder) {
    final String displayName = getDisplayName("Empty table with null criterion");
    builder.add(displayName, () -> {
      logger.info("Test {}: Count the empty table with a null criterion.",
          methodName);
      logger.info("Clear all the {}s.", modelName);
      daoInfo.clear();
      final Long count = (Long) methodInfo.invoke(true, null);
      assertNotNull(count, "Return value of " + methodName + " cannot be null.");
      assertEquals(0, count, "Calling " + methodName + " on an empty table with"
          + " null criterion must return 0.");
    });
  }

  private void countNonEmptyTableWithNullCriterion(final DaoDynamicTestBuilder builder) {
    final String displayName = getDisplayName("Empty table with null criterion");
    final int loops = parameters.getLoops();
    final int tableSize = parameters.getTableSize();
    builder.add(displayName, () -> {
      logger.info("Test {}: Count the non-empty table with a null criterion.",
          methodName);
      for (int i = 0; i < loops; ++i) {
        logger.info("Clear all the {}s.", modelName);
        daoInfo.clear();
        final int n = random.nextInt(0, tableSize);
        logger.info("Add {} {}s.", n, modelName);
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
        final List<T> models = beanCreator.prepare(n, modelInfo);
        // 通过 count 记录下目前数据库中已有的模型数量
        final Long existingCount = (Long) methodInfo.invoke(true, null);
        // 接下来把准备好的模型加入
        daoInfo.addAll(models);
        // 计算出目前实际应该有的对象总数
        final long expectedCount = n + existingCount;
        // 再接下来再次 count 目前数据库中已有的模型数量
        logger.info("Counts the total number of {}s without filter.", modelName);
        final Long count = (Long) methodInfo.invoke(true, null);
        assertNotNull(count, "Return value of " + methodName + " cannot be null.");
        assertEquals(expectedCount, count,
            "Calling " + methodName + " on an non-empty table"
            + " with null criterion must return the exactly number of records "
            + "in the table.");
        daoInfo.clear();
      }
    });
  }

  // private void countEmptyTableWithNonNullCriterion(final DaoDynamicTestBuilder builder) {
  //   final String displayName = getDisplayName("Empty table with null criterion");
  //   final int loops = parameters.getLoops();
  //   builder.add(displayName, () -> {
  //     for (int i = 0; i < loops; ++i) {
  //       logger.info("Test {}: Count the empty table with a non-null criterion: "
  //               + "{} of {}", methodName, i + 1, loops);
  //       final Long count = (Long) methodInfo.invokeWithArguments(true, null);
  //       assertNotNull(count, "Return value of " + methodName + " cannot be null.");
  //       assertEquals(0, count, "Calling " + methodName + " on an empty table "
  //           + "with non-null criterion must return 0.");
  //     }
  //   });
  // }
}
