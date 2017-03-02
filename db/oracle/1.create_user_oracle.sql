-- Create the user 
create user "SHOP"
  identified by "shop"
  default tablespace USERS
  temporary tablespace TEMP
  profile DEFAULT;

-- Grant/Revoke role privileges 
grant resource to "SHOP";
grant connect to "SHOP";
grant exp_full_database to "SHOP";
grant imp_full_database to "SHOP";
GRANT UNLIMITED TABLESPACE TO "SHOP";