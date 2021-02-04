package com.tss.mangosercivea.manager.design.observer;

import org.apache.commons.lang3.StringUtils;

import java.util.Vector;
import java.util.concurrent.CompletableFuture;

/**
 * 线程安全的被观察者实现
 * Created by yangxiangjun on 2020/12/21.
 */
public class ConcurrentSubject implements Subject{
    private Vector<Observer> observers;
    private String content;

    public ConcurrentSubject() {
        this.observers = new Vector<>();
        this.content = "";
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
        CompletableFuture[] futures = new CompletableFuture[observers.size()];
        if (StringUtils.isNotBlank(content)){
            observers.stream().forEach(observer -> {
                final CompletableFuture<Void> future = CompletableFuture.runAsync(() -> observer.call(content));
                futures[observers.indexOf(observer)] = future;
            });
            CompletableFuture<Void> completableFuture = CompletableFuture.allOf(futures);
            completableFuture.join();
        }
        long m2 = System.currentTimeMillis();
        System.out.println((m2 - m1));
    }
}
