////////////////////////////////////////////////////////////////////////////////
//
//    Copyright (c) 2022 - 2024.
//    Haixing Hu, Qubit Co. Ltd.
//
//    All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////
package ltd.qubit.commons.test.dao;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ltd.qubit.commons.lang.Argument.requireNonNull;

/**
 * 提供通过 Flyway 初始化数据库的方法。
 *
 * @author 胡海星
 */
public class DatabaseInitializer {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final DataSource dataSource;

  public DatabaseInitializer(final DataSource dataSource) {
    this.dataSource = requireNonNull("dataSource", dataSource);
  }

  public void init() {
    logger.info("Initialize the database ...");
    final Flyway flyway = Flyway.configure().dataSource(dataSource).load();
    logger.info("Clean the database...");
    flyway.clean();
    logger.info("Creating the database...");
    flyway.migrate();
  }

  public void clean() {
    logger.info("Clean the database...");
    final Flyway flyway = Flyway.configure().dataSource(dataSource).load();
    flyway.clean();
  }
}
