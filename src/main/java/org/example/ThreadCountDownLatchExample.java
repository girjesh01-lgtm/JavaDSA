package org.example;

import java.util.concurrent.CountDownLatch;

public class ThreadCountDownLatchExample {

    /*
     * Topic: Thread Coordination Using CountDownLatch
     *
     * Problem:
     * Print all odd numbers from 1 to 20 first, then print all even numbers from
     * 1 to 20 using two separate threads.
     *
     * Requirement:
     * 1. oddThread should print: 1 3 5 7 9 11 13 15 17 19
     * 2. evenThread should print: 2 4 6 8 10 12 14 16 18 20
     * 3. evenThread should print only after oddThread has completely finished.
     *
     * Key idea:
     * CountDownLatch lets one or more threads wait until another thread signals.
     *
     * In this example:
     * - evenThread starts immediately, but waits at oddDone.await()
     * - oddThread prints odd numbers
     * - oddThread calls oddDone.countDown()
     * - evenThread is released and prints even numbers
     *
     * Difference from join():
     * - With join(), main waits for a thread to finish.
     * - With CountDownLatch, one worker thread can wait for a signal from another
     *   worker thread.
     */
    public static void main(String[] args) {
        CountDownLatch oddDone = new CountDownLatch(1);

        Thread oddThread = new Thread(() -> {
            for (int i = 1; i <= 20; i += 2) {
                System.out.println(Thread.currentThread().getName() + " -> " + i);
            }

            System.out.println(Thread.currentThread().getName() + " signals oddDone");
            oddDone.countDown();
        }, "odd-thread");

        Thread evenThread = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " waits for oddDone");
                oddDone.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            for (int i = 2; i <= 20; i += 2) {
                System.out.println(Thread.currentThread().getName() + " -> " + i);
            }
        }, "even-thread");

        oddThread.start();
        evenThread.start();
    }
}
