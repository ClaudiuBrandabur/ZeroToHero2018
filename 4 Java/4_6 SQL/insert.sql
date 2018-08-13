ALTER TABLE EMPLOYEES ADD CONSTRAINT FK_EMPLOYEES_DEPARTAMENTS FOREIGN KEY (DEPARTMENT_ID) REFERENCES DEPARTMENTS(DEPARTMENT_ID);
ALTER TABLE EMPLOYEES ADD CONSTRAINT FK_EMPLOYEES_JOBS FOREIGN KEY (JOB_ID) REFERENCES JOBS(JOB_ID);
ALTER TABLE EMPLOYEES ADD CONSTRAINT FK_EMPLOYEES_EMPL_MANAGER FOREIGN KEY (MANAGER_ID) REFERENCES EMPLOYEES (EMPLOYEE_ID);

-- ALTER TABLE DEPARTMENTS ADD FOREIGN KEY (LOCATION_ID) REFERENCES LOCATIONS(LOCATION_ID);

CREATE SEQUENCE TAB_DEPARTMENTS_SEQ
  START WITH     1
  INCREMENT BY   1;
CREATE SEQUENCE TAB_EMPLOYEES_SEQ
  START WITH     1
  INCREMENT BY   1;
CREATE SEQUENCE TAB_JOBS_SEQ
  START WITH     1
  INCREMENT BY   1;
CREATE SEQUENCE ZTH_SEQ
  START WITH     406
  INCREMENT BY   1;

insert into departments values
(  TAB_DEPARTMENTS_SEQ.nextval,
   'Administration',
   1700
);

insert into jobs values
(TAB_JOBS_SEQ.nextval
    	    , 'President'
     	    , 20000
          , 40000
);

INSERT INTO employees
     		VALUES (TAB_EMPLOYEES_SEQ.nextval,
             	  'Steven',
                    'King',
                    'SKING',
                    '515.123.4567',
                    sysdate,
                    'AD_PRES',--value from second insert
                    24000,
                    NULL,
                    NULL,
                    90);--value from first insert
rollback;


commit;

