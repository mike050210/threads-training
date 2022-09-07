package com.techsessions.threads.e5deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MasonPerson extends Thread {

    public static int bricks = 500_000;

    private Lock resource1;
    private Lock resource2;
    private Random waitingTime = new Random();

    public MasonPerson(String name, Lock resource1, Lock resource2) {
        this.setName(name);
        this.resource1 = resource1;
        this.resource2 = resource2;
    }

    @Override
    public void run() {
        while (bricks > 0) {
            // Lock the resources
            resource1.lock();
            /* TODO 1 - Notice the usage of tryLock instead of a simple lock, see the order of locks that may cause a deadlock [Livelock] (simulation)
             * - Verify the CPU usage in the Resource monitor
             */
            if (!resource2.tryLock()) {
                System.out.println(this.getName() + " is releasing the first lock");
                resource1.unlock();
                // TODO 2 - Uncomment the following block and see the difference
                /**
                try {
                    Thread.sleep(waitingTime.nextInt(3));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /**/
            } else {
                try {
                    if (bricks > 0) {
                        --bricks;
                        System.out.println(this.getName() + " just laid 'Another brick in the wall'. Remaining bricks: " + bricks);
                    }
                } finally {
                    // Free the resources
                    resource1.unlock();
                    resource2.unlock();
                }


            }
        }
    }
}

public class HouseBuilderLivelock {

    public static void main(String[] args) throws InterruptedException {
        // Shared resources
        Lock trowel = new ReentrantLock();
        Lock spiritLevel = new ReentrantLock();
        Lock clubHammer = new ReentrantLock();

        new MasonPerson("Miguel", trowel, spiritLevel).start();
        new MasonPerson("Eric", spiritLevel, clubHammer).start();
        new MasonPerson("Emmanuel", clubHammer, trowel).start();

    }
}
