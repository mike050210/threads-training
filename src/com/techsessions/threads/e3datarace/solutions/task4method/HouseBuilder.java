package com.techsessions.threads.e3datarace.solutions.task4method;


class Engineer extends Thread {
    public static int bricksOnWall = 0;

    /* TODO 4 - Use Synchronize and intrinsic locks
     * - Synchronized method
     */

    public static synchronized void addBrickToWall() {
        bricksOnWall++;
    }

    private int bricksToAdd;

    public Engineer(String name, int bricksToAdd) {
        super(name);
        this.bricksToAdd = bricksToAdd;
    }

    public void run() {
        for (int i = 1; i <= bricksToAdd; i++) {
            Engineer.addBrickToWall();
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
