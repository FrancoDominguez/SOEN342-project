
# offerings table
ALTER TABLE offerings ADD COLUMN private_public VARCHAR(30), ADD COLUMN is_available BOOLEAN, ADD COLUMN start_time DATETIME, ADD COLUMN end_time DATETIME, ADD COLUMN location_id INT, ADD COLUMN max_num_of_participants INT, ADD COLUMN num_of_participants INT, ADD FOREIGN KEY (location_id) REFERENCES locations(id);

#locations table
CREATE TABLE locations (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100), address VARCHAR(100), city VARCHAR(100))

# association table for offerings and instructors
CREATE TABLE offering_instructor (offering_id INT NOT NULL, instructor_id INT NOT NULL, PRIMARY KEY (offering_id, instructor_id), FOREIGN KEY (offering_id) REFERENCES offerings(id), FOREIGN KEY (instructor_id) REFERENCES instructors(id));

# instructors table
CREATE TABLE instructors (id INT AUTO_INCREMENT PRIMARY KEY, firstname VARCHAR(100) NOT NULL, lastname VARCHAR(100) NOT NULL, username VARCHAR(100) NOT NULL UNIQUE, password VARCHAR(255) NOT NULL, phone_number VARCHAR(15) NOT NULL, date_of_birth DATE NOT NULL, specialization VARCHAR(100), city VARCHAR(100));
ALTER TABLE instructors DROP COLUMN city;
# specialization and city

# cities table
CREATE TABLE cities (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), instructor_id INT, FOREIGN KEY (instructor_id) REFERENCES instructors(id));

#administrators table
CREATE TABLE administrators (id INT AUTO_INCREMENT PRIMARY KEY, firstname VARCHAR(100) NOT NULL, lastname VARCHAR(100) NOT NULL, username VARCHAR(100) NOT NULL UNIQUE, password VARCHAR(255) NOT NULL, phone_number VARCHAR(15) NOT NULL, date_of_birth DATE NOT NULL);

CREATE TABLE timeslots (id INT AUTO_INCREMENT PRIMARY KEY, location_id INT NOT NULL, start_time DATETIME NOT NULL, end_time DATETIME NOT NULL, FOREIGN KEY (location_id) REFERENCES locations(id));






ALTER TABLE clients MODIFY username VARCHAR(255) NOT NULL;