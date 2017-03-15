/*create table T_XT_SYS_CODE
(
  ID     VARCHAR2(36) not null,
  TYPE   VARCHAR2(100),
  CODE   VARCHAR2(100),
  LABEL  VARCHAR2(200),
  REMARK VARCHAR2(200),
  FLAG   CHAR(1)
);
alter table T_XT_SYS_CODE
  add constraint PK_SYS_CODE primary key (ID);*/

create table T_AGENCY
(
  ID               VARCHAR2(36) not null,
  AGENCY_ID        VARCHAR2(50),
  AGENCY_NAME      VARCHAR2(50),
  SHORT_NAME       VARCHAR2(50),
  PARENT_AGENCY_ID VARCHAR2(50),
  ORDER_           NUMBER,
  ROOT             CHAR(1),
  AGENCY_PATH      VARCHAR2(200),
  AGENCY_LEVEL     NUMBER,
  ADDR             VARCHAR2(200),
  STS              CHAR(1) default 1 not null,
  OPEN_TIME        DATE,
  CLOSE_TIME       DATE,
  CREATE_USER_ID   VARCHAR2(36),
  CREATE_TIME      DATE,
  UPDATE_USER_ID   VARCHAR2(36),
  UPDATE_TIME      DATE,
  FLAG             CHAR(1) default 1 not null,
  INIT_FLAG        CHAR(1)
);
comment on column T_AGENCY.AGENCY_ID
  is '机构编码';
comment on column T_AGENCY.AGENCY_NAME
  is '机构名称';
comment on column T_AGENCY.SHORT_NAME
  is '机构简称';
comment on column T_AGENCY.PARENT_AGENCY_ID
  is '上级机构编码';
comment on column T_AGENCY.ORDER_
  is '机构顺序';
comment on column T_AGENCY.ROOT
  is '是否根机构';
comment on column T_AGENCY.AGENCY_PATH
  is '机构路径';
comment on column T_AGENCY.AGENCY_LEVEL
  is '机构级别';
comment on column T_AGENCY.ADDR
  is '机构地址';
comment on column T_AGENCY.STS
  is '默认1: 1启用，0停用';
comment on column T_AGENCY.FLAG
  is '默认1: 1启用，0停用';
comment on column T_AGENCY.INIT_FLAG
  is '默认0: 1已经初始化，0未初始';
alter table T_AGENCY
  add constraint PK_AGENCY_ID primary key (ID);

create table T_AGENCY_PARAMS
(
  ID             VARCHAR2(36) not null,
  AGENCY_ID      VARCHAR2(36),
  PARAM_NAME     VARCHAR2(50),
  PARAM_VAL      VARCHAR2(50),
  PARAM_DESC     VARCHAR2(50),
  CREATE_USER_ID VARCHAR2(36),
  CREATE_TIME    DATE,
  UPDATE_USER_ID VARCHAR2(36),
  UPDATE_TIME    DATE,
  FLAG           CHAR(1),
  TITLE          VARCHAR2(50),
  ORDER_         NUMBER
);
comment on column T_AGENCY_PARAMS.ID
  is '主键';
comment on column T_AGENCY_PARAMS.AGENCY_ID
  is '机构ID';
comment on column T_AGENCY_PARAMS.PARAM_NAME
  is '参数名';
comment on column T_AGENCY_PARAMS.PARAM_VAL
  is '参数值';
comment on column T_AGENCY_PARAMS.PARAM_DESC
  is '参数描述';
comment on column T_AGENCY_PARAMS.CREATE_USER_ID
  is '创建人';
comment on column T_AGENCY_PARAMS.CREATE_TIME
  is '创建时间';
comment on column T_AGENCY_PARAMS.UPDATE_USER_ID
  is '修改人';
comment on column T_AGENCY_PARAMS.UPDATE_TIME
  is '更新时间';
comment on column T_AGENCY_PARAMS.FLAG
  is '数据状态';
comment on column T_AGENCY_PARAMS.TITLE
  is '参数标题';
comment on column T_AGENCY_PARAMS.ORDER_
  is '顺序';
alter table T_AGENCY_PARAMS
  add constraint ID primary key (ID);

create table T_BANK_INFO
(
  ID             VARCHAR2(36) not null,
  BANK_NAME      VARCHAR2(100),
  CREATE_USER_ID VARCHAR2(36),
  CREATE_TIME    DATE,
  UPDATE_USER_ID VARCHAR2(36),
  UPDATE_TIME    DATE,
  FLAG           CHAR(1),
  BANK_DESC      VARCHAR2(200),
  URL            VARCHAR2(100),
  LOGO           VARCHAR2(100),
  TEL            VARCHAR2(20)
);
comment on column T_BANK_INFO.ID
  is '主键';
comment on column T_BANK_INFO.BANK_NAME
  is '银行名称';
comment on column T_BANK_INFO.CREATE_USER_ID
  is '创建人';
comment on column T_BANK_INFO.CREATE_TIME
  is '创建时间';
comment on column T_BANK_INFO.UPDATE_USER_ID
  is '修改人';
comment on column T_BANK_INFO.UPDATE_TIME
  is '更新时间';
comment on column T_BANK_INFO.FLAG
  is '数据状态/默认1: 1启用，0停用';
comment on column T_BANK_INFO.BANK_DESC
  is '银行描述';
alter table T_BANK_INFO
  add constraint T_BANK_INFO_PK primary key (ID);

create table T_PB_DATASOURCE
(
  ID             VARCHAR2(36) not null,
  SOURCE_NAME    VARCHAR2(100),
  SOURCE_REMARK  VARCHAR2(600),
  SOURCE_SQL     VARCHAR2(4000),
  CREATE_USER_ID VARCHAR2(36) not null,
  WHERE_SQL      VARCHAR2(4000),
  FLAG           CHAR(1) default '1',
  CREATE_TIME    TIMESTAMP(6) default sysdate not null,
  UPDATE_TIME    TIMESTAMP(6)
);

create table T_PB_UPLOADFILE
(
  FILE_ID        VARCHAR2(36) not null,
  PARENT_FILE_ID INTEGER,
  FILE_NAME      VARCHAR2(240) not null,
  FILE_TYPE      VARCHAR2(24),
  FILE_SIZE      INTEGER default 0 not null,
  FILE_PATH      VARCHAR2(4000) not null,
  UP_TYPE        VARCHAR2(24),
  LRRY_DM        VARCHAR2(50) not null,
  USER_TYPE      VARCHAR2(12) not null,
  XZFW           VARCHAR2(50),
  YX_BJ          CHAR(1) default 'N' not null,
  LR_SJ          TIMESTAMP(6) not null,
  XG_SJ          TIMESTAMP(6),
  GROUP_ID       VARCHAR2(36)
);

create table T_XT_AUDIT_LOG
(
  ID         VARCHAR2(36) not null,
  LOGINNAME  VARCHAR2(10),
  LOGDETAIL  VARCHAR2(2000),
  LOGSOURCE  VARCHAR2(200),
  IP         VARCHAR2(30),
  CREATEDATE DATE
);
alter table T_XT_AUDIT_LOG
  add primary key (ID);

create table T_XT_EMP
(
  ID              VARCHAR2(36) not null,
  USER_ID         VARCHAR2(36) not null,
  USER_PWD        VARCHAR2(36),
  USER_NAME       VARCHAR2(200),
  ORG_ID          VARCHAR2(36),
  WORK_ADDR       VARCHAR2(2000),
  TELPHONE        VARCHAR2(50),
  MOBILE          VARCHAR2(11),
  FAX             VARCHAR2(50),
  SEX             CHAR(1),
  EMAIL           VARCHAR2(50),
  REMARK          VARCHAR2(500),
  POSITION_ID     VARCHAR2(36),
  VALIDATE_DOMAIN VARCHAR2(72),
  VALIDATE_IP     VARCHAR2(15),
  PCUSERNAME      VARCHAR2(100),
  FLAG            CHAR(1)
);
alter table T_XT_EMP
  add primary key (ID);

create table T_XT_MENU
(
  MENU_ID         VARCHAR2(36) not null,
  MENU_NAME       VARCHAR2(400) not null,
  MENU_PARENT_ID  VARCHAR2(36) not null,
  MENU_PARENT_IDS VARCHAR2(2000) not null,
  URL             VARCHAR2(2000),
  ORDER_          INTEGER,
  OPEN_METHOD     VARCHAR2(24),
  FLAG            CHAR(1) default '1',
  CREATE_TIME     TIMESTAMP(6) not null,
  UPDATE_TIME     TIMESTAMP(6),
  LOGO            VARCHAR2(200)
);
alter table T_XT_MENU
  add primary key (MENU_ID);

create table T_XT_ORG
(
  ID            VARCHAR2(36) not null,
  ORG_ID        VARCHAR2(50),
  ORG_NAME      VARCHAR2(500),
  PARENT_ORG_ID VARCHAR2(36),
  ORDER_        INTEGER,
  ROOT          CHAR(1),
  ORG_CODE      VARCHAR2(36),
  SHORT_NAME    VARCHAR2(500),
  ORG_PATH      VARCHAR2(1000),
  ORG_LEVEL     INTEGER
);
alter table T_XT_ORG
  add primary key (ID);

create table T_XT_POSITION
(
  POSITION_ID   VARCHAR2(36) not null,
  POSITION_NAME VARCHAR2(200),
  POSITION_DESC VARCHAR2(200)
);
alter table T_XT_POSITION
  add primary key (POSITION_ID);

create table T_XT_ROLE
(
  ROLE_ID          VARCHAR2(36) not null,
  ROLE_NAME        VARCHAR2(200) not null,
  ROLE_TYPE        CHAR(1) not null,
  ROLE_REMARK      VARCHAR2(2000),
  ROLE_STATE_VALUE VARCHAR2(12),
  FLAG             CHAR(1) default '1',
  CREATE_TIME      TIMESTAMP(6) not null,
  UPDATE_TIME      TIMESTAMP(6)
);
alter table T_XT_ROLE
  add primary key (ROLE_ID);

create table T_XT_ROLE_MENU
(
  ROLE_ID     VARCHAR2(36) not null,
  MENU_ID     VARCHAR2(36) not null,
  FLAG        CHAR(1) default '1',
  CREATE_TIME TIMESTAMP(6) not null,
  UPDATE_TIME TIMESTAMP(6)
);
alter table T_XT_ROLE_MENU
  add constraint PK_T_XT_ROLE_MENU primary key (ROLE_ID, MENU_ID);

create table T_XT_ROLE_USER
(
  ROLE_ID     VARCHAR2(36) not null,
  U_ID        VARCHAR2(36) not null,
  FLAG        CHAR(1) default '1',
  CREATE_TIME TIMESTAMP(6) not null,
  UPDATE_TIME TIMESTAMP(6)
);
alter table T_XT_ROLE_USER
  add constraint PK_T_XT_ROLE_USER primary key (ROLE_ID, U_ID);

create table T_XT_USER
(
  U_ID       VARCHAR2(36) not null,
  U_PWD      VARCHAR2(36),
  U_NAME     VARCHAR2(200),
  U_REMARK   VARCHAR2(2000),
  FLAG        CHAR(1) default '1',
  CREATE_TIME TIMESTAMP(6) not null,
  UPDATE_TIME TIMESTAMP(6),
  ORDER_   INTEGER
);
alter table T_XT_USER
  add primary key (U_ID);

create table T_XT_WORKDAY
(
  DAY         VARCHAR2(10) not null,
  WORKDAY     CHAR(1),
  REMARK      VARCHAR2(200),
  FLAG        CHAR(1) default '1',
  CREATE_TIME DATE,
  UPDATE_TIME DATE
);
alter table T_XT_WORKDAY
  add primary key (DAY);
insert into T_AGENCY (ID, AGENCY_ID, AGENCY_NAME, SHORT_NAME, PARENT_AGENCY_ID, ORDER_, ROOT, AGENCY_PATH, AGENCY_LEVEL, ADDR, STS, OPEN_TIME, CLOSE_TIME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, INIT_FLAG)
values ('cec43ca9-c553-4301-8b40-4e716f071268', '001001', '重庆市', '重庆市', '001', 1, '0', '所有机构/重庆市', 2, null, '1', null, null, null, null, null, null, '1', '0');
insert into T_AGENCY (ID, AGENCY_ID, AGENCY_NAME, SHORT_NAME, PARENT_AGENCY_ID, ORDER_, ROOT, AGENCY_PATH, AGENCY_LEVEL, ADDR, STS, OPEN_TIME, CLOSE_TIME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, INIT_FLAG)
values ('006cbc85-af64-4cf2-917e-8519dbbf82cd', '001', '所有机构', '所有机构', null, 1, '1', '所有机构', 0, '所有机构', '1', to_date('05-12-2016 17:55:24', 'dd-mm-yyyy hh24:mi:ss'), null, 'admin', to_date('05-12-2016 17:55:24', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('05-12-2016 17:55:24', 'dd-mm-yyyy hh24:mi:ss'), '1', '1');
commit;
insert into T_AGENCY_PARAMS (ID, AGENCY_ID, PARAM_NAME, PARAM_VAL, PARAM_DESC, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, TITLE, ORDER_)
values ('9272a760-5985-452a-8df9-0d651c6b34b8', '001', 'consume_card_scale', '0.3', '参数1', 'admin', to_date('22-12-2016 14:54:55', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '参数1', 1);
insert into T_AGENCY_PARAMS (ID, AGENCY_ID, PARAM_NAME, PARAM_VAL, PARAM_DESC, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, TITLE, ORDER_)
values ('154bc129-24bb-4ea9-8bb9-5cc3f745b63a', '001', 'sales_award', '0.1', '参数2', 'admin', to_date('22-12-2016 14:54:55', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '参数2', 2);
insert into T_AGENCY_PARAMS (ID, AGENCY_ID, PARAM_NAME, PARAM_VAL, PARAM_DESC, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, TITLE, ORDER_)
values ('bdfcdd21-78c4-4af1-9281-9950b3bcbb09', '001', 'serv_award_one', '0.08', '参数3', 'admin', to_date('22-12-2016 14:54:55', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '参数3', 3);
insert into T_AGENCY_PARAMS (ID, AGENCY_ID, PARAM_NAME, PARAM_VAL, PARAM_DESC, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, TITLE, ORDER_)
values ('766704b6-c937-45e1-add9-db30d27c082b', '001', 'serv_award_two', '0.08', '参数4', 'admin', to_date('22-12-2016 14:54:55', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '参数4', 4);
insert into T_AGENCY_PARAMS (ID, AGENCY_ID, PARAM_NAME, PARAM_VAL, PARAM_DESC, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, TITLE, ORDER_)
values ('6d81ec31-57fa-4bb2-bf3f-6d6e85db4750', '001', 'serv_award_three', '0.08', '参数5', 'admin', to_date('22-12-2016 14:54:55', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '参数5', 5);
insert into T_AGENCY_PARAMS (ID, AGENCY_ID, PARAM_NAME, PARAM_VAL, PARAM_DESC, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, TITLE, ORDER_)
values ('928575ca-1816-4762-a70e-75979ccaeba0', '001', 'revenue', '100', '参数6', 'admin', to_date('22-12-2016 14:54:55', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '参数6', 6);
insert into T_AGENCY_PARAMS (ID, AGENCY_ID, PARAM_NAME, PARAM_VAL, PARAM_DESC, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, TITLE, ORDER_)
values ('63a269d7-66d6-4b42-81d7-ce0c5097303e', '001', 'cust_service_qq', '110110', '参数7', 'admin', to_date('22-12-2016 14:54:55', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '参数7', 7);
insert into T_AGENCY_PARAMS (ID, AGENCY_ID, PARAM_NAME, PARAM_VAL, PARAM_DESC, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, TITLE, ORDER_)
values ('8b6f1363-202b-4f11-b371-55ac9c190325', '001', 'min_transfer_amount', '100', '参数8', 'admin', to_date('22-12-2016 14:54:55', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '参数8', 8);
insert into T_AGENCY_PARAMS (ID, AGENCY_ID, PARAM_NAME, PARAM_VAL, PARAM_DESC, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, TITLE, ORDER_)
values ('eccc3dbd-62ec-422d-b095-a38e3b969ed8', '001001', 'sales_award', '0.1', '参数2', 'admin', to_date('29-12-2016 13:54:34', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '参数2', 2);
insert into T_AGENCY_PARAMS (ID, AGENCY_ID, PARAM_NAME, PARAM_VAL, PARAM_DESC, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, TITLE, ORDER_)
values ('63810923-3025-42ab-9863-1ee2a388b72d', '001001', 'serv_award_one', '0.08', '参数3', 'admin', to_date('29-12-2016 13:54:34', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '参数3', 3);
insert into T_AGENCY_PARAMS (ID, AGENCY_ID, PARAM_NAME, PARAM_VAL, PARAM_DESC, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, TITLE, ORDER_)
values ('2e339caf-32ea-4e6b-943c-e1290b0e7cc7', '001001', 'serv_award_two', '0.08', '参数4', 'admin', to_date('29-12-2016 13:54:34', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '参数4', 4);
insert into T_AGENCY_PARAMS (ID, AGENCY_ID, PARAM_NAME, PARAM_VAL, PARAM_DESC, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, TITLE, ORDER_)
values ('2bda4490-8108-4953-a88f-92f4e027f073', '001001', 'serv_award_three', '0.08', '参数5', 'admin', to_date('29-12-2016 13:54:34', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '参数5', 5);
insert into T_AGENCY_PARAMS (ID, AGENCY_ID, PARAM_NAME, PARAM_VAL, PARAM_DESC, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, TITLE, ORDER_)
values ('178e88a0-6700-4115-a8a8-6d031f5624b9', '001', 'remit_fee', '100', '参数9', 'admin', to_date('22-12-2016 14:54:55', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '参数9', 9);
insert into T_AGENCY_PARAMS (ID, AGENCY_ID, PARAM_NAME, PARAM_VAL, PARAM_DESC, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, TITLE, ORDER_)
values ('1b0a8644-b459-4c00-b715-dee8e1c32a5b', '001', 'withdraw_switch', '1', '参数10', 'admin', to_date('22-12-2016 14:54:55', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '参数10', 10);
insert into T_AGENCY_PARAMS (ID, AGENCY_ID, PARAM_NAME, PARAM_VAL, PARAM_DESC, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, TITLE, ORDER_)
values ('6bc74fd4-441b-4080-980f-98a66420642b', '001001', 'consume_card_scale', '0.3', '参数1', 'admin', to_date('29-12-2016 13:54:34', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '参数1', 1);
insert into T_AGENCY_PARAMS (ID, AGENCY_ID, PARAM_NAME, PARAM_VAL, PARAM_DESC, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, TITLE, ORDER_)
values ('0f551ea4-abbb-478d-8fe8-6875c4dfba17', '001001', 'revenue', '100', '参数6', 'admin', to_date('29-12-2016 13:54:34', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '参数6', 6);
insert into T_AGENCY_PARAMS (ID, AGENCY_ID, PARAM_NAME, PARAM_VAL, PARAM_DESC, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, TITLE, ORDER_)
values ('7c29b5a3-fc18-4575-8e17-6bf9410ba9d8', '001001', 'cust_service_qq', '110110', '参数7', 'admin', to_date('29-12-2016 13:54:34', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '参数7', 7);
insert into T_AGENCY_PARAMS (ID, AGENCY_ID, PARAM_NAME, PARAM_VAL, PARAM_DESC, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, TITLE, ORDER_)
values ('25a424a1-0f16-4ed9-90f1-f7d540a5c472', '001001', 'min_transfer_amount', '100', '参数8', 'admin', to_date('29-12-2016 13:54:34', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '参数8', 8);
insert into T_AGENCY_PARAMS (ID, AGENCY_ID, PARAM_NAME, PARAM_VAL, PARAM_DESC, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, TITLE, ORDER_)
values ('e70c24cf-477b-42cd-956e-cf712c404777', '001001', 'remit_fee', '100', '参数9', 'admin', to_date('29-12-2016 13:54:34', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '参数9', 9);
insert into T_AGENCY_PARAMS (ID, AGENCY_ID, PARAM_NAME, PARAM_VAL, PARAM_DESC, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, TITLE, ORDER_)
values ('52e96a54-ad4e-45ec-af7b-63a6ccb9271a', '001001', 'withdraw_switch', '1', '参数10', 'admin', to_date('29-12-2016 13:54:34', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '参数10', 10);
commit;
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('694bbbd4-895f-4c48-ab5d-3df578a87cad', '建设银行', null, to_date('06-01-2017 14:23:48', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('06-01-2017 14:27:46', 'dd-mm-yyyy hh24:mi:ss'), '1', '建设银行', '建设银行', null, '建设银行');
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('bc289fc9-3f74-4958-bd1f-a36442b8c771', '222', null, to_date('29-12-2016 13:45:48', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('29-12-2016 13:46:03', 'dd-mm-yyyy hh24:mi:ss'), '0', null, null, null, '333');
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('6d5701ec-b6b5-4c28-bb75-c3d052f72602', '123', 'admin', to_date('21-12-2016 17:01:12', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-12-2016 17:01:20', 'dd-mm-yyyy hh24:mi:ss'), '0', '213', null, null, null);
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('17864b0e-14e0-4673-b9dc-e2790316c5e0', '建设银行', null, to_date('27-12-2016 20:34:25', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('06-01-2017 14:28:12', 'dd-mm-yyyy hh24:mi:ss'), '1', '建设银行', '建设银行', null, '建设银行');
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('5a04d115-e079-4b18-a5bc-6046ffea8443', '建设银行', null, to_date('27-12-2016 20:34:28', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('06-01-2017 14:28:06', 'dd-mm-yyyy hh24:mi:ss'), '1', '建设银行', '建设银行', null, '建设银行');
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('1cf126ef-987c-4d8f-9197-ed71977b789a', '建设银行', null, to_date('27-12-2016 20:34:32', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('06-01-2017 14:28:00', 'dd-mm-yyyy hh24:mi:ss'), '1', '建设银行', '建设银行', null, '建设银行');
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('e888c7d3-9315-40de-9c8c-3bec8f46a0d8', '建设银行', null, to_date('27-12-2016 20:34:42', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('06-01-2017 14:27:54', 'dd-mm-yyyy hh24:mi:ss'), '1', '建设银行', '建设银行', null, '建设银行');
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('fa2c05bf-2abf-4902-b4c6-d3abedfc5674', '建设银行', null, to_date('27-12-2016 20:34:45', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('06-01-2017 14:27:41', 'dd-mm-yyyy hh24:mi:ss'), '1', '建设银行', '建设银行', null, '建设银行');
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('6f7303fd-4de6-43e8-b7e2-41edd8192bea', '建设银行', null, to_date('27-12-2016 20:34:17', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('06-01-2017 14:28:27', 'dd-mm-yyyy hh24:mi:ss'), '1', '建设银行', '建设银行', null, '建设银行');
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('11d9e4a3-7f2e-47f2-8561-1ac3c0a2d691', '建设银行', null, to_date('27-12-2016 20:34:21', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('06-01-2017 14:28:22', 'dd-mm-yyyy hh24:mi:ss'), '1', '建设银行', '建设银行', null, '建设银行');
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('41cf2ccd-453f-4b51-883f-ffb300d686f9', '建设银行', null, to_date('27-12-2016 20:34:49', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('06-01-2017 14:27:37', 'dd-mm-yyyy hh24:mi:ss'), '1', null, '建设银行', null, '建设银行');
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('ba56cb04-86bf-4f4e-b356-8624002f6ab4', '建设银行', null, to_date('27-12-2016 20:34:53', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('06-01-2017 14:27:33', 'dd-mm-yyyy hh24:mi:ss'), '1', '建设银行', '建设银行', null, '建设银行');
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('2ed931ed-2c08-40ab-ae64-e3c2bd2bfed9', '建设银行', null, to_date('27-12-2016 20:34:55', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('06-01-2017 14:24:24', 'dd-mm-yyyy hh24:mi:ss'), '1', null, '建设银行', null, '建设银行');
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('7e808e57-54df-4c4a-af8c-96cb5d7b57f1', '建设银行', null, to_date('27-12-2016 20:34:58', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('06-01-2017 14:24:19', 'dd-mm-yyyy hh24:mi:ss'), '1', null, '建设银行', null, '建设银行');
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('15bb9ca3-9eb4-4e6c-b5b3-a9be1ac88a55', '建设银行', null, to_date('27-12-2016 20:35:01', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('06-01-2017 14:24:16', 'dd-mm-yyyy hh24:mi:ss'), '1', null, '建设银行', null, '建设银行');
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('64ca8b36-9b0e-4522-abd3-69976b742ecf', '建设银行', null, to_date('27-12-2016 20:35:03', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('06-01-2017 14:24:12', 'dd-mm-yyyy hh24:mi:ss'), '1', null, '建设银行', null, '建设银行');
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('cb9cdae8-952e-43c5-a947-c276345881bf', '建设银行', null, to_date('27-12-2016 20:35:05', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('06-01-2017 14:24:00', 'dd-mm-yyyy hh24:mi:ss'), '1', '建设银行', '建设银行', null, '建设银行');
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('db9f593c-25dc-4095-8a9a-d08a92a7a099', '1', null, to_date('27-12-2016 20:35:08', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('06-01-2017 13:47:32', 'dd-mm-yyyy hh24:mi:ss'), '0', null, null, null, null);
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('16fbb520-3abd-42ae-a9ae-49744ce4a8cd', '1', null, to_date('27-12-2016 20:35:11', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('06-01-2017 13:47:20', 'dd-mm-yyyy hh24:mi:ss'), '0', null, null, null, null);
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('a99eda29-fc2b-4e76-b915-fec571ad6b6a', '中国工商银行', 'admin', to_date('20-12-2016 14:49:59', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-12-2016 16:08:17', 'dd-mm-yyyy hh24:mi:ss'), '1', '中国工商银行', 'http://www.icbc.com.cn', null, '95588');
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('c044c66f-5044-48e6-9afe-64761c001a7e', '中国农业银行', 'admin', to_date('20-12-2016 14:49:51', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-12-2016 14:27:33', 'dd-mm-yyyy hh24:mi:ss'), '1', '中国农业银行', null, null, null);
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('ed51e9ad-ca11-473d-b599-cf4c11ebb38d', '中国银行', 'admin', to_date('20-12-2016 14:49:56', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-12-2016 14:23:47', 'dd-mm-yyyy hh24:mi:ss'), '1', '中国银行', null, null, null);
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('5945c338-9e0c-4adf-9f6a-88ac965aba31', '中国建设银行', 'admin', to_date('20-12-2016 14:52:29', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-12-2016 14:27:15', 'dd-mm-yyyy hh24:mi:ss'), '1', '中国建设银行', null, null, null);
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('cf795dcd-2bb1-4a84-8056-50de5d5b5597', '交通银行', 'admin', to_date('21-12-2016 14:24:14', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '交通银行', null, null, null);
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('6dab8310-581f-4152-8c89-1dcd08dfa333', '招商银行', 'admin', to_date('21-12-2016 14:24:27', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '招商银行', null, null, null);
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('cf9b773b-8308-4e34-a369-cca2a083a8e6', '兴业银行', 'admin', to_date('21-12-2016 14:24:37', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '兴业银行', null, null, null);
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('e0f3ce89-3cbc-4f27-8fef-12a1104f1335', '光大银行', 'admin', to_date('21-12-2016 14:24:46', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '光大银行', null, null, null);
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('22f0f918-28d1-4542-ba3f-34f1eb6dd7dd', '浦发银行', 'admin', to_date('21-12-2016 14:25:22', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-12-2016 14:25:42', 'dd-mm-yyyy hh24:mi:ss'), '1', '浦发银行', null, null, null);
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('e6e1fbb1-5331-4b0d-8ed7-ac7214b407bd', '中信银行', 'admin', to_date('21-12-2016 14:25:50', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '中信银行', null, null, null);
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('8962d49d-3172-4bf0-81bd-b701f9f03470', '邮政储蓄银行', 'admin', to_date('21-12-2016 14:26:07', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-12-2016 14:27:47', 'dd-mm-yyyy hh24:mi:ss'), '1', '邮政储蓄银行', null, null, null);
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('84b877ae-176f-4d3b-b023-e2d2f5a87c5d', '华夏银行', 'admin', to_date('21-12-2016 14:28:02', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '华夏银行', null, null, null);
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('06851cb5-a716-4b0b-87f0-96864da3d384', '北京银行', 'admin', to_date('21-12-2016 14:29:01', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '北京银行', null, null, null);
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('4631c6b5-9f30-4757-8f71-b5f3af4d2742', '平安银行', 'admin', to_date('21-12-2016 14:29:06', 'dd-mm-yyyy hh24:mi:ss'), null, null, '1', '平安银行', null, null, null);
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('90d49727-8497-4994-a9c3-167d785fad8b', '民生银行', 'admin', to_date('21-12-2016 14:29:13', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-12-2016 16:06:24', 'dd-mm-yyyy hh24:mi:ss'), '1', '民生银行', 'http://www.cmbc.com.cn/', null, '95568');
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('500f495a-886b-4250-be2e-2044fbf60d79', '广发银行', 'admin', to_date('21-12-2016 14:30:18', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('06-01-2017 14:28:35', 'dd-mm-yyyy hh24:mi:ss'), '1', '广发银行', '广发银行', null, '广发银行');
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('95c74edf-2946-46f9-96cf-09f9b28b1ff7', '123', 'admin', to_date('22-12-2016 11:11:02', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('22-12-2016 11:11:04', 'dd-mm-yyyy hh24:mi:ss'), '0', null, null, null, null);
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('6efaefa5-3607-4cc8-8dbf-81198ab31c85', '123', 'admin', to_date('21-12-2016 15:58:13', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-12-2016 15:58:20', 'dd-mm-yyyy hh24:mi:ss'), '0', '123', null, null, null);
insert into T_BANK_INFO (ID, BANK_NAME, CREATE_USER_ID, CREATE_TIME, UPDATE_USER_ID, UPDATE_TIME, FLAG, BANK_DESC, URL, LOGO, TEL)
values ('7f0e3909-dc32-4d2a-a409-4693b698560b', '建设银行', null, to_date('29-12-2016 14:04:31', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('06-01-2017 14:24:03', 'dd-mm-yyyy hh24:mi:ss'), '1', null, '建设银行', null, '建设银行');
commit;
insert into T_XT_EMP (ID, USER_ID, USER_PWD, USER_NAME, ORG_ID, WORK_ADDR, TELPHONE, MOBILE, FAX, SEX, EMAIL, REMARK, POSITION_ID, VALIDATE_DOMAIN, VALIDATE_IP, PCUSERNAME, FLAG)
values ('d6ce7860-a122-41bd-80d7-cb80f437044c', 'admin', '123456', '系统管理员', '81277dc5-6c4b-4246-97b9-47aafcb3d573', null, null, null, null, '0', null, null, '5bfad0ff-8227-4979-a5ad-6435564b020b', 'test', 'test', 'test', '1');
commit;
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('ee602084-42f3-4633-a1e8-c68c9bb7aabd', '短信邮件模版管理', 'c962e30d-2dec-4be4-8c9b-1e0cc5290c56', 'root,xt,c962e30d-2dec-4be4-8c9b-1e0cc5290c56,ee602084-42f3-4633-a1e8-c68c9bb7aabd', '1', 4, 'tab', '1', to_timestamp('02-03-2017 11:55:56.215000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('06-03-2017 12:56:24.714000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('8861d139-1eb5-48cc-9c7f-5b5e17af5b25', '商品管理', 'xt', 'root,xt,8861d139-1eb5-48cc-9c7f-5b5e17af5b25', null, 2, 'tab', '1', to_timestamp('07-03-2017 11:28:45.535000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('07-03-2017 11:38:50.904000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('cce3a1ee-0b58-4880-b667-a3e6fb5e9799', '商品录入', '8861d139-1eb5-48cc-9c7f-5b5e17af5b25', 'root,xt,8861d139-1eb5-48cc-9c7f-5b5e17af5b25,cce3a1ee-0b58-4880-b667-a3e6fb5e9799', '1', 1, 'tab', '1', to_timestamp('07-03-2017 11:29:03.314000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('07-03-2017 11:59:31.241000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('274b2319-2e8d-47e9-a0cb-c9e90817d204', '广告管理', 'c3a85436-e78c-4249-b6f1-9344e45d8d22', 'root,xt,c3a85436-e78c-4249-b6f1-9344e45d8d22,274b2319-2e8d-47e9-a0cb-c9e90817d204', '1', 1, 'tab', '1', to_timestamp('09-03-2017 10:54:40.358000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('de61c5b9-5628-4470-a016-c1408e0eba2f', '已上架商品', '8861d139-1eb5-48cc-9c7f-5b5e17af5b25', 'root,xt,8861d139-1eb5-48cc-9c7f-5b5e17af5b25,de61c5b9-5628-4470-a016-c1408e0eba2f', '1', 3, 'tab', '1', to_timestamp('07-03-2017 11:30:17.944000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('07-03-2017 11:59:37.795000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('3b395cb7-e806-4280-8366-6d41cf13d158', '售后服务', 'xt', 'root,xt,3b395cb7-e806-4280-8366-6d41cf13d158', null, 4, 'tab', '1', to_timestamp('07-03-2017 11:31:48.679000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('07-03-2017 11:39:01.684000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('1f2d1568-1fe5-42fd-abaa-b504200f52cc', '活动管理', 'c3a85436-e78c-4249-b6f1-9344e45d8d22', 'root,xt,c3a85436-e78c-4249-b6f1-9344e45d8d22,1f2d1568-1fe5-42fd-abaa-b504200f52cc', '1', 2, 'tab', '1', to_timestamp('09-03-2017 10:54:50.747000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('547744b5-836d-45dd-9c82-7e6e955ecf72', '会员信息管理', '3f456dff-36a6-4316-9556-27e33a0909e8', 'root,xt,3f456dff-36a6-4316-9556-27e33a0909e8,547744b5-836d-45dd-9c82-7e6e955ecf72', '1', 1, 'tab', '1', to_timestamp('09-03-2017 10:56:17.504000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('344ac375-d7bb-4bd8-b4f3-3a72c4dd2003', '目录结构管理', '06465872-afbc-4c5b-b0e3-fae4caeb27ed', 'root,xt,06465872-afbc-4c5b-b0e3-fae4caeb27ed,344ac375-d7bb-4bd8-b4f3-3a72c4dd2003', '1', 1, 'tab', '1', to_timestamp('09-03-2017 10:55:49.803000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('3f456dff-36a6-4316-9556-27e33a0909e8', '会员管理', 'xt', 'root,xt,3f456dff-36a6-4316-9556-27e33a0909e8', null, 8, 'tab', '1', to_timestamp('09-03-2017 10:56:08.688000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-03-2017 11:02:15.517000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('dfb5aa2f-c4c6-4081-8e82-d98d481192fe', '用户安全信息查询', '3f456dff-36a6-4316-9556-27e33a0909e8', 'root,xt,3f456dff-36a6-4316-9556-27e33a0909e8,dfb5aa2f-c4c6-4081-8e82-d98d481192fe', '1', 2, 'tab', '1', to_timestamp('09-03-2017 10:56:27.220000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('c1c8cbed-c3e5-4d8e-871f-b6589ec21f30', '会员等级设置', '3f456dff-36a6-4316-9556-27e33a0909e8', 'root,xt,3f456dff-36a6-4316-9556-27e33a0909e8,c1c8cbed-c3e5-4d8e-871f-b6589ec21f30', '1', 3, 'tab', '1', to_timestamp('09-03-2017 10:56:34.692000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('322ba930-9e06-472a-8f61-86482499515a', '会员消费流水查询', '3f456dff-36a6-4316-9556-27e33a0909e8', 'root,xt,3f456dff-36a6-4316-9556-27e33a0909e8,322ba930-9e06-472a-8f61-86482499515a', '1', 4, 'tab', '1', to_timestamp('09-03-2017 10:56:46.927000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('dec5913f-d40e-4c40-8ad3-4af94832fc39', '会员账户余额查询', '3f456dff-36a6-4316-9556-27e33a0909e8', 'root,xt,3f456dff-36a6-4316-9556-27e33a0909e8,dec5913f-d40e-4c40-8ad3-4af94832fc39', '1', 5, 'tab', '1', to_timestamp('09-03-2017 10:56:54.380000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('0bb65c6a-12e5-437d-b474-5e46f67034b6', '实名认证审核', '3f456dff-36a6-4316-9556-27e33a0909e8', 'root,xt,3f456dff-36a6-4316-9556-27e33a0909e8,0bb65c6a-12e5-437d-b474-5e46f67034b6', '1', 6, 'tab', '1', to_timestamp('09-03-2017 10:57:05.325000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('0b7e69df-9280-48fd-84b4-5a0025f66b40', '资源管理', 'xt', 'root,xt,0b7e69df-9280-48fd-84b4-5a0025f66b40', null, 6, 'tab', '1', to_timestamp('09-03-2017 10:57:16.667000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-03-2017 11:02:02.079000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('17d8428f-6595-4751-9f42-13acc064f903', '证件证书上传', '0b7e69df-9280-48fd-84b4-5a0025f66b40', 'root,xt,0b7e69df-9280-48fd-84b4-5a0025f66b40,17d8428f-6595-4751-9f42-13acc064f903', '1', 1, 'tab', '1', to_timestamp('09-03-2017 10:57:28.020000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('fc05fec5-92c8-4fc4-a153-bed5c8807813', '图片管理', '0b7e69df-9280-48fd-84b4-5a0025f66b40', 'root,xt,0b7e69df-9280-48fd-84b4-5a0025f66b40,fc05fec5-92c8-4fc4-a153-bed5c8807813', '1', 2, 'tab', '1', to_timestamp('09-03-2017 10:57:36.597000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('e8d24ab4-04d4-43cc-847f-81c1707a6e9e', '待发布商品', '8861d139-1eb5-48cc-9c7f-5b5e17af5b25', 'root,xt,8861d139-1eb5-48cc-9c7f-5b5e17af5b25,e8d24ab4-04d4-43cc-847f-81c1707a6e9e', '1', 2, 'tab', '1', to_timestamp('07-03-2017 11:59:54.505000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-03-2017 10:46:38.773000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('1b3fb348-ad0e-4d4c-b05b-b1e1494387f8', '附件管理', '0b7e69df-9280-48fd-84b4-5a0025f66b40', 'root,xt,0b7e69df-9280-48fd-84b4-5a0025f66b40,1b3fb348-ad0e-4d4c-b05b-b1e1494387f8', '1', 3, 'tab', '1', to_timestamp('09-03-2017 10:57:44.545000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('507f53b0-399a-463f-a1a0-46526f1b1e46', 'ZIP包上传', '0b7e69df-9280-48fd-84b4-5a0025f66b40', 'root,xt,0b7e69df-9280-48fd-84b4-5a0025f66b40,507f53b0-399a-463f-a1a0-46526f1b1e46', '1', 4, 'tab', '1', to_timestamp('09-03-2017 10:58:29.571000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-03-2017 10:58:45.701000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('198279c9-d68f-4792-a221-06cb0958e4fc', '查询统计', 'xt', 'root,xt,198279c9-d68f-4792-a221-06cb0958e4fc', null, 10, 'tab', '1', to_timestamp('09-03-2017 10:59:03.965000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('3e31f950-05d6-4733-af53-d6e3b5f78611', '订单统计', '198279c9-d68f-4792-a221-06cb0958e4fc', 'root,xt,198279c9-d68f-4792-a221-06cb0958e4fc,3e31f950-05d6-4733-af53-d6e3b5f78611', '1', 1, 'tab', '1', to_timestamp('09-03-2017 10:59:12.298000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('caf85891-affb-442d-9450-33db8f3ebec7', '商品统计', '198279c9-d68f-4792-a221-06cb0958e4fc', 'root,xt,198279c9-d68f-4792-a221-06cb0958e4fc,caf85891-affb-442d-9450-33db8f3ebec7', '1', 2, 'tab', '1', to_timestamp('09-03-2017 10:59:21.549000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('0c17d75d-5721-4d65-afc2-8cf3b29a8de6', '物流管理', 'xt', 'root,xt,0c17d75d-5721-4d65-afc2-8cf3b29a8de6', null, 12, 'tab', '1', to_timestamp('09-03-2017 11:02:52.435000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('41678558-f241-42b1-9dd0-6adb9485c0a4', '运费设置', '0c17d75d-5721-4d65-afc2-8cf3b29a8de6', 'root,xt,0c17d75d-5721-4d65-afc2-8cf3b29a8de6,41678558-f241-42b1-9dd0-6adb9485c0a4', '1', 1, 'tab', '1', to_timestamp('09-03-2017 11:03:03.359000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('111b4d47-52c6-43e8-9e77-7ce49164d157', '物流设置', '0c17d75d-5721-4d65-afc2-8cf3b29a8de6', 'root,xt,0c17d75d-5721-4d65-afc2-8cf3b29a8de6,111b4d47-52c6-43e8-9e77-7ce49164d157', '1', 2, 'tab', '1', to_timestamp('09-03-2017 11:03:13.353000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('c1095bc5-16d7-4268-9ae9-540824a1ca20', '机构管理', '7846b141-65c8-4eb9-9fc5-ae63bb236836', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,c1095bc5-16d7-4268-9ae9-540824a1ca20', '/view/agency/agencymanager.jsp', 2, 'tab', '1', to_timestamp('29-12-2016 13:34:17.435000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('fd8fc71c-d011-4484-9e39-bedd4bcd7506', '机构管理', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', 'root,xt,ca795db5-52ce-4fc8-a02d-ea0da2adb8b1,fd8fc71c-d011-4484-9e39-bedd4bcd7506', '/view/system/org.jsp', 2, 'tab', '1', to_timestamp('17-11-2013 17:16:52.715000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-11-2013 19:29:44.917000', 'dd-mm-yyyy hh24:mi:ss.ff'), '/images/logos/jigouguanli.png');
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('0ff033aa-51fe-4600-a113-d771a03f08fa', '菜单管理', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', 'root,xt,ca795db5-52ce-4fc8-a02d-ea0da2adb8b1,0ff033aa-51fe-4600-a113-d771a03f08fa', '/view/system/menu.jsp', 3, 'tab', '1', to_timestamp('17-11-2013 17:18:28.944000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-05-2014 22:04:32.935000', 'dd-mm-yyyy hh24:mi:ss.ff'), '/images/logos/caidanguanli.png');
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('b782816d-cb7e-40ce-beab-949d6d7b8512', '角色菜单管理', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', 'root,xt,ca795db5-52ce-4fc8-a02d-ea0da2adb8b1,b782816d-cb7e-40ce-beab-949d6d7b8512', '/view/system/role_menu_config.jsp', 5, 'tab', '1', to_timestamp('17-11-2013 17:19:38.738000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('01-12-2013 15:24:52.031000', 'dd-mm-yyyy hh24:mi:ss.ff'), '/images/logos/jiaosecaidanguanli.png');
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('2456aacc-2776-49ad-b8ce-b7a893703033', '角色管理', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', 'root,xt,ca795db5-52ce-4fc8-a02d-ea0da2adb8b1,2456aacc-2776-49ad-b8ce-b7a893703033', '/view/system/role.jsp', 4, 'tab', '1', to_timestamp('17-11-2013 17:18:51.272000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-05-2014 22:08:30.042000', 'dd-mm-yyyy hh24:mi:ss.ff'), '/images/logos/jiaoseguanli.png');
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('e9f55cad-2593-427c-9427-48ebeee935cf', '角色人员管理', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', 'root,xt,ca795db5-52ce-4fc8-a02d-ea0da2adb8b1,e9f55cad-2593-427c-9427-48ebeee935cf', '/view/system/role_user_config.jsp', 6, 'tab', '1', to_timestamp('17-11-2013 17:20:01.214000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('01-12-2013 15:18:03.518000', 'dd-mm-yyyy hh24:mi:ss.ff'), '/images/logos/jiaoserenyuanguanli.png');
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', '权限管理', 'xt', 'root,xt', null, 99, 'tab', '1', to_timestamp('17-11-2013 17:15:41.107000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('05-12-2016 18:00:41.497000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('48d1dc9a-e401-417e-91fe-2bd7baab78c8', '用户管理', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', 'root,xt,ca795db5-52ce-4fc8-a02d-ea0da2adb8b1,48d1dc9a-e401-417e-91fe-2bd7baab78c8', '/view/system/user.jsp', 1, 'tab', '1', to_timestamp('17-11-2013 17:16:10.976000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('01-12-2013 15:25:53.208000', 'dd-mm-yyyy hh24:mi:ss.ff'), '/images/logos/yonghuguanli.png');
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('xt', '系统', 'root', 'root,xt', null, 1, 'tab', '1', to_timestamp('03-11-2013 14:25:41.149000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('7846b141-65c8-4eb9-9fc5-ae63bb236836', 'DEMO', 'xt', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836', null, 100, 'tab', '1', to_timestamp('03-11-2013 14:25:41.170000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-11-2013 11:59:25.733000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('d9727862-e2a6-40d3-8617-9f74a9cac2cc', '银行管理', '7846b141-65c8-4eb9-9fc5-ae63bb236836', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,d9727862-e2a6-40d3-8617-9f74a9cac2cc', '/view/bank/bankmanager.jsp', 1, 'tab', '1', to_timestamp('29-11-2016 20:26:02.971000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-12-2016 09:52:26.659000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('72238e08-76a4-4876-b9de-878c1dff3874', '操作日志查询', 'c962e30d-2dec-4be4-8c9b-1e0cc5290c56', 'root,xt,c962e30d-2dec-4be4-8c9b-1e0cc5290c56,72238e08-76a4-4876-b9de-878c1dff3874', '/view/log/log.jsp', 3, 'tab', '1', to_timestamp('29-12-2016 13:02:23.101000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-03-2017 11:00:11.592000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('c962e30d-2dec-4be4-8c9b-1e0cc5290c56', '系统管理', 'xt', 'root,xt,c962e30d-2dec-4be4-8c9b-1e0cc5290c56', null, 11, 'tab', '1', to_timestamp('27-12-2016 21:49:19.825000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('29-12-2016 12:56:15.135000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('5e60867a-9f1e-4830-b1be-64b025803e11', '系统参数设置', 'c962e30d-2dec-4be4-8c9b-1e0cc5290c56', 'root,xt,c962e30d-2dec-4be4-8c9b-1e0cc5290c56,5e60867a-9f1e-4830-b1be-64b025803e11', '1', 1, 'open', '1', to_timestamp('27-12-2016 21:50:27.929000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('27-12-2016 21:51:36.018000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
<<<<<<< HEAD
<<<<<<< HEAD
values ('0e280917-2f27-46ae-8495-0632c0283062', '工作日管理', 'c962e30d-2dec-4be4-8c9b-1e0cc5290c56', 'root,xt,c962e30d-2dec-4be4-8c9b-1e0cc5290c56,0e280917-2f27-46ae-8495-0632c0283062', '/view/system/workday.jsp', 2, 'tab', '1', to_timestamp('28-12-2016 11:50:27.556000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('ee602084-42f3-4633-a1e8-c68c9bb7aabd', '数据字典管理', 'c962e30d-2dec-4be4-8c9b-1e0cc5290c56', 'root,xt,c962e30d-2dec-4be4-8c9b-1e0cc5290c56,ee602084-42f3-4633-a1e8-c68c9bb7aabd', '/view/shop/system/dict.jsp', 4, 'tab', '1', to_timestamp('02-03-2017 11:55:56.215000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('06-03-2017 12:56:24.714000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
=======
values ('0e280917-2f27-46ae-8495-0632c0283062', '数据字典管理', 'c962e30d-2dec-4be4-8c9b-1e0cc5290c56', 'root,xt,c962e30d-2dec-4be4-8c9b-1e0cc5290c56,0e280917-2f27-46ae-8495-0632c0283062', '/view/shop/system/dict.jsp', 2, 'tab', '1', to_timestamp('28-12-2016 11:50:27.556000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-03-2017 10:59:56.884000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('06465872-afbc-4c5b-b0e3-fae4caeb27ed', '内容管理', 'xt', 'root,xt,06465872-afbc-4c5b-b0e3-fae4caeb27ed', null, 7, 'tab', '1', to_timestamp('07-03-2017 14:03:33.365000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('fec8a303-f88d-4b3b-8f52-3d9b3158b536', '已下架商品', '8861d139-1eb5-48cc-9c7f-5b5e17af5b25', 'root,xt,8861d139-1eb5-48cc-9c7f-5b5e17af5b25,fec8a303-f88d-4b3b-8f52-3d9b3158b536', '1', 4, 'tab', '1', to_timestamp('09-03-2017 10:47:45.791000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('4509c0e7-0547-47ea-ae1b-675a2dfe8360', '订单管理', 'xt', 'root,xt,4509c0e7-0547-47ea-ae1b-675a2dfe8360', null, 3, 'tab', '1', to_timestamp('09-03-2017 10:51:35.247000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('64166aec-782c-4a33-9b82-af8f05964ff6', '订单管理', '4509c0e7-0547-47ea-ae1b-675a2dfe8360', 'root,xt,4509c0e7-0547-47ea-ae1b-675a2dfe8360,64166aec-782c-4a33-9b82-af8f05964ff6', '1', 1, 'tab', '1', to_timestamp('09-03-2017 10:52:10.533000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('c3a85436-e78c-4249-b6f1-9344e45d8d22', '营销管理', 'xt', 'root,xt,c3a85436-e78c-4249-b6f1-9344e45d8d22', null, 5, 'tab', '1', to_timestamp('07-03-2017 13:51:56.440000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('07-03-2017 14:35:30.012000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('2f422b95-bd54-441a-b822-c091425c416b', '优惠券设置', 'c3a85436-e78c-4249-b6f1-9344e45d8d22', 'root,xt,c3a85436-e78c-4249-b6f1-9344e45d8d22,2f422b95-bd54-441a-b822-c091425c416b', '/view/shop/coupon/coupon.jsp', 3, 'tab', '1', to_timestamp('07-03-2017 13:52:04.412000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('07-03-2017 14:33:09.153000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('f58580cc-92f2-4403-a3fc-cf87631f6481', '优惠券使用记录', 'c3a85436-e78c-4249-b6f1-9344e45d8d22', 'root,xt,c3a85436-e78c-4249-b6f1-9344e45d8d22,f58580cc-92f2-4403-a3fc-cf87631f6481', '/view/shop/coupon/user_coupon.jsp', 4, 'tab', '1', to_timestamp('07-03-2017 13:52:12.529000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('07-03-2017 14:33:15.786000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('0c10145d-d154-4393-9afe-f3f6d0c4d29b', '退货管理', '3b395cb7-e806-4280-8366-6d41cf13d158', 'root,xt,3b395cb7-e806-4280-8366-6d41cf13d158,0c10145d-d154-4393-9afe-f3f6d0c4d29b', '1', 3, 'tab', '1', to_timestamp('07-03-2017 13:55:01.877000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-03-2017 10:48:28.900000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('e4ad4afb-56e7-4e11-bce7-709b5be45b91', '退款审核', '3b395cb7-e806-4280-8366-6d41cf13d158', 'root,xt,3b395cb7-e806-4280-8366-6d41cf13d158,e4ad4afb-56e7-4e11-bce7-709b5be45b91', '1', 4, 'tab', '1', to_timestamp('07-03-2017 13:55:19.384000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-03-2017 10:49:07.187000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('f250b168-9158-4fda-9360-f88149a3d287', '维修管理', '3b395cb7-e806-4280-8366-6d41cf13d158', 'root,xt,3b395cb7-e806-4280-8366-6d41cf13d158,f250b168-9158-4fda-9360-f88149a3d287', '1', 5, 'tab', '1', to_timestamp('07-03-2017 13:58:51.459000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-03-2017 10:49:13.846000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('7ad4851f-3fa8-401e-a612-2d0b1a4b4631', '意见反馈', '3b395cb7-e806-4280-8366-6d41cf13d158', 'root,xt,3b395cb7-e806-4280-8366-6d41cf13d158,7ad4851f-3fa8-401e-a612-2d0b1a4b4631', '1', 6, 'tab', '1', to_timestamp('07-03-2017 13:59:41.545000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-03-2017 10:49:22.386000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('258852a6-0d16-4084-9ffe-0720a761094f', '站内信', '3b395cb7-e806-4280-8366-6d41cf13d158', 'root,xt,3b395cb7-e806-4280-8366-6d41cf13d158,258852a6-0d16-4084-9ffe-0720a761094f', '1', 7, 'tab', '1', to_timestamp('07-03-2017 14:00:14.664000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-03-2017 10:49:35.755000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('3b9ba693-d655-481f-b908-4f2a1654fa41', '换货管理', '3b395cb7-e806-4280-8366-6d41cf13d158', 'root,xt,3b395cb7-e806-4280-8366-6d41cf13d158,3b9ba693-d655-481f-b908-4f2a1654fa41', '1', 3, 'tab', '1', to_timestamp('07-03-2017 14:01:14.392000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-03-2017 10:48:57.588000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('4366213a-4aa5-4c5f-822d-851797067d50', '文章管理', '06465872-afbc-4c5b-b0e3-fae4caeb27ed', 'root,xt,06465872-afbc-4c5b-b0e3-fae4caeb27ed,4366213a-4aa5-4c5f-822d-851797067d50', '1', 1, 'tab', '1', to_timestamp('07-03-2017 14:03:50.119000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('c1111c17-9f53-4ef5-82d0-dff9771ebf17', '库存价格维护', '8861d139-1eb5-48cc-9c7f-5b5e17af5b25', 'root,xt,8861d139-1eb5-48cc-9c7f-5b5e17af5b25,c1111c17-9f53-4ef5-82d0-dff9771ebf17', '1', 5, 'tab', '1', to_timestamp('09-03-2017 10:47:59.819000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('d0c3452e-70b5-4ecc-ae82-e8bd760fac4f', '发货管理', '4509c0e7-0547-47ea-ae1b-675a2dfe8360', 'root,xt,4509c0e7-0547-47ea-ae1b-675a2dfe8360,d0c3452e-70b5-4ecc-ae82-e8bd760fac4f', '1', 2, 'tab', '1', to_timestamp('09-03-2017 10:52:20.908000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('e3e4042f-4ff6-4446-a4c8-e6ffba0ffb4c', '评论管理', 'fb83cdc3-4110-403f-ad0b-bd8296c620d0', 'root,xt,fb83cdc3-4110-403f-ad0b-bd8296c620d0,e3e4042f-4ff6-4446-a4c8-e6ffba0ffb4c', '1', 1, 'tab', '1', to_timestamp('06-03-2017 16:55:18.843000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('07-03-2017 11:33:36.378000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('ba955ff0-5ce2-48fa-87de-3d083a26484b', '常见问题管理', 'fb83cdc3-4110-403f-ad0b-bd8296c620d0', 'root,xt,fb83cdc3-4110-403f-ad0b-bd8296c620d0,ba955ff0-5ce2-48fa-87de-3d083a26484b', '1', 2, 'tab', '1', to_timestamp('06-03-2017 16:55:42.188000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('06-03-2017 16:59:55.739000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('421290f9-c5be-44f5-b420-32e3b7170dda', '常见问题分类管理', 'fb83cdc3-4110-403f-ad0b-bd8296c620d0', 'root,xt,fb83cdc3-4110-403f-ad0b-bd8296c620d0,421290f9-c5be-44f5-b420-32e3b7170dda', '1', 3, 'tab', '1', to_timestamp('06-03-2017 16:56:03.241000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('06-03-2017 17:00:02.966000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('f52be96f-cb0e-4472-8ed5-011704170b6f', '分类管理', 'xt', 'root,xt,f52be96f-cb0e-4472-8ed5-011704170b6f', null, 1, 'tab', '1', to_timestamp('06-03-2017 16:57:14.828000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('06-03-2017 16:57:39.985000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('c5e2852e-823f-464b-af63-83214ebc40ba', '商品类别管理', 'f52be96f-cb0e-4472-8ed5-011704170b6f', 'root,xt,f52be96f-cb0e-4472-8ed5-011704170b6f,c5e2852e-823f-464b-af63-83214ebc40ba', '/view/shop/goodsmanage/categorymanager.jsp', 1, 'tab', '1', to_timestamp('06-03-2017 16:57:33.435000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('06-03-2017 17:01:42.507000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('40f78727-fe97-4f3f-8de4-0405a65056ce', '商品属性管理', 'f52be96f-cb0e-4472-8ed5-011704170b6f', 'root,xt,f52be96f-cb0e-4472-8ed5-011704170b6f,40f78727-fe97-4f3f-8de4-0405a65056ce', '1', 2, 'tab', '1', to_timestamp('06-03-2017 16:57:50.788000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('06-03-2017 17:01:47.453000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('61f2005c-22e4-448c-8f96-cd88d6ab5572', '商品属性值管理', 'f52be96f-cb0e-4472-8ed5-011704170b6f', 'root,xt,f52be96f-cb0e-4472-8ed5-011704170b6f,61f2005c-22e4-448c-8f96-cd88d6ab5572', '2', 3, 'tab', '1', to_timestamp('06-03-2017 16:57:59.581000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('06-03-2017 17:01:53.692000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('fb83cdc3-4110-403f-ad0b-bd8296c620d0', '评论管理', 'xt', 'root,xt,fb83cdc3-4110-403f-ad0b-bd8296c620d0', null, 9, 'tab', '1', to_timestamp('06-03-2017 16:53:23.019000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('07-03-2017 11:39:08.737000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
>>>>>>> refs/remotes/origin/yangchaowen
=======
values ('0e280917-2f27-46ae-8495-0632c0283062', '数据字典管理', 'c962e30d-2dec-4be4-8c9b-1e0cc5290c56', 'root,xt,c962e30d-2dec-4be4-8c9b-1e0cc5290c56,0e280917-2f27-46ae-8495-0632c0283062', '/view/shop/system/dict.jsp', 2, 'tab', '1', to_timestamp('28-12-2016 11:50:27.556000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-03-2017 10:59:56.884000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('06465872-afbc-4c5b-b0e3-fae4caeb27ed', '内容管理', 'xt', 'root,xt,06465872-afbc-4c5b-b0e3-fae4caeb27ed', null, 7, 'tab', '1', to_timestamp('07-03-2017 14:03:33.365000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('fec8a303-f88d-4b3b-8f52-3d9b3158b536', '已下架商品', '8861d139-1eb5-48cc-9c7f-5b5e17af5b25', 'root,xt,8861d139-1eb5-48cc-9c7f-5b5e17af5b25,fec8a303-f88d-4b3b-8f52-3d9b3158b536', '1', 4, 'tab', '1', to_timestamp('09-03-2017 10:47:45.791000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('4509c0e7-0547-47ea-ae1b-675a2dfe8360', '订单管理', 'xt', 'root,xt,4509c0e7-0547-47ea-ae1b-675a2dfe8360', null, 3, 'tab', '1', to_timestamp('09-03-2017 10:51:35.247000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('64166aec-782c-4a33-9b82-af8f05964ff6', '订单管理', '4509c0e7-0547-47ea-ae1b-675a2dfe8360', 'root,xt,4509c0e7-0547-47ea-ae1b-675a2dfe8360,64166aec-782c-4a33-9b82-af8f05964ff6', '1', 1, 'tab', '1', to_timestamp('09-03-2017 10:52:10.533000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('c3a85436-e78c-4249-b6f1-9344e45d8d22', '营销管理', 'xt', 'root,xt,c3a85436-e78c-4249-b6f1-9344e45d8d22', null, 5, 'tab', '1', to_timestamp('07-03-2017 13:51:56.440000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('07-03-2017 14:35:30.012000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('2f422b95-bd54-441a-b822-c091425c416b', '优惠券设置', 'c3a85436-e78c-4249-b6f1-9344e45d8d22', 'root,xt,c3a85436-e78c-4249-b6f1-9344e45d8d22,2f422b95-bd54-441a-b822-c091425c416b', '/view/shop/coupon/coupon.jsp', 3, 'tab', '1', to_timestamp('07-03-2017 13:52:04.412000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('07-03-2017 14:33:09.153000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('f58580cc-92f2-4403-a3fc-cf87631f6481', '优惠券使用记录', 'c3a85436-e78c-4249-b6f1-9344e45d8d22', 'root,xt,c3a85436-e78c-4249-b6f1-9344e45d8d22,f58580cc-92f2-4403-a3fc-cf87631f6481', '/view/shop/coupon/user_coupon.jsp', 4, 'tab', '1', to_timestamp('07-03-2017 13:52:12.529000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('07-03-2017 14:33:15.786000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('0c10145d-d154-4393-9afe-f3f6d0c4d29b', '退货管理', '3b395cb7-e806-4280-8366-6d41cf13d158', 'root,xt,3b395cb7-e806-4280-8366-6d41cf13d158,0c10145d-d154-4393-9afe-f3f6d0c4d29b', '1', 3, 'tab', '1', to_timestamp('07-03-2017 13:55:01.877000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-03-2017 10:48:28.900000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('e4ad4afb-56e7-4e11-bce7-709b5be45b91', '退款审核', '3b395cb7-e806-4280-8366-6d41cf13d158', 'root,xt,3b395cb7-e806-4280-8366-6d41cf13d158,e4ad4afb-56e7-4e11-bce7-709b5be45b91', '1', 4, 'tab', '1', to_timestamp('07-03-2017 13:55:19.384000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-03-2017 10:49:07.187000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('f250b168-9158-4fda-9360-f88149a3d287', '维修管理', '3b395cb7-e806-4280-8366-6d41cf13d158', 'root,xt,3b395cb7-e806-4280-8366-6d41cf13d158,f250b168-9158-4fda-9360-f88149a3d287', '1', 5, 'tab', '1', to_timestamp('07-03-2017 13:58:51.459000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-03-2017 10:49:13.846000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('7ad4851f-3fa8-401e-a612-2d0b1a4b4631', '意见反馈', '3b395cb7-e806-4280-8366-6d41cf13d158', 'root,xt,3b395cb7-e806-4280-8366-6d41cf13d158,7ad4851f-3fa8-401e-a612-2d0b1a4b4631', '1', 6, 'tab', '1', to_timestamp('07-03-2017 13:59:41.545000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-03-2017 10:49:22.386000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('258852a6-0d16-4084-9ffe-0720a761094f', '站内信', '3b395cb7-e806-4280-8366-6d41cf13d158', 'root,xt,3b395cb7-e806-4280-8366-6d41cf13d158,258852a6-0d16-4084-9ffe-0720a761094f', '1', 7, 'tab', '1', to_timestamp('07-03-2017 14:00:14.664000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-03-2017 10:49:35.755000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('3b9ba693-d655-481f-b908-4f2a1654fa41', '换货管理', '3b395cb7-e806-4280-8366-6d41cf13d158', 'root,xt,3b395cb7-e806-4280-8366-6d41cf13d158,3b9ba693-d655-481f-b908-4f2a1654fa41', '1', 3, 'tab', '1', to_timestamp('07-03-2017 14:01:14.392000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('09-03-2017 10:48:57.588000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('4366213a-4aa5-4c5f-822d-851797067d50', '文章管理', '06465872-afbc-4c5b-b0e3-fae4caeb27ed', 'root,xt,06465872-afbc-4c5b-b0e3-fae4caeb27ed,4366213a-4aa5-4c5f-822d-851797067d50', '1', 1, 'tab', '1', to_timestamp('07-03-2017 14:03:50.119000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('c1111c17-9f53-4ef5-82d0-dff9771ebf17', '库存价格维护', '8861d139-1eb5-48cc-9c7f-5b5e17af5b25', 'root,xt,8861d139-1eb5-48cc-9c7f-5b5e17af5b25,c1111c17-9f53-4ef5-82d0-dff9771ebf17', '1', 5, 'tab', '1', to_timestamp('09-03-2017 10:47:59.819000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('d0c3452e-70b5-4ecc-ae82-e8bd760fac4f', '发货管理', '4509c0e7-0547-47ea-ae1b-675a2dfe8360', 'root,xt,4509c0e7-0547-47ea-ae1b-675a2dfe8360,d0c3452e-70b5-4ecc-ae82-e8bd760fac4f', '1', 2, 'tab', '1', to_timestamp('09-03-2017 10:52:20.908000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('e3e4042f-4ff6-4446-a4c8-e6ffba0ffb4c', '评论管理', 'fb83cdc3-4110-403f-ad0b-bd8296c620d0', 'root,xt,fb83cdc3-4110-403f-ad0b-bd8296c620d0,e3e4042f-4ff6-4446-a4c8-e6ffba0ffb4c', '1', 1, 'tab', '1', to_timestamp('06-03-2017 16:55:18.843000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('07-03-2017 11:33:36.378000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('ba955ff0-5ce2-48fa-87de-3d083a26484b', '常见问题管理', 'fb83cdc3-4110-403f-ad0b-bd8296c620d0', 'root,xt,fb83cdc3-4110-403f-ad0b-bd8296c620d0,ba955ff0-5ce2-48fa-87de-3d083a26484b', '1', 2, 'tab', '1', to_timestamp('06-03-2017 16:55:42.188000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('06-03-2017 16:59:55.739000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('421290f9-c5be-44f5-b420-32e3b7170dda', '常见问题分类管理', 'fb83cdc3-4110-403f-ad0b-bd8296c620d0', 'root,xt,fb83cdc3-4110-403f-ad0b-bd8296c620d0,421290f9-c5be-44f5-b420-32e3b7170dda', '1', 3, 'tab', '1', to_timestamp('06-03-2017 16:56:03.241000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('06-03-2017 17:00:02.966000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('f52be96f-cb0e-4472-8ed5-011704170b6f', '分类管理', 'xt', 'root,xt,f52be96f-cb0e-4472-8ed5-011704170b6f', null, 1, 'tab', '1', to_timestamp('06-03-2017 16:57:14.828000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('06-03-2017 16:57:39.985000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('c5e2852e-823f-464b-af63-83214ebc40ba', '商品类别管理', 'f52be96f-cb0e-4472-8ed5-011704170b6f', 'root,xt,f52be96f-cb0e-4472-8ed5-011704170b6f,c5e2852e-823f-464b-af63-83214ebc40ba', '/view/shop/goodsmanage/categorymanager.jsp', 1, 'tab', '1', to_timestamp('06-03-2017 16:57:33.435000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('06-03-2017 17:01:42.507000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('40f78727-fe97-4f3f-8de4-0405a65056ce', '商品属性管理', 'f52be96f-cb0e-4472-8ed5-011704170b6f', 'root,xt,f52be96f-cb0e-4472-8ed5-011704170b6f,40f78727-fe97-4f3f-8de4-0405a65056ce', '1', 2, 'tab', '1', to_timestamp('06-03-2017 16:57:50.788000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('06-03-2017 17:01:47.453000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('61f2005c-22e4-448c-8f96-cd88d6ab5572', '商品属性值管理', 'f52be96f-cb0e-4472-8ed5-011704170b6f', 'root,xt,f52be96f-cb0e-4472-8ed5-011704170b6f,61f2005c-22e4-448c-8f96-cd88d6ab5572', '2', 3, 'tab', '1', to_timestamp('06-03-2017 16:57:59.581000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('06-03-2017 17:01:53.692000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, ORDER_, OPEN_METHOD, FLAG, CREATE_TIME, UPDATE_TIME, LOGO)
values ('fb83cdc3-4110-403f-ad0b-bd8296c620d0', '评论管理', 'xt', 'root,xt,fb83cdc3-4110-403f-ad0b-bd8296c620d0', null, 9, 'tab', '1', to_timestamp('06-03-2017 16:53:23.019000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('07-03-2017 11:39:08.737000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
>>>>>>> refs/remotes/origin/yangchaowen
commit;
insert into T_XT_ORG (ID, ORG_ID, ORG_NAME, PARENT_ORG_ID, ORDER_, ROOT, ORG_CODE, SHORT_NAME, ORG_PATH, ORG_LEVEL)
values ('9ee3fed0-d4cb-414b-959b-e381dc0f18b5', '001001', '办公室', '001', 1, null, null, '办公室', '根机构/办公室', 2);
insert into T_XT_ORG (ID, ORG_ID, ORG_NAME, PARENT_ORG_ID, ORDER_, ROOT, ORG_CODE, SHORT_NAME, ORG_PATH, ORG_LEVEL)
values ('81277dc5-6c4b-4246-97b9-47aafcb3d573', '001', '根机构', 'root', 1, '1', null, '根机构', '根机构', 0);
commit;
insert into T_XT_POSITION (POSITION_ID, POSITION_NAME, POSITION_DESC)
values ('5bfad0ff-8227-4979-a5ad-6435564b020b', '系统管理员', '系统管理员');
commit;
insert into T_XT_ROLE (ROLE_ID, ROLE_NAME, ROLE_TYPE, ROLE_REMARK, ROLE_STATE_VALUE, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '系统管理员', '0', null, null, '1', to_timestamp('29-11-2016 21:18:14.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('29-11-2016 21:18:14.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into T_XT_ROLE (ROLE_ID, ROLE_NAME, ROLE_TYPE, ROLE_REMARK, ROLE_STATE_VALUE, FLAG, CREATE_TIME, UPDATE_TIME)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', '普通用户', '0', '普通用户', null, '1', to_timestamp('29-11-2016 21:18:14.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('29-11-2016 21:18:14.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
commit;
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'xt', '1', to_timestamp('09-03-2017 11:03:53.713000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'f52be96f-cb0e-4472-8ed5-011704170b6f', '1', to_timestamp('09-03-2017 11:03:53.713000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'c5e2852e-823f-464b-af63-83214ebc40ba', '1', to_timestamp('09-03-2017 11:03:53.729000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '40f78727-fe97-4f3f-8de4-0405a65056ce', '1', to_timestamp('09-03-2017 11:03:53.729000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '61f2005c-22e4-448c-8f96-cd88d6ab5572', '1', to_timestamp('09-03-2017 11:03:53.729000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '8861d139-1eb5-48cc-9c7f-5b5e17af5b25', '1', to_timestamp('09-03-2017 11:03:53.729000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'cce3a1ee-0b58-4880-b667-a3e6fb5e9799', '1', to_timestamp('09-03-2017 11:03:53.745000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'e8d24ab4-04d4-43cc-847f-81c1707a6e9e', '1', to_timestamp('09-03-2017 11:03:53.745000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'de61c5b9-5628-4470-a016-c1408e0eba2f', '1', to_timestamp('09-03-2017 11:03:53.760000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'fec8a303-f88d-4b3b-8f52-3d9b3158b536', '1', to_timestamp('09-03-2017 11:03:53.760000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'c1111c17-9f53-4ef5-82d0-dff9771ebf17', '1', to_timestamp('09-03-2017 11:03:53.760000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '4509c0e7-0547-47ea-ae1b-675a2dfe8360', '1', to_timestamp('09-03-2017 11:03:53.775000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '64166aec-782c-4a33-9b82-af8f05964ff6', '1', to_timestamp('09-03-2017 11:03:53.775000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'd0c3452e-70b5-4ecc-ae82-e8bd760fac4f', '1', to_timestamp('09-03-2017 11:03:53.775000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '3b395cb7-e806-4280-8366-6d41cf13d158', '1', to_timestamp('09-03-2017 11:03:53.791000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '0c10145d-d154-4393-9afe-f3f6d0c4d29b', '1', to_timestamp('09-03-2017 11:03:53.791000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '3b9ba693-d655-481f-b908-4f2a1654fa41', '1', to_timestamp('09-03-2017 11:03:53.791000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'e4ad4afb-56e7-4e11-bce7-709b5be45b91', '1', to_timestamp('09-03-2017 11:03:53.791000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'f250b168-9158-4fda-9360-f88149a3d287', '1', to_timestamp('09-03-2017 11:03:53.807000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '7ad4851f-3fa8-401e-a612-2d0b1a4b4631', '1', to_timestamp('09-03-2017 11:03:53.807000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '258852a6-0d16-4084-9ffe-0720a761094f', '1', to_timestamp('09-03-2017 11:03:53.807000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'c3a85436-e78c-4249-b6f1-9344e45d8d22', '1', to_timestamp('09-03-2017 11:03:53.822000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '274b2319-2e8d-47e9-a0cb-c9e90817d204', '1', to_timestamp('09-03-2017 11:03:53.824000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '1f2d1568-1fe5-42fd-abaa-b504200f52cc', '1', to_timestamp('09-03-2017 11:03:53.824000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '2f422b95-bd54-441a-b822-c091425c416b', '1', to_timestamp('09-03-2017 11:03:53.824000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'f58580cc-92f2-4403-a3fc-cf87631f6481', '1', to_timestamp('09-03-2017 11:03:53.824000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '0b7e69df-9280-48fd-84b4-5a0025f66b40', '1', to_timestamp('09-03-2017 11:03:53.840000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '17d8428f-6595-4751-9f42-13acc064f903', '1', to_timestamp('09-03-2017 11:03:53.840000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'fc05fec5-92c8-4fc4-a153-bed5c8807813', '1', to_timestamp('09-03-2017 11:03:53.840000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '1b3fb348-ad0e-4d4c-b05b-b1e1494387f8', '1', to_timestamp('09-03-2017 11:03:53.856000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '507f53b0-399a-463f-a1a0-46526f1b1e46', '1', to_timestamp('09-03-2017 11:03:53.950000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '06465872-afbc-4c5b-b0e3-fae4caeb27ed', '1', to_timestamp('09-03-2017 11:03:53.950000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '344ac375-d7bb-4bd8-b4f3-3a72c4dd2003', '1', to_timestamp('09-03-2017 11:03:53.950000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '4366213a-4aa5-4c5f-822d-851797067d50', '1', to_timestamp('09-03-2017 11:03:53.965000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '3f456dff-36a6-4316-9556-27e33a0909e8', '1', to_timestamp('09-03-2017 11:03:53.965000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '547744b5-836d-45dd-9c82-7e6e955ecf72', '1', to_timestamp('09-03-2017 11:03:53.981000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'dfb5aa2f-c4c6-4081-8e82-d98d481192fe', '1', to_timestamp('09-03-2017 11:03:53.981000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'c1c8cbed-c3e5-4d8e-871f-b6589ec21f30', '1', to_timestamp('09-03-2017 11:03:53.981000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '322ba930-9e06-472a-8f61-86482499515a', '1', to_timestamp('09-03-2017 11:03:53.996000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'dec5913f-d40e-4c40-8ad3-4af94832fc39', '1', to_timestamp('09-03-2017 11:03:53.996000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '0bb65c6a-12e5-437d-b474-5e46f67034b6', '1', to_timestamp('09-03-2017 11:03:54.012000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'fb83cdc3-4110-403f-ad0b-bd8296c620d0', '1', to_timestamp('09-03-2017 11:03:54.012000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'e3e4042f-4ff6-4446-a4c8-e6ffba0ffb4c', '1', to_timestamp('09-03-2017 11:03:54.012000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'ba955ff0-5ce2-48fa-87de-3d083a26484b', '1', to_timestamp('09-03-2017 11:03:54.028000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '421290f9-c5be-44f5-b420-32e3b7170dda', '1', to_timestamp('09-03-2017 11:03:54.028000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '198279c9-d68f-4792-a221-06cb0958e4fc', '1', to_timestamp('09-03-2017 11:03:54.043000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '3e31f950-05d6-4733-af53-d6e3b5f78611', '1', to_timestamp('09-03-2017 11:03:54.059000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'caf85891-affb-442d-9450-33db8f3ebec7', '1', to_timestamp('09-03-2017 11:03:54.059000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'c962e30d-2dec-4be4-8c9b-1e0cc5290c56', '1', to_timestamp('09-03-2017 11:03:54.075000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '5e60867a-9f1e-4830-b1be-64b025803e11', '1', to_timestamp('09-03-2017 11:03:54.121000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '0e280917-2f27-46ae-8495-0632c0283062', '1', to_timestamp('09-03-2017 11:03:54.121000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '72238e08-76a4-4876-b9de-878c1dff3874', '1', to_timestamp('09-03-2017 11:03:54.121000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'ee602084-42f3-4633-a1e8-c68c9bb7aabd', '1', to_timestamp('09-03-2017 11:03:54.121000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '0c17d75d-5721-4d65-afc2-8cf3b29a8de6', '1', to_timestamp('09-03-2017 11:03:54.137000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '41678558-f241-42b1-9dd0-6adb9485c0a4', '1', to_timestamp('09-03-2017 11:03:54.137000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '111b4d47-52c6-43e8-9e77-7ce49164d157', '1', to_timestamp('09-03-2017 11:03:54.137000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', '1', to_timestamp('09-03-2017 11:03:54.137000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '48d1dc9a-e401-417e-91fe-2bd7baab78c8', '1', to_timestamp('09-03-2017 11:03:54.153000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'fd8fc71c-d011-4484-9e39-bedd4bcd7506', '1', to_timestamp('09-03-2017 11:03:54.153000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '0ff033aa-51fe-4600-a113-d771a03f08fa', '1', to_timestamp('09-03-2017 11:03:54.153000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '2456aacc-2776-49ad-b8ce-b7a893703033', '1', to_timestamp('09-03-2017 11:03:54.168000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'b782816d-cb7e-40ce-beab-949d6d7b8512', '1', to_timestamp('09-03-2017 11:03:54.168000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'e9f55cad-2593-427c-9427-48ebeee935cf', '1', to_timestamp('09-03-2017 11:03:54.184000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '7846b141-65c8-4eb9-9fc5-ae63bb236836', '1', to_timestamp('09-03-2017 11:03:54.184000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'd9727862-e2a6-40d3-8617-9f74a9cac2cc', '1', to_timestamp('09-03-2017 11:03:54.184000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'c1095bc5-16d7-4268-9ae9-540824a1ca20', '1', to_timestamp('09-03-2017 11:03:54.200000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', 'd9727862-e2a6-40d3-8617-9f74a9cac2cc', '1', to_timestamp('06-03-2017 16:54:08.273000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', '5e60867a-9f1e-4830-b1be-64b025803e11', '1', to_timestamp('06-03-2017 16:54:08.281000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', '0e280917-2f27-46ae-8495-0632c0283062', '1', to_timestamp('06-03-2017 16:54:08.284000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', '1', to_timestamp('06-03-2017 16:54:08.288000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', '48d1dc9a-e401-417e-91fe-2bd7baab78c8', '1', to_timestamp('06-03-2017 16:54:08.290000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', 'fd8fc71c-d011-4484-9e39-bedd4bcd7506', '1', to_timestamp('06-03-2017 16:54:08.294000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', '0ff033aa-51fe-4600-a113-d771a03f08fa', '1', to_timestamp('06-03-2017 16:54:08.299000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', '2456aacc-2776-49ad-b8ce-b7a893703033', '1', to_timestamp('06-03-2017 16:54:08.305000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', 'b782816d-cb7e-40ce-beab-949d6d7b8512', '1', to_timestamp('06-03-2017 16:54:08.311000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', 'e9f55cad-2593-427c-9427-48ebeee935cf', '1', to_timestamp('06-03-2017 16:54:08.315000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', '7846b141-65c8-4eb9-9fc5-ae63bb236836', '1', to_timestamp('06-03-2017 16:54:08.318000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', 'xt', '1', to_timestamp('06-03-2017 16:54:08.321000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', 'c962e30d-2dec-4be4-8c9b-1e0cc5290c56', '1', to_timestamp('06-03-2017 16:54:08.327000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
commit;
insert into T_XT_ROLE_USER (ROLE_ID, U_ID, FLAG, CREATE_TIME, UPDATE_TIME)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'admin', '1', to_timestamp('29-11-2016 21:18:14.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
commit;
insert into T_XT_USER (U_ID, U_PWD, U_NAME, U_REMARK, FLAG, CREATE_TIME, UPDATE_TIME, ORDER_)
values ('admin', '123456', '系统管理员', null, '1', to_timestamp('29-11-2016 21:18:15.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('29-12-2016 13:27:06.057000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
commit;
