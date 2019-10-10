package pers.lyks.example.controller;

import org.junit.Test;
import pers.lyks.example.BaseRestTest;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author lawyerance
 * @version 1.0 2019-09-28
 */
public class ExampleControllerTest extends BaseRestTest {

    @Test
    public void hello() throws Exception {
        String value = "world";
        this.mockMvc.perform(get("/hello/{name}", value))
                .andExpect(status().isOk())
                .andDo(document("{method-name}",
                        pathParameters(parameterWithName("name").description("hello message"))))
                .andDo(print());
    }
}