ALTER TABLE `e_marketplace`.`user`
CHANGE COLUMN `email` `email` VARCHAR(75) NOT NULL ,
CHANGE COLUMN `password` `password` VARCHAR(200) NOT NULL ;
