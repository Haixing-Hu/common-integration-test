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
import java.util.List;

import ltd.qubit.commons.test.dao.testbed.model.Child;
import ltd.qubit.commons.test.dao.testbed.model.Parent;

import static java.util.Objects.requireNonNull;

public class ParentDaoImpl extends SimpleDaoImpl<Parent> implements ParentDao {

  private final ChildDao childDao;

  public ParentDaoImpl(final ChildDao childDao) {
    super(Parent.class);
    this.childDao = requireNonNull(childDao);
  }

  @Override
  public Instant add(final Parent entity) {
    final Instant result = super.add(entity);
    // TODO: check validity
    final List<Child> children = entity.getChildren();
    for (final Child child : children) {
      child.setParentId(entity.getId());
      child.setGrandpaId(entity.getParentId());
      child.setParentCountry(entity.getCountry());
      child.setParentProvince(entity.getProvince());
      child.setGrandpaCountry(entity.getParentCountry());
      child.setGrandpaProvince(entity.getParentProvince());
      childDao.add(child);
    }
    return result;
  }
}
