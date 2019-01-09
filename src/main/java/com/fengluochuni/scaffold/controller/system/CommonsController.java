package com.fengluochuni.scaffold.controller.system;

import com.fengluochuni.scaffold.commons.shiro.captcha.DreamCaptcha;
import net.dreamlu.module.ueditor.UeditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用的控制器
 *
 * @author L.cm
 */
@Controller
public class CommonsController {
    @Autowired
    private DreamCaptcha dreamCaptcha;
    @Autowired
    private UeditorService ueditorService;
    
    /**
     * 图标页
     */
    @GetMapping("icons.html")
    public String icons() {
        return "icons";
    }
    
    /**
     * 图形验证码
     */
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        dreamCaptcha.generate(request, response);
    }
    
    /**
     * ueditor编辑器
     */
    @GetMapping("ueditor")
    public ResponseEntity<String> ueditor(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        String json = ueditorService.exec(request);
        return new ResponseEntity<String>(json, headers, HttpStatus.OK);
    }
}
