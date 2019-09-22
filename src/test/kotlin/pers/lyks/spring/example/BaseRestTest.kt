package pers.lyks.spring.example

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.restdocs.JUnitRestDocumentation
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

import javax.annotation.Resource

import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder

/**
 * @author lawyerance
 * @version 1.0 2019-09-19
 */
@RunWith(SpringRunner::class)
@SpringBootTest
abstract class BaseRestTest {
    @Rule
    @JvmField
    var restDocumentation = JUnitRestDocumentation()

    @Resource
    private val context: WebApplicationContext? = null

    protected lateinit var mockMvc: MockMvc;

    @Before
    fun before() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context!!)
                .apply<DefaultMockMvcBuilder>(documentationConfiguration(this.restDocumentation))
                .build()
    }

    @Test
    fun doNothing() {

    }
}
