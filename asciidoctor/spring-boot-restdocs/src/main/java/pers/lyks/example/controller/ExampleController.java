package pers.lyks.example.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pers.lyks.example.resp.CommonResponse;

/**
 * @author lawyerance
 * @version 1.0 2019-09-28
 */
@RestController
public class ExampleController {

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public CommonResponse<String> hello(@PathVariable("name") String name) {
        CommonResponse<String> resp = new CommonResponse<>();
        resp.setCode(200);
        resp.setMessage("OK");
        resp.setData(String.format("hello, %s", name));
        return resp;
    }
}
