package com.techsessions.threads.e1threadcreation;

/**
 * Class to
 */
class JvmTestThread extends Thread {
    // TODO: Implement infinite loop thread
}

public class JVMTest {
    public static void main(String[] args) {
        /* TODO: Task 1 - Use different Thread implementations.
         * - Try with Runnable
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

        }
        // display current information about this process
        usedKB = (rt.totalMemory() - rt.freeMemory()) / 1024;
        System.out.format("  Process ID: %d\n", ProcessHandle.current().pid());
        System.out.format("Thread Count: %d\n", Thread.activeCount());
        System.out.format("Memory Usage: %d KB\n", usedKB);

    }
}
