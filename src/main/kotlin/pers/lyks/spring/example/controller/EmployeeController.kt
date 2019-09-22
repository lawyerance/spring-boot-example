package pers.lyks.spring.example.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import pers.lyks.spring.example.model.EmployeeModel
import pers.lyks.spring.example.service.EmployeeService

import javax.annotation.Resource

/**
 * @author lawyerance
 * @version 1.0 2018-08-04
 */
@RestController
@RequestMapping(value = ["/employee"])
@Api(value = "员工信息", tags = ["employee operation"])
class EmployeeController {

    @Resource
    private val employeeService: EmployeeService? = null

    @ApiOperation(value = "Query employee details", notes = "Obtain employee detail information according to id")
    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET])
    operator fun get(@PathVariable id: Long): ResponseEntity<*> {
        return ResponseEntity<EmployeeModel>(employeeService!!.get(id), HttpStatus.OK)
    }
}
