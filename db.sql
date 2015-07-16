--
-- Скрипт сгенерирован Devart dbForge Studio for MySQL, Версия 6.3.358.0
-- Домашняя страница продукта: http://www.devart.com/ru/dbforge/mysql/studio
-- Дата скрипта: 16.07.2015 19:20:09
-- Версия сервера: 5.6.24
-- Версия клиента: 4.1
--


USE kazpost_test;

CREATE TABLE student (
  id varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  subjects blob NOT NULL,
  status varchar(255) DEFAULT NULL,
  surname varchar(255) DEFAULT NULL
)
ENGINE = INNODB
AVG_ROW_LENGTH = 1638
CHARACTER SET utf8
COLLATE utf8_unicode_ci;

CREATE TABLE student2 (
  id varchar(255) NOT NULL,
  subjects blob NOT NULL
)
ENGINE = INNODB
AVG_ROW_LENGTH = 1489
CHARACTER SET utf8
COLLATE utf8_unicode_ci;