package pers.lyks.spring.example.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pers.lyks.spring.example.base.BaseController;
import pers.lyks.spring.example.bean.CommonResponse;

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
}
