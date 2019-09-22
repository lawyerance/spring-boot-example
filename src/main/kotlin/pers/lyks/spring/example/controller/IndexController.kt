package pers.lyks.spring.example.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pers.lyks.spring.example.strategy.CalculateContext
import pers.lyks.spring.example.strategy.CalculateStrategy

import javax.annotation.Resource

/**
 * @author lawyerance
 * @version 1.0 2018-08-03
 */
@RestController
class IndexController {

    @Resource
    private val calculateContext: CalculateContext? = null

    @RequestMapping(value = "/index/{word}", method = [RequestMethod.GET])
    fun hello(@PathVariable word: String): ResponseEntity<String> {

        return ResponseEntity("hello $word", HttpStatus.OK)
    }

    @RequestMapping(value = "/calculate", method = [RequestMethod.GET])
    fun hello(@RequestParam("type") type: String, @RequestParam("first") first: Number, @RequestParam("second") second: Number): ResponseEntity<Number> {
        val strategy = calculateContext!!.getInstance<Number, Number>(type)
        return ResponseEntity(strategy.calculate(first, second), HttpStatus.OK)
    }
}
