package pers.lyks.spring.example.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lawyerance
 * @version 1.0 2018-08-04
 */
public class EmployeeModel implements Serializable {
    private long id;
    private String empno;
    private String name;
    private int age;
    private Date birthday;
    private BigDecimal salary;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmpno() {
        return empno;
    }

    public void setEmpno(String empno) {
        this.empno = empno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
