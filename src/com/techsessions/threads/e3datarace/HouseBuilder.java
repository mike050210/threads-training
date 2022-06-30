package com.techsessions.threads.e3datarace;

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
     * - Synchronized statement ('this', '.class', primitives, immutable (String, Wrapper), and mutable objects)
     */



    private int bricksToAdd;

    public Engineer(int bricksToAdd) {
        this.bricksToAdd = bricksToAdd;
    }
    public void run() {
        // TODO 1 - Cycle "bricksToAdd" times to add to bricksOnWall count

    }

}

public class HouseBuilder {
    public static void main(String[] args) throws InterruptedException {
        /*
         * TODO 1 - Create two or more threads and start them off, see final bricks
         * Question. What is the critical section?
         * Try with 10, 1_000, 1_000_000, 10_000_000 of bricks, why this is happening?
         */

        // TODO 1 - Try with and without using the join() method?

    }
}
