package com.tss.mangosercivea.manager.design.observer;

import org.apache.commons.lang.StringUtils;

import java.util.Vector;

/**
 * 具体的被观察者实现
 * Created by yangxiangjun on 2020/12/21.
 */
public class ConcreteSubject implements Subject{

    //存放观察者列表的集合
    private Vector<Observer> observers;

    public ConcreteSubject() {
        this.observers = new Vector<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int indexOf = observers.indexOf(observer);
        if (indexOf > 0) {
            observers.remove(indexOf);
        }
    }

    @Override
    public void notifyObserver(String content) {
        long m1 = System.currentTimeMillis();
        if (StringUtils.isNotBlank(content)){
            //循环通知每一个观察者
            observers.stream().forEach(observer -> observer.call(content));
        }
        long m2 = System.currentTimeMillis();
        System.out.println((m2 - m1));
    }
}
