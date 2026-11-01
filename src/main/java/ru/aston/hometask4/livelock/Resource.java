package ru.aston.hometask4.livelock;

public class Resource {

    private Worker owner;

    public Resource(Worker owner) {
        this.owner = owner;
    }

    public synchronized Worker getOwner() {
        return owner;
    }

    public synchronized void setOwner(Worker owner) {
        this.owner = owner;
    }
}