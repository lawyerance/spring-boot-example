package pers.lyks.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.lyks.example.strategy.CalculateContext;
import pers.lyks.example.strategy.CalculateStrategy;

import javax.annotation.Resource;

/**
 * @author lawyerance
 * @version 1.0 2018-08-03
 */
@RestController
public class IndexController {

    @RequestMapping(value = "/index/{word}", method = RequestMethod.GET)
    public ResponseEntity<String> hello(@PathVariable String word) {

        return new ResponseEntity<>("hello " + word, HttpStatus.OK);
    }

    @Resource
    private CalculateContext calculateContext;

    @RequestMapping(value = "/calculate", method = RequestMethod.GET)
    public ResponseEntity<Number> hello(@RequestParam("type") String type, @RequestParam("first") Number first, @RequestParam("second") Number second) {
        CalculateStrategy strategy = calculateContext.getInstance(type);
        return new ResponseEntity<>(strategy.calculate(first, second), HttpStatus.OK);
    }
}
