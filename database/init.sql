drop database sphic;

create database sphic;

use sphic;

# ---------------------------------------------------------------------- #
# Add tables                                                             #
# ---------------------------------------------------------------------- #

# ---------------------------------------------------------------------- #
# Add table "patient"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `patient` (
    `uuid` VARCHAR(50) NOT NULL COMMENT 'System assigned unique id',
    `c_name` VARCHAR(40) COMMENT 'Chinese name',
    `name` VARCHAR(40) COMMENT 'Name',
    `phone` VARCHAR(40) COMMENT 'Phone',
    `birthdate` DATETIME COMMENT 'Birth date',
    `age` INTEGER COMMENT 'Age',
    `address` VARCHAR(100) COMMENT 'Address',
    `created` DATETIME COMMENT 'Creation time',
    `updated` DATETIME COMMENT 'Update time',
    `deleted` DATETIME COMMENT 'Deletion time',
    CONSTRAINT `PK_patient` PRIMARY KEY (`uuid`)
);

# ---------------------------------------------------------------------- #
# Add table "study"                                                      #
# ---------------------------------------------------------------------- #

CREATE TABLE `study` (
    `id` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'ID of study',
    `study_ins_id` VARCHAR(50) COMMENT 'ID of study in DCM',
    `patient_id` VARCHAR(50) COMMENT 'ID of Patient',
    `accession_number` VARCHAR(100) COMMENT 'Accession number',
    `referring_physician_name` VARCHAR(100) COMMENT 'Referring physician name',
    `institution_name` VARCHAR(100) COMMENT 'Institution name',
    `name` VARCHAR(100) COMMENT 'Name of study',
    `description` VARCHAR(300) COMMENT 'Description',
    `study_ins_uid` VARCHAR(800) COMMENT 'Study instance UID',
    `study_date_time` DATE COMMENT 'Study datetime',
    `created` DATETIME COMMENT 'Creation time',
    `updated` DATETIME COMMENT 'Update time',
    `deleted` DATETIME COMMENT 'Deletion time',
    CONSTRAINT `PK_study` PRIMARY KEY (`id`)
);

# ---------------------------------------------------------------------- #
# Add table "series"                                                     #
# ---------------------------------------------------------------------- #

CREATE TABLE `series` (
    `id` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'ID of Series',
    `study_id` INTEGER COMMENT 'ID of Study',
    `modality` VARCHAR(20) COMMENT 'Modality',
    `name` VARCHAR(100) COMMENT 'Name of series',
    `description` VARCHAR(300) COMMENT 'Description',
    `series_number` INTEGER COMMENT 'Series number',
    `manufacturer` VARCHAR(100) COMMENT 'Manufacturer',
    `manufct_model` VARCHAR(100) COMMENT 'Manufacture model',
    `series_date_time` DATE COMMENT 'Series datetime',
    `series_ins_id` VARCHAR(800) COMMENT 'Series instance UID',
    `series_ins_uid` VARCHAR(800) COMMENT 'Series instance UID',
    `created` DATETIME COMMENT 'Creation time',
    `updated` DATETIME COMMENT 'Update time',
    `deleted` DATETIME COMMENT 'Deletion time',
    CONSTRAINT `PK_series` PRIMARY KEY (`id`)
);

# ---------------------------------------------------------------------- #
# Add table "structure_set"                                              #
# ---------------------------------------------------------------------- #

CREATE TABLE `structure_set` (
    `id` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'ID of structure set',
    `series_id` INTEGER COMMENT 'ID of Series',
    `name` VARCHAR(100) COMMENT 'Name of structure set',
    `description` VARCHAR(200) COMMENT 'Description',
    `created` DATETIME COMMENT 'Creation time',
    `updated` DATETIME COMMENT 'Update time',
    `deleted` DATETIME COMMENT 'Deletion time',
    CONSTRAINT `PK_structure_set` PRIMARY KEY (`id`)
);

# ---------------------------------------------------------------------- #
# Add table "structure"                                                  #
# ---------------------------------------------------------------------- #

CREATE TABLE `structure` (
    `id` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'ID of structure',
    `structure_set_id` INTEGER COMMENT 'ID of Structure set',
    `name` VARCHAR(100) COMMENT 'Name of structure set',
    `description` VARCHAR(200) COMMENT 'Description',
    `roi_color` VARCHAR(50) COMMENT 'Color',
    `roi_number` VARCHAR(50) COMMENT 'ROI Number',
    `created` DATETIME COMMENT 'Creation time',
    `updated` DATETIME COMMENT 'Update time',
    `deleted` DATETIME COMMENT 'Deletion time',
    CONSTRAINT `PK_structure` PRIMARY KEY (`id`)
);

# ---------------------------------------------------------------------- #
# Add table "slice"                                                      #
# ---------------------------------------------------------------------- #

CREATE TABLE `slice` (
    `id` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'ID of Slice',
    `series_id` INTEGER COMMENT 'ID of Series',
    `view` CHAR(1) NOT NULL COMMENT 'View of slice',
    `number` INTEGER NOT NULL COMMENT 'Number of slice on the view',
    `rows` INTEGER COMMENT 'Number of rows',
    `columns` INTEGER COMMENT 'Number of columns',
    `rowspacing` double COMMENT 'Spacing for rows',
    `columnspacing` double COMMENT 'Spacing for columns',
    `sop_ins_uid` VARCHAR(800) COMMENT 'SOP Instance UID',
    `ins_number` INTEGER COMMENT 'Instance Number',
    `slice_location` double COMMENT 'Slice Loacation',
    `image_pos_pat` VARCHAR(100) COMMENT 'Image Position Patient',
    `data` MEDIUMTEXT COMMENT 'Slice data in density',
    `name` VARCHAR(100) COMMENT 'Name of structure set',
    `description` VARCHAR(200) COMMENT 'Description',
    `created` DATETIME COMMENT 'Creation time',
    `updated` DATETIME COMMENT 'Update time',
    `deleted` DATETIME COMMENT 'Deletion time',
    CONSTRAINT `PK_slice` PRIMARY KEY (`id`)
);

# ---------------------------------------------------------------------- #
# Add table "contour"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `contour` (
    `id` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'ID of structure set',
    `structure_id` INTEGER COMMENT 'ID of Structure',
    `slice_id` INTEGER COMMENT 'ID of Slice',
    `name` VARCHAR(100) COMMENT 'Name of structure set',
    `description` VARCHAR(200) COMMENT 'Description',
    `created` DATETIME COMMENT 'Creation time',
    `updated` DATETIME COMMENT 'Update time',
    `deleted` DATETIME COMMENT 'Deletion time',
    `data` MEDIUMTEXT COMMENT 'Contour data',
    CONSTRAINT `PK_contour` PRIMARY KEY (`id`)
);

# ---------------------------------------------------------------------- #
# Add table "account"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `account` (
    `id` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'ID of Account',
    `username` VARCHAR(40) NOT NULL COMMENT 'User name',
    `password` VARCHAR(40) NOT NULL COMMENT 'Password',
    CONSTRAINT `PK_account` PRIMARY KEY (`id`)
);

# ---------------------------------------------------------------------- #
# Add table "image_series"                                               #
# ---------------------------------------------------------------------- #

CREATE TABLE `image_series` (
    `ID` INTEGER NOT NULL AUTO_INCREMENT,
    `series_id` INTEGER NOT NULL,
    `sop_cls_uid` VARCHAR(500),
    `slice_thick` DOUBLE,
    `image_orient_pat` VARCHAR(100),
    `rows` INTEGER,
    `columns` INTEGER,
    `slice_num` INTEGER,
    `patient_position` VARCHAR(100),
    `pixel_spacing` VARCHAR(100),
    `slope` DOUBLE,
    `intercept` DOUBLE,
    `image_type` VARCHAR(100),
    `derivation_descrpt` VARCHAR(100),
    `patient_orient` VARCHAR(100),
    `specific_character_set` VARCHAR(100),
    `sample_per_pixel` VARCHAR(100),
    `photometric_interpretation` VARCHAR(100),
    `bits_allocated` INTEGER,
    `bits_stored` INTEGER,
    `high_bit` INTEGER,
    `pixel_representation` INTEGER,
    `smallest_img_pixel_val` INTEGER,
    `largest_img_pixel_val` INTEGER,
    PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add foreign key constraints                                            #
# ---------------------------------------------------------------------- #

ALTER TABLE `study` ADD CONSTRAINT `patient_study`
    FOREIGN KEY (`patient_id`) REFERENCES `patient` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `series` ADD CONSTRAINT `study_series`
    FOREIGN KEY (`study_id`) REFERENCES `study` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `structure_set` ADD CONSTRAINT `series_structure_set`
    FOREIGN KEY (`series_id`) REFERENCES `series` (`id`) ON DELETE CASCADE;

ALTER TABLE `structure` ADD CONSTRAINT `structure_set_structure`
    FOREIGN KEY (`structure_set_id`) REFERENCES `structure_set` (`id`);

ALTER TABLE `slice` ADD CONSTRAINT `series_slice`
    FOREIGN KEY (`series_id`) REFERENCES `series` (`id`) ON DELETE CASCADE;

ALTER TABLE `contour` ADD CONSTRAINT `slice_contour`
    FOREIGN KEY (`slice_id`) REFERENCES `slice` (`id`) ON DELETE CASCADE;

ALTER TABLE `contour` ADD CONSTRAINT `structure_contour`
    FOREIGN KEY (`structure_id`) REFERENCES `structure` (`id`) ON DELETE CASCADE;

ALTER TABLE `image_series` ADD CONSTRAINT `series_image_series`
    FOREIGN KEY (`series_id`) REFERENCES `series` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
