package com.techsessions.threads.e1threadcreation.solution;


class JvmTestThread extends Thread {
    @Override
    public void run() {
        // TODO: Implement infinite loop thread
        while (true) {

        }
    }
}

public class JVMTest {
    public static void main(String[] args) {
        /* TODO: Task 1 - Thread implementation. See the runtime process and threads, implement infinite loop thread
         * - Try extending Thread
         * - Try implementing Runnable
         * - Try with Anonymous class
         * - Try with lambda
         */

        // TODO: See the runtime process and threads

        // display current information about this process
        Runtime rt = Runtime.getRuntime();
        long usedKB = (rt.totalMemory() - rt.freeMemory()) / 1024;
        System.out.println("Available processors: " + rt.availableProcessors());
        System.out.format("  Process ID: %d\n", ProcessHandle.current().pid());
        System.out.format("Thread Count: %d\n", Thread.activeCount());
        System.out.format("Memory Usage: %d KB\n", usedKB);

        // start 2 new threads
        int threadsNumber = 2;
        System.out.println("\nStarting " + threadsNumber + " Engineer waster threads...\n");

        for (int i = 1; i <= threadsNumber; i++) {
            // 1. Extending Thread
            new JvmTestThread().start();

            // 2. Implementing Runnable
            // new Thread(new MyFirstThread()).start();

            // 3. Runnable as Anonymous class
            /*
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                    }
                }
            }).start();
            */

            // 4. Runnable as lambda
            //new Thread(() -> {while(true){}}).start();
        }

        // display current information about this process
        usedKB = (rt.totalMemory() - rt.freeMemory()) / 1024;
        System.out.format("  Process ID: %d\n", ProcessHandle.current().pid());
        System.out.format("Thread Count: %d\n", Thread.activeCount());
        System.out.format("Memory Usage: %d KB\n", usedKB);
    }
}
