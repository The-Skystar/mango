package com.tss.mangosercivea.manager.design;

import com.tss.mangosercivea.manager.design.observer.*;

/**
 * Created by yangxiangjun on 2020/12/21.
 */
public class Test {
    public static void main(String[] args) {
        Subject subject = new ConcurrentSubject();
        subject.addObserver(new ConcreteObserver());
        subject.addObserver(new ConcreteObserver1());
        subject.addObserver(new ConcreteObserver2());
        subject.addObserver(new ConcreteObserver3());
        subject.notifyObserver("12");
    }
}
