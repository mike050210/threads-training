package com.techsessions.threads.e3datarace.solutions.task3;


import java.util.concurrent.atomic.AtomicInteger;

class Engineer extends Thread {

    // TODO 3 - Use Atomic operations (AtomicInteger)
    public static AtomicInteger bricksOnWall = new AtomicInteger(0);

    private int bricksToAdd;

    public Engineer(String name, int bricksToAdd) {
        super(name);
        this.bricksToAdd = bricksToAdd;
    }

    public void run() {
        for (int i = 1; i <= bricksToAdd; i++) {
            bricksOnWall.incrementAndGet();
        }
    }

}

public class HouseBuilder {
    public static void main(String[] args) throws InterruptedException {

        Engineer bob = new Engineer("Bob", 10_000_000);
        Engineer patrick = new Engineer("Patrick", 10_000_000);

        bob.start();
        patrick.start();

        bob.join();
        patrick.join();

        System.out.println("Total bricks: " + Engineer.bricksOnWall);
    }
}
