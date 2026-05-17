package org.example;

public class ThreadWaitNotifyExample {

    /*
     * Topic: Thread Coordination Using wait() and notify()
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
     * wait() makes the current thread release the lock and wait.
     * notify() wakes one thread waiting on the same lock.
     *
     * Important:
     * wait() and notify() must be called inside synchronized(lock).
     */
    public static void main(String[] args) {
        Object lock = new Object();
        boolean[] oddDone = {false};

        Thread evenThread = new Thread(() -> {
            synchronized (lock) {
                while (!oddDone[0]) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " waits");
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }

            for (int i = 2; i <= 20; i += 2) {
                System.out.println(Thread.currentThread().getName() + " -> " + i);
            }
        }, "even-thread");

        Thread oddThread = new Thread(() -> {
            for (int i = 1; i <= 20; i += 2) {
                System.out.println(Thread.currentThread().getName() + " -> " + i);
            }

            synchronized (lock) {
                oddDone[0] = true;
                System.out.println(Thread.currentThread().getName() + " notifies");
                lock.notify();
            }
        }, "odd-thread");

        evenThread.start();
        oddThread.start();
    }
}
