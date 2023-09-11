INSERT INTO public.taddress
(id, street, zip_code, city)
VALUES
(nextval('taddress_id_seq'), 'Akacjowa','77-777','Warszawa');

INSERT INTO public.taddress
(id, street, zip_code, city)
VALUES
(nextval('taddress_id_seq'),'Akacjowa','77-777','Warszawa');

INSERT INTO
public.taddress(id, street, zip_code, city)
VALUES
(nextval('taddress_id_seq'),'Akacjowa','77-777','Warszawa');

INSERT INTO
    public.taddress(id, street, zip_code, city)
VALUES
    (nextval('taddress_id_seq'),'Olimpijczyków','42-600','Tarnowskie Góry');


INSERT INTO
public.tuser (address_id, id, name, surname, login, password, email, phone_number, role, active)
VALUES
(1, nextval('tuser_id_seq'), 'Tomek','Wodz', 'tomwodz','6e43a6632d8f44360736762f86f66d1d','twodzinski@op.pl','123-456-789','ADMIN', true);

INSERT INTO public.tuser
(address_id, id, name, surname, login, password, email, phone_number, role, active)
VALUES
(2, nextval('tuser_id_seq'),'Iwona','Nowak', 'parent','d0e45878043844ffc41aac437e86b602','twodzinski@op.pl','123-456-789','PARENT', true);

INSERT INTO public.tuser
(address_id, id, name, surname, login, password, email, phone_number, role, active)
VALUES
(3, nextval('tuser_id_seq'),'Iza','Fiołek', 'employee','fa5473530e4d1a5a1e1eb53d2fedb10c','twodzinski@op.pl','123-456-789','EMPLOYEE', true);

INSERT INTO public.tuser
(address_id, id, name, surname, login, password, email, phone_number, role, active)
VALUES
    (4, nextval('tuser_id_seq'),'Agnieszka','Kowalska', 'anowak','d0e45878043844ffc41aac437e86b602','twodzinski@op.pl','123-456-789','PARENT', true);

INSERT INTO
    tgroupchildren
(name)
VALUES
    ('Rekrutacja 2023');

INSERT INTO
tgroupchildren
(name)
 VALUES
('Domyślna');

INSERT INTO
tgroupchildren
(name)
VALUES
('Misie');

INSERT INTO
tgroupchildren
(name)
VALUES
('Żuczki');

INSERT INTO
tgroupchildren
(name)
VALUES
 ('Biedronki');

INSERT INTO
tchild (name, surname, day_birth, group_children_id, parent_id)
VALUES
('Alicja', 'Nowak', '2021-03-02', 2, 2);

INSERT INTO
tchild (name, surname, day_birth, group_children_id, parent_id)
VALUES
('Tymoteusz', 'Kowalski', '2021-01-02', 2, 4);

INSERT INTO
tchild (name, surname, day_birth, group_children_id, parent_id)
VALUES
('Weronika', 'Kowalska', '2021-12-03', 2, 4);

INSERT INTO
tchild (name, surname, day_birth, group_children_id, parent_id)
VALUES
('Justyna', 'Kowalska', '2022-12-03', 3, 4);

INSERT INTO
tinformation (id, title, content, date_creation, author_id)
VALUES
(nextval('tinformation_id_seq'), 'Rusza rekrutacja do żłobka', 'Prosimy o składanie dokumnetów do...', '2023-08-30 11:21:07.517650', 1);


INSERT INTO
    tpresence (id, data_time_entry, data_time_departure, child_id)
VALUES
    (nextval('tpresence_id_seq'),'2023-09-11 07:00', '2023-09-11 15:00', 1);
