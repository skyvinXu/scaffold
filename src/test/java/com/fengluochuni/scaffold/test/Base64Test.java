package com.fengluochuni.scaffold.test;

import com.fengluochuni.scaffold.commons.utils.Charsets;
import org.springframework.util.Base64Utils;

import java.util.Arrays;

public class Base64Test {

    /**
     * Shiro 记住密码采用的是AES加密，AES key length 需要是16位，该方法生成16位的key
     */
    public static void main(String[] args) {
        String keyStr = "如梦技术";
        
        byte[] keys = keyStr.getBytes(Charsets.UTF_8);
        
        System.out.println(Base64Utils.encodeToString(Arrays.copyOf(keys, 16)));
    }

}
