////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2022 - 2024.
//    Haixing Hu, Qubit Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////
package ltd.qubit.commons.test.dao.testbed;

import java.time.Instant;

import ltd.qubit.commons.test.dao.testbed.model.Grandpa;
import ltd.qubit.commons.test.dao.testbed.model.Parent;

import static java.util.Objects.requireNonNull;

public class GrandpaDaoImpl extends SimpleDaoImpl<Grandpa> implements GrandpaDao {

  private final ParentDao parentDao;

  public GrandpaDaoImpl(final ParentDao parentDao) {
    super(Grandpa.class);
    this.parentDao = requireNonNull(parentDao);
  }

  public Instant add(final Grandpa entity) {
    final Instant result = super.add(entity);
    // TODO: check validity
    final Parent child = entity.getChild();
    child.setParentId(entity.getId());
    child.setParentCountry(entity.getCountry());
    child.setParentProvince(entity.getProvince());
    parentDao.add(entity.getChild());
    return result;
  }
}
