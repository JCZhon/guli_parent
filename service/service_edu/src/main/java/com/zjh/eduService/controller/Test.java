package com.zjh.eduService.controller;

import cn.hutool.json.JSONUtil;
import com.zjh.eduService.entity.EduCourse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *个人练习测试1
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

        DoubleKey doubleKey = DoubleKey.getInstance();

        Holder holder = Holder.getInstance();

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


/**
 * 懒汉式
 */
class Hungry {
    private Hungry() {
    }

    private static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance() {
        return HUNGRY;
    }
}

/**
 * 饿汉式,线程不安全
 */
class LazyMan {
    private LazyMan() {
    }

    private static LazyMan LAZYMAN;

    public static LazyMan getInstance() {
        if (LAZYMAN == null) {
            LAZYMAN = new LazyMan();
        }
        return LAZYMAN;
    }
}

/**
 * 双重检测锁模式
 */
class DoubleKey {
    private DoubleKey() {
        System.out.println(Thread.currentThread().getName() + "ok");
    }

    private volatile static DoubleKey doubleKey;

    public static DoubleKey getInstance() {
        if (doubleKey == null) {
            synchronized (DoubleKey.class) {
                if (doubleKey == null) {
                    doubleKey = new DoubleKey();
                }
            }
        }
        return doubleKey;
    }

}

/**
 * 静态内部类的方式
 */
class Holder {
    private Holder() {
        System.out.println("获得holder");
    }

    public static Holder getInstance() {
        return innerClass.holder;
    }

    private static class innerClass {
        public static Holder holder = new Holder();
    }


}

