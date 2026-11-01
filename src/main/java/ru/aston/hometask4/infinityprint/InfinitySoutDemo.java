package ru.aston.hometask4.infinityprint;

public class InfinitySoutDemo {

    private static final Object MONITOR = new Object();
    private static boolean firstTurn = true;

    public static void main(String[] args) {

        Thread first = new Thread(() -> printloop("1", true), "Thread-1");
        Thread second = new Thread(() -> printloop("2",false),"Thread-2");

        first.start();
        second.start();

    }

    private static void printloop(String text, boolean isFirst) {

        while (!Thread.currentThread().isInterrupted()) {
            synchronized (MONITOR) {
                if (firstTurn != isFirst) {
                    try {
                        MONITOR.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                System.out.println(text);
                firstTurn = !firstTurn;
                MONITOR.notifyAll();
            }
            pause(300);
        }

    }

    private static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
