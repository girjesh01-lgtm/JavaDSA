package org.example;

import java.util.concurrent.Semaphore;

public class AlternateOddEvenSemaphoreExample {

    /*
     * Scenario 2: Print 1 To 20 Alternately Using Two Threads
     *
     * Problem:
     * Use two separate threads to print numbers from 1 to 20 in order.
     *
     * Requirement:
     * 1. oddThread should print odd numbers: 1, 3, 5, ..., 19
     * 2. evenThread should print even numbers: 2, 4, 6, ..., 20
     * 3. Output should be alternating and ordered:
     *    1 2 3 4 5 6 ... 20
     *
     * Key idea:
     * A Semaphore controls permits.
     *
     * - oddSemaphore starts with 1 permit, so oddThread can print first.
     * - evenSemaphore starts with 0 permits, so evenThread must wait.
     * - after oddThread prints, it releases evenSemaphore.
     * - after evenThread prints, it releases oddSemaphore.
     */
    public static void main(String[] args) {
        Semaphore oddSemaphore = new Semaphore(1);
        Semaphore evenSemaphore = new Semaphore(0);

        Thread oddThread = new Thread(() -> {
            for (int i = 1; i <= 20; i += 2) {
                try {
                    oddSemaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " -> " + i);
                    evenSemaphore.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }, "odd-thread");

        Thread evenThread = new Thread(() -> {
            for (int i = 2; i <= 20; i += 2) {
                try {
                    evenSemaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " -> " + i);
                    oddSemaphore.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }, "even-thread");

        oddThread.start();
        evenThread.start();
    }
}
