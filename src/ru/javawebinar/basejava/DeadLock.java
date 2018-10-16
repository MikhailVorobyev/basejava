package ru.javawebinar.basejava;

public class DeadLock {
    private static final Object LOCK_1 = new Object();
    private static final Object LOCK_2 = new Object();

    public void deadLockMethod(Object firstLock, Object secondLock) {
        synchronized (firstLock) {
            System.out.println(Thread.currentThread() + " inside first lock");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " waiting for second lock...");
            synchronized (secondLock) {
                System.out.println(Thread.currentThread() + " inside second lock");
            }
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(() -> deadLock.deadLockMethod(LOCK_1, LOCK_2)).start();
        new Thread(() -> deadLock.deadLockMethod(LOCK_2, LOCK_1)).start();
    }

}
