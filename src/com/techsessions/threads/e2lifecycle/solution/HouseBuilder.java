package com.techsessions.threads.e2lifecycle.solution;


class Engineer extends Thread {

    // TODO 2 - Add thread name
    public Engineer(String name) {
        super(name);
    }

    public void run() {
        System.out.println("Engineer " + Thread.currentThread().getName() + " waiting for tools");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Engineer " + Thread.currentThread().getName() + " completed his work for today");
    }

}

public class HouseBuilder {
    public static void main(String[] args) throws InterruptedException {
        // TODO 1 - See state (getState()) after creating Thread.new, start(), wait (Thread.sleep), join()
        System.out.println("Building a house");
        System.out.println("Create a new thread");

        Thread bob = new Engineer("Bob");
        System.out.println("Thread Name: " + bob.getName());

        System.out.println("1. thread state: " + bob.getState());

        bob.start();
        System.out.println("2. thread state: " + bob.getState());

        Thread.sleep(500);
        System.out.println("3. thread state: " + bob.getState());

        bob.join();
        System.out.println("4. thread state: " + bob.getState());







    }
}
