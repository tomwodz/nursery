INSERT INTO public.taddress (id, street, zip_code, city)
VALUES (nextval('taddress_id_seq'), 'Akacjowa','77-777','Warszawa');

INSERT INTO public.taddress (id, street, zip_code, city)
VALUES (nextval('taddress_id_seq'),'Akacjowa','77-777','Warszawa');

INSERT INTO public.taddress(id, street, zip_code, city)
VALUES (nextval('taddress_id_seq'),'Akacjowa','77-777','Warszawa');


INSERT INTO public.tuser (address_id, id, name, surname, login, password, email, phone_number, role, active)
VALUES (1, nextval('tuser_id_seq'), 'Tomek','Wodz', 'tomwodz','6e43a6632d8f44360736762f86f66d1d','twodzinski@op.pl','123 456 789','ADMIN', true);

INSERT INTO public.tuser  (address_id, id, name, surname, login, password, email, phone_number, role, active)
VALUES (2 ,nextval('tuser_id_seq'),'Iwona','Nowak', 'parent','d0e45878043844ffc41aac437e86b602','twodzinski@op.pl','123 456 789','PARENT', true);

INSERT INTO public.tuser  (address_id, id, name, surname, login, password, email, phone_number, role, active)
VALUES (3, nextval('tuser_id_seq'),'Iwona','Nowak', 'employee','fa5473530e4d1a5a1e1eb53d2fedb10c','twodzinski@op.pl','123 456 789','EMPLOYEE', true);

INSERT INTO tgroupchildren (name)
VALUES ('Domyślna');

INSERT INTO tgroupchildren (name)
VALUES ('Misie');

INSERT INTO tchild (name, surname, day_of_birth, group_children_id, parent_id)
VALUES ('Alicja', 'Kowalska', '2021-02-02', 1, 2);

INSERT INTO tchild (name, surname, day_of_birth, group_children_id, parent_id)
VALUES ('Tymoteusz', 'Nowak', '2021-02-02', 1, 2);

