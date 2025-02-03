/*package com.avibha.common.db;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;


public class DataTransaction {
   private final static BasicDataSource dataSource;

   static {
      dataSource = new BasicDataSource();
      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
      dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/registrationtest");
      dataSource.setUsername("root");
      dataSource.setPassword("root");
      dataSource.setMaxIdle(10);
      dataSource.setMaxTotal(100);
      dataSource.setInitialSize(5);
      dataSource.setValidationQuery("select 1");
      dataSource.setTestOnBorrow(true);
      
   }

 
   public static DataSource getDataSource() {
      return dataSource;
   }
}*/