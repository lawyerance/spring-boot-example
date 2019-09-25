package pers.lyks.example.service.impl;

import org.springframework.stereotype.Service;
import pers.lyks.example.model.EmployeeModel;
import pers.lyks.example.service.EmployeeService;

/**
 * @author lawyerance
 * @version 1.0 2018-08-04
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Override
    public EmployeeModel get(long id) {
        EmployeeModel model = new EmployeeModel();
        model.setId(id);
        model.setAge(25);
        model.setEmpno("10");
        return model;
    }
}
