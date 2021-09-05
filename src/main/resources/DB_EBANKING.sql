-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Gazdă: localhost:3306
-- Timp de generare: aug. 18, 2021 la 07:38 PM
-- Versiune server: 10.0.38-MariaDB
-- Versiune PHP: 7.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Bază de date: jupiter_1
--
-- --------------------------------------------------------

--
-- Structură tabel pentru tabel tbl_client
-- CLIENT – ClientId, Nume, Prenume, UserName, Parola
CREATE TABLE `jupiter_1`.`tbl_client` (
  `ClientId` INTEGER(20)  NOT NULL AUTO_INCREMENT,
  `Nume` VARCHAR(32) NOT NULL,
  `Prenume` VARCHAR(32) NOT NULL,
  `Email` VARCHAR(32) NOT NULL,
  `Parola` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`ClientId`), UNIQUE KEY `INDX_EMAIL_CLIENT` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Definitie: CLIENT – ClientId, Nume, Prenume, UserName, Parola';

--
-- Alimentare tabel tbl_client
--
INSERT INTO `jupiter_1`.`tbl_client` (`ClientId`, `Nume`, `Prenume`, `Email`, `Parola`) VALUES
(10000000, 'ING', 'Bank', 'ing_owner@ingbank.ro', 'test123'),
(10000001, 'Maxwell', 'James Clerk', 'cmaxwell@cpln.org', 'test_1234'),
(10000002, 'Planck', 'Maximilian', 'mplanck@mit.edu', 'test_1234'),
(10000003, 'Boltzmann', 'Ludwig', 'lboltzmann@cern.ch', 'test_1234');
COMMIT;





--
-- Structură tabel pentru tabel tbl_conturi
-- CLIENT – ContId, Nume, Prenume, UserName, Parola
CREATE TABLE `jupiter_1`.`tbl_conturi` (
  `ContId` INTEGER(20)  NOT NULL AUTO_INCREMENT,
  `NrCont` VARCHAR(32) NOT NULL,
  `Banca` VARCHAR(32) NOT NULL,
  `ClientId` INTEGER(20)  NOT NULL COMMENT 'User Id as foreign key ',
  `Suma` FLOAT NOT NULL,
  `Stare` INT NOT NULL COMMENT '(1 – activ, 2 – in aprobare, 3 - inactiv)',
  PRIMARY KEY (`ContId`),
  KEY `ACCT_CLIENT_KEY` (`ClientId`),
  CONSTRAINT `ACCT_CLIENT_FKEY` FOREIGN KEY (`ClientId`) REFERENCES `tbl_client` (`ClientId`),
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Definitie: CLIENT – Id, Nume, Prenume, UserName, Parola';

--
-- Alimentare tabel tbl_conturi
--
INSERT INTO `tbl_conturi` (`ContId`, `NrCont`, `Banca`, `ClientId`, `Suma`, `Stare`) VALUES
(14008000, '000099990100', 'INGB', 10000001, 0.0, 1),
(14008701, '564721532456', 'INGB', 10000001, 450000.00, 2),
(14008731, '331445679875', 'INGB', 10000002, 13565.23, 1),
(14008722, '211756473013', 'INGB', 10000003, 12322.00, 1);
COMMIT;


--
-- Structură tabel tbl_tranzactii
-- TRANZACTII - operatii de transfer bancar intre conturi
 
CREATE TABLE `jupiter_1`.`tbl_tranzactii` (
  `TranzactId` INTEGER(20) NOT NULL AUTO_INCREMENT,
  `DtTranzactie` datetime NOT NULL,
  `AcctSrcId` INTEGER(20) NOT NULL,
  `AcctTrgId` INTEGER(20) NOT NULL,
  `SoldSrcSum` float NOT NULL COMMENT 'Sold Cont Debitor',
  `SoldTrgSum` float NOT NULL COMMENT 'Sold Cont Receptor',
  `TransactSum` float NOT NULL COMMENT 'Suma Transferata',
   PRIMARY KEY (`TranzactId`),
   KEY `SOURCE_CUST_INDX` (`AcctSrcId`),
   KEY `TARGET_CUST_INDX` (`AcctTrgId`),
   CONSTRAINT `SOURCE_CUST_FKEY` FOREIGN KEY (`AcctSrcId`) REFERENCES `tbl_conturi` (`ContId`) ON DELETE CASCADE,
   CONSTRAINT `TARGET_CUST_FKEY` FOREIGN KEY (`AcctTrgId`) REFERENCES `tbl_conturi`(`ContId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Definitie: TRANZACTII - operatii de transfer bancar intre conturi';

--
-- Alimentare tabel tbl_tranzactii
--

INSERT INTO `tbl_tranzactii` (`TranzactId`, `DtTranzactie`, `AcctSrcId`, `AcctTrgId`, `SoldSrcSum`, `SoldTrgSum`, `TransactSum`) VALUES
(18945761, '2021-01-23 12:45:56', 14008701, 14008722, 449988.00, 12334.00, 12.00),
(65460987, '2021-02-23 12:45:56', 14008722, 14008701, 12299.82, 450022.18, 34.18);
COMMIT;




--
-- Structură tabel pentru tabel users
-- Spring Security predefined model for registered users
CREATE TABLE `users` (
  `username` varchar(32) NOT NULL COMMENT 'email address',
  `password` varchar(128) NOT NULL,
  `enabled` tinyint(1) NOT NULL COMMENT ,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Alimentare tabel users
--
INSERT INTO `users`(`username`,`password`,`enabled`) VALUES
('ing_owner@ingbank.ro', '{bcrypt}$2a$12$EGCWR...', 1),
('cmaxwell@cpln.org', '{bcrypt}$2a$12$EGCWRwzOpetDDn3EfR3sPeI9unMfAzA61h4c6KJET1qDYga/cncl.', 1),
('lboltzmann@cern.ch', '{bcrypt}$2a$12$EGCWRwzOpetDDn3EfR3sPeI9unMfAzA61h4c6KJET1qDYga/cncl.',1),
('mplanck@mit.edu', '{bcrypt}$2a$12$EGCWRwzOpetDDn3EfR3sPeI9unMfAzA61h4c6KJET1qDYga/cncl.', 1);

--
-- Structură tabel pentru tabel authorities
-- AUTORIZ   -  username(email user), rol
CREATE TABLE `authorities` (
  `username` varchar(32) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `AUTHORITIES_IDX_1` (`username`,`authority`),
  CONSTRAINT `AUTHORITIES_IBFK_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Alimentare tabel tbl_athorisations
--
INSERT INTO `authorities` (`username`, `authority`) VALUES
('ing_owner@ingbank.ro', 'ROLE_EMPLOYEE')
('ing_owner@ingbank.ro', 'ROLE_CLIENT')
('ing_owner@ingbank.ro', 'ROLE_ADMIN'),
('cmaxwell@cpln.org', 'ROLE_ADMIN'),
('lboltzmann@cern.ch', 'ROLE_CLIENT'),
('mplanck@mit.edu', 'ROLE_CLIENT');
COMMIT;

/*!40101 SET @CHARACTER_SET_CLIENT=@@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET @CHARACTER_SET_RESULTS=@@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET @COLLATION_CONNECTION=@@OLD_COLLATION_CONNECTION */;
