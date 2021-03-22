package com.zjh.eduService.controller;

import cn.hutool.json.JSONUtil;
import com.zjh.eduService.entity.EduCourse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class Test {
    public static void main(String[] args) {

   /*     Test.stringTest();
        Test.multiplicationForm();
        Test.TrinocularOperation();
        Test.calculation();

        Test.noLengthParameter("第一个参数", 1, 2, 3, 4);

        System.out.println("----------使用递归实现阶乘---------");
       */
        Test.transferTest();
        Test.iteratorTest();
        int result = Test.testRecursion(4);
        System.out.println(result);
    }

    public static void multiplicationForm() {
        System.out.println("-----------乘法口诀表----------");
        int m;
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                m = i * j;
                System.out.print(j + "*" + i + "=" + m + "  ");
            }
            System.out.println("\t");
        }
    }

    public static void TrinocularOperation() {
        System.out.println("--------------三目运算-----------");
        int a = 12;
        int b = 20;
        int c;
        c = a > b ? a : b;
        System.out.println(c);
    }

    public static void stringTest() {
        System.out.println("-----------StringBuilder和StringBuffer---------");

        String a = "zhongjiahua";

        System.out.println(a.startsWith(""));
        System.out.println(a.toUpperCase());

        StringBuffer b = new StringBuffer("zhongjiaqing");
        b.append("aaaaaaaaa");
        System.out.println(b);

        StringBuilder c = new StringBuilder("zhongtao");
        c.append("cccccccccccc");
        System.out.println("c=" + c);
    }

    public static void calculation() {
        System.out.println("---------测试赋值运算符---------");
        for (int i = 1; i <= 10; i++) {
            i += 1;
            System.out.println(i);
        }
    }

    public static void noLengthParameter(String str, int... args) {
        System.out.println("------------测试不定长参数-----------");
        System.out.println(str);
        for (int arg : args) {
            System.out.println(arg);
        }
    }

    //使用递归实现阶乘
    public static int testRecursion(int n) {
        //n==1就是递归的出口，n=1时直接返回确定的结果
        if (n == 1) {
            return n;
        } else {
            return n * testRecursion(n - 1);
        }
    }

    //值传递
    public static void transferTest() {
        String str = "111111";
        str = "222222222";
        System.out.println(str);
    }

    //使用遍历器，只能单向遍历
    public static void iteratorTest() {
        List<String> list = new ArrayList<>();
        list.add("我");
        list.add("叫");
        list.add("钟");
        list.add("家");
        list.add("华");
        System.out.println(list.toString());
        Iterator i = list.iterator();
        String a = "";
        while (i.hasNext()) {
            String obj = (String) i.next();
            System.out.println(obj);
            a = a + obj;
        }
        System.out.println(a);
    }
}
