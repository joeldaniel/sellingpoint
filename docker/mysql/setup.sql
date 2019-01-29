DROP DATABASE IF EXISTS `sellingpoint-db`;
CREATE DATABASE IF NOT EXISTS `sellingpoint-db`;
USE `sellingpoint-db`;


-- Dumping structure for table sellingpoint-db.notification
DROP TABLE IF EXISTS `notification`;
CREATE TABLE IF NOT EXISTS `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creationDate` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `notificationType` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table sellingpoint-db.product
DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `forSale` tinyint(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table sellingpoint-db.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cartCount` int(11) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `userName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table sellingpoint-db.userlog
DROP TABLE IF EXISTS `userlog`;
CREATE TABLE IF NOT EXISTS `userlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `logDateTime` datetime DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `user` (`id`, `cartCount`, `contact`, `count`, `deleted`, `email`, `firstName`, `lastName`, `locked`, `password`, `userName`) VALUES (1, 1, NULL, 0, 0, 'admin@unisys.com', 'admin', 'admin', 0, 'ee00db28a32fa42402faf7a9bd378a0a', 'admin');
