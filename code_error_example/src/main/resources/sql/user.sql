CREATE TABLE IF NOT EXISTS `fantasybaby_dev_error`.`user`
(
    `id`           BIGINT       NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `name`         VARCHAR(45)  NULL COMMENT '组号',
    `nick_name`    VARCHAR(100) NULL COMMENT '集合单类型',
    `age`          INT(11)      null COMMENT '版本号',
    `created_date` DATETIME(3)  NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
)
    ENGINE = INNODB
    DEFAULT CHARACTER SET = utf8
    COMMENT '用户';


CREATE TABLE IF NOT EXISTS `fantasybaby_dev_error`.`user_score`
(
    `id`    BIGINT NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `score` BIGINT NULL COMMENT '分数',
    PRIMARY KEY (`id`)
)
    ENGINE = INNODB
    DEFAULT CHARACTER SET = utf8
    COMMENT '用户分数';



CREATE TABLE IF NOT EXISTS `fantasybaby_dev_error`.`user_entity`
(
    `id`   BIGINT      NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `name` VARCHAR(45) NULL COMMENT '组号',
        PRIMARY KEY (`id`)
)
    ENGINE = INNODB
    DEFAULT CHARACTER SET = utf8
    COMMENT '用户实体';

