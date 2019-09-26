package pers.lyks.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lyks.example.mapper.EmployeeMapper;
import pers.lyks.example.model.EmployeeModel;
import pers.lyks.example.service.EmployeeService;

import javax.annotation.Resource;

/**
 * @author lawyerance
 * @version 1.0 2018-08-04
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public EmployeeModel get(long id) {
        return employeeMapper.get(id);
    }
}
