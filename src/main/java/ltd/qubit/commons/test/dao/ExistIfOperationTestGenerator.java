////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2022 - 2024.
//    Haixing Hu, Qubit Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////
package ltd.qubit.commons.test.dao;

import ltd.qubit.commons.reflect.Property;
import ltd.qubit.commons.sql.Criterion;
import ltd.qubit.commons.sql.SimpleCriterion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static ltd.qubit.commons.util.ComparisonOperator.EQUAL;
import static ltd.qubit.commons.util.ComparisonOperator.NOT_EQUAL;

public class ExistIfOperationTestGenerator<T> extends DaoOperationTestGenerator<T> {

  public ExistIfOperationTestGenerator(final DaoTestGeneratorRegistry registry,
      final Class<T> modelType, final DaoMethodInfo methodInfo) {
    super(registry, modelType, methodInfo);
  }

  @Override
 protected void buildTests(final DaoDynamicTestBuilder builder) {
    testOnEmptyTableWithNullCriterion(builder);
    testOnNonEmptyTableWithNullCriterion(builder);
    if (modelInfo.hasIdProperty()) {
      testOnNonEmptyTableWithIdEqualityCriterion(builder);
    }
  }

  private void testOnEmptyTableWithNullCriterion(final DaoDynamicTestBuilder builder) {
    final String displayName = getDisplayName("Empty table with null criterion");
    builder.add(displayName, () -> {
      logger.info("Test {}: Tests the existence of entities on an empty table with a null criterion.",
          methodName);
      daoInfo.clear();
      final Boolean result = (Boolean) methodInfo.invoke(true, null);
      assertNotNull(result, "Return value of " + methodName + " cannot be null.");
      assertEquals(false, result, "Calling " + methodName + " on an empty table with"
          + " null criterion must return false.");
    });
  }

  private void testOnNonEmptyTableWithNullCriterion(final DaoDynamicTestBuilder builder) {
    final String displayName = getDisplayName("Empty table with null criterion");
    builder.add(displayName, () -> {
      logger.info("Test {}: Tests the existence of entities on a non-empty table with a null criterion.",
          methodName);
      logger.info("Clear all the {}s.", modelName);
      daoInfo.clear();
      final Object model = beanCreator.prepare(modelInfo);
      daoInfo.add(model);
      logger.info("Tests the existence of entities on a non-empty table of {}s without filter.", modelName);
      final Boolean result = (Boolean) methodInfo.invoke(true, null);
      assertNotNull(result, "Return value of " + methodName + " cannot be null.");
      assertEquals(true, result, "Calling " + methodName + " on a non-empty table with"
          + " null criterion must return true.");
    });
  }

  private void testOnNonEmptyTableWithIdEqualityCriterion(final DaoDynamicTestBuilder builder) {
    final String displayName = getDisplayName("Empty table with ID equality criterion");
    @SuppressWarnings("unchecked")
    final Class<T> beanClass = (Class<T>) modelInfo.getType();
    final Property idProperty = modelInfo.getIdProperty();
    if (idProperty == null) {
      logger.info("No ID property found in {}.", modelName);
      return;
    }
    builder.add(displayName, () -> {
      logger.info("Test {}: Tests the existence of entities on a non-empty table with an ID equality criterion.",
          methodName);
      logger.info("Clear all the {}s.", modelName);
      daoInfo.clear();
      final Object model = beanCreator.prepare(modelInfo);
      daoInfo.add(model);
      final Object id = idProperty.getValue(model);
      logger.info("Tests the existence of entities on a non-empty table of {}s with ID equality filter.", modelName);
      final Criterion<T> f1 = new SimpleCriterion<>(beanClass, idProperty.getName(), EQUAL, id);
      final Boolean result = (Boolean) methodInfo.invoke(true, f1);
      assertNotNull(result, "Return value of " + methodName + " cannot be null.");
      assertEquals(true, result, "Calling " + methodName + " on a non-empty table with"
          + " the criterion that ID equals to the ID of an existent entity must return true.");
    });
  }

  private void testOnNonEmptyTableWithIdNonEqualityCriterion(final DaoDynamicTestBuilder builder) {
    final String displayName = getDisplayName("Empty table with ID non-equality criterion");
    @SuppressWarnings("unchecked")
    final Class<T> beanClass = (Class<T>) modelInfo.getType();
    final Property idProperty = modelInfo.getIdProperty();
    if (idProperty == null) {
      logger.info("No ID property found in {}.", modelName);
      return;
    }
    builder.add(displayName, () -> {
      logger.info("Test {}: Tests the existence of entities on a non-empty table with an ID equality criterion.",
          methodName);
      logger.info("Clear all the {}s.", modelName);
      daoInfo.clear();
      final Object m1 = beanCreator.prepare(modelInfo);
      daoInfo.add(m1);
      final Object id = idProperty.getValue(m1);
      logger.info("Tests the existence of entities on a non-empty table of {}s with ID non-equality filter.", modelName);
      final Criterion<T> filter = new SimpleCriterion<>(beanClass, idProperty.getName(), NOT_EQUAL, id);
      final Boolean r1 = (Boolean) methodInfo.invoke(true, filter);
      assertNotNull(r1, "Return value of " + methodName + " cannot be null.");
      assertEquals(false, r1, "Calling " + methodName + " on a non-empty table with"
          + " the criterion that ID not equals to the ID of an existent entity must return false.");
      daoInfo.clear();
      final Object m2 = beanCreator.prepare(modelInfo);
      daoInfo.add(m2);
      final Boolean r2 = (Boolean) methodInfo.invoke(true, filter);
      assertNotNull(r2, "Return value of " + methodName + " cannot be null.");
      assertEquals(true, r2, "Calling " + methodName + " on a non-empty table with"
          + " the criterion that ID not equals to the ID of a non-existent entity must return true.");
    });
  }
}
