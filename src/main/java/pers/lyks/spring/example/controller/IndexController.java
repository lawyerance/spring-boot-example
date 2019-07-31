package pers.lyks.spring.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.lyks.spring.example.base.BaseController;
import pers.lyks.spring.example.bean.CommonResponse;
import pers.lyks.spring.example.strategy.CalculateContext;
import pers.lyks.spring.example.strategy.CalculateStrategy;

import javax.annotation.Resource;

/**
 * @author lawyerance
 * @version 1.0 2018-08-03
 */
@RestController
public class IndexController extends BaseController {

    @RequestMapping(value = "/index/{word}", method = RequestMethod.GET)
    public CommonResponse<String> hello(@PathVariable String word) {
        if ("shit".equalsIgnoreCase(word)) {
            return error(10001, new Object[]{word});
        }
        return success(null, "hello " + word);
    }

    @Resource
    private CalculateContext calculateContext;

    @RequestMapping(value = "/calculate", method = RequestMethod.GET)
    public CommonResponse<Number> hello(@RequestParam("type")String type,@RequestParam("first")Number first,@RequestParam("second")Number second) {
        CalculateStrategy strategy =  calculateContext.getInstance(type);
        return success(null, strategy.calculate(first,second));
    }
}
