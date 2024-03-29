CREATE TABLE IF NOT EXISTS `USER_ORDER` (
  `PK_USER_ID` BIGINT NOT NULL AUTO_INCREMENT,
  `PERSON_ID` BIGINT NULL,
  `USER_NAME` VARCHAR(45) NULL,
  `STATUS` INT NULL,
  `SITE_ID` INT NULL,
  `EMAIL` VARCHAR(45) NULL,
  `PHONE_NUMBER` VARCHAR(45) NULL,
  `NICK_NAME` VARCHAR(45) NULL,
  PRIMARY KEY (`PK_USER_ID`),
  UNIQUE INDEX `USER_NAME_UNIQUE` (`USER_NAME` ASC)
  );
