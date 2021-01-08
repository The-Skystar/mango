package com.tss.mangosercivea.manager.design.signleton;

import java.lang.reflect.Constructor;

/**
 * 避免反射机制破坏的双重锁检查懒汉式（DCL模式）
 */
public class Signleton {
    //使用volatile关键字修饰，避免jvm指令重排序
    private volatile static Signleton instance = null;

    private static boolean flag = false;

    private Signleton(){
        if (flag){
            throw new RuntimeException("不要试图通过反射机制破坏单例模式");
        } else {
            flag = true;
        }
    }

    //假设有ABCD个线程同时使用这个方法
    public static Signleton getInstance(){
        //BCD都进入了这个方法
        if(instance==null){
            //而A线程已经给第二个的判断加锁了
            synchronized (Signleton.class){
                //这时A挂起,对象instance还没创建 ，故BCD都进入了第一个判断里面，并排队等待A释放锁
                //A唤醒继续执行并创建了instance对象，执行完毕释放锁。
                //此时到B线程进入到第二个判断并加锁，但由于B进入第二个判断时instance 不为null了  故需要再判断多一次  不然会再创建一次实例
                if(instance==null){
                    instance = new Signleton();
                }

            }
        }
        return instance;
    }

    public static void main(String[] args) throws Exception {
        //首先我们获得这个空参构造器
        //由于构造器是私有的,所以我们用.setAccessible()解决了私有构造器的问题

        Constructor<Signleton> declaredConstructor = Signleton.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        //这样就又创建出了另一个实例
        Signleton instance1 = declaredConstructor.newInstance();
        Signleton instance2 = declaredConstructor.newInstance();
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
    }
}
