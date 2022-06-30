package com.techsessions.threads.e2lifecycle;


class Engineer extends Thread {

    // TODO 2 - Add thread name

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

    }
}
