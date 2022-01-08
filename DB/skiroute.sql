-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema skiroutedb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `skiroutedb` ;

-- -----------------------------------------------------
-- Schema skiroutedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `skiroutedb` DEFAULT CHARACTER SET utf8 ;
USE `skiroutedb` ;

-- -----------------------------------------------------
-- Table `peak`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `peak` ;

CREATE TABLE IF NOT EXISTS `peak` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `elevation` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lift`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lift` ;

CREATE TABLE IF NOT EXISTS `lift` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `run_time` VARCHAR(45) NOT NULL,
  `peak_id` INT NOT NULL,
  `carrier_number` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_lift_peak_idx` (`peak_id` ASC),
  CONSTRAINT `fk_lift_peak`
    FOREIGN KEY (`peak_id`)
    REFERENCES `peak` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `route`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `route` ;

CREATE TABLE IF NOT EXISTS `route` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `distance` DECIMAL NOT NULL,
  `level` VARCHAR(45) NULL,
  `lift_id` INT NOT NULL,
  `peak_id` INT NOT NULL,
  `snow_condition` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_route_lift1_idx` (`lift_id` ASC),
  INDEX `fk_route_peak1_idx` (`peak_id` ASC),
  CONSTRAINT `fk_route_lift1`
    FOREIGN KEY (`lift_id`)
    REFERENCES `lift` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_route_peak1`
    FOREIGN KEY (`peak_id`)
    REFERENCES `peak` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `snow_condition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `snow_condition` ;

CREATE TABLE IF NOT EXISTS `snow_condition` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS skiuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'skiuser'@'localhost' IDENTIFIED BY 'skiuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'skiuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `peak`
-- -----------------------------------------------------
START TRANSACTION;
USE `skiroutedb`;
INSERT INTO `peak` (`id`, `name`, `elevation`) VALUES (1, 'Peak 8', '12998 ft');
INSERT INTO `peak` (`id`, `name`, `elevation`) VALUES (2, 'Peak 9', '13195 ft');
INSERT INTO `peak` (`id`, `name`, `elevation`) VALUES (3, 'Peak 10', '13640 ft');

COMMIT;


-- -----------------------------------------------------
-- Data for table `lift`
-- -----------------------------------------------------
START TRANSACTION;
USE `skiroutedb`;
INSERT INTO `lift` (`id`, `name`, `run_time`, `peak_id`, `carrier_number`) VALUES (1, 'Peak 8 SuperConnect', '15 mins', 1, 4);
INSERT INTO `lift` (`id`, `name`, `run_time`, `peak_id`, `carrier_number`) VALUES (2, 'Snowflake', '10 mins', 1, 4);
INSERT INTO `lift` (`id`, `name`, `run_time`, `peak_id`, `carrier_number`) VALUES (3, 'Mecury SuperChair', '20 mins', 2, 6);
INSERT INTO `lift` (`id`, `name`, `run_time`, `peak_id`, `carrier_number`) VALUES (4, 'E-Chair', '8 mins', 2, 4);
INSERT INTO `lift` (`id`, `name`, `run_time`, `peak_id`, `carrier_number`) VALUES (5, 'Falcon Superchair', '10 mins', 3, 6);
INSERT INTO `lift` (`id`, `name`, `run_time`, `peak_id`, `carrier_number`) VALUES (6, 'Zendo Chair', '9 mins', 3, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `route`
-- -----------------------------------------------------
START TRANSACTION;
USE `skiroutedb`;
INSERT INTO `route` (`id`, `name`, `distance`, `level`, `lift_id`, `peak_id`, `snow_condition`) VALUES (1, 'Last Hoot', 1, 'Green', 1, 1, 'Icy');
INSERT INTO `route` (`id`, `name`, `distance`, `level`, `lift_id`, `peak_id`, `snow_condition`) VALUES (2, 'Dyersville', 2 , 'Blue', 1, 1, 'Icy');
INSERT INTO `route` (`id`, `name`, `distance`, `level`, `lift_id`, `peak_id`, `snow_condition`) VALUES (3, 'Springmeier', 1.5 , 'Blue', 1, 1, 'Groomed');
INSERT INTO `route` (`id`, `name`, `distance`, `level`, `lift_id`, `peak_id`, `snow_condition`) VALUES (4, 'Eldorado', 1 , 'Green', 2, 1, 'Groomed');
INSERT INTO `route` (`id`, `name`, `distance`, `level`, `lift_id`, `peak_id`, `snow_condition`) VALUES (5, 'Sundown', 0.8, 'Blue', 2, 1, 'Powder');
INSERT INTO `route` (`id`, `name`, `distance`, `level`, `lift_id`, `peak_id`, `snow_condition`) VALUES (6, 'Mine Shaft', 0.9, 'Expert', 2, 1, 'Moguls');
INSERT INTO `route` (`id`, `name`, `distance`, `level`, `lift_id`, `peak_id`, `snow_condition`) VALUES (7, 'Freeway', 1.6, 'Blue', 3, 2, 'Icy');
INSERT INTO `route` (`id`, `name`, `distance`, `level`, `lift_id`, `peak_id`, `snow_condition`) VALUES (8, 'Easy Street', 0.4, 'Black', 3, 2, 'Groomed');
INSERT INTO `route` (`id`, `name`, `distance`, `level`, `lift_id`, `peak_id`, `snow_condition`) VALUES (9, '9 Lives', 0.2, 'Expert', 3, 2, 'Moguls');
INSERT INTO `route` (`id`, `name`, `distance`, `level`, `lift_id`, `peak_id`, `snow_condition`) VALUES (10, 'Frontier', 2.3 , 'Green', 4, 2, 'Powder');
INSERT INTO `route` (`id`, `name`, `distance`, `level`, `lift_id`, `peak_id`, `snow_condition`) VALUES (11, 'Sawmill', 3.5 , 'Green', 4, 2, 'Icy');
INSERT INTO `route` (`id`, `name`, `distance`, `level`, `lift_id`, `peak_id`, `snow_condition`) VALUES (12, 'Pioneer', 2 , 'Blue', 4, 2, 'Icy');
INSERT INTO `route` (`id`, `name`, `distance`, `level`, `lift_id`, `peak_id`, `snow_condition`) VALUES (13, 'Ore Bucket', 0.4, 'Black', 5, 3, 'Powder');
INSERT INTO `route` (`id`, `name`, `distance`, `level`, `lift_id`, `peak_id`, `snow_condition`) VALUES (14, 'Lincoln Meadows', 1.8 , 'Blue', 5, 3, 'Powder');
INSERT INTO `route` (`id`, `name`, `distance`, `level`, `lift_id`, `peak_id`, `snow_condition`) VALUES (15, 'Wirepath', 1.9 , 'Blue', 5, 3, 'Moguls');
INSERT INTO `route` (`id`, `name`, `distance`, `level`, `lift_id`, `peak_id`, `snow_condition`) VALUES (16, 'Boundary Chutes', 0.7, 'Expert', 6, 3, 'Slash');
INSERT INTO `route` (`id`, `name`, `distance`, `level`, `lift_id`, `peak_id`, `snow_condition`) VALUES (17, 'Queen', 0.5, 'Expert', 6, 3, 'Slash');
INSERT INTO `route` (`id`, `name`, `distance`, `level`, `lift_id`, `peak_id`, `snow_condition`) VALUES (18, 'White Crown', 1, 'Black', 6, 3, 'Icy');

COMMIT;


-- -----------------------------------------------------
-- Data for table `snow_condition`
-- -----------------------------------------------------
START TRANSACTION;
USE `skiroutedb`;
INSERT INTO `snow_condition` (`id`, `title`) VALUES (1, 'Icy');
INSERT INTO `snow_condition` (`id`, `title`) VALUES (2, 'Groomed');
INSERT INTO `snow_condition` (`id`, `title`) VALUES (3, 'Moguls');
INSERT INTO `snow_condition` (`id`, `title`) VALUES (4, 'Slash');
INSERT INTO `snow_condition` (`id`, `title`) VALUES (5, 'Powder');

COMMIT;

