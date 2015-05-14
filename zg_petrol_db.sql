-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 15, 2014 at 05:10 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `zg_petrol_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `changes`
--

CREATE TABLE IF NOT EXISTS `changes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `user` varchar(255) NOT NULL,
  `pump_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pump_id` (`pump_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=72 ;

--
-- Dumping data for table `changes`
--

INSERT INTO `changes` (`id`, `date`, `description`, `user`, `pump_id`) VALUES
(17, '2014/09/03', 'prva', 'Emil Makovac (user)', 7),
(62, '2014/09/13', 'novadva', 'Renato Pasric (admin)', 4),
(63, '2014/09/13', 'novaasdfg promjeeeenaaaa', 'Renato Pasric (admin)', 4),
(65, '2014/09/13', 'druga dodana', 'Renato Pasric (admin)', 4),
(66, '2014/09/14', 'dfs', 'Renato Pasric (admin)', 4),
(71, '2014/09/15', '', 'Renato Pasric (admin)', 5);

-- --------------------------------------------------------

--
-- Table structure for table `cico`
--

CREATE TABLE IF NOT EXISTS `cico` (
  `city_id` bigint(20) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  UNIQUE KEY `city_id` (`city_id`,`company_id`),
  KEY `cico_ibfk_2` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cico`
--

INSERT INTO `cico` (`city_id`, `company_id`) VALUES
(1, 1),
(2, 1),
(1, 2),
(2, 2),
(1, 3),
(4, 4),
(5, 4);

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE IF NOT EXISTS `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `region_id` bigint(20) NOT NULL,
  `country_Id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `region_id` (`region_id`),
  KEY `countryId` (`country_Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`id`, `name`, `region_id`, `country_Id`) VALUES
(1, 'Zagreb', 1, 1),
(2, 'Umag', 2, 1),
(3, 'Porec', 2, 1),
(4, 'Beograd', 3, 2),
(5, 'Subotica', 4, 2);

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE IF NOT EXISTS `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`id`, `name`) VALUES
(1, 'Crodux'),
(2, 'Ina'),
(3, 'Tifon'),
(4, 'Luk_oil'),
(5, 'Adria_oil');

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE IF NOT EXISTS `country` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id` (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `country`
--

INSERT INTO `country` (`Id`, `Name`) VALUES
(1, 'Hrvatska'),
(2, 'Srbija');

-- --------------------------------------------------------

--
-- Table structure for table `pump`
--

CREATE TABLE IF NOT EXISTS `pump` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `latitude` double(10,6) NOT NULL,
  `longitude` double(10,6) NOT NULL,
  `contact` varchar(30) DEFAULT NULL,
  `working_time` varchar(255) DEFAULT NULL,
  `company_id` bigint(20) NOT NULL,
  `city_id` bigint(20) NOT NULL,
  `country_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `company_id` (`company_id`),
  KEY `city_id` (`city_id`),
  KEY `country_id` (`country_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

--
-- Dumping data for table `pump`
--

INSERT INTO `pump` (`id`, `name`, `address`, `latitude`, `longitude`, `contact`, `working_time`, `company_id`, `city_id`, `country_id`) VALUES
(1, 'BP Zagreb-?rnomerec', 'Ilica 278', 45.813873, 15.929358, '914971335', '06-22', 2, 1, 1),
(2, 'BP Zagreb-Jagi?eva', 'Ulica Vatroslava Jagi?a 31a', 45.805850, 16.222358, '914971348', 'NON-STOP', 2, 1, 1),
(3, 'BP Zagreb-Miramarska', 'Miramarska cesta 25', 45.801797, 15.974703, '914971356', 'NON-STOP', 2, 1, 1),
(4, 'BP Umag-obala', 'Šetalište V. Gortana 5/a', 45.440363, 13.520600, '914971138', '06-22', 2, 2, 1),
(5, 'BP Pore?-obala', 'Rade Kon?ara 2b', 45.222835, 13.596790, '914971123', '07-19', 2, 3, 1),
(6, 'BP Pore?-Vukovarska', 'Vukovarska 2', 45.224563, 13.614080, '914971124', 'NON-STOP', 2, 3, 1),
(7, 'BP ZAGREB ISTOK', 'Slavonska avenija 110', 45.798106, 16.042183, '914641124', 'NON-STOP', 3, 1, 1),
(8, 'BP JANKOMIR', 'Ljubljanska avenija 10,\r\n10090 Zagreb', 45.797362, 15.865803, '914641141', 'NON-STOP', 3, 1, 1),
(9, 'BP JARUN', 'Horva?anska 71,\r\n10000 Zagreb', 45.788505, 15.924275, '914641167', 'NON-STOP', 3, 1, 1),
(10, 'BP BUZIN', 'Buzin - Baneki 18,\r\n10010 Zagreb', 45.749296, 15.991287, '914641076', 'NON-STOP', 3, 1, 1),
(12, 'B.P. OPOROVE?KA', 'Rudolfa Kolaka 1, 10000 Zagreb', 45.837852, 16.046959, '916099305', 'NON-STOP', 4, 1, 1),
(13, 'B.P. PODSUSED', 'Aleja  Bolonje 76, 10090 Zagreb', 45.814997, 15.852263, '15605302', 'NON-STOP', 4, 1, 1),
(14, 'B.P. RADNI?KA CESTA', ' Radni?ka cesta 212 a, 10000 Zagreb', 45.788431, 16.034828, '15600791', 'NON-STOP', 4, 1, 1),
(15, 'Aerodrom\r\n\r\n', 'Autoput za Zagreb 296', 44.816667, 20.466667, '631040574', 'NON-STOP', 4, 4, 2),
(16, 'Staro Sajmište', 'Zemunski put 7', 44.811200, 20.445240, '0112130915', 'NON-STOP', 4, 4, 2),
(17, 'Kalemegdan', 'Bul. Vojvode Bojovi?a bb', 44.816667, 20.466667, '011624623', 'NON-STOP', 4, 4, 2),
(18, 'Avalski put', 'Bulevar oslobo?enja 56 ', 44.755819, 20.483123, '0113973422', 'NON-STOP', 4, 4, 2),
(19, 'Subotica 1', 'Petefi Šandora bb', 46.100278, 19.665556, '024558660', 'NON-STOP', 4, 5, 2),
(20, 'Subotica 2', 'Beogradski put 227', 46.077615, 19.676654, '024566227', 'NON-STOP', 4, 5, 2),
(22, 'fdasa', 'asdffff', 45.123456, 45.123456, 'asd', 'f', 4, 4, 2);

-- --------------------------------------------------------

--
-- Table structure for table `region`
--

CREATE TABLE IF NOT EXISTS `region` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `country_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `country_id` (`country_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `region`
--

INSERT INTO `region` (`id`, `name`, `country_id`) VALUES
(1, 'Zagrebacka', 1),
(2, 'Istarska', 1),
(3, 'Beograd', 2),
(4, 'Vojvodina', 2);

-- --------------------------------------------------------

--
-- Table structure for table `separators`
--

CREATE TABLE IF NOT EXISTS `separators` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `kanalica` double DEFAULT NULL COMMENT 'in meters',
  `slivnici` int(11) DEFAULT NULL,
  `okna` int(11) DEFAULT NULL,
  `pump_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pump_id` (`pump_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `separators`
--

INSERT INTO `separators` (`id`, `kanalica`, `slivnici`, `okna`, `pump_id`) VALUES
(12, 3, 4, 5, 4),
(13, 4, 5, 6, 4),
(14, 4, 4, 4, 4),
(15, 4, 4, 4, 6);

-- --------------------------------------------------------

--
-- Table structure for table `tank`
--

CREATE TABLE IF NOT EXISTS `tank` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `volumen` double DEFAULT NULL,
  `pad` int(11) DEFAULT NULL,
  `gorivo` varchar(255) DEFAULT NULL,
  `br_kosara` int(11) DEFAULT NULL,
  `pump_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pump_id` (`pump_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `tank`
--

INSERT INTO `tank` (`id`, `volumen`, `pad`, `gorivo`, `br_kosara`, `pump_id`) VALUES
(2, 3, 3, 'Eurosuper BS 98+ Class', 4, 16),
(5, 7, 7, 'Eurosuper BS 95 Class', 1, 4),
(6, 4, 4, 'Eurosuper BS 95 Class', 3, 4),
(7, 5, 5, 'Eurosuper BS 98+ Class', 2, 4);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `is_admin` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `username`, `password`, `is_admin`) VALUES
(1, 'Renato', 'Pasric', 'admin', 'admin', 1),
(24, 'sdf', 'sdf', 'sdf', 'sf', 0),
(25, 'zu', 'zu', 'zu', '', 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `changes`
--
ALTER TABLE `changes`
  ADD CONSTRAINT `changes_ibfk_1` FOREIGN KEY (`pump_id`) REFERENCES `pump` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `cico`
--
ALTER TABLE `cico`
  ADD CONSTRAINT `cico_ibfk_1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  ADD CONSTRAINT `cico_ibfk_2` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`);

--
-- Constraints for table `city`
--
ALTER TABLE `city`
  ADD CONSTRAINT `city_ibfk_1` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`),
  ADD CONSTRAINT `city_ibfk_2` FOREIGN KEY (`country_Id`) REFERENCES `country` (`Id`);

--
-- Constraints for table `pump`
--
ALTER TABLE `pump`
  ADD CONSTRAINT `pump_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  ADD CONSTRAINT `pump_ibfk_2` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  ADD CONSTRAINT `pump_ibfk_3` FOREIGN KEY (`country_id`) REFERENCES `country` (`Id`);

--
-- Constraints for table `region`
--
ALTER TABLE `region`
  ADD CONSTRAINT `region_ibfk_1` FOREIGN KEY (`country_id`) REFERENCES `country` (`Id`);

--
-- Constraints for table `separators`
--
ALTER TABLE `separators`
  ADD CONSTRAINT `separators_ibfk_1` FOREIGN KEY (`pump_id`) REFERENCES `pump` (`id`);

--
-- Constraints for table `tank`
--
ALTER TABLE `tank`
  ADD CONSTRAINT `tank_ibfk_1` FOREIGN KEY (`pump_id`) REFERENCES `pump` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
