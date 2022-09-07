package com.techsessions.threads.e5deadlock.solution;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Mason extends Thread {

    // TODO 1 - Increase the number of bricks and see the behavior - The Dining Philosophers [Deadlock]
    public static int bricks = 5000;

    private int bricksLaid = 0;
    private Lock resource1;
    private Lock resource2;

    public Mason(String name, Lock resource1, Lock resource2) {
        this.setName(name);
        this.resource1 = resource1;
        this.resource2 = resource2;
    }

    @Override
    public void run() {
        while (bricks > 0) {
            // Lock the resources
            resource1.lock();
            resource2.lock();

            try {
                if (bricks > 0) {
                    --bricks;
                    ++this.bricksLaid;
                    System.out.println(this.getName() + " just laid 'Another brick in the wall'. Remaining bricks: " + bricks);
                }

                // TODO 3 - Uncomment the following if block and see the behavior [Abandoned locks]
                /**/
                if (bricks == 10) {
                    //throw new RuntimeException();
                }
                /**/

            } finally {
                // TODO 4 - Fix the abandoned lock with a try-finally block  [Abandoned locks]

                // Free the resources
                resource1.unlock();
                resource2.unlock();
            }

        }

        System.out.println(this.getName() + " laid " + bricksLaid + " bricks in the wall");
    }
}

public class HouseBuilderDeadlock {

    public static void main(String[] args) throws InterruptedException {

        // Shared resources
        Lock trowel = new ReentrantLock();
        Lock spiritLevel = new ReentrantLock();
        Lock clubHammer = new ReentrantLock();

        // TODO 2 - Fix the order to avoid a deadlocks
        /**/
        new Mason("Miguel", trowel, spiritLevel).start();
        new Mason("Eric", spiritLevel, clubHammer).start();
        new Mason("Emmanuel", trowel, clubHammer).start();
        /**/

        /* TODO 5 - Compare the numbers of bricks laid [Starvation]
         *  - Remove the runtime exception to visualize the results better
         *  - Why one of the masons is consuming way more bricks? Try only with two locks in all masons in the same order
         *  - Create 100 masons replicas of each mason in a for-loop and analyze the results.
         */
        /**
        for (int i = 0; i <100; i++) {
            new Mason("Miguel", trowel, spiritLevel).start();
            new Mason("Eric", spiritLevel, clubHammer).start();
            new Mason("Emmanuel", trowel, clubHammer).start();
        }
        /**/

    }
}
