alter table restaurante add column aberto TINYINT(1);
update restaurante set aberto = false;
