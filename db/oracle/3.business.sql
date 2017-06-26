create table SC_ADVERTISE
(
  ID          VARCHAR2(36) not null,
  NAME        VARCHAR2(50),
  MEMO        VARCHAR2(100),
  LINKKIND    VARCHAR2(50),
  IMGLINK     VARCHAR2(50),
  ORDERNUMB   VARCHAR2(50),
  TYPE        VARCHAR2(50),
  ISUSE       CHAR(1),
  KIND        VARCHAR2(36),
  START_TIME  DATE,
  END_TIME    DATE,
  CREATE_TIME DATE,
  UPDATE_TIME DATE,
  FLAG        CHAR(1)
)
;
comment on column SC_ADVERTISE.ID
  is '主键';
comment on column SC_ADVERTISE.NAME
  is '名称';
comment on column SC_ADVERTISE.MEMO
  is '介绍/说明';
comment on column SC_ADVERTISE.LINKKIND
  is '广告类型';
comment on column SC_ADVERTISE.IMGLINK
  is '商品ID/活动ID/url';
comment on column SC_ADVERTISE.ORDERNUMB
  is '显示顺序';
comment on column SC_ADVERTISE.TYPE
  is '广告位置';
comment on column SC_ADVERTISE.ISUSE
  is '是否启用/默认0，0启用，1不启用
';
comment on column SC_ADVERTISE.KIND
  is '类型';
comment on column SC_ADVERTISE.START_TIME
  is '广告开始时间';
comment on column SC_ADVERTISE.END_TIME
  is '广告结束时间';
comment on column SC_ADVERTISE.CREATE_TIME
  is '创建时间';
comment on column SC_ADVERTISE.UPDATE_TIME
  is '修改时间';
comment on column SC_ADVERTISE.FLAG
  is '数据有效性/默认0，0有效，1无效';
alter table SC_ADVERTISE
  add constraint PK_ADVERTISE primary key (ID);

create table SC_ATTACHMENTS
(
  ID             VARCHAR2(50) not null,
  KIND           VARCHAR2(50),
  ATTACH_NAME    VARCHAR2(100),
  FILE_NAME      VARCHAR2(100),
  ATTACH_TYPE    VARCHAR2(50),
  ABS_PATH       VARCHAR2(100),
  ATTACH_PATH    VARCHAR2(100),
  PARENT_ID      VARCHAR2(50),
  CREATE_USER_ID VARCHAR2(50),
  CREATE_TIME    DATE,
  ATTACH_SIZE    NUMBER,
  BELONG         VARCHAR2(50),
  REMARK         VARCHAR2(100),
  FLAG           CHAR(1) not null
)
;
comment on column SC_ATTACHMENTS.ID
  is 'id';
comment on column SC_ATTACHMENTS.KIND
  is '附件类型';
comment on column SC_ATTACHMENTS.ATTACH_NAME
  is '附件名称  ';
comment on column SC_ATTACHMENTS.FILE_NAME
  is '文件名称  ';
comment on column SC_ATTACHMENTS.ATTACH_TYPE
  is '文件类型 ';
comment on column SC_ATTACHMENTS.ABS_PATH
  is '保存绝对路径';
comment on column SC_ATTACHMENTS.ATTACH_PATH
  is '访问路径';
comment on column SC_ATTACHMENTS.PARENT_ID
  is '关联项ID';
comment on column SC_ATTACHMENTS.CREATE_USER_ID
  is '上传人';
comment on column SC_ATTACHMENTS.CREATE_TIME
  is '上传时间';
comment on column SC_ATTACHMENTS.ATTACH_SIZE
  is '附件大小';
comment on column SC_ATTACHMENTS.BELONG
  is '所属';
comment on column SC_ATTACHMENTS.REMARK
  is '描述';
comment on column SC_ATTACHMENTS.FLAG
  is '是否删除';
alter table SC_ATTACHMENTS
  add constraint PK_ATTACHMENTS primary key (ID);

create table SC_BCUSER
(
  ID         VARCHAR2(36) not null,
  USER_ID    VARCHAR2(36),
  USER_NAME  VARCHAR2(50),
  TRUE_NAME  VARCHAR2(50),
  USER_PHONE VARCHAR2(50),
  USER_MAIL  VARCHAR2(50),
  USER_SEX   CHAR(1)
)
;
comment on column SC_BCUSER.ID
  is '主键';
comment on column SC_BCUSER.USER_ID
  is '会员id';
comment on column SC_BCUSER.USER_NAME
  is '用户名';
comment on column SC_BCUSER.TRUE_NAME
  is '会员姓名';
comment on column SC_BCUSER.USER_PHONE
  is '电话';
comment on column SC_BCUSER.USER_MAIL
  is '邮箱';
comment on column SC_BCUSER.USER_SEX
  is '性别/0 男 1 女';
alter table SC_BCUSER
  add constraint PK_BCUSER primary key (ID);

create table SC_CATEGORY
(
  ID          VARCHAR2(36) not null,
  CATE_NAME   VARCHAR2(100),
  MEMO        VARCHAR2(100),
  CATE_ORDER  CHAR(1),
  LEVELS      CHAR(1),
  LOGO        VARCHAR2(100),
  URL         VARCHAR2(100),
  ISUSE       CHAR(1),
  PARENT_ID   VARCHAR2(36),
  CATE_CODE   VARCHAR2(10),
  CREATE_TIME DATE,
  UPDATE_TIME DATE,
  FLAG        CHAR(1)
)
;
comment on column SC_CATEGORY.ID
  is '自增ID';
comment on column SC_CATEGORY.CATE_NAME
  is '类别名称';
comment on column SC_CATEGORY.MEMO
  is '类别描述';
comment on column SC_CATEGORY.CATE_ORDER
  is '排序';
comment on column SC_CATEGORY.LEVELS
  is '所属级别.0根节点，1一级类别，2二级类别，3三级类别';
comment on column SC_CATEGORY.LOGO
  is '类别图标';
comment on column SC_CATEGORY.URL
  is '导航链接';
comment on column SC_CATEGORY.ISUSE
  is '是否有效';
comment on column SC_CATEGORY.PARENT_ID
  is '上级类别ID';
comment on column SC_CATEGORY.CATE_CODE
  is '类别代码';
comment on column SC_CATEGORY.CREATE_TIME
  is '创建时间';
comment on column SC_CATEGORY.UPDATE_TIME
  is '修改时间';
comment on column SC_CATEGORY.FLAG
  is '数据有效性';
alter table SC_CATEGORY
  add constraint CATEGORY_PK primary key (ID);

create table SC_COUPON
(
  ID                 VARCHAR2(36) not null,
  COUPON_NAME        VARCHAR2(36),
  COUPON_TYPE        VARCHAR2(36),
  COUPON_ZS_BALANCE  NUMBER(10,2),
  COUPON_BLANCE      NUMBER(10,2),
  COUPON_XF_BALANCE  NUMBER(10,2),
  COUPON_EXPIRY_DATE INTEGER,
  COUPON_REMARK      VARCHAR2(200),
  COUPON_STATUS      CHAR(1),
  COUPON_FLAG        CHAR(1),
  CREATE_TIME        DATE,
  OPTION_ADMINID     VARCHAR2(36),
  OPTION_TIME        DATE
)
;
comment on column SC_COUPON.COUPON_NAME
  is '优惠券名称';
comment on column SC_COUPON.COUPON_TYPE
  is '优惠券使用类型
';
comment on column SC_COUPON.COUPON_ZS_BALANCE
  is '满额赠送';
comment on column SC_COUPON.COUPON_BLANCE
  is '优惠券金额';
comment on column SC_COUPON.COUPON_XF_BALANCE
  is '满足金额使用
';
comment on column SC_COUPON.COUPON_EXPIRY_DATE
  is '有效期天数';
comment on column SC_COUPON.COUPON_REMARK
  is '备注
';
comment on column SC_COUPON.COUPON_STATUS
  is '状态
';
comment on column SC_COUPON.COUPON_FLAG
  is '是否删除
';
comment on column SC_COUPON.CREATE_TIME
  is '创建时间
';
comment on column SC_COUPON.OPTION_ADMINID
  is '操作人';
comment on column SC_COUPON.OPTION_TIME
  is '操作时间';
alter table SC_COUPON
  add constraint PK_COUPON primary key (ID);

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
)
;
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

create table SC_EVENTSINFO
(
  ID         VARCHAR2(36) not null,
  NAME       VARCHAR2(50),
  ISUSE      CHAR(1),
  MEMO       VARCHAR2(2000),
  STARTTIME  DATE,
  ENDTIME    DATE,
  CREATETIME DATE,
  UPDATETIME DATE,
  FLAG       CHAR(1)
)
;
comment on column SC_EVENTSINFO.ID
  is '主键';
comment on column SC_EVENTSINFO.NAME
  is '名称';
comment on column SC_EVENTSINFO.ISUSE
  is '是否启用/默认0，0启用，1关闭';
comment on column SC_EVENTSINFO.MEMO
  is '活动介绍';
comment on column SC_EVENTSINFO.STARTTIME
  is '活动开始时间';
comment on column SC_EVENTSINFO.ENDTIME
  is '活动结束时间';
comment on column SC_EVENTSINFO.CREATETIME
  is '创建时间';
comment on column SC_EVENTSINFO.UPDATETIME
  is '修改时间';
comment on column SC_EVENTSINFO.FLAG
  is '数据有效性/默认0，0有效，1无效
';
alter table SC_EVENTSINFO
  add constraint PK_EVENTSINFO primary key (ID);

create table SC_GRADE
(
  ID        VARCHAR2(36) not null,
  USER_NAME VARCHAR2(50)
)
;
comment on column SC_GRADE.ID
  is '主键';
comment on column SC_GRADE.USER_NAME
  is '用户名';
alter table SC_GRADE
  add constraint PK_GRADE primary key (ID);

create table SC_IMAGES
(
  ID          VARCHAR2(36) not null,
  IMG_NAME    VARCHAR2(100) not null,
  FILE_NAME   VARCHAR2(50) not null,
  IMG_PATH    VARCHAR2(100) not null,
  IMG_TYPE    VARCHAR2(5) not null,
  IMG_SIZE    NUMBER not null,
  CREATE_TIME DATE not null,
  FLAG        CHAR(1)
)
;
comment on column SC_IMAGES.ID
  is '主键';
comment on column SC_IMAGES.IMG_NAME
  is '图片名称';
comment on column SC_IMAGES.FILE_NAME
  is '文件名称';
comment on column SC_IMAGES.IMG_PATH
  is '存放路径';
comment on column SC_IMAGES.IMG_TYPE
  is '图片格式';
comment on column SC_IMAGES.IMG_SIZE
  is '图片大小';
comment on column SC_IMAGES.CREATE_TIME
  is '创建时间';
comment on column SC_IMAGES.FLAG
  is '是否删除';
alter table SC_IMAGES
  add constraint PK_IMAGES primary key (ID);

create table SC_PROPERTY
(
  ID            VARCHAR2(36) not null,
  PROP_CODE     VARCHAR2(36),
  PROP_NAME     VARCHAR2(36),
  PROP_ORDER    VARCHAR2(2),
  FLAG          CHAR(1),
  CREATE_TIME   DATE,
  CREATE_PEOPLE VARCHAR2(36)
)
;
comment on column SC_PROPERTY.ID
  is 'ID';
comment on column SC_PROPERTY.PROP_CODE
  is '属性code';
comment on column SC_PROPERTY.PROP_NAME
  is '属性name';
comment on column SC_PROPERTY.PROP_ORDER
  is '排序';
comment on column SC_PROPERTY.FLAG
  is '状态';
comment on column SC_PROPERTY.CREATE_TIME
  is '创建日期';
comment on column SC_PROPERTY.CREATE_PEOPLE
  is '创建人';
alter table SC_PROPERTY
  add constraint PROPERTY_PK primary key (ID);

create table SC_PROPERTY_CATEGORY
(
  ID            VARCHAR2(36) not null,
  PROP_ID       VARCHAR2(36),
  CATE_ID       VARCHAR2(36),
  ORDERS        CHAR(2),
  FLAG          CHAR(1),
  CREATE_TIME   DATE,
  CREATE_PEOPLE VARCHAR2(36)
)
;
comment on column SC_PROPERTY_CATEGORY.ID
  is 'ID';
comment on column SC_PROPERTY_CATEGORY.PROP_ID
  is '属性id';
comment on column SC_PROPERTY_CATEGORY.CATE_ID
  is '类别id';
comment on column SC_PROPERTY_CATEGORY.ORDERS
  is '排序';
comment on column SC_PROPERTY_CATEGORY.FLAG
  is '状态';
comment on column SC_PROPERTY_CATEGORY.CREATE_TIME
  is '创建时间';
comment on column SC_PROPERTY_CATEGORY.CREATE_PEOPLE
  is '创建人';
alter table SC_PROPERTY_CATEGORY
  add constraint PROPERTY_CATEGORY_PK primary key (ID);

create table SC_PROPERTY_PROPVALUE
(
  ID            VARCHAR2(36) not null,
  PROP_ID       VARCHAR2(36),
  VAL_ID        VARCHAR2(36),
  FLAG          CHAR(1),
  ORDERS        CHAR(2),
  CREATE_TIME   DATE,
  CREATE_PEOPLE VARCHAR2(36)
)
;
comment on column SC_PROPERTY_PROPVALUE.ID
  is 'ID';
comment on column SC_PROPERTY_PROPVALUE.PROP_ID
  is '属性id';
comment on column SC_PROPERTY_PROPVALUE.VAL_ID
  is '属性值id';
comment on column SC_PROPERTY_PROPVALUE.FLAG
  is '状态';
comment on column SC_PROPERTY_PROPVALUE.ORDERS
  is '排序';
comment on column SC_PROPERTY_PROPVALUE.CREATE_TIME
  is '创建时间';
comment on column SC_PROPERTY_PROPVALUE.CREATE_PEOPLE
  is '创建人';
alter table SC_PROPERTY_PROPVALUE
  add constraint PROPERTY_PROPVALUE_PK primary key (ID);

create table SC_PROPVALUE
(
  ID            VARCHAR2(36) not null,
  VAL_CODE      VARCHAR2(36),
  VAL_NAME      VARCHAR2(36),
  VAL_ORDER     VARCHAR2(2),
  FLAG          CHAR(1),
  CREATE_TIME   DATE,
  CREATE_PEOPLE VARCHAR2(36),
  UPDATE_TIME   DATE,
  UPDATE_PEOPLE VARCHAR2(36)
)
;
comment on column SC_PROPVALUE.ID
  is 'ID';
comment on column SC_PROPVALUE.VAL_CODE
  is '属性值code';
comment on column SC_PROPVALUE.VAL_NAME
  is '属性值name';
comment on column SC_PROPVALUE.VAL_ORDER
  is '排序';
comment on column SC_PROPVALUE.FLAG
  is '状态';
comment on column SC_PROPVALUE.CREATE_TIME
  is '创建日期';
comment on column SC_PROPVALUE.CREATE_PEOPLE
  is '创建人';
comment on column SC_PROPVALUE.UPDATE_TIME
  is '修改日期';
comment on column SC_PROPVALUE.UPDATE_PEOPLE
  is '修改人';
alter table SC_PROPVALUE
  add constraint PROPVALUE_PK primary key (ID);

create table SC_USER
(
  ID            VARCHAR2(36),
  USER_NAME     VARCHAR2(50),
  NICK_NAME     VARCHAR2(50),
  LOGIN_PWD     VARCHAR2(36),
  PAY_PWD       VARCHAR2(36),
  USER_GRADE    VARCHAR2(36),
  TIXIAN_STATUS CHAR(1),
  CREATE_TIME   DATE,
  OPTION_TIME   DATE,
  USER_STATUS   CHAR(1)
)
;
comment on column SC_USER.ID
  is '主键';
comment on column SC_USER.USER_NAME
  is '用户名';
comment on column SC_USER.NICK_NAME
  is '昵称';
comment on column SC_USER.LOGIN_PWD
  is '登录密码';
comment on column SC_USER.PAY_PWD
  is '支付密码';
comment on column SC_USER.USER_GRADE
  is '会员等级id';
comment on column SC_USER.TIXIAN_STATUS
  is '提现状态/0无 1申请中';
comment on column SC_USER.CREATE_TIME
  is '注册时间';
comment on column SC_USER.OPTION_TIME
  is '修改时间';
comment on column SC_USER.USER_STATUS
  is '账户状态/0注销 1正常 2冻结';
alter table SC_USER
  add constraint PK_USER primary key (ID);

create table SC_USER_COUPON
(
  ID                VARCHAR2(36) not null,
  USER_ID           VARCHAR2(36),
  USER_NAME         VARCHAR2(100),
  COUPON_ID         VARCHAR2(36),
  COUPON_NAME       VARCHAR2(100),
  COUPON_BLANCE     NUMBER(10,2),
  COUPON_XF_BALANCE NUMBER(10,2),
  COUPON_CREATDATE  DATE,
  COUPON_EXPIRYDATE DATE,
  ORDER_SN          VARCHAR2(36),
  COUPON_STATUS     CHAR(1),
  OPTION_TIME       DATE,
  OPTION_ADMINID    VARCHAR2(36),
  OPTION_REMARK     VARCHAR2(100)
)
;
comment on column SC_USER_COUPON.USER_ID
  is '用户id
';
comment on column SC_USER_COUPON.USER_NAME
  is '用户用户名
';
comment on column SC_USER_COUPON.COUPON_ID
  is '优惠券id
';
comment on column SC_USER_COUPON.COUPON_NAME
  is '优惠券名称
';
comment on column SC_USER_COUPON.COUPON_BLANCE
  is '优惠券金额
';
comment on column SC_USER_COUPON.COUPON_XF_BALANCE
  is '满足金额使用
';
comment on column SC_USER_COUPON.COUPON_CREATDATE
  is '优惠券发放时间
';
comment on column SC_USER_COUPON.COUPON_EXPIRYDATE
  is '有效期至
';
comment on column SC_USER_COUPON.ORDER_SN
  is '使用订单号
';
comment on column SC_USER_COUPON.COUPON_STATUS
  is '优惠券状态/0未使用 1已使用 2作废
';
comment on column SC_USER_COUPON.OPTION_TIME
  is '操作时间
';
comment on column SC_USER_COUPON.OPTION_ADMINID
  is '系统操作人
';
comment on column SC_USER_COUPON.OPTION_REMARK
  is '操作备注
';
alter table SC_USER_COUPON
  add constraint PK_USER_COUPON primary key (ID);

insert into SC_DICTIONARY (ID, TYPE, CODE, NAME, ENAME, PCODE, REMARK, ISUSE, ISEDIT, SORT, CREATE_TIME, FLAG)
values ('c8acd661-077a-4dda-ac7c-385541f2815f', 'ATTACH_TYPE', '1', '文档', null, null, '文档', '0', '0', 0, to_date('14-03-2017 16:56:35', 'dd-mm-yyyy hh24:mi:ss'), '1');
insert into SC_DICTIONARY (ID, TYPE, CODE, NAME, ENAME, PCODE, REMARK, ISUSE, ISEDIT, SORT, CREATE_TIME, FLAG)
values ('3399cafe-998c-4107-b8e5-3da7d71bcf9c', 'ATTACH_TYPE', '2', '图片', null, null, '图片', '0', '0', 0, to_date('14-03-2017 16:56:54', 'dd-mm-yyyy hh24:mi:ss'), '1');
insert into SC_DICTIONARY (ID, TYPE, CODE, NAME, ENAME, PCODE, REMARK, ISUSE, ISEDIT, SORT, CREATE_TIME, FLAG)
values ('f16d24c1-e0a6-421a-b44b-c877928a0960', 'ATTACH_TYPE', '3', '其它文件', null, null, '其它文件', '0', '0', 0, to_date('14-03-2017 16:57:08', 'dd-mm-yyyy hh24:mi:ss'), '1');
insert into SC_DICTIONARY (ID, TYPE, CODE, NAME, ENAME, PCODE, REMARK, ISUSE, ISEDIT, SORT, CREATE_TIME, FLAG)
values ('e4b8cd6c-b0bf-44e0-b4de-430794edddec', 'CATEGORY_ISUSE', '0', '无用', 'UNUSE', null, '无用', '0', '0', 0, to_date('07-03-2017 11:46:30', 'dd-mm-yyyy hh24:mi:ss'), '1');
insert into SC_DICTIONARY (ID, TYPE, CODE, NAME, ENAME, PCODE, REMARK, ISUSE, ISEDIT, SORT, CREATE_TIME, FLAG)
values ('cf4c6a77-3ca6-4561-8fd0-c6d24e549ae7', 'CATEGORY_ISUSE', '1', '有用', 'ISUSE', null, '有用', '0', '0', 0, to_date('07-03-2017 11:47:37', 'dd-mm-yyyy hh24:mi:ss'), '1');
insert into SC_DICTIONARY (ID, TYPE, CODE, NAME, ENAME, PCODE, REMARK, ISUSE, ISEDIT, SORT, CREATE_TIME, FLAG)
values ('4209dd9e-c8df-46d4-957e-b2249e998d87', 'COUPON_TYPE', '0', '饿货优惠券', null, null, null, '0', '0', 0, to_date('09-03-2017 11:52:41', 'dd-mm-yyyy hh24:mi:ss'), '1');
insert into SC_DICTIONARY (ID, TYPE, CODE, NAME, ENAME, PCODE, REMARK, ISUSE, ISEDIT, SORT, CREATE_TIME, FLAG)
values ('07f98830-701f-4e7d-ae8b-15fe9a50d997', 'COUPON_TYPE', '1', '吃货优惠券', '用于吃', null, null, '0', '0', 0, to_date('07-03-2017 15:22:57', 'dd-mm-yyyy hh24:mi:ss'), '1');
insert into SC_DICTIONARY (ID, TYPE, CODE, NAME, ENAME, PCODE, REMARK, ISUSE, ISEDIT, SORT, CREATE_TIME, FLAG)
values ('f9d29656-27cb-4177-90cb-49e1e6b19bab', 'FLAG_STATUS', '0', '无效', null, null, '无效', '0', '0', 0, to_date('03-03-2017 10:55:22', 'dd-mm-yyyy hh24:mi:ss'), '1');
insert into SC_DICTIONARY (ID, TYPE, CODE, NAME, ENAME, PCODE, REMARK, ISUSE, ISEDIT, SORT, CREATE_TIME, FLAG)
values ('4aba56b3-bac1-416c-9a5d-f8b673bbd7a4', 'FLAG_STATUS', '1', '有效', null, '123', '有效', '0', '0', 0, to_date('03-03-2017 10:27:36', 'dd-mm-yyyy hh24:mi:ss'), '1');
commit;
insert into SC_CATEGORY (ID, CATE_NAME, MEMO, CATE_ORDER, LEVELS, LOGO, URL, ISUSE, PARENT_ID, CATE_CODE, CREATE_TIME, UPDATE_TIME, FLAG)
values ('id1', '所有类别', '所有类别', '1', '0', null, null, '1', null, '代码1', null, null, '1');
commit;