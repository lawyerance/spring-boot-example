package pers.lyks.spring.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pers.lyks.spring.example.base.BaseController;
import pers.lyks.spring.example.bean.CommonResponse;
import pers.lyks.spring.example.service.EmployeeService;

/**
 * @author lawyerance
 * @version 1.0 2018-08-04
 */
@RestController
@RequestMapping(value = "/employee")
@Api("员工信息")
public class EmployeeController extends BaseController {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "查看单个员工信息",notes = "通过ID查看员工信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public CommonResponse get(@PathVariable long id) {
        return success(employeeService.get(id));
    }
}
