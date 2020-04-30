-- MySQL Script generated by MySQL Workbench
-- Sat Apr 18 17:35:12 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema default_schema
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema SavesForSales
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `SavesForSales` ;

-- -----------------------------------------------------
-- Schema SavesForSales
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SavesForSales` DEFAULT CHARACTER SET utf8 ;
USE `SavesForSales` ;

CREATE USER IF NOT EXISTS 'sfsback'@'localhost' IDENTIFIED BY '0000';
GRANT ALL PRIVILEGES ON *.* TO 'sfsback'@'localhost';
FLUSH PRIVILEGES;
-- -----------------------------------------------------
-- Table `SavesForSales`.`Proveedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SavesForSales`.`Proveedor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL UNIQUE,
  `password` VARCHAR(45) NOT NULL,
  `avatar` VARCHAR(45) DEFAULT NULL,
  `ubicacion` varchar(100) DEFAULT NULL, 
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SavesForSales`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SavesForSales`.`Usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL UNIQUE,
  `password` VARCHAR(45) NOT NULL,
  `avatar` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SavesForSales`.`Producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SavesForSales`.`Producto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `precio` DOUBLE NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `proveedor` INT NULL DEFAULT NULL,
  `imagen` VARCHAR(45) NULL,
  `cantidad` DOUBLE NOT NULL,
  `fecha_publicacion` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX (`proveedor` ASC),
  CONSTRAINT ``
    FOREIGN KEY (`proveedor`)
    REFERENCES `SavesForSales`.`Proveedor` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `SavesForSales`.`Historico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SavesForSales`.`Historico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuario` INT NOT NULL,
  `producto` INT NOT NULL,
  `tiempo_reserva` DATETIME NULL,
  `cantidad` DOUBLE NULL,
  `estado` VARCHAR(20) NOT NULL DEFAULT 'RESERVA',
  `fecha_compra` DATETIME NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `c1`
    FOREIGN KEY (`usuario`)
    REFERENCES `SavesForSales`.`Usuario` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `c2`
    FOREIGN KEY (`producto`)
    REFERENCES `SavesForSales`.`Producto` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;