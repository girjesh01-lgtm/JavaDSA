package org.example;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadFutureOrderExample {

    /*
     * Topic: Thread Coordination Using ExecutorService and Future
     *
     * Problem:
     * Print all odd numbers from 1 to 20 first, then print all even numbers from
     * 1 to 20.
     *
     * Requirement:
     * 1. The odd-number task should print: 1 3 5 7 9 11 13 15 17 19
     * 2. The even-number task should print: 2 4 6 8 10 12 14 16 18 20
     * 3. The even-number task should start only after the odd-number task has
     *    completely finished.
     *
     * Key idea:
     * ExecutorService manages worker threads.
     * Future.get() makes the current thread wait until the submitted task finishes.
     *
     * In this example:
     * - main submits odd task to the pool
     * - main waits using oddFuture.get()
     * - main submits even task after odd task is done
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            Future<?> oddFuture = executorService.submit(() -> {
                for (int i = 1; i <= 20; i += 2) {
                    System.out.println(Thread.currentThread().getName() + " -> " + i);
                }
            });

            System.out.println(Thread.currentThread().getName() + " waits for odd task");
            oddFuture.get();

            Future<?> evenFuture = executorService.submit(() -> {
                for (int i = 2; i <= 20; i += 2) {
                    System.out.println(Thread.currentThread().getName() + " -> " + i);
                }
            });

            System.out.println(Thread.currentThread().getName() + " waits for even task");
            evenFuture.get();
        }

        System.out.println(Thread.currentThread().getName() + " finished");
    }
}
