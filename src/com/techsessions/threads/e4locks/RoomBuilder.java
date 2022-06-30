package com.techsessions.threads.e4locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker extends Thread {

    enum WorkerRole {
        MASON,
        SUPERVISOR
    }
    public static int currentRoomIdx = 0;
    private static final String[] ROOMS_TO_BUILD = {
            "Basement", "Kitchen", "Living Room", "Bathroom", "Bedroom 1", "Bedroom 2", "Game room", "Study"};

    // TODO 2 - Use ReentrantReadWriteLock instead
    private static Lock lock = new ReentrantLock();

    public WorkerRole workerRole;

    public Worker(String name, WorkerRole workerRole) {
        this.workerRole = workerRole;
        this.setName(name);
    }

    @Override
    public void run() {
        // TODO 3 - Implement different functions depending on Worker Type using Read and Write locks
        while (currentRoomIdx < ROOMS_TO_BUILD.length) {
            if (this.workerRole == WorkerRole.MASON) {
                if (lock.tryLock()) {
                    //System.out.println("# of masons reading instructions: " + lock.getReadLockCount());
                    System.out.println(this.getName() + " is working at the " + ROOMS_TO_BUILD[currentRoomIdx]);
                    lock.unlock();
                } else {
                    System.out.println(this.getName() + " is working and waiting for new instructions");
                }

                // Working... wait for some time before reading new instructions
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } else { // Supervisor (writer)
                lock.lock();
                ++currentRoomIdx;
                if (currentRoomIdx < ROOMS_TO_BUILD.length) {
                    System.out.println(this.getName() + " is moving masons to the " + ROOMS_TO_BUILD[currentRoomIdx]);
                } else {
                    System.out.println(this.getName() + " let the masons go.");
                }
                lock.unlock();

                // Wait for some time to allow masons to work
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class RoomBuilder {
    public static void main(String[] args) {
        List<Thread> workers = new ArrayList<>();

        /* TODO 1: Create threads of types MASON and SUPERVISOR.
         * 1. Create 10 masons to read instructions
         * 2. Create two supervisors to send instructions
         * 3. Wait for all threads to finish
         */
    }
}
