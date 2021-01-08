package com.tss.mangosercivea.manager.design.observer;

/**
 * 抽象被观察者
 * Created by yangxiangjun on 2020/12/21.
 */
public interface Subject {
    /**
     * 观察者注册
     * @param observer 具体观察者
     */
    void addObserver(Observer observer);

    /**
     * 删除观察者
     * @param observer 具体观察者
     */
    void removeObserver(Observer observer);

    /**
     * 主题有变化时通知观察者
     * @param content 要通知的内容
     */
    void notifyObserver(String content);
}
