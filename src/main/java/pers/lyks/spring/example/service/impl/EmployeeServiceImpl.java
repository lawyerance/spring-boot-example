package pers.lyks.spring.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lyks.spring.example.dao.EmployeeDao;
import pers.lyks.spring.example.model.EmployeeModel;
import pers.lyks.spring.example.service.EmployeeService;

/**
 * @author lawyerance
 * @version 1.0 2018-08-04
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public EmployeeModel get(long id) {
        return employeeDao.get(id);
    }
}
