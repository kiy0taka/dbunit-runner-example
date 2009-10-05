package example;

import java.math.BigDecimal;
import java.sql.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Emp {

    private int empno;

    private String ename;

    private Date hiredate;

    private BigDecimal sal;

    public int getEmpno() {
        return empno;
    }

    public String getEname() {
        return ename;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public BigDecimal getSal() {
        return sal;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public void setSal(BigDecimal sal) {
        this.sal = sal;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
