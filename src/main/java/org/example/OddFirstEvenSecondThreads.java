package org.example;

import java.util.concurrent.*;

public class OddFirstEvenSecondThreads {

    /*
     * Problem: Print Odd Numbers First, Then Even Numbers Using Two Threads
     *
     * Given the numbers from 1 to 20, use two separate threads:
     *
     * 1. One thread should print all odd numbers.
     * 2. One thread should print all even numbers.
     *
     * The output should print all odd numbers first, then all even numbers.
     *
     * Expected order:
     * 1 3 5 7 9 11 13 15 17 19 2 4 6 8 10 12 14 16 18 20
     *
     * Important:
     * This problem is not asking for alternating output like 1 2 3 4 5...
     * The even-number thread must wait until the odd-number thread has finished.
     *
     * Goal:
     * Learn how two threads can coordinate their execution order.
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //printOddEvenUsingJoin();

        //printOddEvenWithLatch();

        //printUsingThreadPool();

        printUsingSyncwaitnotify();


        //pringUsingCompletableFuture();
    }

    private static void pringUsingCompletableFuture() {
        CompletableFuture
                .runAsync(() -> printOdds())
                .thenRun(() -> printEvens())
                .join();
    }

    private static void printUsingSyncwaitnotify() {
        Object lock = new Object();
        boolean[] oddDone = {false};

        Thread evenThread = new Thread(() -> {
            synchronized (lock) {
                while (!oddDone[0]) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }

            printEvens();
        });

        Thread oddThread = new Thread(() -> {
            printOdds();

            synchronized (lock) {
                oddDone[0] = true;
                lock.notify();
            }
        });
        evenThread.start();
        oddThread.start();
    }

    private static void printUsingThreadPool() throws InterruptedException, ExecutionException {
        ExecutorService executor =  Executors.newFixedThreadPool(2);

        Future<?> oddFuture = executor.submit(() -> {
            printOdds();
        });

        oddFuture.get();

        executor.submit(() -> {
            printEvens();
        });

        executor.shutdown();
    }



    private static void printOddEvenUsingJoin() throws InterruptedException {
        Thread oddThread = new Thread(() -> {
            printOdds();
        }, "odd-Thread");

        Thread evenThread = new Thread(() -> {
            printEvens();
        }, "even-Thread");

        oddThread.start();
        oddThread.join();
        evenThread.start();
    }

    private static void printOddEvenWithLatch() {
        CountDownLatch oddDone = new CountDownLatch(1);

        Thread oddThread = new Thread(() -> {
            printOdds();

            oddDone.countDown();
        }, "odd-thread");

        Thread evenThread = new Thread(() -> {
            try {
                oddDone.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            printEvens();
        }, "even-thread");

        oddThread.start();
        evenThread.start();
    }

    private static void printEvens() {
        for (int i =2; i <= 20; i += 2) {
            System.out.println("Even : "+i);
        }
    }

    private static void printOdds() {
        for (int i = 1; i < 20; i += 2) {
            System.out.println("Odd : "+i);
        }
    }
}
