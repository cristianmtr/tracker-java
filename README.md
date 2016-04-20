# TASKER #

A pretentious, useless task manager, with overly complicated REST backend. Using redis, sqlalchemy, Jersey.

## INSTALL ##

- Clone the repos;
    - this one
    - the database: [here](https://github.com/cristianmtr/tracker-db)
    - the frontend: [here](https://github.com/cristianmtr/tracker-frontend)
    - Make sure the directories match the ones mentioned in the nginx configuration file;
- Install:
    - PostgreSQL;
    - Redis;
    - JDK 8;
    - Maven;
    - sqlalchemy;
    - nginx;
- Create database “tracker” in PostgreSQL;
- Make sure the demo data in the Python script suits your needs;
- Set the environment variables;
    - PSQLUSER: the username for the db;
    - PSQLPASSWORD: the password;
- Execute the schema creation and demo data scripts;
- Compile the Java code using Maven, then run it;
- Start the redis server;
- Copy the nginx configuration file to the proper location and initialize it;
- You can now connect to the site using ``cristian:cristian``;


