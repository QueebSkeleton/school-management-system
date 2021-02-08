
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema school_system_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `school_system_db` ;

-- -----------------------------------------------------
-- Schema school_system_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `school_system_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `school_system_db` ;

-- -----------------------------------------------------
-- Table `school_system_db`.`instructor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `school_system_db`.`instructor` ;

CREATE TABLE IF NOT EXISTS `school_system_db`.`instructor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `email_address` VARCHAR(200) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `school_system_db`.`subject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `school_system_db`.`subject` ;

CREATE TABLE IF NOT EXISTS `school_system_db`.`subject` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `code` VARCHAR(45) NOT NULL,
  `instructor_id` INT NULL DEFAULT NULL,
  `description` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `instructor_id` (`instructor_id` ASC) VISIBLE,
  CONSTRAINT `subject_ibfk_1`
    FOREIGN KEY (`instructor_id`)
    REFERENCES `school_system_db`.`instructor` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `school_system_db`.`subject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `school_system_db`.`activity` ;

CREATE TABLE `school_system_db`.`activity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `subject_id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `created_on` datetime NOT NULL,
  `deadline` datetime NOT NULL,
  `total_score` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `subject_id` (`subject_id`),
  CONSTRAINT `activity_ibfk_1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- -----------------------------------------------------
-- User 'school_system'@'localhost'
-- -----------------------------------------------------

DROP USER 'school_system'@'localhost';
CREATE USER 'school_system'@'localhost' IDENTIFIED BY 'schoolsystem123';
GRANT ALL PRIVILEGES ON `school_system_db`.* TO 'school_system'@'localhost';