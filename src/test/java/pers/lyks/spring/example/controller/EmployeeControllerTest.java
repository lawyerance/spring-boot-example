package pers.lyks.spring.example.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pers.lyks.spring.example.BaseRestTest;
import pers.lyks.spring.example.model.EmployeeModel;
import pers.lyks.spring.example.service.EmployeeService;

import javax.annotation.Resource;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author lawyerance
 * @version 1.0 2019-09-18
 */
public class EmployeeControllerTest  extends BaseRestTest {

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void getEmploy() throws Exception {
        long id = 2L;
        final EmployeeModel model = new EmployeeModel();
        model.setAge(18);
        model.setId(id);
        //mock
        given(employeeService.get(Mockito.anyLong())).willReturn(model);

        this.mockMvc.perform(get("/employee/{id}", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(status().isOk()).andDo(print());


    }
}
