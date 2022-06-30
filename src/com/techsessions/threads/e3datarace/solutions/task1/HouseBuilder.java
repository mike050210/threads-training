package com.techsessions.threads.e3datarace.solutions.task1;


class Engineer extends Thread {
    public static int bricksOnWall = 0;

    /* TODO 2 - Create a static Lock object (ReentrantLock) and surround the critical section with the lock
     * First, surround all code within the method,
     * then only the section that needs the lock (bricksOnWall).
     */

    // TODO 3 - Use Atomic operations (AtomicInteger)

    /* TODO 4 - Use Synchronize and intrinsic locks
     * - Synchronized method
     * - Synchronized statement
     * - Synchronized statement (this, primitives, wrapper)
     */


    private int bricksToAdd;

    public Engineer(int bricksToAdd) {
        this.bricksToAdd = bricksToAdd;
    }

    public void run() {
        // TODO 1 - Cycle "bricksToAdd" times to add to bricksOnWall count
        for (int i = 1; i <= bricksToAdd; i++) {
            bricksOnWall++;
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

        Engineer bob = new Engineer(10_000_000);
        Engineer patrick = new Engineer(10_000_000);

        bob.start();
        patrick.start();

        // TODO - Try with and without using the join() method?
        bob.join();
        patrick.join();

        System.out.println("Total bricks: " + Engineer.bricksOnWall);

    }
}
