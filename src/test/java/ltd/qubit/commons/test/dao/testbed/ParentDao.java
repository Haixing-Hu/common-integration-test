////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2022 - 2024.
//    Haixing Hu, Qubit Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////
package ltd.qubit.commons.test.dao.testbed;

import ltd.qubit.commons.test.dao.testbed.model.Parent;

public interface ParentDao extends GettableDao<Parent>, AddableDao<Parent>,
    UpdatableDao<Parent>, ErasableDao<Parent> {
  //  empty
}
