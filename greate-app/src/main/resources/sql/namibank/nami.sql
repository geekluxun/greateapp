######################### base库 start !!! #####################
DESC lo_loan_order;
DESC t_customer_bankcard;
DESC t_user_shares;
DESC t_user_customer_ykqj;
# 一刻千金用户与客户关联表, 多对一关系
DESC t_user_ykqj_extend;
# 一刻千金的用户表！！！
DESC t_user_registry_log;
DESC t_mer_branch_person;
DESC t_sms_template;
# 短信模板
DESC t_user_registry_log;
DESC t_customer_sub_account;
# 会员子账户
DESC t_user_device;
# 用户设备表 注册时候插入
DESC t_account_application;
# 账户表
DESC t_customer_risk_log;
# 风控日志表
DESC t_customer_bankcard;
# 银行卡
DESC t_customer_ykqj;
# 这个是真正的一刻千金的客户表!!!
DESC lo_loan_order;
# 交易系统的订单表
DESC lo_credit_record;
# 信用卡授信记录表
DESC lo_preauth_flow;
# 预授权流水
DESC t_notify_push;
# 站内信
DESC t_notify_unicast;
DESC t_notify_custom_template;
# 短信模板
DESC t_notify_custom;
DESC lo_repay_info;
# 还款信息总表
DESC lo_repay_plan_exe;
# 还款计划执行表
DESC lo_loan_refund;
# 退款订单
DESC t_code_library;
DESC t_partner_account;
DESC t_gate_bank_bin;
# 银行卡bin表

#交易系统
DESC lo_pay_record;
# 放款和扣款记录表
DESC lo_preauth_info;
DESC lo_receivable_record;
# 应收款记录表
DESC lo_sales_strategy;
DESC lo_trans_limit;
DESC lo_repay_late_detail;
# 逾期详情
DESC lo_loan_overdue_record;
# 逾期记录
DESC lo_repay_app_detail;
# 还款
DESC lo_sms_notify;
DESC lo_verification;

#账户系统
DESC t_customer_account_trans;

#采购平台相关表
DESC t_elect_req_order_bak;
DESC t_elect_skus;
DESC t_elect_operation;
DESC t_elect_req_order;
DESC t_elect_req_orderInfo;
DESC t_elect_balance_detail;
DESC t_elect_res_order;
DESC t_base_area;
DESC t_elect_order_return;

USE base;

#count 和 join 性能 比较
USE base;
EXPLAIN
SELECT count(u.user_id)
FROM t_user_ykqj_extend u
       LEFT JOIN t_user_customer_ykqj q ON u.user_id = q.user_id
       LEFT JOIN t_customer_ykqj y ON q.customer_id = y.id
WHERE y.phone = 13917621838;

#慢查询？？？
EXPLAIN
SELECT COUNT(*)
FROM t_user_registry_log a
       INNER JOIN t_user_customer_ykqj b ON a.user_id = b.user_id
WHERE a.prod_code = #{prodCode, jdbcType= VARCHAR}
AND a.invitation_code = #{invitationCode, jdbcType= VARCHAR}
AND a.channel IN ('2017ykqj@0913')
AND DATE_FORMAT (a.create_time, '%Y-%m') = DATE_FORMAT (NOW(), '%Y-%m')
AND EXISTS (SELECT 1
FROM t_customer_sub_account c
WHERE b.customer_id = c.user_id AND c.product_code = 'YKQJ');


SELECT req_eoid, req_orderId, prodOrderId, createTime, updateTime, result
FROM t_elect_req_order_bak
WHERE result LIKE '%重复下单%'
ORDER BY createTime DESC;


SELECT *
FROM t_elect_req_order
WHERE prodOrderId = 201709281166784
ORDER BY createTime DESC;

SELECT *
FROM t_elect_req_order
WHERE prodOrderId = 201709281166784;


SELECT *
FROM t_elect_req_order
WHERE req_orderId = '201709281166784';

USE base;
USE mall;


SELECT *
FROM xx_order
WHERE sn = '201709281166784';

######################### base库 end !!! #####################


######################### mall库 start !!! #####################
USE mall;
SHOW CREATE TABLE t_audit_order;

######################### mall库 end !!! #####################