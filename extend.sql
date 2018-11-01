CREATE TABLE IF NOT EXISTS `my_curd`.`bc_user` (
  `idbc_user` VARCHAR(128) NOT NULL,
  `bc_username` VARCHAR(128) NOT NULL,
  `bc_usepw` VARCHAR(128) NOT NULL,
  `bc_userrole` VARCHAR(128) NOT NULL DEFAULT '',
  `bc_userleve` INT NOT NULL DEFAULT 1,
  `bc_userdel` TINYINT NOT NULL DEFAULT 0,
  `createtime` DATETIME NOT NULL DEFAULT now(),
  `bc_usertoken` VARCHAR(128) NULL,
  PRIMARY KEY (`idbc_user`),
  UNIQUE INDEX `bc_username_UNIQUE` (`bc_username` ASC))
ENGINE = InnoDB;

ALTER TABLE `my_curd`.`bc_user` 
ADD COLUMN `bc_usermobile` VARCHAR(45) NULL AFTER `bc_usertoken`;
ALTER TABLE `bc_user` 
	ADD COLUMN `bc_alias` varchar(32) NULL DEFAULT '未填写' COMMENT '昵称';
ALTER TABLE `bc_user` 
	ADD COLUMN `bc_avatar` varchar(256) NULL DEFAULT '' COMMENT '头像'

CREATE TABLE IF NOT EXISTS `my_curd`.`bc_barcode` (
  `idbc_barcode` VARCHAR(128) NOT NULL,
  `bc_barcodesn` VARCHAR(128) NOT NULL,
  `bc_barcodename` VARCHAR(128) NOT NULL,
  `bc_barcodelocation` VARCHAR(128) NOT NULL,
  `bc_barcoderole` VARCHAR(128) NOT NULL DEFAULT '',
  `bc_barcodeleve` INT NOT NULL DEFAULT 1,
  `bc_barcodetype` INT NOT NULL DEFAULT 1,
  `bc_barcodedescription` VARCHAR(256) NULL,
  `bc_barcodedel` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`idbc_barcode`),
  UNIQUE INDEX `bc_barcodename_UNIQUE` (`bc_barcodename` ASC),
  UNIQUE INDEX `bc_barcodelocation_UNIQUE` (`bc_barcodelocation` ASC),
  UNIQUE INDEX `bc_barcodesn_UNIQUE` (`bc_barcodesn` ASC))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `my_curd`.`bc_scanlog` (
  `idbc_scanlog` VARCHAR(128) NOT NULL,
  `bc_scanlogcreatetime` DATETIME NOT NULL DEFAULT now(),
  `bc_scanlogstatus` VARCHAR(45) NOT NULL DEFAULT 0,
  `bc_scanlogimg1` VARCHAR(256) NULL,
  `bc_scanlogimg2` VARCHAR(256) NULL,
  `bc_scanlogimg3` VARCHAR(256) NULL,
  `bc_scanlogimg4` VARCHAR(256) NULL,
  `bc_scanlogimg5` VARCHAR(256) NULL,
  `bc_scanlogcontent1` VARCHAR(256) NULL,
  `bc_scanlogcontent2` VARCHAR(256) NULL,
  `bc_scanlogcontent3` VARCHAR(256) NULL,
  `bc_scanlogcontent4` VARCHAR(256) NULL,
  `bc_scanlogcontent5` VARCHAR(256) NULL,
  `bc_user_idbc_user` VARCHAR(128) NOT NULL,
  `bc_barcode_idbc_barcode` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`idbc_scanlog`),
  INDEX `fk_bc_scanlog_bc_user_idx` (`bc_user_idbc_user` ASC),
  INDEX `fk_bc_scanlog_bc_barcode1_idx` (`bc_barcode_idbc_barcode` ASC),
  CONSTRAINT `fk_bc_scanlog_bc_user`
    FOREIGN KEY (`bc_user_idbc_user`)
    REFERENCES `my_curd`.`bc_user` (`idbc_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bc_scanlog_bc_barcode1`
    FOREIGN KEY (`bc_barcode_idbc_barcode`)
    REFERENCES `my_curd`.`bc_barcode` (`idbc_barcode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE `bc_events` (
	`idbc_events` varchar(128) NOT NULL,
	`bc_eventname` varchar(128) NULL,
	`bc_eventlocation` varchar(256) NULL,
	`bc_eventtitle` varchar(128) NULL,
	`bc_eventcontent` varchar(256) NULL,
	`bc_eventf1` varchar(256) NULL,
	`bc_eventf2` varchar(256) NULL,
	`bc_eventf3` varchar(256) NULL,
	`bc_eventf4` varchar(256) NULL,
	`bc_eventf5` varchar(256) NULL,
	`bc_eventdel` tinyint NULL,
	`bc_eventcreatetime` datetime NULL,
	PRIMARY KEY (`idbc_events`)
) ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='重大事件';

CREATE TABLE `bc_app` (
  `idbc_app` varchar(32) DEFAULT NULL,
  `versionName` varchar(32) DEFAULT NULL,
  `versionCode` varchar(32) DEFAULT NULL,
  `downloadUrl` varchar(256) DEFAULT NULL,
  `release` varchar(32) DEFAULT NULL,
  `comment` varchar(32) DEFAULT NULL,
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='app更新下载';

ALTER TABLE `bc_app` 
	MODIFY COLUMN `idbc_app` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL FIRST,
	ADD PRIMARY KEY(`idbc_app`);
