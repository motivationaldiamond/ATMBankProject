-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema DiamondBankDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `DiamondBankDB` ;

-- -----------------------------------------------------
-- Schema DiamondBankDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `DiamondBankDB` DEFAULT CHARACTER SET DEFAULT ;
USE `DiamondBankDB` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NULL,
  `last_name` VARCHAR(100) NULL,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(200) NULL,
  `email` VARCHAR(200) NULL,
  `phone_number` VARCHAR(45) NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `create_date` DATETIME NULL,
  `last_update` TIMESTAMP NULL,
  `address` VARCHAR(500) NULL,
  `address2` VARCHAR(500) NULL,
  `city` VARCHAR(100) NULL,
  `state` VARCHAR(100) NULL,
  `zipcode` VARCHAR(45) NULL,
  `role` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `account` ;

CREATE TABLE IF NOT EXISTS `account` (
  `account_number` CHAR(8) NOT NULL,
  `pin` CHAR(4) NOT NULL,
  `balance` DECIMAL(12,2) NULL,
  `account_type` VARCHAR(45) NULL,
  `transaction_limit` VARCHAR(45) NULL,
  `account_status` VARCHAR(45) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`account_number`, `user_id`),
  UNIQUE INDEX `account_number_UNIQUE` (`account_number` ASC),
  INDEX `fk_account_customer_idx` (`user_id` ASC),
  CONSTRAINT `fk_account_customer`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = DEFAULT
PACK_KEYS = Default;


-- -----------------------------------------------------
-- Table `transaction_record`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `transaction_record` ;

CREATE TABLE IF NOT EXISTS `transaction_record` (
  `id` INT NOT NULL,
  `transaction_type` VARCHAR(100) NULL,
  `amount` DECIMAL(12,2) NULL,
  `timestamp` TIMESTAMP NULL,
  `transaction_status` VARCHAR(45) NULL,
  `account_number` CHAR(8) NOT NULL,
  PRIMARY KEY (`id`, `account_number`),
  INDEX `fk_transaction_account1_idx` (`account_number` ASC),
  CONSTRAINT `fk_transaction_account1`
    FOREIGN KEY (`account_number`)
    REFERENCES `account` (`account_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = DEFAULT;

SET SQL_MODE = '';
DROP USER IF EXISTS banker@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'banker'@'localhost' IDENTIFIED BY 'banker';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'banker'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `DiamondBankDB`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `phone_number`, `enabled`, `create_date`, `last_update`, `address`, `address2`, `city`, `state`, `zipcode`, `role`) VALUES (1, 'test', 'test', 'test', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'test@test.com', '000-000-0000', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

COMMIT;

