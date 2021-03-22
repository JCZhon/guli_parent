package com.zjh.eduService.filter.badLanguageHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.List;

/**
 * 处理请求中的脏话字眼
 */
public class RequestUtils extends HttpServletRequestWrapper {
    public RequestUtils(HttpServletRequest request) {
        super(request);
    }

    public String getParameter(String name) {
        String str = super.getParameter(name);
        List<String> list = GetWord.getBadWords();
        for (String word :
                list) {
            str = str.replaceAll(word, "**");
        }
        return str;
    }
}
