-- MySQL Script generated by MySQL Workbench
-- Sun Mar 29 18:54:39 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema SavesForSales
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `SavesForSales` ;

-- -----------------------------------------------------
-- Schema SavesForSales
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SavesForSales` DEFAULT CHARACTER SET utf8 ;
USE `SavesForSales` ;

-- -----------------------------------------------------
-- Table `SavesForSales`.`Proveedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SavesForSales`.`Proveedor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `avatar` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SavesForSales`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SavesForSales`.`Usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `avatar` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Correo_UNIQUE` (`correo` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SavesForSales`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SavesForSales`.`Producto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `precio` INT NOT NULL,
  `proveedor` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`proveedor`) REFERENCES Proveedor (`id`),	
  UNIQUE INDEX `Correo_UNIQUE` (`correo` ASC) VISIBLE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
