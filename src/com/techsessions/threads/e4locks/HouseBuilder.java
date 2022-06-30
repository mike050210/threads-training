package com.techsessions.threads.e4locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Engineer extends Thread {
    public static int bricksOnWall = 0;
    public static int concretePounds = 0;
    public static int pictures = 0;
    static Lock brickTowel = new ReentrantLock();

    private int bricksToAdd;

    public Engineer(String name, int bricksToAdd) {
        super(name);
        this.bricksToAdd = bricksToAdd;
    }

    private void addBrick() {
        brickTowel.lock();
        // TODO 2 - Change the lock to ReentrantLock type and print the number of locks there are under the same mutex
        bricksOnWall++;
        brickTowel.unlock();
    }

    private void addConcretePounds(int pounds) {
        brickTowel.lock();
        concretePounds += pounds;
        brickTowel.unlock();
    }

    private void takeAPictureWorking() {
        // TODO 3 - Only take a picture if the lock is available, otherwise ignore
        brickTowel.lock();
        pictures++;
        brickTowel.unlock();
    }


    // TODO 1 - Refactor the method calls so that whenever an engineer adds a brick, it always add two concrete pounds
    public void run() {
        for (int i = 1; i <= bricksToAdd; i++) {
            addBrick();
            addConcretePounds(2);
            takeAPictureWorking();
        }
    }
}

public class HouseBuilder {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Engineer eric = new Engineer("Eric", 100_000);
        Engineer emma = new Engineer("Emma", 100_000);

        eric.start();
        emma.start();
        eric.join();
        emma.join();

        System.out.println("Total bricks: " + Engineer.bricksOnWall);
        System.out.println("Total concrete pounds: " + Engineer.concretePounds);
        System.out.println("Total pictures taken: " + Engineer.pictures);

        // TODO 4 - Discussion. How can we improve this program performance by using tryLock
        System.out.println("Time: " + (System.currentTimeMillis() - start) + "ms");
    }
}
