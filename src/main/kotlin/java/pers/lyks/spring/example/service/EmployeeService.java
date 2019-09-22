package pers.lyks.spring.example.service;

import pers.lyks.spring.example.model.EmployeeModel;

/**
 * @author lawyerance
 * @version 1.0 2018-08-04
 */
public interface EmployeeService {
    EmployeeModel get(long id);
}
