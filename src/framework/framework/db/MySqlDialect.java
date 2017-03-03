package framework.db;

import java.sql.Types;

import org.hibernate.Hibernate;

public class MySqlDialect extends org.hibernate.dialect.MySQL5InnoDBDialect {     

     public MySqlDialect() {     
         super();     
         //very important, mapping char(n) to String     
         registerHibernateType(Types.LONGVARCHAR, Hibernate.STRING.getName());     
     }     

} 