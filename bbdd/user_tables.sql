delimiter $$

CREATE TABLE `sessions` (
  `SESSION_ID` varchar(125) NOT NULL COMMENT 'Identificador de la sesion',
  `USER_ID` int(10) unsigned DEFAULT NULL COMMENT 'Usuario asociado a la sesion en caso de saberlo',
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

CREATE TABLE `users` (
  `USER_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(45) NOT NULL,
  `USER_EMAIL` varchar(100) NOT NULL,
  `USER_PWD` varchar(40) DEFAULT NULL,
  `USER_IS_OFF` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Indica si el usuario está dado de baja, es decir',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USER_EMAIL_UNIQUE` (`USER_EMAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='Tabla en la que se guardan los usuarios de la aplicación'$$

delimiter $$

CREATE TABLE `users_settings` (
  `USER_ID` int(10) unsigned NOT NULL,
  `USER_PREFERRED_MAIN_VIEW` int(10) unsigned NOT NULL DEFAULT '1' COMMENT 'Define la ventana principal que el usuario quiere ver al logarse. Las posibilidades son 1 Solicitante y 2 Experto',
  PRIMARY KEY (`USER_ID`),
  KEY `fk_tuprofile_cuser_id_idx` (`USER_ID`),
  CONSTRAINT `fk_tuprofile_cuser_id` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


