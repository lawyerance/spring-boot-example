package pers.lyks.example.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lawyerance
 * @version 1.0 2018-08-04
 */
@Data
public class EmployeeModel implements Serializable {
    private long id;
    private String empno;
    private String name;
    private int age;
    private Date birthday;
    private BigDecimal salary;
}
