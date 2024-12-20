////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2022 - 2024.
//    Haixing Hu, Qubit Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////
package ltd.qubit.commons.test.dao.testbed;

import ltd.qubit.commons.test.dao.testbed.model.Family;

public interface FamilyDao extends GettableDao<Family>, AddableDao<Family>,
    UpdatableDao<Family>, ErasableDao<Family> {
  //  empty
}
