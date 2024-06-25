
########################### USERDB ###################################
create database if not exists USERDB default character set utf8mb4 collate utf8mb4_unicode_ci;
create table if not exists USERDB.users (
  id int primary key auto_increment comment '用户ID',
  username varchar(255) not null unique comment '用户名称',
  password varchar(255) not null comment '用户密码',
  phone varchar(32) not null unique comment '用户电话号码',
  index idx_username(username),
  index idx_phone(phone)
) ENGINE=InnoDB;

########################### PRODUCTDB ###################################
create database if not exists PRODUCTDB default character set utf8mb4 collate utf8mb4_unicode_ci;
create table if not exists PRODUCTDB.products (
  id int primary key auto_increment comment '商品ID',
  name varchar(255) not null unique comment '商品名称',
  price decimal(6, 2) null comment '商品价格',
  stock int default 0 comment '商品库存',
  index idx_name(name)
) ENGINE=InnoDB;

########################### ORDERDB ###################################
create database if not exists ORDERDB default character set utf8mb4 collate utf8mb4_unicode_ci;
create table if not exists ORDERDB.orders (
  id int primary key auto_increment comment '订单ID',
  user_id int not null comment '用户ID',
  product_id int not null comment '商品ID',
  quantity int default 0 comment '商品数量',
  index idx_user_product(user_id, product_id)
) ENGINE=InnoDB;

insert into USERDB.users(username, password, phone) values('张三', '123456', '18123456789');
insert into USERDB.users(username, password, phone) values('李四', '123456', '18322342871');
insert into USERDB.users(username, password, phone) values('王五', '123456', '13176093648');
insert into USERDB.users(username, password, phone) values('赵六', '123456', '15165384903');

insert into PRODUCTDB.products(name, price, stock) values("薯片", "23.19", 100);
insert into PRODUCTDB.products(name, price, stock) values("可乐", "13.45", 106);
insert into PRODUCTDB.products(name, price, stock) values("香蕉", "12.90", 140);
insert into PRODUCTDB.products(name, price, stock) values("鸡翅", "41.70", 190);
insert into PRODUCTDB.products(name, price, stock) values("面包", "56.00", 888);
