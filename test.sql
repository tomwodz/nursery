INSERT INTO tuser (name, surname, login, password, email, phone_number, role)
VALUES ('Tomek','Wodz', 'tomwodz','6e43a6632d8f44360736762f86f66d1d','twodzinski@op.pl','123 456 789','ADMIN');

INSERT INTO tuser (name, surname, login, password, email, phone_number, role)
VALUES ('Tomek','Wodz', 'parent','6e43a6632d8f44360736762f86f66d1d','twodzinski@op.pl','123 456 789','PARENT');

INSERT INTO tuser (name, surname, login, password, email, phone_number, role)
VALUES ('Iwona','Nowak', 'nowak','6e43a6632d8f44360736762f86f66d1d','twodzinski@op.pl','123 456 789','PARENT');

INSERT INTO tuser (name, surname, login, password, email, phone_number, role)
VALUES ('Iwona','Nowak', 'employee','6e43a6632d8f44360736762f86f66d1d','twodzinski@op.pl','123 456 789','EMPLOYEE');

INSERT INTO taddress (user_id, city, street, zip_code)
VALUES (1,'Akacjowa','77-777','Warszawa');

INSERT INTO taddress (user_id, city, street, zip_code)
VALUES (2,'Akacjowa','77-777','Warszawa');

INSERT INTO taddress (user_id, city, street, zip_code)
VALUES (3,'Akacjowa','77-777','Warszawa');

INSERT INTO taddress (user_id, city, street, zip_code)
VALUES (4,'Akacjowa','77-777','Warszawa');

INSERT INTO tgroupchildren (name)
VALUES ('Domyślna');

INSERT INTO tgroupchildren (name)
VALUES ('Misie');

INSERT INTO tchild (name, surname, age, group_children_id, parent_id)
VALUES ('Alicja', 'Kowalska', 2, 1, 2);

INSERT INTO tchild (name, surname, age, group_children_id, parent_id)
VALUES ('Tymoteusz', 'Nowak', 2, 1, 2);

