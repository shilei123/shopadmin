create table SC_DICTIONARY
(
  V_ID         VARCHAR2(36) not null,
  V_TYPE       VARCHAR2(50) not null,
  V_CODE       VARCHAR2(50) not null,
  V_NAME       VARCHAR2(100) not null,
  V_ENAME      VARCHAR2(100),
  V_PCODE      VARCHAR2(50),
  V_REMARK     VARCHAR2(100),
  V_ISUSE      CHAR(1) not null,
  V_ISEDIT     CHAR(1) default 0,
  V_SORT       NUMBER not null,
  V_CREATETIME DATE,
  V_FALG       CHAR(1) default 0 not null
);
comment on column SC_DICTIONARY.V_TYPE
  is '类型';
comment on column SC_DICTIONARY.V_CODE
  is '代码';
comment on column SC_DICTIONARY.V_NAME
  is '中文名称';
comment on column SC_DICTIONARY.V_ENAME
  is '英文名称';
comment on column SC_DICTIONARY.V_PCODE
  is '父ID';
comment on column SC_DICTIONARY.V_REMARK
  is '备注说明';
comment on column SC_DICTIONARY.V_ISUSE
  is '是否有效0有效，1无效 默认为0';
comment on column SC_DICTIONARY.V_ISEDIT
  is '是否可编辑默认0，0可编辑，1不可编辑';
comment on column SC_DICTIONARY.V_SORT
  is '排序';
comment on column SC_DICTIONARY.V_CREATETIME
  is '创建时间';
comment on column SC_DICTIONARY.V_FALG
  is '是否删除默认0，0有效，1无效';
alter table SC_DICTIONARY
  add constraint PK_DICTIONARY primary key (V_ID);
