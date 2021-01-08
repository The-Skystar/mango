package com.tss.mangosercivea.manager.design.observer;

/**
 * Created by yangxiangjun on 2020/12/21.
 */
public class ConcreteObserver2 implements Observer{
    @Override
    public void call(String content) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("This is three:" + content);
    }
}
