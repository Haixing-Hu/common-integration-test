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

import static ltd.qubit.commons.reflect.ConstructorUtils.newInstance;
import static ltd.qubit.commons.reflect.Option.DEFAULT;

/**
 * The enumeration of operations performed by a method of a DAO.
 *
 * @author Haixing Hu
 */
public enum DaoOperation {

  EXIST_IF("^existIf$",
      ExistIfOperationTestGenerator.class),

  EXIST_NON_DELETED("^existNonDeleted(\\p{Upper}\\p{Alpha}+)?$",
      ExistNonDeletedOperationTestGenerator.class),

  EXIST("^exist(\\p{Upper}\\p{Alpha}+)?$",
      ExistOperationTestGenerator.class),

  COUNT("^count$",
      CountOperationTestGenerator.class),

  LIST("^list(\\p{Upper}\\p{Alpha}+?)?$",
      ListOperationTestGenerator.class),

  // 注意：不能把 GET_ELSE_NULL 的正则表达式合并到 GET 中，否则会无法正确匹配到一些方法
  GET_ELSE_NULL("^get(?:(\\p{Upper}\\p{Alpha}+?)??(?:By(\\p{Upper}\\p{Alpha}+))?)?ElseNull$",
      GetOperationTestGenerator.class),

  GET("^get(?:(\\p{Upper}\\p{Alpha}+?)??(?:By(\\p{Upper}\\p{Alpha}+))?)?$",
      GetOperationTestGenerator.class),

  ADD("^add$", AddOperationTestGenerator.class),

  UPDATE("^update(?:(\\p{Upper}\\p{Alpha}+?)??(?:By(\\p{Upper}\\p{Alpha}+))?)?$",
      UpdateOperationTestGenerator.class),

  ADD_OR_UPDATE("^addOrUpdate(?:(\\p{Upper}\\p{Alpha}+?)??(?:By(\\p{Upper}\\p{Alpha}+))?)?$",
      AddOrUpdateOperationTestGenerator.class),

  DELETE("^delete(?:By(\\p{Upper}\\p{Alpha}+))?$",
      DeleteOperationTestGenerator.class),

  RESTORE("^restore(?:By(\\p{Upper}\\p{Alpha}+))?$",
      RestoreOperationTestGenerator.class),

  PURGE("^purge(?:By(\\p{Upper}\\p{Alpha}+))?$",
      PurgeOperationTestGenerator.class),

  PURGE_ALL("^purgeAll$",
      PurgeAllOperationTestGenerator.class),

  ERASE("^erase(?:By(\\p{Upper}\\p{Alpha}+))?$",
      EraseOperationTestGenerator.class),

  CLEAR("^clear$",
      ClearOperationTestGenerator.class);

  private final String pattern;

  @SuppressWarnings("rawtypes")
  private final Class<? extends DaoOperationTestGenerator> factoryClass;

  public static DaoOperation of(final Method method) {
    final String name = method.getName();
    for (final DaoOperation value : values()) {
      if (name.matches(value.pattern)) {
        return value;
      }
    }
    return null;
  }

  @SuppressWarnings("rawtypes")
  DaoOperation(final String pattern,
      final Class<? extends DaoOperationTestGenerator> factoryClass) {
    this.pattern = pattern;
    this.factoryClass = factoryClass;
  }

  String pattern() {
    return pattern;
  }

  @SuppressWarnings("unchecked")
  <T> DaoOperationTestGenerator<T> getGenerator(
      final DaoTestGeneratorRegistry factory,
      final Class<T> modelType,
      final DaoMethodInfo methodInfo) {
    return newInstance(factoryClass, DEFAULT, factory, modelType, methodInfo);
  }
}
