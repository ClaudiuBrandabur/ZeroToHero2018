CREATE USER andreeag IDENTIFIED BY andreeag; -- you should replace my user name with yours. “IDENTIFIED BY” means the password.
GRANT CREATE SESSION TO andreeag; -- necessary for connecting to the database with your new user.
GRANT CREATE ANY INDEX TO andreeag; -- we use indexes for faster queries.
GRANT ALTER ANY INDEX TO andreeag;
GRANT DROP ANY INDEX TO andreeag;
GRANT CREATE ANY PROCEDURE TO andreeag; -- we will use them in following workshops.
GRANT ALTER ANY PROCEDURE TO andreeag;
GRANT DROP ANY PROCEDURE TO andreeag;
GRANT EXECUTE ANY PROCEDURE TO andreeag;
GRANT CREATE ANY SEQUENCE TO andreeag; -- we need sequences for inserting data.
GRANT ALTER ANY SEQUENCE TO andreeag;
GRANT DROP ANY SEQUENCE TO andreeag;
GRANT SELECT ANY SEQUENCE TO andreeag;
GRANT CREATE ANY TABLE TO andreeag;
GRANT ALTER ANY TABLE TO andreeag;
GRANT DELETE ANY TABLE TO andreeag;
GRANT DROP ANY TABLE TO andreeag;
GRANT INSERT ANY TABLE TO andreeag;
GRANT LOCK ANY TABLE TO andreeag;
GRANT SELECT ANY TABLE TO andreeag;
GRANT UPDATE ANY TABLE TO andreeag;
GRANT CREATE TABLESPACE TO andreeag;
GRANT ALTER TABLESPACE TO andreeag;
GRANT DROP TABLESPACE TO andreeag;
GRANT CREATE ANY VIEW TO andreeag;
GRANT DROP ANY VIEW TO andreeag;
GRANT UNDER ANY VIEW TO andreeag;
alter user andreeag quota 50m on system;
