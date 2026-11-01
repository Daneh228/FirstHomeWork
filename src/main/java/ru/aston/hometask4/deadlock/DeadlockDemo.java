package ru.aston.hometask4.deadlock;

public class DeadlockDemo {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();

        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            synchronized (lock1){
                pause(100);
                synchronized (lock2){

                }
            }
            System.out.println(Thread.currentThread().getName() + " end");
        },"Thread-1");

        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            synchronized (lock2){
                pause(100);
                synchronized (lock1){
                }
            }
            System.out.println(Thread.currentThread().getName() + " end");
        },"Thread-2");

        thread1.start();
        thread2.start();
    }
    private static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
