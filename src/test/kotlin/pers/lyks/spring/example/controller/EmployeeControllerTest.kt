package pers.lyks.spring.example.controller

import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import pers.lyks.spring.example.BaseRestTest
import pers.lyks.spring.example.model.EmployeeModel
import pers.lyks.spring.example.service.EmployeeService

/**
 * @author lawyerance
 * @version 1.0 2019-09-18
 */
class EmployeeControllerTest : BaseRestTest() {

    @MockBean
    private val employeeService: EmployeeService? = null

    @Test
    @Throws(Exception::class)
    fun getEmploy() {
        val id = 2L
        val model = EmployeeModel()
        model.age = 18
        model.id = id
        //mock
        given(employeeService!![Mockito.anyLong()]).willReturn(model)

        this.mockMvc.perform(get("/employee/{id}", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(status().isOk).andDo(print())


    }
}
