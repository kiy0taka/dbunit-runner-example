SET SCHEMA dev;
 
DROP TABLE IF EXISTS emp;
CREATE TABLE emp (
    empno INTEGER PRIMARY KEY,
    ename VARCHAR(10),
    hiredate DATE,
    sal DECIMAL(7,2)
);
