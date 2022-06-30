package com.techsessions.threads.e4locks.solution.readwritelock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Worker extends Thread {

    enum WorkerRole {
        MASON,
        SUPERVISOR
    }

    public static int currentRoomIdx = 0;
    private static final String[] ROOMS_TO_BUILD = {
            "Basement", "Kitchen", "Living Room", "Bathroom", "Bedroom 1", "Bedroom 2", "Game room", "Study"};

    // TODO 1 - Use ReentrantReadWriteLock
    private static ReentrantReadWriteLock instructionsLock = new ReentrantReadWriteLock();
    private static Lock readInstructionsLock = instructionsLock.readLock();
    private static Lock writeInstructionsLock = instructionsLock.writeLock();

    public WorkerRole workerRole;

    public Worker(String name, WorkerRole workerRole) {
        this.workerRole = workerRole;
        this.setName(name);
    }

    @Override
    public void run() {
        while (currentRoomIdx < ROOMS_TO_BUILD.length) {
            if (this.workerRole == WorkerRole.MASON) {
                if (readInstructionsLock.tryLock()) {
                    System.out.println("# of masons reading instructions: " + instructionsLock.getReadLockCount());
                    System.out.println(this.getName() + " is working at the " + ROOMS_TO_BUILD[currentRoomIdx]);
                    readInstructionsLock.unlock();
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
                writeInstructionsLock.lock();
                ++currentRoomIdx;

                if (currentRoomIdx < ROOMS_TO_BUILD.length) {
                    System.out.println(this.getName() + " is moving masons to the " + ROOMS_TO_BUILD[currentRoomIdx]);
                } else {
                    System.out.println(this.getName() + " let the masons go. Masons finishing up: " + instructionsLock.getReadLockCount());
                }
                writeInstructionsLock.unlock();

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
        // Create 10 masons to read instructions
        for (int i = 0; i < 10; i++) {
            Worker worker = new Worker("mason-" + i, Worker.WorkerRole.MASON);
            worker.start();
            workers.add(worker);
        }

        // Create two supervisors to send instructions
        for (int i = 0; i < 2; i++) {
            Worker worker = new Worker("supervisor-" + i, Worker.WorkerRole.SUPERVISOR);
            worker.start();
            workers.add(worker);
        }
        // Wait for all threads to finish
        workers.forEach(worker -> {
            try {
                worker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
