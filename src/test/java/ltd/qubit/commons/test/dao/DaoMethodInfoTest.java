////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2022 - 2024.
//    Haixing Hu, Qubit Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////
package ltd.qubit.commons.test.dao;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ltd.qubit.commons.reflect.BeanInfo;
import ltd.qubit.commons.reflect.Option;
import ltd.qubit.commons.test.assertion.Assertions;
import ltd.qubit.commons.test.dao.testbed.CountryDao;
import ltd.qubit.commons.test.dao.testbed.CountryDaoImpl;
import ltd.qubit.commons.test.model.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import static ltd.qubit.commons.reflect.FieldUtils.getField;
import static ltd.qubit.commons.reflect.MethodUtils.getMethodByName;
import static ltd.qubit.commons.reflect.Option.BEAN_FIELD;
import static ltd.qubit.commons.reflect.Option.BEAN_METHOD;

public class DaoMethodInfoTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(DaoMethodInfoTest.class);

  @Test
  public void testConstructor() throws Exception {
    final CountryDao countryDao = new CountryDaoImpl();
    final Class<?> daoType = CountryDao.class;
    final Class<?> modelType = Country.class;
    final BeanInfo modelInfo = BeanInfo.of(modelType);
    Method method;
    DaoMethodInfo methodInfo;

    method = getMethodByName(daoType, Option.BEAN_METHOD, "count");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals("CountryDao.count", methodInfo.getQualifiedName());
    assertEquals(URI.create("method:ltd.qubit.commons.test.dao.testbed."
        + "ListableDao#count(ltd.qubit.commons.sql.Criterion)"),
        methodInfo.getUri());
    assertEquals(DaoOperation.COUNT, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(null, methodInfo.getIdentifier());
    Assertions.assertCollectionEquals(Collections.emptySet(), methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(modelInfo.getNonComputedProperties(), methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "list");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.LIST, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(null, methodInfo.getIdentifier());
    Assertions.assertCollectionEquals(Collections.emptySet(), methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(modelInfo.getNonComputedProperties(),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "exist");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.EXIST, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "id"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(Collections.emptySet(), methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(modelInfo.getNonComputedProperties(),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "existCode");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.EXIST, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "code"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(Collections.emptySet(), methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(modelInfo.getNonComputedProperties(),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "existName");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.EXIST, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "name"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(Collections.emptySet(), methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(modelInfo.getNonComputedProperties(),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "existNonDeleted");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.EXIST_NON_DELETED, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "id"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(Collections.emptySet(), methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(modelInfo.getNonComputedProperties(),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "existNonDeletedCode");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.EXIST_NON_DELETED, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "code"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(Collections.emptySet(), methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(modelInfo.getNonComputedProperties(),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "get");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.GET, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "id"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(Collections.emptySet(), methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(modelInfo.getNonComputedProperties(),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "getByCode");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.GET, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "code"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(Collections.emptySet(), methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(modelInfo.getNonComputedProperties(),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "getByName");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.GET, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "name"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(Collections.emptySet(), methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(modelInfo.getNonComputedProperties(),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "getInfo");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.GET, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget().getField());
    assertEquals(getMethodByName(Country.class, BEAN_METHOD, "getInfo"),
        methodInfo.getTarget().getReadMethod());
    assertEquals(getField(modelType, BEAN_FIELD, "id"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(Collections.emptySet(), methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(modelInfo.getNonComputedProperties(),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "getInfoByCode");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.GET, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget().getField());
    assertEquals(getMethodByName(Country.class, BEAN_METHOD, "getInfo"),
        methodInfo.getTarget().getReadMethod());
    assertEquals(getField(modelType, BEAN_FIELD, "code"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(Collections.emptySet(), methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(modelInfo.getNonComputedProperties(),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "add");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals("CountryDao.add", methodInfo.getQualifiedName());
    assertEquals(URI.create("method:ltd.qubit.commons.test.dao.testbed."
            + "AddableDao#add(ltd.qubit.commons.test.model.Identifiable)"),
        methodInfo.getUri());
    assertEquals(DaoOperation.ADD, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(null, methodInfo.getIdentifier());
    Assertions.assertCollectionEquals(
        modelInfo.getProperties("id", "createTime", "modifyTime", "deleteTime"),
        methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(
        modelInfo.getProperties("code", "name", "phoneArea", "postalcode",
            "icon", "url", "description", "predefined", "class"),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "addOrUpdateByCode");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.ADD_OR_UPDATE, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "code"),
        methodInfo.getIdentifier().getField());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "addOrUpdateByName");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.ADD_OR_UPDATE, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "name"),
        methodInfo.getIdentifier().getField());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "update");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.UPDATE, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "id"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(
        modelInfo.getProperties("name", "phoneArea", "postalcode",
            "icon", "url", "description", "predefined", "modifyTime"),
        methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(modelInfo.getProperties("id", "code", "createTime",
            "deleteTime", "class"),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "updateByCode");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.UPDATE, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "code"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(
        modelInfo.getProperties("name", "phoneArea", "postalcode",
            "icon", "url", "description", "predefined", "modifyTime"),
        methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(
        modelInfo.getProperties("id", "code", "createTime", "deleteTime",
           "class"),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "updateByName");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.UPDATE, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "name"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(
        modelInfo.getProperties("phoneArea", "postalcode",
            "icon", "url", "description", "predefined", "modifyTime"),
        methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(
        modelInfo.getProperties("id", "code", "name", "createTime",
            "deleteTime", "class"),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "updateName");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.UPDATE, methodInfo.getOperation());
    assertEquals(getField(modelType, BEAN_FIELD, "name"),
        methodInfo.getTarget().getField());
    assertEquals(getField(modelType, BEAN_FIELD, "id"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(modelInfo.getProperties("name", "modifyTime"),
        methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(modelInfo.getProperties("id", "code", "phoneArea",
            "postalcode", "icon", "url", "description", "createTime",
            "deleteTime", "class"),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "updateNameByCode");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.UPDATE, methodInfo.getOperation());
    assertEquals(getField(modelType, BEAN_FIELD, "name"),
        methodInfo.getTarget().getField());
    assertEquals(getField(modelType, BEAN_FIELD, "code"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(modelInfo.getProperties("name", "modifyTime"),
        methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(modelInfo.getProperties("id", "code", "phoneArea",
            "postalcode", "icon", "url", "description", "createTime",
            "deleteTime", "class"),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "delete");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.DELETE, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "id"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(modelInfo.getProperties("deleteTime"),
        methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(
        modelInfo.getProperties("id", "code", "name", "phoneArea", "postalcode",
            "icon", "url", "description", "predefined", "createTime", "modifyTime",
            "class"),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "deleteByCode");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.DELETE, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "code"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(modelInfo.getProperties("deleteTime"),
        methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(
        modelInfo.getProperties("id", "code", "name", "phoneArea", "postalcode",
            "icon", "url", "description", "predefined", "createTime", "modifyTime",
            "class"),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "deleteByName");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.DELETE, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "name"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(modelInfo.getProperties("deleteTime"),
        methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(
        modelInfo.getProperties("id", "code", "name", "phoneArea", "postalcode",
            "icon", "url", "description", "predefined", "createTime", "modifyTime",
            "class"),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "restore");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.RESTORE, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "id"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(modelInfo.getProperties("deleteTime"),
        methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(
        modelInfo.getProperties("id", "code", "name", "phoneArea", "postalcode",
            "icon", "url", "description", "predefined", "createTime", "modifyTime",
            "class"),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "restoreByCode");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.RESTORE, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "code"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(modelInfo.getProperties("deleteTime"),
        methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(
        modelInfo.getProperties("id", "code", "name", "phoneArea", "postalcode",
            "icon", "url", "description", "predefined", "createTime", "modifyTime",
            "class"),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "restoreByName");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.RESTORE, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "name"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(modelInfo.getProperties("deleteTime"),
        methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(
        modelInfo.getProperties("id", "code", "name", "phoneArea", "postalcode",
            "icon", "url", "description", "predefined", "createTime", "modifyTime",
            "class"),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "purge");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.PURGE, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "id"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(Collections.emptySet(),
        methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(
        modelInfo.getProperties("id", "code", "name", "phoneArea", "postalcode",
            "icon", "url", "description", "predefined", "createTime", "modifyTime",
            "deleteTime", "class"),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "purgeByCode");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.PURGE, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "code"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(Collections.emptySet(),
        methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(
        modelInfo.getProperties("id", "code", "name", "phoneArea", "postalcode",
            "icon", "url", "description", "predefined", "createTime", "modifyTime",
            "deleteTime", "class"),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "purgeByName");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.PURGE, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "name"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(Collections.emptySet(),
        methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(
        modelInfo.getProperties("id", "code", "name", "phoneArea", "postalcode",
            "icon", "url", "description", "predefined", "createTime", "modifyTime",
            "deleteTime", "class"),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "purgeAll");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.PURGE_ALL, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(null, methodInfo.getIdentifier());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "erase");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.ERASE, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "id"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(Collections.emptySet(),
        methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(
        modelInfo.getProperties("id", "code", "name", "phoneArea", "postalcode",
            "icon", "url", "description", "predefined", "createTime", "modifyTime",
            "deleteTime", "class"),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "eraseByCode");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.ERASE, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(getField(modelType, BEAN_FIELD, "code"),
        methodInfo.getIdentifier().getField());
    Assertions.assertCollectionEquals(Collections.emptySet(),
        methodInfo.getModifiedProperties());
    Assertions.assertCollectionEquals(
        modelInfo.getProperties("id", "code", "name", "phoneArea", "postalcode",
            "icon", "url", "description", "predefined", "createTime", "modifyTime",
            "deleteTime", "class"),
        methodInfo.getUnmodifiedProperties());

    method = getMethodByName(daoType, Option.BEAN_METHOD, "clear");
    assertNotNull(method);
    methodInfo = DaoMethodInfo.create(modelInfo, daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.CLEAR, methodInfo.getOperation());
    assertEquals(null, methodInfo.getTarget());
    assertEquals(null, methodInfo.getIdentifier());
  }

  @Test
  public void testConstructor_delete() throws Exception {
    final CountryDao countryDao = new CountryDaoImpl();
    final Class<?> daoType = CountryDao.class;
    final Class<?> modelType = Country.class;
    final BeanInfo modelInfo = BeanInfo.of(modelType);

    final Method method = getMethodByName(daoType, Option.BEAN_METHOD, "delete");
    assertNotNull(method);
    final DaoMethodInfo methodInfo = DaoMethodInfo.create(modelInfo,
        daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.DELETE, methodInfo.getOperation());
    assertEquals(modelInfo.getProperty("id"), methodInfo.getIdentifier());
    assertEquals(null, methodInfo.getTarget());
  }

  @Test
  public void testConstructor_deleteBy() throws Exception {
    final CountryDao countryDao = new CountryDaoImpl();
    final Class<?> daoType = CountryDao.class;
    final Class<?> modelType = Country.class;
    final BeanInfo modelInfo = BeanInfo.of(modelType);

    final Method method = getMethodByName(daoType, Option.BEAN_METHOD, "deleteByCode");
    assertNotNull(method);
    final DaoMethodInfo methodInfo = DaoMethodInfo.create(modelInfo,
        daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.DELETE, methodInfo.getOperation());
    assertEquals(modelInfo.getProperty("code"), methodInfo.getIdentifier());
    assertEquals(null, methodInfo.getTarget());
  }

  @Test
  public void testConstructor_existCode() throws Exception {
    final CountryDao countryDao = new CountryDaoImpl();
    final Class<?> daoType = CountryDao.class;
    final Class<?> modelType = Country.class;
    final BeanInfo modelInfo = BeanInfo.of(modelType);

    final Method method = getMethodByName(daoType, Option.BEAN_METHOD, "existCode");
    assertNotNull(method);
    final DaoMethodInfo methodInfo = DaoMethodInfo.create(modelInfo,
        daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.EXIST, methodInfo.getOperation());
    assertEquals(modelInfo.getProperty("code"), methodInfo.getIdentifier());
    assertEquals(null, methodInfo.getTarget());
  }

  @Test
  public void testConstructor_restoreBy() throws Exception {
    final CountryDao countryDao = new CountryDaoImpl();
    final Class<?> daoType = CountryDao.class;
    final Class<?> modelType = Country.class;
    final BeanInfo modelInfo = BeanInfo.of(modelType);

    final Method method = getMethodByName(daoType, Option.BEAN_METHOD, "restoreByCode");
    assertNotNull(method);
    final DaoMethodInfo methodInfo = DaoMethodInfo.create(modelInfo,
        daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.RESTORE, methodInfo.getOperation());
    assertEquals(modelInfo.getProperty("code"), methodInfo.getIdentifier());
    assertEquals(null, methodInfo.getTarget());
  }

  @Test
  public void testConstructor_purgeBy() throws Exception {
    final CountryDao countryDao = new CountryDaoImpl();
    final Class<?> daoType = CountryDao.class;
    final Class<?> modelType = Country.class;
    final BeanInfo modelInfo = BeanInfo.of(modelType);

    final Method method = getMethodByName(daoType, Option.BEAN_METHOD, "purgeByName");
    assertNotNull(method);
    final DaoMethodInfo methodInfo = DaoMethodInfo.create(modelInfo,
        daoType, countryDao, method);
    assertNotNull(methodInfo);
    assertEquals(modelType, methodInfo.getModelInfo().getType());
    assertSame(countryDao, methodInfo.getDao());
    assertSame(method, methodInfo.getMethod());
    assertEquals(DaoOperation.PURGE, methodInfo.getOperation());
    assertEquals(modelInfo.getProperty("name"), methodInfo.getIdentifier());
    assertEquals(null, methodInfo.getTarget());
  }
}
