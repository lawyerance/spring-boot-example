package pers.lyks.spring.example.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pers.lyks.spring.example.mapper.EmployeeMapper
import pers.lyks.spring.example.model.EmployeeModel
import pers.lyks.spring.example.service.EmployeeService

import javax.annotation.Resource

/**
 * @author lawyerance
 * @version 1.0 2018-08-04
 */
@Service
class EmployeeServiceImpl : EmployeeService {
    @Resource
    private val employeeMapper: EmployeeMapper? = null

    override fun get(id: Long): EmployeeModel {
        return employeeMapper!![id]
    }
}
