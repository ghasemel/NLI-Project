-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.28-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5278
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for nli_sa
CREATE DATABASE IF NOT EXISTS `nli_sa` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `nli_sa`;

-- Dumping structure for table nli_sa.attribute
CREATE TABLE IF NOT EXISTS `attribute` (
  `name` varchar(50) NOT NULL,
  `keyword` varchar(1000) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table nli_sa.attribute: ~22 rows (approximately)
/*!40000 ALTER TABLE `attribute` DISABLE KEYS */;
INSERT INTO `attribute` (`name`, `keyword`) VALUES
	('classroom', ',classroom,'),
	('classtime', ',time,'),
	('content', ',content,'),
	('course', ',course,curse,subject,'),
	('credit', ',credit,'),
	('degree', ',degree,type,'),
	('director', ',director,'),
	('duration', ',duration,length,'),
	('email', ',email,mail,'),
	('end', ',end,close,'),
	('language', ',language,'),
	('location', ',locate,place,where,locates,'),
	('name', ',name,'),
	('officelocation', ',location,office,where,'),
	('person_name', ',name,call,who,'),
	('price', ',price,money,tuition,fee,'),
	('programme', ',master,programme,program,'),
	('services', ',service,'),
	('start', ',start,open,'),
	('summary', ',summary,'),
	('syllabus', ',syllabus,'),
	('teacher', ',teacher,who,teach,');
/*!40000 ALTER TABLE `attribute` ENABLE KEYS */;

-- Dumping structure for table nli_sa.course
CREATE TABLE IF NOT EXISTS `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classroom` varchar(20) NOT NULL,
  `classtime` varchar(100) NOT NULL,
  `credit` int(11) NOT NULL,
  `end` varchar(100) NOT NULL,
  `language` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `start` varchar(100) NOT NULL,
  `summary` varchar(1000) NOT NULL,
  `syllabus` varchar(300) DEFAULT NULL,
  `frame_name` varchar(30) DEFAULT NULL,
  `programme_id` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6tux2e87xdhgxp5hstvoq4x20` (`frame_name`),
  KEY `FKj0yhei2qlrvbwfa2atgvgp8ks` (`programme_id`),
  KEY `FKsybhlxoejr4j3teomm5u2bx1n` (`teacher_id`),
  CONSTRAINT `FK6tux2e87xdhgxp5hstvoq4x20` FOREIGN KEY (`frame_name`) REFERENCES `frame` (`name`),
  CONSTRAINT `FKj0yhei2qlrvbwfa2atgvgp8ks` FOREIGN KEY (`programme_id`) REFERENCES `programme` (`id`),
  CONSTRAINT `FKsybhlxoejr4j3teomm5u2bx1n` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table nli_sa.course: ~5 rows (approximately)
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` (`id`, `classroom`, `classtime`, `credit`, `end`, `language`, `name`, `start`, `summary`, `syllabus`, `frame_name`, `programme_id`, `teacher_id`) VALUES
	(1, '32213', '3 o clock', 5, 'twenty second of march', 'english', 'research methodologies', 'twenty first of september', 'This course covers the major considerations and tasks involved in conducting scientific research, with special emphasis in those aspects related to the context of Information and Communication Technologies.', 'How to Get a PhD from Derek Pugh and Robert Williams', 'AskForCourse', 1, 1),
	(2, '32213', 'six oclock', 6, 'first of may', 'english', 'natural language interaction', 'twenty first of march', 'The couse covers the central themes involved in the interaction with intelligent agents through the use of natural language, with emphasis on dialogue and language generation.', 'How to Get a PhD from Derek Pugh and Robert Williams', 'AskForCourse', 1, 3),
	(3, '32213', 'half past 3', 6, 'twenty second of may', 'english', 'machine learning', 'twenty second of september', 'This course covers the major considerations and tasks involved in conducting scientific research, with special emphasis in those aspects related to the context of Information and Communication Technologies.', 'How to Get a PhD from Derek Pugh and Robert Williams', 'AskForCourse', 1, 2),
	(4, '32213', 'two twenty', 6, 'twenty first of march', 'english', 'web intelligence', 'twenty third of september', 'This course covers the major considerations and tasks involved in conducting scientific research, with special emphasis in those aspects related to the context of Information and Communication Technologies.', 'How to Get a PhD from Derek Pugh and Robert Williams', 'AskForCourse', 2, 1),
	(5, '32213', 'four o clock', 6, 'twenty first of september', 'english', 'computer vision', 'first of february', 'This course covers the major considerations and tasks involved in conducting scientific research, with special emphasis in those aspects related to the context of Information and Communication Technologies.', 'How to Get a PhD from Derek Pugh and Robert Williams', 'AskForCourse', 2, 2);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;

-- Dumping structure for table nli_sa.error
CREATE TABLE IF NOT EXISTS `error` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(50) DEFAULT NULL,
  `sentence` varchar(200) DEFAULT NULL,
  `attribute_name` varchar(50) DEFAULT NULL,
  `frame_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqdefjw7be30u74vucqybhngky` (`attribute_name`),
  KEY `FK9wqykxx6k3iqe50m92d2rxhrv` (`frame_name`),
  CONSTRAINT `FK9wqykxx6k3iqe50m92d2rxhrv` FOREIGN KEY (`frame_name`) REFERENCES `frame` (`name`),
  CONSTRAINT `FKqdefjw7be30u74vucqybhngky` FOREIGN KEY (`attribute_name`) REFERENCES `attribute` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Dumping data for table nli_sa.error: ~6 rows (approximately)
/*!40000 ALTER TABLE `error` DISABLE KEYS */;
INSERT INTO `error` (`id`, `keyword`, `sentence`, `attribute_name`, `frame_name`) VALUES
	(1, 'NO_FRAME', 'sorry, could you repeat the question?', NULL, NULL),
	(2, 'NO_QUESTION', 'sorry, what are you asking for?', NULL, NULL),
	(3, 'NO_INFO', 'sorry, could you give me more information?', NULL, NULL),
	(4, 'NO_COURSE_INFO', 'sorry, there is not a course called like this', NULL, NULL),
	(5, 'NO_TEACHER_INFO', 'sorry, there is none teacher named like this', NULL, NULL),
	(6, 'NO_PROGRAMME_INFO', 'sorry, there is not a programme called like this', NULL, NULL),
	(7, 'NO_LIBRARY_INFO', 'sorry, which library are you talking about?', NULL, NULL);
/*!40000 ALTER TABLE `error` ENABLE KEYS */;

-- Dumping structure for table nli_sa.frame
CREATE TABLE IF NOT EXISTS `frame` (
  `name` varchar(30) NOT NULL,
  `keyword` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table nli_sa.frame: ~4 rows (approximately)
/*!40000 ALTER TABLE `frame` DISABLE KEYS */;
INSERT INTO `frame` (`name`, `keyword`) VALUES
	('AskForCourse', ',course,'),
	('AskForLibrary', ',library,'),
	('AskForProgramme', ',programme,master,program,'),
	('AskForTeacher', ',professor,lecture,');
/*!40000 ALTER TABLE `frame` ENABLE KEYS */;

-- Dumping structure for table nli_sa.frame_attribute
CREATE TABLE IF NOT EXISTS `frame_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `frame_name` varchar(30) DEFAULT NULL,
  `attribute_name` varchar(50) DEFAULT NULL,
  `search_for_value` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiupnk8r5rj6upm4l9wsecqy45` (`attribute_name`),
  KEY `FKkmx6ciq64bj82p4193y6kh3mp` (`frame_name`),
  CONSTRAINT `FKiupnk8r5rj6upm4l9wsecqy45` FOREIGN KEY (`attribute_name`) REFERENCES `attribute` (`name`),
  CONSTRAINT `FKkmx6ciq64bj82p4193y6kh3mp` FOREIGN KEY (`frame_name`) REFERENCES `frame` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

-- Dumping data for table nli_sa.frame_attribute: ~28 rows (approximately)
/*!40000 ALTER TABLE `frame_attribute` DISABLE KEYS */;
INSERT INTO `frame_attribute` (`id`, `frame_name`, `attribute_name`, `search_for_value`) VALUES
	(1, 'AskForCourse', 'classroom', b'0'),
	(3, 'AskForCourse', 'classtime', b'0'),
	(4, 'AskForCourse', 'credit', b'0'),
	(5, 'AskForCourse', 'end', b'0'),
	(6, 'AskForCourse', 'language', b'0'),
	(7, 'AskForCourse', 'location', b'0'),
	(8, 'AskForCourse', 'name', b'1'),
	(13, 'AskForCourse', 'programme', b'1'),
	(14, 'AskForCourse', 'start', b'0'),
	(15, 'AskForCourse', 'summary', b'0'),
	(16, 'AskForCourse', 'syllabus', b'0'),
	(17, 'AskForCourse', 'teacher', b'0'),
	(18, 'AskForLibrary', 'end', b'0'),
	(19, 'AskForLibrary', 'start', b'0'),
	(20, 'AskForLibrary', 'services', b'0'),
	(21, 'AskForTeacher', 'person_name', b'1'),
	(22, 'AskForTeacher', 'course', b'1'),
	(23, 'AskForTeacher', 'officelocation', b'0'),
	(24, 'AskForTeacher', 'email', b'0'),
	(25, 'AskForProgramme', 'name', b'1'),
	(26, 'AskForProgramme', 'price', b'0'),
	(27, 'AskForProgramme', 'content', b'0'),
	(28, 'AskForProgramme', 'course', b'1'),
	(29, 'AskForProgramme', 'language', b'0'),
	(30, 'AskForProgramme', 'director', b'0'),
	(31, 'AskForProgramme', 'duration', b'0'),
	(32, 'AskForProgramme', 'degree', b'0'),
	(33, 'AskForLibrary', 'location', b'0');
/*!40000 ALTER TABLE `frame_attribute` ENABLE KEYS */;

-- Dumping structure for table nli_sa.library
CREATE TABLE IF NOT EXISTS `library` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end` varchar(100) NOT NULL,
  `location` varchar(200) NOT NULL,
  `name` varchar(100) NOT NULL,
  `services` varchar(300) NOT NULL,
  `start` varchar(100) NOT NULL,
  `frame_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4p03iymr6a5pmhfdcwrvvluka` (`frame_name`),
  CONSTRAINT `FK4p03iymr6a5pmhfdcwrvvluka` FOREIGN KEY (`frame_name`) REFERENCES `frame` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table nli_sa.library: ~0 rows (approximately)
/*!40000 ALTER TABLE `library` DISABLE KEYS */;
INSERT INTO `library` (`id`, `end`, `location`, `name`, `services`, `start`, `frame_name`) VALUES
	(1, 'ten thirty in the evening', 'the college of arts, humanities and social sciences', 'Central Library', 'rooms for studying and a service of book borrowing', 'eight in the morning', 'AskForLibrary');
/*!40000 ALTER TABLE `library` ENABLE KEYS */;

-- Dumping structure for table nli_sa.programme
CREATE TABLE IF NOT EXISTS `programme` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `degree` varchar(50) DEFAULT NULL,
  `director` varchar(100) NOT NULL,
  `duration` int(11) NOT NULL,
  `language` varchar(50) NOT NULL,
  `price` float NOT NULL,
  `frame_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKncqdmmoup9jvdr6vf6sgaidiu` (`frame_name`),
  CONSTRAINT `FKncqdmmoup9jvdr6vf6sgaidiu` FOREIGN KEY (`frame_name`) REFERENCES `frame` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table nli_sa.programme: ~4 rows (approximately)
/*!40000 ALTER TABLE `programme` DISABLE KEYS */;
INSERT INTO `programme` (`id`, `name`, `content`, `degree`, `director`, `duration`, `language`, `price`, `frame_name`) VALUES
	(1, 'intelligent systems', 'Intelligent Systems is an exciting and interdisciplinary area spanning fields including computer science, linguistics, psychology, neuroscience, and philosophy.', 'master', 'vicent', 9, 'english', 3000, 'AskForProgramme'),
	(2, 'sound and music computing', 'the content of sound and music computing ', 'Bachelor', 'lara croft', 36, 'catalan', 2000, 'AskForProgramme'),
	(3, 'mathematics', ' ', 'bachelor', 'sophie Germain', 36, 'catalan', 2000, 'AskForProgramme'),
	(4, 'data science', 'This degree prepares computer scientists or mathematicians to become data engineers or scientists ', 'master', 'hedy lamar', 24, 'english', 4000, 'AskForProgramme');
/*!40000 ALTER TABLE `programme` ENABLE KEYS */;

-- Dumping structure for table nli_sa.sentence
CREATE TABLE IF NOT EXISTS `sentence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `sentence` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table nli_sa.sentence: ~0 rows (approximately)
/*!40000 ALTER TABLE `sentence` DISABLE KEYS */;
/*!40000 ALTER TABLE `sentence` ENABLE KEYS */;

-- Dumping structure for table nli_sa.spacy
CREATE TABLE IF NOT EXISTS `spacy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dependency` varchar(255) DEFAULT NULL,
  `idx` bigint(20) NOT NULL,
  `lemma` varchar(255) DEFAULT NULL,
  `pos` varchar(255) DEFAULT NULL,
  `punctuation` bit(1) NOT NULL,
  `shape` varchar(255) DEFAULT NULL,
  `space` bit(1) NOT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table nli_sa.spacy: ~0 rows (approximately)
/*!40000 ALTER TABLE `spacy` DISABLE KEYS */;
/*!40000 ALTER TABLE `spacy` ENABLE KEYS */;

-- Dumping structure for table nli_sa.teacher
CREATE TABLE IF NOT EXISTS `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `office_location` varchar(200) NOT NULL,
  `frame_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8x53jqcvjj29ne68vae464qn4` (`frame_name`),
  CONSTRAINT `FK8x53jqcvjj29ne68vae464qn4` FOREIGN KEY (`frame_name`) REFERENCES `frame` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table nli_sa.teacher: ~4 rows (approximately)
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` (`id`, `name`, `email`, `office_location`, `frame_name`) VALUES
	(1, 'adam smith', 'Toni.noble@ed.ac.es', 'College of Arts, Humanities and Social Sciences', 'AskForTeacher'),
	(2, 'tom bolton', 'adam.noble@ed.ac.es', 'College of Arts, Humanities and Social Sciences', 'AskForTeacher'),
	(3, 'mireia farrus and leo wanner', 'meireia.farrus@upf.edu', 'School of Philosophy, Psychology and Language Sciences', 'AskForTeacher'),
	(4, 'richard mayr', 'r.mayr@ed.ac.es', 'School of Informatics Teaching Organisation', 'AskForTeacher');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;

-- Dumping structure for table nli_sa.template
CREATE TABLE IF NOT EXISTS `template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `val` varchar(300) DEFAULT NULL,
  `attribute_name` varchar(50) DEFAULT NULL,
  `frame_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfaxe82tr13fae0tj3o5jvpnfm` (`attribute_name`),
  KEY `FKnx0ciix36a32h927x0jduhrgo` (`frame_name`),
  CONSTRAINT `FKfaxe82tr13fae0tj3o5jvpnfm` FOREIGN KEY (`attribute_name`) REFERENCES `attribute` (`name`),
  CONSTRAINT `FKnx0ciix36a32h927x0jduhrgo` FOREIGN KEY (`frame_name`) REFERENCES `frame` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;

-- Dumping data for table nli_sa.template: ~23 rows (approximately)
/*!40000 ALTER TABLE `template` DISABLE KEYS */;
INSERT INTO `template` (`id`, `val`, `attribute_name`, `frame_name`) VALUES
	(1, '%s starts %s', 'start', 'AskForCourse'),
	(20, '%s ends %s', 'end', 'AskForCourse'),
	(21, '%s has %s credits', 'credit', 'AskForCourse'),
	(22, 'the recommended book for %s is %s', 'syllabus', 'AskForCourse'),
	(23, 'classroom %s', 'classroom', 'AskForCourse'),
	(24, '%s is taught in %s', 'language', 'AskForCourse'),
	(25, 'the professor is %s', 'teacher', 'AskForCourse'),
	(26, '%s starts at %s', 'classtime', 'AskForCourse'),
	(27, '%s is taught in the %s master programme', 'programme', 'AskForCourse'),
	(28, 'sure, %s aims at %s', 'summary', 'AskForCourse'),
	(29, 'the library is located in the %s ', 'location', 'AskForLibrary'),
	(30, 'the library opens at %s', 'start', 'AskForLibrary'),
	(31, 'the library closes at %s', 'end', 'AskForLibrary'),
	(32, 'the library offers %s', 'services', 'AskForLibrary'),
	(33, '%s teaches %s', 'person_name', 'AskForTeacher'),
	(34, 'the name is %s', 'person_name', 'AskForTeacher'),
	(38, 'the office of %s is located in %s', 'officelocation', 'AskForTeacher'),
	(41, 'the email of %s is %s', 'email', 'AskForTeacher'),
	(42, 'the courses of %s are %s', 'course', 'AskForTeacher'),
	(43, '%s is a %s degree', 'degree', 'AskForProgramme'),
	(44, 'the language of the %s is %s', 'language', 'AskForProgramme'),
	(45, 'the director of the %s is %s', 'director', 'AskForProgramme'),
	(46, 'the price of the %s master is %s euros', 'price', 'AskForProgramme'),
	(47, 'the courses of the %s master are %s', 'course', 'AskForProgramme'),
	(48, 'the duration of the %s master is %s months', 'duration', 'AskForProgramme');
/*!40000 ALTER TABLE `template` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
