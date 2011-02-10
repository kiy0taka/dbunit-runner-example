package example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class EmpDao {

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @SuppressWarnings("unchecked")
    public List<Emp> findAll() throws SQLException {
        return (List<Emp>) new QueryRunner().query(
            connection, 
            "select * from emp",
            new BeanListHandler(Emp.class));
    }

    public Emp findByEmpno(int empno) throws SQLException {
        return (Emp) new QueryRunner().query(
            connection,
            "select * from emp where empno=?",
            new BeanHandler(Emp.class),
            new Object[] { empno });
    }

    public int insert(Emp emp) throws SQLException {
        return new QueryRunner().update(
            connection,
            "insert into emp (empno, ename, hiredate, sal) values (?, ?, ?, ?)",
            new Object[] {
                emp.getEmpno(),
                emp.getEname(),
                emp.getHiredate(),
                emp.getSal()
            });
    }

    public int update(Emp emp) throws SQLException {
        return new QueryRunner().update(
            connection,
            "update emp set ename=?, hiredate=?, sal=? where empno=?",
            new Object[] {
                emp.getEname(),
                emp.getHiredate(),
                emp.getSal(),
                emp.getEmpno()
            });
    }

    public int delete(int empno) throws SQLException {
        return new QueryRunner().update(
            connection,
            "delete from emp where empno=?",
            empno);
    }

    public long newSequence() throws SQLException {
        return (Long) new QueryRunner().query(connection, "select nextval('my_seq')", new ScalarHandler());
    }
}
