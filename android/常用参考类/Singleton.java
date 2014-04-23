package com.example.test;

public class Singleton {
    /**
     * 使用内部类来做到延迟加载对象，JLS(Java Language Specification)会保证这个类的线程安全
     */

    private Singleton() {
    }
    
    private static class SingletonHolder {
        public static final Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }
}
