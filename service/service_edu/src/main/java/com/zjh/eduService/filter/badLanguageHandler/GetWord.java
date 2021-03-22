package com.zjh.eduService.filter.badLanguageHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 获得脏话字眼
 */
public class GetWord {
    private static List<String> badWords = new ArrayList<>();

    static {
        badWords.add("骂人");
        badWords.add("sb");
        badWords.add("傻逼");
    }

    public static List<String> getBadWords() {
        return badWords;
    }
}
