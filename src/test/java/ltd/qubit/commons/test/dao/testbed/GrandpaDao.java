////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2022 - 2024.
//    Haixing Hu, Qubit Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////
package ltd.qubit.commons.test.dao.testbed;

import ltd.qubit.commons.test.dao.testbed.model.Grandpa;

public interface GrandpaDao extends GettableDao<Grandpa>, AddableDao<Grandpa>,
    UpdatableDao<Grandpa>, ErasableDao<Grandpa> {
  //  empty
}
