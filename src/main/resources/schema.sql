DROP TABLE  IF EXISTS `storages` ;
CREATE TABLE `storages` (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `storage_type` ENUM('PERMANENT','STAGING') NOT NULL,
                         `bucket` varchar(100) NOT NULL,
                          PRIMARY KEY (`id`));