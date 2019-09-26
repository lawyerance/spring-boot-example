package pers.lyks.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pers.lyks.example.service.EmployeeService;

import javax.annotation.Resource;

/**
 * @author lawyerance
 * @version 1.0 2018-08-04
 */
@RestController
@RequestMapping(value = "/employee")
@Api(value = "员工信息", tags = "employee operation")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @ApiOperation(value = "Query employee details", notes = "Obtain employee detail information according to id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable long id) {
        return new ResponseEntity<>(employeeService.get(id), HttpStatus.OK);
    }
}
