######################### mybatits库 start !!! #####################


USE mybatis;

SELECT *
FROM country;

######################### mybatis库 end !!! #####################


######################### test库 start !!! #####################

#慢查询测试
CREATE TABLE t_test_slow_query (
  id          INT         NOT NULL,
  user_id     VARCHAR(50) NOT NULL,
  customer_id VARCHAR(50) NOT NULL,
  create_time DATETIME    NOT NULL,
  PRIMARY KEY pk_id(id),
  UNIQUE KEY uk_uid_cid(user_id, customer_id),
  KEY         idx_uid (user_id),
  KEY         idx_cid (customer_id)
)
  ENGINE = InnoDB, CHARSET = utf8;

DESC t_test_slow_query;

SELECT count(1)
FROM t_test_slow_query;

DELETE
FROM t_test_slow_query;

USE test;
#存储过程
DROP PROCEDURE IF EXISTS `t_test_slow_query_batch_insert`;
DELIMITER;
;
CREATE PROCEDURE `t_test_slow_query_batch_insert`()
BEGIN
  DECLARE i INT;
    SET     i = 0;
    WHILE   i < 1000000 DO
  INSERT INTO t_test_slow_query VALUES (i, i + 1, i + 2, now());
  SET i = i + 1;
END WHILE;
END
;
;
DELIMITER;


CALL t_test_slow_query_batch_insert();


SHOW VARIABLES LIKE '%slow%';

EXPLAIN
SELECT SQL_NO_CACHE count(*)
FROM t_test_slow_query a
       INNER JOIN t_test_slow_query b
       INNER JOIN t_test_slow_query c ON a.user_id = b.user_id AND a.user_id = c.user_id
WHERE a.user_id = 100;


EXPLAIN
SELECT SQL_NO_CACHE count(*)
FROM t_test_slow_query a
       INNER JOIN t_test_slow_query b ON a.user_id = b.user_id;


SHOW INDEX FROM t_test_slow_query;

ANALYZE TABLE t_test_slow_query;


ALTER TABLE t_test_slow_query
CHANGE user_id user_id VARCHAR (150);


SELECT avg(sum(user_id))
FROM t_test_slow_query;

SELECT avg(sum_colu)
FROM (SELECT sum(user_id) AS sum_colu FROM t_test_slow_query) AS t2;


USE test;

#测试1
CREATE TABLE t1 (
  c1 INT
);
CREATE TABLE t2 (
  c1 INT
);

DELIMITER //
CREATE FUNCTION f1(p1 INT)
  RETURNS INT
BEGIN
  INSERT INTO t2 VALUES (p1);
  RETURN p1;
END //
DELIMITER;

SELECT *
FROM t2;

SHOW VARIABLES LIKE '%func%';


SET GLOBAL log_bin_trust_function_creators = 1;


EXPLAIN
SELECT f1(5);

EXPLAIN
SELECT *
FROM t1,
     (SELECT f1(5)) AS tt;

#测试2
DROP TABLE t1, t2;
CREATE TABLE t1 (
  s1 INT
);
INSERT INTO t1
VALUES (1);
CREATE TABLE t2 (
  s1 INT
);
INSERT INTO t2
VALUES (2);
INSERT INTO t2
VALUES (3);


SELECT (SELECT s1 FROM t2)
FROM t1;

SELECT UPPER((SELECT s1 FROM t1))
FROM t2;


USE test;

CREATE TABLE t3 (
  a   INT,
  b   INT,
  KEY idx ( a, b)
);

INSERT INTO t3
VALUES (1, 1);
INSERT INTO t3
VALUES (2, 2);
INSERT INTO t3
VALUES (3, 3);

EXPLAIN
SELECT *
FROM t3
WHERE a >= 1
  AND b < 2;

USE test;
CREATE TABLE t5 (
  a ENUM ('a1', 'a2', 'a3')
);

#按index顺序排序
SELECT *
FROM t5
ORDER BY a DESC;

INSERT INTO t5
VALUES ('3');
INSERT INTO t5
VALUES ('a1');
INSERT INTO t5
VALUES (NULL);

SELECT *
FROM t5
WHERE a = '2';

#显示enum 列
SHOW COLUMNS FROM t5
LIKE 'a';


SELECT JSON_TYPE('"hello"');


CREATE TABLE T6 (
  i INT NOT NULL
);
INSERT INTO T6
VALUES ();
INSERT INTO T6
VALUES (DEFAULT);
INSERT INTO T6
VALUES (DEFAULT (i));


SELECT NULL <=> NULL,
    1 <=> NULL;

SELECT 1 <> 1, NULL <> NULL;

#第一个比较会转换成数值
SELECT '.01' = 0.01, '.01' = '0.01', '.01' <> '0.01';


SELECT 1 IS TRUE,
    0 IS FALSE,
    NULL IS UNKNOWN;
SELECT 1 IS NOT UNKNOWN,
    0 IS NOT UNKNOWN,
    NULL IS NOT UNKNOWN;

#返回第一个非空值
SELECT coalesce(1, NULL, 2);


SELECT isnull(1 / 0);


SELECT INTERVAL(23, 1, 15, 17, 30, 44, 200, 3);


SELECT 1 AND NULL;

SELECT 0 AND NULL;

SELECT NULL AND 0;

SELECT FALSE AND NULL;

SELECT CASE 1
         WHEN 1
                 THEN 'one'
         WHEN 2
                 THEN 'two'
         ELSE 'more' END;

#类似逗号表达式
SELECT IF(1 > 2, 2, 3);

SELECT char_length('fasdf');

SELECT concat('fad', 'abc');

SELECT CONCAT_WS(',', 'First name', NULL, 'Last Name');


SELECT ELT(3, 'ej', 'Heja', 'hej', 'foo');

SELECT FIELD('ej', 'Hej', 'ej', 'Heja', 'hej', 'foo');


SELECT TO_BASE64('abc');


SELECT DAYOFMONTH('2001-11-00'), MONTH('2005-00-00');


SELECT DATE_ADD('2006-05-00', INTERVAL 1 DAY);

SELECT DAYNAME('2006-05-01');


SELECT ADDDATE('2008-01-02', 31);

SELECT DATEDIFF('2010-11-30 23:59:59', '2010-12-31');


SELECT '2008-12-31 23:59:59' + INTERVAL 1 SECOND;

SELECT DATE_ADD('2100-12-31 23:59:59', INTERVAL '1:1' MINUTE_SECOND);

SELECT DATE_SUB('2005-01-01 00:00:00', INTERVAL '1 1:1:1' DAY_SECOND);


SELECT DATE_ADD('2009-01-30', INTERVAL 1 MONTH);

SELECT LAST_DAY('2003-02-05');


SELECT now(6), sleep(2), now(6);


SELECT time_to_sec('23:59:59');

SELECT UTC_TIME();

SELECT UTC_TIMESTAMP();


SELECT CONVERT('test', CHAR CHARACTER SET utf8);
SELECT CAST('test' AS CHAR CHARACTER SET utf8);

#相等
SELECT 'a' = 'A';
#不等
SELECT BINARY 'a' = 'A';


SELECT charset('abc');


SELECT charset(convert('abc' USING utf8));


SELECT user();

SELECT current_user();

SELECT database();


SELECT last_insert_id();

SHOW CREATE TABLE t5;


SELECT time_to_sec(now());

#批量插入示例1
INSERT INTO test.t1
SELECT *
FROM test.t1;

#一致性锁定读 加排它锁X
SELECT *
FROM test.t1 FOR UPDATE;
#一致性锁定读 加共享锁S
SELECT *
FROM test.t1 LOCK IN SHARE MODE;

SET autocommit = 0;
START TRANSACTION;
INSERT INTO test.t3
VALUES (6, 6);
# SELECT *
  FROM test.t3
  WHERE a < 3 FOR UPDATE;

SET autocommit = 0;
START TRANSACTION;
INSERT INTO test.t3
VALUES (1, 106);
INSERT INTO test.t3
VALUES (2, 104);
COMMIT;

SELECT *
FROM test.t3;

#查看当前会话commit
SELECT @@autocommit;
#查看当前会话的事务隔离级别
SELECT @@tx_isolation;
#查看全局的事务隔离级别
SELECT @@global.tx_isolation;

DELETE
FROM t3;

SET @@autocommit = 0;

SHOW VARIABLES LIKE 'auto%';

SET autocommit = 0;

SELECT @@version;

ALTER TABLE test.t3
  ADD PRIMARY KEY (b);

ALTER TABLE test.t3
  MODIFY COLUMN b INT NOT NULL PRIMARY KEY;


SHOW CREATE TABLE test.t3;


CREATE DATABASE test;

##打开或关闭通用日志查询 方便定位问题
SHOW VARIABLES LIKE '%general_log%';
SHOW VARIABLES LIKE '%log_output%';

#打开
SET GLOBAL GENERAL_LOG = 'on';
SET GLOBAL LOG_OUTPUT = 'table';
#关闭
SET GLOBAL GENERAL_LOG = 'off';
SET GLOBAL LOG_OUTPUT = 'file';
#查询
SELECT *
FROM mysql.general_log
ORDER BY event_time DESC;

DESC mysql.general_log;


EXPLAIN EXTENDED SELECT *
FROM mysql.general_log
ORDER BY event_time DESC;
SHOW WARNINGS;

#《mysql排错指南》

CREATE DATABASE mysql_book_paicuo_guide;


DROP TABLE IF EXISTS item;

CREATE TABLE item (
  id      INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  example TEXT
);


CREATE TABLE item_links (
  iid    INT NULL,
  linkid INT NULL,
  KEY    key_iid (iid)
);

INSERT INTO test.item (test.item.example)
VALUES ('luxun');

INSERT INTO test.item_links
VALUES (3, 111);

SELECT count(*)
FROM test.item;

#此处有一个错误，item_links表没有id字段，这里会使用 依赖子查询
SELECT count(*)
FROM item
WHERE id IN (SELECT id FROM item_links);
EXPLAIN EXTENDED SELECT count(*)
FROM item
WHERE id IN (SELECT id
FROM item_links);
SHOW WARNINGS;

CREATE TEMPORARY TABLE t1 (
f1 INT
);
CREATE TEMPORARY TABLE t2 (
f2 INT
);

INSERT INTO t1
VALUES (1);

SELECT *
FROM t1;


DELETE
FROM t1, t2 USING t1, t2;

EXPLAIN EXTENDED SELECT *
FROM t1, t2;


SHOW GLOBAL STATUS LIKE 'uptime';

#用户权限相关

SELECT user(), current_user();
#授权
GRANT ALL ON *.* TO root@ '%';
#撤销权限
REVOKE ALL ON *.* FROM root@ '%';

FLUSH PRIVILEGES;

SELECT *
FROM mysql.user;


GRANT ALL ON *.* TO luxun@ '%';

SELECT user, host
FROM mysql.user
WHERE user = 'luxun'
ORDER BY Host DESC;

#显示用户有的权限
SHOW GRANTS FOR 'luxun'@'%';

#锁表示例
USE test;

DROP TABLE IF EXISTS test.t;

CREATE TABLE t (
  id    INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  value VARCHAR(100) NULL
);

INSERT INTO test.t (value)
VALUES ('luxun');

LOCK TABLES test.t READ;

SELECT *
FROM test.t;
UNLOCK TABLES;

UPDATE test . t
SET value = sleep(100)
WHERE id = 4;

SHOW PROCESSLIST;


SHOW CREATE TABLE t;


SELECT *
FROM information_schema.PROCESSLIST;

#INNODB 状态监控器 排错时候很常用的工具！！！
SHOW ENGINE innodb STATUS;

# INNODB 事务 锁 三剑客 排错工具！！！
SELECT *
FROM information_schema.INNODB_LOCKS;

SELECT *
FROM information_schema.INNODB_LOCK_WAITS;

SELECT *
FROM information_schema.INNODB_TRX;

#注意INNODB是行级锁，两个事务只有对同一行数据并发访问时，才会产生锁等待


SHOW ENGINES;


SHOW VARIABLES LIKE '%sql_mode%';


SELECT @@sql_mode;

#查看字符集相关变量
SHOW VARIABLES LIKE '%char%';
#查看排序相关变量
SHOW VARIABLES LIKE '%coll%';

SHOW SLAVE STATUS;

#创建一个和原来表一样的表
CREATE TABLE test.t_bak LIKE test.t;

SHOW CREATE TABLE test.t_bak;
#新创建的表插入原来表的数据
INSERT INTO test.t_bak (test.t_bak.value) (SELECT ` value ` FROM test.t);

#自增加的列用NULL表示
INSERT INTO t_bak
VALUES (NULL, 'luxun456');

SELECT *
FROM test.t_bak;

#表上的索引 其中
SHOW INDEX FROM t_bak;

#可以重新统计Cardinality
ANALYZE TABLE test.t_bak;

#查看latch
SHOW ENGINE innodb MUTEX;

SHOW VARIABLES LIKE '%version%';

#分布式事务是否打开
SHOW VARIABLES LIKE 'innodb_support_xa';


DROP TABLE IF EXISTS t_user;


CREATE TABLE test.t_user (
  id          BIGINT   NOT NULL AUTO_INCREMENT PRIMARY KEY
  COMMENT '自增主键',
  name        VARCHAR(100) DEFAULT NULL
  COMMENT '姓名',
  password    VARCHAR(100) DEFAULT NULL
  COMMENT '密码',
  create_time DATETIME NOT NULL
  COMMENT '创建时间',
  modify_time DATETIME NOT NULL
  COMMENT '修改时间',
  remained    INT          DEFAULT NULL
  COMMENT '剩余',
  version     INT          DEFAULT NULL
  COMMENT '系统版本号'
)
COMMENT '用户表';

SHOW CREATE TABLE test.t_user;

######################### test库 end !!! #####################


######################### 事务测试大全 start #########################

#查看当前会话的事务隔离级别
SELECT @@tx_isolation;
#查看全局的事务隔离级别
SELECT @@global.tx_isolation;

#查看全局配置 autocommit
SELECT @@global.autocommit;
#查看当前会话 autocommit
SELECT @@autocommit;


CREATE TABLE test.t_trx_test (
  id       BIGINT NOT NULL PRIMARY KEY,
  name     VARCHAR(100) DEFAULT NULL,
  age      INT          DEFAULT NULL,
  password VARCHAR(100) DEFAULT NULL
);

SHOW CREATE TABLE test.t_trx_test;

SELECT *
FROM test.t_trx_test;

INSERT INTO test.t_trx_test (id, name, age, password)
VALUES (1, 'luxun', 20, '123456');

INSERT INTO test.t_trx_test (id, name, age, password)
VALUES (3, 'luxun222', 30, '123456');

INSERT INTO test.t_trx_test (id, name, age, password)
VALUES (5, 'luxun333', 40, '123456');


INSERT INTO test.t_trx_test (id, name, age, password)
VALUES (6, 'luxun666', 40, '123456');

INSERT INTO test.t_trx_test (id, name, age, password)
VALUES (7, 'luxun666', 40, '123456');


INSERT INTO test.t_trx_test (id, name, age, password)
VALUES (8, 'luxun666', 40, '123456');

###【1】脏读示例

#会话1

SET autocommit = 0;

SHOW SESSION VARIABLES LIKE 'auto%';

SHOW GLOBAL VARIABLES LIKE 'auto%';


SET TX_ISOLATION = ''


######################### 事务测试大全 end #########################


