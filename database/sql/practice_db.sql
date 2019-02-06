DROP DATABASE IF EXISTS `practice_db`;
CREATE DATABASE `practice_db`;
USE `practice_db`;


CREATE TABLE subject (
  id  INT(11)  NOT NULL  AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,

  PRIMARY KEY (id),
  KEY k_name (name)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE school (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  year INT(11) NOT NULL,

  PRIMARY KEY (id),
  KEY k_name (name),
  KEY k_year (year),
  UNIQUE KEY u_name_year (name, year)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `group` (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  room INT(11) NOT NULL,
  school_id INT(11) NOT NULL,

  PRIMARY KEY (id),
  FOREIGN KEY (`school_id`) REFERENCES `school` (id) ON DELETE CASCADE,
  UNIQUE KEY k_name (name),
  KEY k_room (room)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE trainee (
  id INT(11) NOT NULL AUTO_INCREMENT,
  firstName VARCHAR(50) NOT NULL,
  lastName VARCHAR(50) NOT NULL,
  rating INT(5) NOT NULL,
  group_id INT(11),

  PRIMARY KEY (id),
  FOREIGN KEY (`group_id`) REFERENCES `group` (id) ON DELETE SET NULL,
  KEY k_firstName (firstName),
  KEY k_lastName (lastName),
  KEY k_rating (rating)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE subject_group (
  subject_id INT(11) NOT NULL,
  group_id INT(11) NOT NULL,

  FOREIGN KEY (`subject_id`) REFERENCES `subject` (id) ON DELETE CASCADE,
  FOREIGN KEY (`group_id`) REFERENCES `group` (id) ON DELETE CASCADE,
  UNIQUE KEY (subject_id, group_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;