package com.tss.mangoservicea.manager.design.observer;

/**
 * 具体的观察者实现
 * Created by yangxiangjun on 2020/12/21.
 */
public class ConcreteObserver implements Observer{
    @Override
    public void call(String content) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("This is one:" + content);
    }
}
