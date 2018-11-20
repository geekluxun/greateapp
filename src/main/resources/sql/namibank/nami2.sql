DELETE
FROM t_cash_order
WHERE apply_id IN (SELECT tca.apply_id
                   FROM t_cash_apply tca
                   WHERE tca.mobile_no = '13817806136'
                     AND tca.prod_code = 'QYL');

DELIMITER;
;
ALTER TABLE `base`.`t_mer_branch_person`
ADD UNIQUE INDEX `invitation_code_UNIQUE` (`invitation_code` ASC);


CREATE DEFINER =`base`@`%` FUNCTION `generateInvitationCode`()
RETURNS VARCHAR (10)
CHARSET utf8mb4
BEGIN
  DECLARE i_code    VARCHAR(10);
    DECLARE i_count INT;

    SET             i_code = concat ('CXY', floor(100000 + rand() * (999999 - 100000 + 1)));

    SELECT count(*)
    INTO i_count
    FROM t_mer_branch_person
    WHERE invitation_code = i_code;

    WHILE i_count > 0 DO
    SELECT count(*)
    INTO i_count
    FROM t_mer_branch_person
    WHERE invitation_code = i_code;
END WHILE;

RETURN i_code;
END
;
;
DELIMITER;


ALTER TABLE t_risk_application
  ADD invitation_code VARCHAR(1000)
AFTER tokenid;


ALTER TABLE `t_risk_application`
ADD UNIQUE (`taid`);


SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS `t_gate_bank_bin`;


ALTER TABLE t_cash_plan
  DROP COLUMN paid_up_periods;


ALTER TABLE t_risk_application
  ADD INDEX index_t_risk_apply_mobileNo(mobile_no);

ALTER TABLE t_redpacket_config
ALTER COLUMN red_money SET DEFAULT 0

ALTER TABLE t_cred_operator
MODIFY COLUMN `ext3` VARCHAR (45) DEFAULT NULL
COMMENT '预留字段';

#表加注释
ALTER TABLE t_cash_apply
COMMENT '现金贷申请信息';

UPDATE t_cash_plan p INNER JOIN t_cash_order o ON p.txn_id = o.txn_id
SET p.start_time = o.lend_time
WHERE o.`status` IN (02, 10) AND o.prod_code = 'QYL';

#数据迁移
INSERT INTO `t_gate_payments` (
    `pay_txn_id`, `batch_id`, `pay_time`,
    `biz_order_id`, `query_id`, `product_code`,
    `business_code`, `currency_code`, `currency_name`,
    `pay_channel_code`, `pay_amt`, `bank_card`,
    `bank_user_name`, `bank_user_id`, `bank_phone`,
    `bank_type`, `bank_name`, `bank_code`,
    `pay_status`, `pay_res_code`, `pay_res_des`,
    ` versions `, `clear_date`, `create_time`, `update_time`)
SELECT pay_txn_id,
       batch_id,
       pay_time,
       biz_order_id,
       query_id,
       product_code,
       business_code,
       currency_code,
       currency_name,
       pay_channel_code,
       pay_amt,
       bank_card,
    `bank_user_name`,
    `bank_user_id`,
    `bank_phone`,
    `bank_type`,
    `bank_name`,
    `bank_code`,
    `pay_status`,
    `pay_res_code`,
    `pay_res_des`,
    '1',
    `clear_date`,
    `create_time`,
    `update_time` FROM t_gate_pay_txn a WHERE a.pay_time <= '20161208235959' AND a.trans_type = '52';

#btree
ALTER TABLE `t_gate_realcheck`
ADD INDEX `IDX_realcheck_bank_card` (bank_card) USING BTREE COMMENT '银行卡号';


LOCK TABLES `t_elect_sn_area` WRITE;
UNLOCK TABLES;


ALTER TABLE `ac_coupon_issue`
DROP INDEX `idx_cci_object_type_ref`,
ADD UNIQUE INDEX `idx_cci_obj_type_ref_conf_ref` (`object_type`, `object_ref`, `coupon_config_ref`) USING BTREE COMMENT '关联类型和编号组合和优惠券配置编号';


UPDATE t_qyl_order q INNER JOIN (SELECT loan_order_id AS loan_order_ref,
    '10' AS STATUS,
    update_time,
    b.versions FROM lo_loan_order b WHERE b.business_code = 'QYL' AND b.loan_status = '01' AND EXISTS (SELECT 1 FROM lo_repay_info a WHERE a.`status` = '11' AND a.business_code = 'QYL' AND a.loan_order_ref = b.loan_order_id))
    de ON q.loan_order_ref = de.loan_order_ref
SET q.loan_order_ref = de.loan_order_ref, q.status = de.status, q.update_time = de.update_time,
q.versions       = de.versions;


CREATE TABLE t_user_contacts_qyl LIKE t_user_contacts;
INSERT INTO t_user_contacts_qyl
SELECT *
FROM t_user_contacts;


RENAME TABLE
  t_user_user TO t_user_user_qyl;

#原有表中有重复数据
ALTER IGNORE TABLE t_risk_blacklist
ADD UNIQUE INDEX unique_index_bl_no(bl_type, bl_no, prod_code);
