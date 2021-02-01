
CREATE SCHEMA `school_system_db`;

CREATE USER 'school_system'@'localhost' IDENTIFIED BY 'schoolsystem123';
GRANT ALL PRIVILEGES ON `school_system_db`.* TO 'school_system'@'localhost';