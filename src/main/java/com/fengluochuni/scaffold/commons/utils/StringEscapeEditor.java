package com.fengluochuni.scaffold.commons.utils;

import org.springframework.web.util.HtmlUtils;

import java.beans.PropertyEditorSupport;

/**
 * 字符串Xss处理类
 */
public class StringEscapeEditor extends PropertyEditorSupport {
    public StringEscapeEditor() {}

    @Override
    public String getAsText() {
        Object value = getValue();
        return value != null ? value.toString() : "";
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null) {
            setValue(null);
        } else {
            setValue(HtmlUtils.htmlEscape(text));
        }
    }

}
