package com.fengluochuni.scaffold.test;

import com.fengluochuni.scaffold.test.base.BaseTest;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.net.URL;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Swagger2测试
 *
 * @author rongsheng.xu
 */
public class Swagger2Tests extends BaseTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    /**
     * 初始化 MOCK
     */
    @Before
    public void init() {
        mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void generateAsciiDocs() throws Exception {
        //    输出Ascii格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withGeneratedExamples()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .build();

        Swagger2MarkupConverter.from(new URL("http://localhost:8080/v2/api-docs"))
                .withConfig(config)
                .build()
                .toFile(Paths.get("src/docs/asciidoc/generated/api"));
    }

    /*@Test
    public void generatePDF() {
        //样式
        String style = "pdf-style=E:\\themes\\theme.yml";
        //字体
        String fontsdir = "pdf-fontsdir=E:\\fonts";
        //需要指定adoc文件位置
        String adocPath = "E:\\all.adoc";
        AsciidoctorInvoker.main(new String[]{"-a",style,"-a",fontsdir,"-b","pdf",adocPath});
    }*/
}
