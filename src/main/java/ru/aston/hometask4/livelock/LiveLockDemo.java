package ru.aston.hometask4.livelock;

public class LiveLockDemo {

    public static void main(String[] args) {
        Worker first = new Worker("Работник 1");
        Worker second = new Worker("Работник 2");
        Resource resource = new Resource(first);

        new Thread(() -> first.work(resource, second), "Thread-1").start();
        new Thread(() -> second.work(resource, first), "Thread-2").start();
    }
}
