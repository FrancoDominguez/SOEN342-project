SELECT clients.id AS client_id, clients.username, clients.firstname, clients.lastname, offerings.id AS offering_id, offerings.lesson_type, offerings.private_public, offerings.is_available, offerings.max_participants, offerings.participants, offerings.start_time, offerings.end_time, locations.id AS location_id, locations.name AS location_name, locations.address, instructors.id AS instructor_id, instructors.firstname AS instructor_firstname, instructors.lastname AS instructor_lastname FROM bookings JOIN clients ON bookings.client_id = clients.id JOIN offerings ON bookings.offering_id = offerings.id JOIN locations ON offerings.location_id = locations.id JOIN instructors ON offerings.instructor_id = instructors.id WHERE bookings.client_id = 1;







