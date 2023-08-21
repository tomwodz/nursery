INSERT INTO tuser (name, surname, login, password, email, phone_number, role)
VALUES ('Tomek','Wodz', 'tomwodz','tomwodz','twodzinski@op.pl','123 456 789','ADMIN');

INSERT INTO tgroupchildren (name)
VALUES ('Misie');

INSERT INTO tchild (name, surname, age, group_children_id, parent_id)
VALUES ('Alicja', 'Kowalska', 2, 1, 1);

INSERT INTO tchild (name, surname, age, group_children_id, parent_id)
VALUES ('Tymoteusz', 'Nowak', 2, 1, 1);

