package com.techsessions.threads.e1threadcreation.solution;


class Engineer extends Thread {
    /* TODO: Task 2
     *  - Lay bricks when start to work and stop when working hours is false.
     *  - Implement run method
     *  - Display counts in main thread and use join() method to wait
     */

    public int bricks = 0;
    public static boolean workingHours = true;

    @Override
    public void run() {
        while(workingHours) {
            System.out.println(this.getName() + " building a wall!");
            bricks++;
        }
    }
}

public class HouseBuilder {
    public static void main(String[] args) throws InterruptedException {
        // TODO: Task 1 - getName(), getID(), currentThread()
        Thread main = Thread.currentThread();
        System.out.println("Main thread name: " + main.getName());
        System.out.println("Main thread Id: " + main.getId());

        Engineer bob = new Engineer();
        System.out.println("Bob thread name: " + bob.getName());
        System.out.println("Bob thread Id: " + bob.getId());

        Engineer patrick = new Engineer();
        System.out.println("Patrick thread name: " + patrick.getName());
        System.out.println("Patrick thread Id: " + patrick.getId());

        bob.start();
        patrick.start();

        Thread.sleep(1000);          // continue working for 1 second
        Engineer.workingHours = false;     // stop working

        // System.out.format("Bob used %d bricks.\n", bob.bricks);
        // System.out.format("Patrick used %d bricks.\n", patrick.bricks);

        bob.join();
        patrick.join();

        System.out.format("Bob used %d bricks.\n", bob.bricks);
        System.out.format("Patrick used %d bricks.\n", patrick.bricks);
    }
}
