package framework.db;

import java.sql.Types;

import org.hibernate.Hibernate;


public class MyOracle10gDialect extends org.hibernate.dialect.OracleDialect {     


     public MyOracle10gDialect() {     
         super();     
         //very important, mapping char(n) to String     
         registerHibernateType(Types.CHAR, Hibernate.STRING.getName());     
     }     

 } 
