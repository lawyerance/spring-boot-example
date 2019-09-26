package pers.lyks.example.controller;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import pers.lyks.example.BaseRestTest;
import pers.lyks.example.model.EmployeeModel;
import pers.lyks.example.service.EmployeeService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author lawyerance
 * @version 1.0 2019-09-18
 */
public class EmployeeControllerTest extends BaseRestTest {

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
