create table TB_USERCAR
(
    ID       unknown,
    GOODSID  unknown,
    MEMBERID unknown,
    COUNT    unknown,
    constraint TB_USERCAR_PK
        primary key ()
)
/

comment on table TB_USERCAR is '购物车表'
/

comment on column TB_USERCAR.ID is '主键'
/

comment on column TB_USERCAR.GOODSID is 'goodsid'
/

comment on column TB_USERCAR.MEMBERID is '用户ID'
/

comment on column TB_USERCAR.COUNT is '数量'
/

create index TB_USERCAR_PK
    on TB_USERCAR ()
/

create table TB_MEMBER
(
    ID       unknown,
    USERNAME unknown,
    TRUENAME unknown,
    PASSWORD unknown,
    CITY     unknown,
    ADDRESS  unknown,
    POSTCODE unknown,
    CARDNO   unknown,
    CARDTYPE unknown,
    TEL      unknown,
    EMAIL    unknown,
    FREEZE   unknown,
    GRADE    unknown,
    constraint TB_MEMBER_PK
        primary key ()
)
/

comment on table TB_MEMBER is '会员表'
/

comment on column TB_MEMBER.ID is '主键'
/

comment on column TB_MEMBER.USERNAME is '用户名'
/

comment on column TB_MEMBER.TRUENAME is '真实姓名'
/

comment on column TB_MEMBER.PASSWORD is '密码'
/

comment on column TB_MEMBER.CITY is '城市'
/

comment on column TB_MEMBER.ADDRESS is '地址'
/

comment on column TB_MEMBER.POSTCODE is '邮政编码'
/

comment on column TB_MEMBER.CARDNO is '证件号码'
/

comment on column TB_MEMBER.CARDTYPE is '证件类型'
/

comment on column TB_MEMBER.TEL is '电话'
/

comment on column TB_MEMBER.EMAIL is '电子邮箱'
/

comment on column TB_MEMBER.FREEZE is '冻结  0表示冻结  1表示正常'
/

comment on column TB_MEMBER.GRADE is '用户等级
铜牌会员
银牌会员
白金会员
黄金会员
钻石会员'
/

create index TB_MEMBER_ID_UINDEX
    on TB_MEMBER ()
/

create table TB_GOODS
(
    ID        unknown,
    GOODSNAME unknown,
    PRICE     unknown,
    PICTURE   unknown,
    CREDATE   unknown,
    INTRODUCE unknown,
    CLASSNAME unknown,
    UN_LINE   unknown,
    constraint TB_GOODS_PK
        primary key ()
)
/

comment on table TB_GOODS is '商品表'
/

comment on column TB_GOODS.ID is '主键'
/

comment on column TB_GOODS.GOODSNAME is '商品名称'
/

comment on column TB_GOODS.PRICE is '原价'
/

comment on column TB_GOODS.PICTURE is '图片'
/

comment on column TB_GOODS.CREDATE is '创建时间'
/

comment on column TB_GOODS.INTRODUCE is '详细信息介绍'
/

comment on column TB_GOODS.CLASSNAME is '类别'
/

comment on column TB_GOODS.UN_LINE is '保留字段'
/

create index TB_GOODS_PK
    on TB_GOODS ()
/

create table TB_CLASSES
(
    ID   unknown,
    NAME unknown,
    PID  unknown,
    constraint TB_CLASSES_PK
        primary key ()
)
/

comment on table TB_CLASSES is '类别表'
/

comment on column TB_CLASSES.ID is '主键列'
/

comment on column TB_CLASSES.NAME is '大类别、小类别名'
/

comment on column TB_CLASSES.PID is '父ID'
/

create index TB_CLASSES_PK
    on TB_CLASSES ()
/

create table TB_TYPE
(
    ID   unknown,
    NAME unknown,
    FLAG unknown,
    constraint TB_TYPE_PK
        primary key ()
)
/

comment on table TB_TYPE is '证件名、支付方式、运送方式表'
/

comment on column TB_TYPE.ID is '主键列'
/

comment on column TB_TYPE.NAME is '证件名、支付方式、运送方式'
/

comment on column TB_TYPE.FLAG is 'A/B/C'
/

create index TB_TYPE_PK
    on TB_TYPE ()
/

create table TB_AREALINK
(
    ID   unknown,
    NAME unknown,
    PID  unknown,
    constraint TB_AREALINK_PK
        primary key ()
)
/

comment on table TB_AREALINK is '省市县表'
/

comment on column TB_AREALINK.ID is '主键列'
/

comment on column TB_AREALINK.NAME is '省市县名'
/

comment on column TB_AREALINK.PID is '父ID'
/

create index TB_AREALINK_PK
    on TB_AREALINK ()
/

create table TB_ORDER
(
    ID        unknown,
    MEMBERID  unknown,
    ORDERCODE unknown,
    BNUMBER   unknown,
    USERNAME  unknown,
    ADDRESS   unknown,
    POSTCODE  unknown,
    TEL       unknown,
    PAY       unknown,
    CARRY     unknown,
    ORDERDATE unknown,
    ENFORCE   unknown,
    BZ        unknown,
    constraint TB_ORDER_PK
        primary key ()
)
/

comment on table TB_ORDER is '订单表'
/

comment on column TB_ORDER.ID is '主键'
/

comment on column TB_ORDER.MEMBERID is '会员ID'
/

comment on column TB_ORDER.ORDERCODE is '订单号'
/

comment on column TB_ORDER.BNUMBER is '品种数'
/

comment on column TB_ORDER.USERNAME is '收件人姓名'
/

comment on column TB_ORDER.ADDRESS is '邮寄地址'
/

comment on column TB_ORDER.POSTCODE is '邮政编码'
/

comment on column TB_ORDER.TEL is '电话'
/

comment on column TB_ORDER.PAY is '支付方式'
/

comment on column TB_ORDER.CARRY is '运送方式'
/

comment on column TB_ORDER.ORDERDATE is '订单日期'
/

comment on column TB_ORDER.ENFORCE is '执行  0初始   1退货   2退款  3已执行'
/

comment on column TB_ORDER.BZ is '备注'
/

create index TB_ORDER_PK
    on TB_ORDER ()
/

create table TB_NOTICE
(
    ID        NUMBER(8)      not null
        constraint TB_NOTICE_PK
            primary key,
    TITLE     VARCHAR2(40)   not null,
    CONTENT   VARCHAR2(2000) not null,
    CREATDATE DATE           not null,
    ENDDATE   DATE           not null,
    ADDFILE   VARCHAR2(100)
)
/

comment on table TB_NOTICE is '公告表'
/

comment on column TB_NOTICE.ID is '主键列'
/

comment on column TB_NOTICE.TITLE is '公告标题'
/

comment on column TB_NOTICE.CONTENT is '公告内容'
/

comment on column TB_NOTICE.CREATDATE is '发布日期'
/

comment on column TB_NOTICE.ENDDATE is '截止日期'
/

comment on column TB_NOTICE.ADDFILE is '上传附件'
/

create table TB_MANAGER
(
    ID      unknown,
    MANAGER unknown,
    PWD     unknown,
    constraint TB_MANAGER_PK
        primary key ()
)
/

comment on table TB_MANAGER is '管理员表'
/

comment on column TB_MANAGER.ID is '主键列'
/

comment on column TB_MANAGER.MANAGER is '管理员名'
/

comment on column TB_MANAGER.PWD is '密码'
/

create index TB_MANAGER_PK
    on TB_MANAGER ()
/

create table TB_ORDER_DETAIL
(
    ID      unknown,
    ORDERID unknown,
    GOODSID unknown,
    PRICE   unknown,
    NUMBERS unknown,
    constraint TB_ORDER_DETAIL_PK
        primary key ()
)
/

comment on table TB_ORDER_DETAIL is '订单明细表'
/

comment on column TB_ORDER_DETAIL.ID is '主键'
/

comment on column TB_ORDER_DETAIL.ORDERID is '订单ID'
/

comment on column TB_ORDER_DETAIL.GOODSID is '商品ID'
/

comment on column TB_ORDER_DETAIL.PRICE is '价格'
/

comment on column TB_ORDER_DETAIL.NUMBERS is '数量
'
/

create index TB_ORDER_DETAIL_PK
    on TB_ORDER_DETAIL ()
/

