package com.techsessions.threads.e3datarace.solutions.task4statement;


class Engineer extends Thread {
    public static int bricksOnWall = 0;

    /* TODO 4 - Use Synchronize and intrinsic locks
     * - Synchronized method
     * - Synchronized statement
     * - Synchronized statement ('this', '.class', primitives, immutable (String, Wrapper), and mutable objects)
     */
    private int bricksToAdd;

    // See this form
    public static void addBrickToWall() {
        synchronized (Engineer.class) {
            bricksOnWall++;
        }
    }

    public Engineer(String name, int bricksToAdd) {
        super(name);
        this.bricksToAdd = bricksToAdd;
    }

    public void run() {
        for (int i = 1; i <= bricksToAdd; i++) {
            // See this other form
            // Try with: 'this', '.class', primitives variables, immutable (String, Wrapper), and mutable objects
            synchronized (Engineer.class) {
                bricksOnWall++;
            }
        }
    }

}

public class HouseBuilder {
    public static void main(String[] args) throws InterruptedException {
        // TODO 1 - Create two or more threads and start them off, see final bricks
        // Question. What is the critical section?

        Engineer bob = new Engineer("Bob", 10_000_000);
        Engineer patrick = new Engineer("Patrick", 10_000_000);

        bob.start();
        patrick.start();
        bob.join();
        patrick.join();

        System.out.println("Total bricks: " + Engineer.bricksOnWall);

    }
}
