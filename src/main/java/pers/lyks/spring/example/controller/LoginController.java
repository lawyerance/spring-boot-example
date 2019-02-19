package pers.lyks.spring.example.controller;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.lyks.spring.example.base.BaseController;
import pers.lyks.spring.example.bean.CommonResponse;


/**
 * @author lawyerance
 * @version 1.0 2018-08-18
 */
@RestController
@Validated
public class LoginController extends BaseController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResponse login(@RequestParam(value = "username") @Length(min = 5, max = 16) String username, @RequestParam(value = "pssword") String password) {
        if ("admin".equals(username)) {
            if ("123456".equals(password)) {
                return success();
            }
            return error(10003, "password is matched to username: " + username);
        }
        return error(10002, "user not exist: " + username);
    }
}
