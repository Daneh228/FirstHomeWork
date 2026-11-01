package ru.aston.hometask4.livelock;

public class Worker {

    private final String name;
    private volatile boolean active = true;

    public Worker(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public void work(Resource resource, Worker other) {
        while (active) {
            if (resource.getOwner() != this) {
                pause(100);
                continue;
            }

            if (other.isActive()) {
                System.out.println(name + ": " + other.getName() + ", возьми ресурс ты, я уступаю");
                resource.setOwner(other);
                pause(100);
                continue;
            }

            System.out.println(name + ": работаю с ресурсом");
            active = false;
        }
    }

    private void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}