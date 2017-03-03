create table SC_DICTIONARY
(
  ID          VARCHAR2(36) not null,
  TYPE        VARCHAR2(50) not null,
  CODE        VARCHAR2(50) not null,
  NAME        VARCHAR2(100) not null,
  ENAME       VARCHAR2(100),
  PCODE       VARCHAR2(50),
  REMARK      VARCHAR2(100),
  ISUSE       CHAR(1) not null,
  ISEDIT      CHAR(1) default 0,
  SORT        NUMBER not null,
  CREATE_TIME DATE,
  FLAG        CHAR(1) default 0 not null
);
comment on column SC_DICTIONARY.TYPE
  is '类型';
comment on column SC_DICTIONARY.CODE
  is '代码';
comment on column SC_DICTIONARY.NAME
  is '中文名称';
comment on column SC_DICTIONARY.ENAME
  is '英文名称';
comment on column SC_DICTIONARY.PCODE
  is '父ID';
comment on column SC_DICTIONARY.REMARK
  is '备注说明';
comment on column SC_DICTIONARY.ISUSE
  is '是否有效0有效，1无效 默认为0';
comment on column SC_DICTIONARY.ISEDIT
  is '是否可编辑默认0，0可编辑，1不可编辑';
comment on column SC_DICTIONARY.SORT
  is '排序';
comment on column SC_DICTIONARY.CREATE_TIME
  is '创建时间';
comment on column SC_DICTIONARY.FLAG
  is '是否删除默认0，0有效，1无效';
alter table SC_DICTIONARY
  add constraint PK_DICTIONARY primary key (ID);
