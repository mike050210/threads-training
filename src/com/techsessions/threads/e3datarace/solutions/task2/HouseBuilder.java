package com.techsessions.threads.e3datarace.solutions.task2;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Engineer extends Thread {
    public static int bricksOnWall = 0;

    /* TODO 2 - Create a static Lock object (ReentrantLock) and surround the critical section with the lock
     * First, surround all code within the method,
     * then only the section that needs the lock (bricksOnWall).
     */
    static Lock brickTowel = new ReentrantLock();

    private int bricksToAdd;

    public Engineer(String name, int bricksToAdd) {
        super(name);
        this.bricksToAdd = bricksToAdd;
    }

    public void run() {
        for (int i = 1; i <= bricksToAdd; i++) {
            brickTowel.lock();
            bricksOnWall++;
            brickTowel.unlock();
        }
    }

}

public class HouseBuilder {
    public static void main(String[] args) throws InterruptedException {
        /*
         * TODO 1 - Create two or more threads and start them off, see final bricks
         * Question. What is the critical section?
         * Try with 10, 1_000, 1_000_000, 10_000_000 of bricks, why this is happening?
         */

        Engineer bob = new Engineer("Bob", 10_000_000);
        Engineer patrick = new Engineer("Patrick", 10_000_000);

        bob.start();
        patrick.start();

        // TODO 1 - Try with and without using the join() method?
        bob.join();
        patrick.join();

        System.out.println("Total bricks: " + Engineer.bricksOnWall);
    }
}
