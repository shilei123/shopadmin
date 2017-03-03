create tablespace sunchinshop_data  
logging  
datafile 'D:\app\Administrator\oradata\sunchinshop\SUNCHINSHOP_DATA.dbf' 
size 100M  
autoextend on  
next 1024K maxsize UNLIMITED  
extent management local;  

create temporary tablespace sunchinshop_temp  
tempfile 'D:\app\Administrator\oradata\sunchinshop\SUNCHINSHOP_TEMP.dbf' 
size 100M  
autoextend on  
next 1024K maxsize UNLIMITED 
extent management local;

create tablespace ywt_index
logging  
datafile 'D:\app\Administrator\oradata\sunchinshop\SUNCHINSHOP_INDEX.dbf' 
size 100M  
autoextend on  
next 1024K maxsize UNLIMITED  
extent management local;    

-- Create the user 
create user "SUNCHINSHOP"
  identified by "123456"
  default tablespace sunchinshop_data
  temporary tablespace sunchinshop_temp
  profile DEFAULT;

-- Grant/Revoke role privileges 
grant resource to "SUNCHINSHOP";
grant connect to "SUNCHINSHOP";
grant exp_full_database to "SUNCHINSHOP";
grant imp_full_database to "SUNCHINSHOP";
GRANT UNLIMITED TABLESPACE TO "SUNCHINSHOP";