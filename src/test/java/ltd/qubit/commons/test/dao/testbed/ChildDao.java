////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2022 - 2024.
//    Haixing Hu, Qubit Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////
package ltd.qubit.commons.test.dao.testbed;

import ltd.qubit.commons.test.dao.testbed.model.Child;

public interface ChildDao extends GettableDao<Child>,
    AddableDao<Child>, UpdatableDao<Child>, ErasableDao<Child> {
  //  empty
}
