package com.fengluochuni.test;

import com.fengluochuni.test.base.BaseTest;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Paths;

public class Swagger2Tests extends BaseTest {

    @Test
    public void generateAsciiDocs() throws Exception {
        //    输出Ascii格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withGeneratedExamples()
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
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
