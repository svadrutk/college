CREATE TABLE `WhatAndWho`.`Who` (
  `Who_pk` INT NOT NULL auto_increment,
  `Who_jj` VARCHAR(45) NULL, 
  `Who_kk` VARCHAR(44) NULL, 
  `Who_What_pk` INT NULL, 
  PRIMARY KEY (`Who_pk`));

alter table `WhatAndWho`.`Who`
add constraint `What_fk`
foreign key (Who_What_pk) references What(What_pk); 

