INSERT INTO tuser (name, surname, login, password, email, phone_number, role)
VALUES ('Tomek','Wodz', 'tomwodz','tomwodz','twodzinski@op.pl','123 456 789','ADMIN');

INSERT INTO tuser (name, surname, login, password, email, phone_number, role)
VALUES ('Tomek','Wodz', 'parent','parent','twodzinski@op.pl','123 456 789','PARENT');

INSERT INTO tuser (name, surname, login, password, email, phone_number, role)
VALUES ('Iwona','Kowalska', 'kowalska','kowalska','twodzinski@op.pl','123 456 789','PARENT');

INSERT INTO taddress (user_id, city, street, zip_code)
VALUES (1,'Akacjowa','77-777','Warszawa');

INSERT INTO taddress (user_id, city, street, zip_code)
VALUES (2,'Akacjowa','77-777','Warszawa');

INSERT INTO tgroupchildren (name)
VALUES ('Domyślna');

INSERT INTO tgroupchildren (name)
VALUES ('Misie');

INSERT INTO tchild (name, surname, age, group_children_id, parent_id)
VALUES ('Alicja', 'Kowalska', 2, 1, 2);

INSERT INTO tchild (name, surname, age, group_children_id, parent_id)
VALUES ('Tymoteusz', 'Nowak', 2, 1, 2);

