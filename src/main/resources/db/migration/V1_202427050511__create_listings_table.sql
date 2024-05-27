CREATE TABLE `e_marketplace`.`listing` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `price` DOUBLE NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `submission_time` DATE NOT NULL,
  `photo_url` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);
