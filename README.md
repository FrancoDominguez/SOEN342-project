# SOEN 342 - Project

## Members

- **Franco Dominguez - 40256199**
- **Ashkan Forghani - 40176561**

### Framework and Languages

### First Deliverable - 10/18/2024

- UML domain model + package diagram
- System sequence diagram(s) to capture success and failure scenarios
- Identification of system operations and operation contracts
- UML interaction diagrams
- UML class diagram
- Implementation

## How to run

1. Install maven
2. run `mvn package`
3. run `java -jar target/lessonbooking-1.0-SNAPSHOT.jar`

note: if you are on wsl, you can alias `alias runjava='mvn clean package && java -jar target/lessonbooking-1.0-SNAPSHOT.jar'`, then simply run `runjava` to compile and run the entire thing (still from the backend directory).

mysqldump -u admin -p lessonbooking > sql_dump.sql

mysqldump -h franco-db.czes8i20a6iw.us-east-1.rds.amazonaws.com -P 3306 -u admin -p