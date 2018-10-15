package ru.javawebinar.basejava;

public class DeadLock {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void method1() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread() + " inside lock1");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " waiting for lock2...");
            synchronized (lock2) {
                System.out.println(Thread.currentThread() + " inside lock2");
            }
        }
    }

    public void method2() {
        synchronized (lock2) {
            System.out.println(Thread.currentThread() + " inside lock2");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " waiting for lock1...");
            synchronized (lock1) {
                System.out.println(Thread.currentThread() + " inside lock1");
            }
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(deadLock::method1).start();
        new Thread(deadLock::method2).start();
    }

}
