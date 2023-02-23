DROP TABLE IF EXISTS `application_parameter`;
CREATE TABLE `application_parameter` (
  `code` varchar(30) NOT NULL,
  `description` varchar(100) NOT NULL,
  `value` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `drugstore_mapping`;
CREATE TABLE `drugstore_mapping` (
  `drugstore_code` varchar(10) NOT NULL,
  `inkaventa_code` varchar(10) NOT NULL,
  `last_evaluated_date` datetime DEFAULT NULL,
  `company` varchar(10) NOT NULL DEFAULT 'IKF',
  `enabled` varchar(1) NOT NULL DEFAULT 'N',
  `status` varchar(30) DEFAULT NULL,
  `callsource` varchar(16) DEFAULT 'IKF_DELIVERY',
  PRIMARY KEY (`drugstore_code`,`inkaventa_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_external_id` bigint(20) DEFAULT NULL,
  `company_id` char(3) DEFAULT NULL,
  `local_id` char(3) DEFAULT NULL,
  `order_id` varchar(10) DEFAULT NULL,
  `currency` varchar(5) DEFAULT NULL,
  `total_amount` decimal(10,2) DEFAULT NULL,
  `order_date` varchar(20) DEFAULT NULL,
  `payment_note` varchar(200) DEFAULT NULL,
  `order_note` varchar(200) DEFAULT NULL,
  `router_note` varchar(200) DEFAULT NULL,
  `payment_method` varchar(200) DEFAULT NULL,
  `with_transference` varchar(20) DEFAULT NULL,
  `status` varchar(30) DEFAULT 'EMITED',
  `status_description` varchar(256) DEFAULT NULL,
  `exchangeRate` decimal(10,2) DEFAULT NULL,
  `paidAmount` decimal(10,2) DEFAULT NULL,
  `changeAmount` decimal(10,2) DEFAULT NULL,
  `priority` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28545 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `order_address`;
CREATE TABLE `order_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `country` varchar(30) DEFAULT NULL,
  `department` varchar(30) DEFAULT NULL,
  `province` varchar(30) DEFAULT NULL,
  `district` varchar(30) DEFAULT NULL,
  `street` varchar(65) DEFAULT NULL,
  `street_number` varchar(10) DEFAULT NULL,
  `apartment` varchar(60) DEFAULT NULL,
  `latitude` decimal(11,8) DEFAULT NULL,
  `longitude` decimal(11,8) DEFAULT NULL,
  `address_note` varchar(100) DEFAULT NULL,
  `reference` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `order_address_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `order_client`;
CREATE TABLE `order_client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `document_type` varchar(12) DEFAULT NULL,
  `document_number` varchar(15) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `paternal_surname` varchar(40) DEFAULT NULL,
  `maternal_surname` varchar(40) DEFAULT NULL,
  `ruc_number` varchar(20) DEFAULT NULL,
  `business_name` varchar(150) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `order_client_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `product_short_name` varchar(200) DEFAULT NULL,
  `product_name` varchar(200) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit_price` decimal(10,2) DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `fractionated` char(1) DEFAULT NULL,
  `brand` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=305 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `local_id` tinyint(4) NOT NULL,
  `order_id` tinyint(4) NOT NULL,
  `order_date` tinyint(4) NOT NULL,
  `total_amount` tinyint(4) NOT NULL,
  `address` tinyint(4) NOT NULL,
  `country` tinyint(4) NOT NULL,
  `department` tinyint(4) NOT NULL,
  `province` tinyint(4) NOT NULL,
  `district` tinyint(4) NOT NULL,
  `street` tinyint(4) NOT NULL,
  `street_number` tinyint(4) NOT NULL,
  `apartment` tinyint(4) NOT NULL,
  `latitude` tinyint(4) NOT NULL,
  `longitude` tinyint(4) NOT NULL,
  `address_note` tinyint(4) NOT NULL,
  `document_type` tinyint(4) NOT NULL,
  `document_number` tinyint(4) NOT NULL,
  `name` tinyint(4) NOT NULL,
  `paternal_surname` tinyint(4) NOT NULL,
  `maternal_surname` tinyint(4) NOT NULL,
  `ruc_number` tinyint(4) NOT NULL,
  `business_name` tinyint(4) NOT NULL,
  `phone` tinyint(4) NOT NULL,
  `email` tinyint(4) NOT NULL,
  `birth_date` tinyint(4) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `schema_version`;
CREATE TABLE `schema_version` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `schema_version_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
