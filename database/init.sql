drop database sphic;

create database sphic;

use sphic;

DROP TABLE IF EXISTS patient;
create table IF NOT EXISTs patient (ID int not null, uuid varchar(50) not null primary key,c_name varchar(100), name varchar(100), birthdate date, age int, address varchar(100), phone varchar(20));
insert into patient(ID,uuid, c_name, name, birthdate, age, address, phone) values(1, '1e33f6b3-93eb-11e5-ac6d-000c295d1671', '张三','San Zhang','1980-01-01', '35', 'Shanghai', '123456789');

DROP TABLE IF EXISTS study;
create table IF NOT EXISTs study(ID int not null primary key, patient_id varchar(100) not null, FOREIGN KEY fk_patient(patient_id) REFERENCES patient(uuid) ON DELETE CASCADE, study_ins_uid varchar(800), referring_physician_Name varchar(100), accession_number varchar(100),institution_name varchar(100), description varchar(300), comments varchar(100), created date, updated date, deleted date);

DROP TABLE IF EXISTS series;
create table IF NOT EXISTs series(ID int not null primary key, study_id int not null, foreign key fk_study(study_id) references study(ID) ON DELETE CASCADE, modality varchar(20), seriesInsUID varchar(800), manufacturer varchar(100), manufctModel varchar(100), seriesNumber int,seriesDate varchar(20),seriesTime varchar(20), seriesDescrip varchar(100), comments varchar(100), created date, updated date, deleted date);

DROP TABLE IF EXISTS image_series;
create table IF NOT EXISTS image_series(ID int not null primary key auto_increment, series_id int not null, foreign key fk_series(series_id) references series(ID) ON DELETE CASCADE, sop_cls_uid varchar(500),slice_thick double, image_orient_pat varchar(100),rows int, columns int, patient_position varchar(100),pixel_spacing varchar(100), slope double, intercept double, image_type varchar(100),
  derivation_descrpt varchar(100), patient_orient varchar(100), specific_character_set varchar(100), sample_per_pixel varchar(100),photometric_interpretation varchar(100), bits_allocated int, bits_stored int, high_bit int,  pixel_representation int, smallest_img_pixel_val int, largest_img_pixel_val int);

DROP TABLE IF EXISTS images;
create table IF NOT EXISTS images(ID int not null primary key auto_increment, series_id int not null, foreign key fk_series(series_id) references series(ID) ON DELETE CASCADE,  sop_ins_uid varchar(200), ins_num varchar(50), slice_location int, image_pos_pat varchar(50) );

DROP TABLE IF EXISTS account;
create table account(ID int not null auto_increment primary key,username varchar(100), password varchar(100), created date);
insert into account(username,password,created) values('admin', '20870e5d7a8b120c0859ef0575920fb6d082d517f98c598d9d67d839b18c6d24', now());

DROP TABLE IF EXISTS patient_profile;
create table patient_profile(ID int not null auto_increment primary key, patient_id varchar(50) not null, FOREIGN KEY fk_patient(patient_id) REFERENCES patient(uuid) ON DELETE CASCADE, step varchar(30), staff_type varchar(20), staff varchar(100), start_date date, end_date date, description varchar(1000));
insert into patient_profile(patient_id, step, staff_type, staff, start_date, end_date, description) values('1e33f6b3-93eb-11e5-ac6d-000c295d1671','registration', 'Nurse', 'Carol', '2015-10-2', '2015-10-2', 'description of registration');
insert into patient_profile(patient_id, step, staff_type, staff, start_date, end_date, description) values('1e33f6b3-93eb-11e5-ac6d-000c295d1671','immobilization', 'Oncologist', 'David', '2015-10-3', '2015-10-3', 'description of immobilization');
insert into patient_profile(patient_id, step, staff_type, staff, start_date, end_date, description) values('1e33f6b3-93eb-11e5-ac6d-000c295d1671','CT scan', 'Oncologist', 'David', '2015-10-4', '2015-10-5', 'description of CT scan');
insert into patient_profile(patient_id, step, staff_type, staff, start_date, end_date, description) values('1e33f6b3-93eb-11e5-ac6d-000c295d1671','contouring', 'Oncologist', 'David', '2015-10-6', '2015-10-8', 'description of contouring');
insert into patient_profile(patient_id, step, staff_type, staff, start_date, end_date, description) values('1e33f6b3-93eb-11e5-ac6d-000c295d1671','planning', 'Phisicist', 'Edward', '2015-10-9', '2015-10-12', 'description of planning');
insert into patient_profile(patient_id, step, staff_type, staff, start_date, end_date, description) values('1e33f6b3-93eb-11e5-ac6d-000c295d1671','plan verification', 'Phisicist', 'Edward', '2015-10-12', '2015-10-13', 'description of plan verification');
insert into patient_profile(patient_id, step, staff_type, staff, start_date, end_date, description) values('1e33f6b3-93eb-11e5-ac6d-000c295d1671','plan approval', 'Phisicist', 'Felix', '2015-10-13', '2015-10-13', 'description of plan approval');
insert into patient_profile(patient_id, step, staff_type, staff, start_date, end_date, description) values('1e33f6b3-93eb-11e5-ac6d-000c295d1671','treatment', 'Therapist ', 'Gary', '2015-10-14', '2015-10-15', 'description of treatment');

DROP TABLE IF EXISTS structure_set;
create table IF NOT EXISTs structure_set(ID int not null primary key, series_id int not null, foreign key fk_structure_set(series_id) references series(ID) ON DELETE CASCADE, name varchar(50), description varchar(1000), created date, updated date, deleted date);
insert into structure_set(ID, series_id, name, description, created, updated, deleted) values(1, 1, 'structure 1', 'test structure', now(), now(), null);

DROP TABLE IF EXISTS structure;
create table IF NOT EXISTs structure(ID int not null primary key, structure_set_id int not null, foreign key fk_structure(structure_set_id) references structure_set(ID) ON DELETE CASCADE, name varchar(50), description varchar(1000), color_id int, created date, updated date, deleted date);
insert into structure(ID, structure_set_id, color_id, name, description, created, updated, deleted) values(1, 1, 1, 'structure 1', 'test structure', now(), now(), null);

DROP TABLE IF EXISTS slice;
create table IF NOT EXISTs slice(ID int not null primary key, series_id int not null, foreign key fk_slice(series_id) references series(ID) ON DELETE CASCADE, view char, number int, name varchar(50), description varchar(1000), created date, updated date, deleted date, data MEDIUMTEXT);
insert into slice(ID, series_id, view, number, name, description, created, updated, deleted) values(1, 1, 'T', 1, 'slice 1', 'test slice', now(), now(), null);

DROP TABLE IF EXISTS contour;
create table IF NOT EXISTs contour(ID int not null auto_increment primary key, structure_id int not null, foreign key fk_contour_structure(structure_id) references structure(ID) ON DELETE CASCADE, slice_id int not null, foreign key fk_contour_slice(slice_id) references slice(ID) ON DELETE CASCADE, name varchar(50), description varchar(1000), created date, updated date, deleted date, data MEDIUMTEXT);
insert into contour(ID, structure_id, slice_id, name, description, created, updated, deleted) values(1, 1, 1, 'contour 1', 'test contour', now(), now(), null);
