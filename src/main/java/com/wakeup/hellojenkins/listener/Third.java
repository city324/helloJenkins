package com.wakeup.hellojenkins.listener;

public class Third {

    public static int a = 10;
    public int b = 20;
    private static int c = 100;
    private int d = 100;

    public static void m1() {
        System.out.println("我是第三方应用的m1方法-------------11111111111");
    }


    public void m2() {
        System.out.println("我是第三方应用的m2方法-------------22222222222");
    }

    public static void main(String[] args) {
        System.out.println("我是第三方应用的main方法");
    }

}
