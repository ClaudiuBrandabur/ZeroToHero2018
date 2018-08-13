CREATE USER system IDENTIFIED BY andrei123; -- you should replace my user name with yours. “IDENTIFIED BY” means the password.
GRANT CREATE SESSION TO system; -- necessary for connecting to the database with your new user.
GRANT CREATE ANY INDEX TO system; -- we use indexes for faster queries.
GRANT ALTER ANY INDEX TO system;
GRANT DROP ANY INDEX TO system;
GRANT CREATE ANY PROCEDURE TO system; -- we will use them in following workshops.
GRANT ALTER ANY PROCEDURE TO system;
GRANT DROP ANY PROCEDURE TO system;
GRANT EXECUTE ANY PROCEDURE TO system;
GRANT CREATE ANY SEQUENCE TO system; -- we need sequences for inserting data.
GRANT ALTER ANY SEQUENCE TO system;
GRANT DROP ANY SEQUENCE TO system;
GRANT SELECT ANY SEQUENCE TO system;
GRANT CREATE ANY TABLE TO system;
GRANT ALTER ANY TABLE TO system;
GRANT DELETE ANY TABLE TO system;
GRANT DROP ANY TABLE TO system;
GRANT INSERT ANY TABLE TO system;
GRANT LOCK ANY TABLE TO system;
GRANT SELECT ANY TABLE TO system;
GRANT UPDATE ANY TABLE TO system;
GRANT CREATE TABLESPACE TO system;
GRANT ALTER TABLESPACE TO system;
GRANT DROP TABLESPACE TO system;
GRANT CREATE ANY VIEW TO system;
GRANT DROP ANY VIEW TO system;
GRANT UNDER ANY VIEW TO system;
alter user system quota 50m on system;
