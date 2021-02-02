
CREATE SCHEMA `school_system_db`;

CREATE USER 'school_system'@'localhost' IDENTIFIED BY 'schoolsystem123';
GRANT ALL PRIVILEGES ON `school_system_db`.* TO 'school_system'@'localhost';

CREATE TABLE `school_system_db`.`instructor` (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(200) NOT NULL,
	email_address VARCHAR(200) NOT NULL,
	password VARCHAR(20) NOT NULL,
	PRIMARY KEY(id)
);