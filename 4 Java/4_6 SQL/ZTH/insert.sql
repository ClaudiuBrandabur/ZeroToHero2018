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
