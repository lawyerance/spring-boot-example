package pers.lyks.spring.example

import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.servlet.MvcResult

import java.io.BufferedWriter
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * @author lawyerance
 * @version 1.0 2019-09-19
 */
class Swagger2markupTest : BaseRestTest() {
    @Test
    @Throws(Exception::class)
    fun createSpringfoxSwaggerJson() {
        //String designFirstSwaggerLocation = Swagger2MarkupTest.class.getResource("/swagger.yaml").getPath();
        //获取生成swagger.json路径，已经在build.gradle中配置
        val outputDir = System.getProperty("io.springfox.staticdocs.outputDir")
        //本项目api路径
        val mvcResult = this.mockMvc.perform(get("/v2/api-docs")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andReturn()

        val response = mvcResult.response
        val swaggerJson = response.contentAsString
        Files.createDirectories(Paths.get(outputDir))
        Files.newBufferedWriter(Paths.get(outputDir, "swagger.json"), StandardCharsets.UTF_8).use { writer -> writer.write(swaggerJson) }
    }
}
