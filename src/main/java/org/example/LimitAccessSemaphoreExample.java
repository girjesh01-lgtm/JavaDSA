package org.example;

import java.util.concurrent.Semaphore;

public class LimitAccessSemaphoreExample {

    /*
     * Scenario 5: Limit Only 2 Threads To Access A Resource At A Time
     *
     * Problem:
     * Suppose five users want to access a limited resource, but only two users are
     * allowed to use it at the same time.
     *
     * Requirement:
     * 1. Start five worker threads.
     * 2. Only two workers should enter the restricted section at a time.
     * 3. Other workers should wait until a permit is released.
     *
     * Key idea:
     * A Semaphore with 2 permits allows at most 2 threads to pass acquire().
     *
     * - acquire() takes one permit, or waits if no permit is available.
     * - release() returns one permit.
     *
     * For capacity limiting, every successful acquire() should have exactly one
     * release(), usually inside finally.
     */
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);

        for (int userId = 1; userId <= 5; userId++) {
            int currentUserId = userId;

            Thread workerThread = new Thread(() -> {
                boolean acquired = false;

                try {
                    System.out.println(Thread.currentThread().getName() + " waiting for permit");
                    semaphore.acquire();
                    acquired = true;

                    System.out.println(Thread.currentThread().getName() + " entered resource");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " leaving resource");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    if (acquired) {
                        semaphore.release();
                    }
                }
            }, "user-" + currentUserId);

            workerThread.start();
        }
    }
}
