CREATE USER botteasorin IDENTIFIED BY oracle; -- you should replace my user name with yours. “IDENTIFIED BY” means the password.
GRANT CREATE SESSION TO botteasorin; -- necessary for connecting to the database with your new user.
GRANT CREATE ANY INDEX TO botteasorin; -- we use indexes for faster queries.
GRANT ALTER ANY INDEX TO botteasorin;
GRANT DROP ANY INDEX TO botteasorin;
GRANT CREATE ANY PROCEDURE TO botteasorin; -- we will use them in following workshops.
GRANT ALTER ANY PROCEDURE TO botteasorin;
GRANT DROP ANY PROCEDURE TO botteasorin;
GRANT EXECUTE ANY PROCEDURE TO botteasorin;
GRANT CREATE ANY SEQUENCE TO botteasorin; -- we need sequences for inserting data.
GRANT ALTER ANY SEQUENCE TO botteasorin;
GRANT DROP ANY SEQUENCE TO botteasorin;
GRANT SELECT ANY SEQUENCE TO botteasorin;
GRANT CREATE ANY TABLE TO botteasorin;
GRANT ALTER ANY TABLE TO botteasorin;
GRANT DELETE ANY TABLE TO botteasorin;
GRANT DROP ANY TABLE TO botteasorin;
GRANT INSERT ANY TABLE TO botteasorin;
GRANT LOCK ANY TABLE TO botteasorin;
GRANT SELECT ANY TABLE TO botteasorin;
GRANT UPDATE ANY TABLE TO botteasorin;
GRANT CREATE TABLESPACE TO botteasorin;
GRANT ALTER TABLESPACE TO botteasorin;
GRANT DROP TABLESPACE TO botteasorin;
GRANT CREATE ANY VIEW TO botteasorin;
GRANT DROP ANY VIEW TO botteasorin;
GRANT UNDER ANY VIEW TO botteasorin;
alter user botteasorin quota 50m on botteasorin;
