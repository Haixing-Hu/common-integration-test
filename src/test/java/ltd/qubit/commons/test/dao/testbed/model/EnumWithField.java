////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2022 - 2024.
//    Haixing Hu, Qubit Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////
package ltd.qubit.commons.test.dao.testbed.model;

public enum EnumWithField {

  VALUE_1(1),

  VALUE_2(2);

  private final int id;

  EnumWithField(final int id) {
    this.id = id;
  }

  int id() {
    return id;
  }

}
