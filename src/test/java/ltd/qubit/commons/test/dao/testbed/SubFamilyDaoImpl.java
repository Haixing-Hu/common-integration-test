////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2022 - 2024.
//    Haixing Hu, Qubit Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////
package ltd.qubit.commons.test.dao.testbed;

import ltd.qubit.commons.test.dao.testbed.model.SubFamily;

public class SubFamilyDaoImpl extends SimpleDaoImpl<SubFamily> implements SubFamilyDao {

  public SubFamilyDaoImpl() {
    super(SubFamily.class);
  }
}
