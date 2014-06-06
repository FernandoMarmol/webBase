delimiter $$

CREATE DATABASE `base` /*!40100 DEFAULT CHARACTER SET utf8 */$$


delimiter $$

CREATE TABLE `users` (
  `USER_ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Identificador del usuario',
  `USER_IS_OFF` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Indica si el usuario está dado de baja',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabla en la que se guardan los usuarios de la aplicación'$$


delimiter $$

CREATE TABLE `sessions` (
  `SESSION_ID` varchar(125) NOT NULL COMMENT 'Identificador de la sesion',
  `USER_ID` int(11) unsigned DEFAULT NULL COMMENT 'Usuario asociado a la sesion en caso de saberlo',
  `SESSION_BEGINS` datetime DEFAULT NULL COMMENT 'Momento en el que la sesion se crea',
  `SESSION_ENDS` datetime DEFAULT NULL COMMENT 'Momento en el que se abandona la sesion',
  `USER_AGENT` varchar(150) DEFAULT NULL COMMENT 'Identificador del navegador que ha usado el usuario',
  `USER_LOGS_IN` datetime DEFAULT NULL COMMENT 'Hora a la que el usuario se ha logado',
  `USER_LOGS_OUT` datetime DEFAULT NULL COMMENT 'Hora a la que el usuario se ha deslogado',
  PRIMARY KEY (`SESSION_ID`),
  KEY `fk_sessions_user_id_idx` (`USER_ID`),
  CONSTRAINT `fk_sessions_user_id` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabla en la que se guardan las sesiones que se han registrado en la aplicacion'$$


delimiter $$

CREATE TABLE `user_data` (
  `USER_ID` int(11) unsigned NOT NULL,
  `USER_ALIAS` varchar(45) NOT NULL COMMENT 'Alias del usuario',
  `USER_NAME` varchar(45) DEFAULT NULL COMMENT 'Nombre real del usuario',
  `USER_EMAIL` varchar(100) NOT NULL COMMENT 'Correo electrónico asociado al usuario',
  `USER_PWD` varchar(40) NOT NULL,
  `USER_SURNAME` varchar(45) DEFAULT NULL COMMENT 'Apellido del usuario',
  PRIMARY KEY (`USER_ALIAS`,`USER_EMAIL`,`USER_ID`),
  UNIQUE KEY `USER_ALIAS_UNIQUE` (`USER_ALIAS`),
  KEY `fk_tuserdata_cuser_id_idx` (`USER_ID`),
  CONSTRAINT `fk_tuserdata_cuser_id` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE `user_settings` (
  `USER_ID` int(11) unsigned NOT NULL,
  KEY `fk_tuprofile_cuser_id_idx` (`USER_ID`),
  CONSTRAINT `fk_tuprofile_cuser_id` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$
