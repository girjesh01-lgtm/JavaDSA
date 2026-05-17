package org.example;

public class ThreadJoinExample {

    /*
     * Topic: Thread Coordination Using join()
     *
     * Problem:
     * Print all odd numbers from 1 to 20 first, then print all even numbers from
     * 1 to 20 using two separate threads.
     *
     * Requirement:
     * 1. oddThread should print: 1 3 5 7 9 11 13 15 17 19
     * 2. evenThread should print: 2 4 6 8 10 12 14 16 18 20
     * 3. evenThread should start only after oddThread has completely finished.
     *
     * Key idea:
     * thread.join() makes the current thread wait until the target thread finishes.
     *
     * In this example:
     * oddThread.join() is written inside main().
     *
     * So:
     * - main thread waits
     * - oddThread keeps running
     * - after oddThread finishes, main continues
     * - then main starts evenThread
     */
    public static void main(String[] args) throws InterruptedException {
        Thread oddThread = new Thread(() -> {
            for (int i = 1; i <= 20; i += 2) {
                System.out.println(Thread.currentThread().getName() + " -> " + i);
            }
        }, "odd-thread");

        Thread evenThread = new Thread(() -> {
            for (int i = 2; i <= 20; i += 2) {
                System.out.println(Thread.currentThread().getName() + " -> " + i);
            }
        }, "even-thread");

        System.out.println(Thread.currentThread().getName() + " starts odd-thread");
        oddThread.start();

        System.out.println(Thread.currentThread().getName() + " waits for odd-thread");
        oddThread.join();

        System.out.println(Thread.currentThread().getName() + " starts even-thread");
        evenThread.start();

        System.out.println(Thread.currentThread().getName() + " waits for even-thread");
        evenThread.join();

        System.out.println(Thread.currentThread().getName() + " finished");
    }
}
