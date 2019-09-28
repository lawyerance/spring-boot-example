package pers.lyks.example.controller;

import io.swagger.annotations.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.lyks.example.service.EmployeeService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @ApiOperation(value = "复杂查询", notes = "多参数和返回值查询")
    @RequestMapping(value = "/pages", method = RequestMethod.POST)
    public Output<PageData<String>> search(@ApiParam(value = "input parameter object", required = true) @RequestBody Input input) {
        Output<PageData<String>> pageDataOutput = new Output<>();
        pageDataOutput.setCode(200);
        pageDataOutput.setMessage("OK");
        PageData<String> pageData = new PageData<>();
        List<String> result = new ArrayList<>();
        result.add("1222");
        result.add("456");
        pageData.setList(result);
        pageDataOutput.setData(pageData);
        return pageDataOutput;
    }

    @ApiModel(value = "pagination object", description = "pagination")
    @Getter
    @Setter
    public static class Page {
        @ApiParam(value = "page index", defaultValue = "1", example = "1")
        @ApiModelProperty(value = "page index", example = "1")
        private int pageNo;
        @ApiParam(value = "page size", defaultValue = "20", example = "2o")
        private int pageSize;

    }

    @ApiModel(value = "page index", description = "the index of search data page")
    @Getter
    @Setter
    public static class Input {
        @ApiParam(value = "search key", required = true, example = "key")
        private String key;
        @ApiParam(value = "search value", required = true, example = "value")
        private String value;
        private Page page;
    }

    @ApiModel(value = "common response object", description = "common response object contain any data")
    @Getter
    @Setter
    public static class Output<T> {
        @ApiParam(value = "return response code", required = true, example = "200")
        private int code;
        @ApiParam(value = "return response message", required = true, example = "OK")
        private String message;
        private T data;
    }

    @ApiModel(value = "page data object with data", description = "page data with collection data")
    @Getter
    @Setter
    public static class PageData<T> extends Page {
        @ApiModelProperty(value = "page collection data", required = true, example = "[]")
        private List<T> list;
    }
}
