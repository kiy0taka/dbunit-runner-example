package example;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.h2.security.AES;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.kiy0taka.dbunit.DbUnitRunner;
import org.kiy0taka.dbunit.DbUnitTest;
import org.kiy0taka.dbunit.TestConnection;

@RunWith(DbUnitRunner.class)
public class EmpDaoTest {

    @TestConnection
    private Connection connection;

    private EmpDao dao;

    @Before
    public void setUp() {
        dao = new EmpDao();
        dao.setConnection(connection);
    }

    @DbUnitTest(init="emp.xml")
    public void findAll() throws SQLException {

        Emp foo = new Emp();
        foo.setEmpno(1);
        foo.setEname("foo");
        foo.setHiredate(Date.valueOf("2009-04-01"));
        foo.setSal(new BigDecimal("100.00"));

        Emp bar = new Emp();
        bar.setEmpno(2);
        bar.setEname("bar");
        bar.setHiredate(Date.valueOf("2009-07-01"));
        bar.setSal(new BigDecimal("130.00"));

        List<Emp> expected = Arrays.asList(foo, bar);
        List<Emp> actual = dao.findAll();

        assertEquals(expected, actual);
    }

    @DbUnitTest(init="emp.xml")
    public void findByPrimeryKey() throws SQLException {

        Emp expected = new Emp();
        expected.setEmpno(1);
        expected.setEname("foo");
        expected.setHiredate(Date.valueOf("2009-04-01"));
        expected.setSal(new BigDecimal("100.00"));

        Emp actual = dao.findByEmpno(1);

        assertEquals(expected, actual);
    }

    @DbUnitTest(init="emp.xml", expected="insert.xml")
    public void insert() throws SQLException {

        Emp newEmp = new Emp();
        newEmp.setEmpno(3);
        newEmp.setEname("baz");
        newEmp.setHiredate(Date.valueOf("2009-10-01"));
        newEmp.setSal(new BigDecimal("80.00"));

        int count = dao.insert(newEmp);

        assertEquals(1, count);
    }

    @DbUnitTest(init="emp.xml", expected="update.xml")
    public void update() throws SQLException {

        Emp bar = new Emp();
        bar.setEmpno(2);
        bar.setEname("bar");
        bar.setHiredate(Date.valueOf("2009-07-01"));
        bar.setSal(new BigDecimal("150.00"));

        int count = dao.update(bar);

        assertEquals(1, count);
    }

    @DbUnitTest(init="emp.xml", expected="delete.xml")
    public void delete() throws SQLException {

        int count = dao.delete(2);

        assertEquals(1, count);
    }

    @DbUnitTest(init="emp.xml", sql="alter sequence my_seq restart with 100")
    public void newSequence() throws SQLException {
        assertEquals(100L, dao.newSequence());
    }
}
