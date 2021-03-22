package com.zjh.testEmployees.demo;

public class Demo01 {
    public static void main(String[] args) {
        int a = 2;
        int b = 5;
        int c;
        c = a > b ? a : b;
        switch (c) {
            case 1:
                System.out.println("c=1");
            case 2:
                System.out.println("c=2");
            case 3:
                System.out.println("c=3");
            case 4:
                System.out.println("c=4");
            case 5:
                System.out.println("c=5");
                break;
            default:
                System.out.println("运算错误");
                break;
        }

    }
}

class Demo {
    private static volatile Demo demo;

    private Demo() {
    }

    public static Demo getDemo() {
        if (demo == null) {
            synchronized (Demo.class) {
                demo = new Demo();
            }
        }
        return demo;
    }
}
