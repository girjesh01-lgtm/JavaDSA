package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreading {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Manual thread creation:");
        runUsingManualThreads();

        System.out.println();
        System.out.println("Thread pool:");
        runUsingThreadPool();
    }

    private static void runUsingManualThreads() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        for (int taskId = 1; taskId <= 5; taskId++) {
            Thread thread = new Thread(new PrintTask(taskId), "manual-worker-" + taskId);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("All manual threads finished.");
    }

    private static void runUsingThreadPool() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int taskId = 1; taskId <= 5; taskId++) {
            executorService.submit(new PrintTask(taskId));
        }

        executorService.shutdown();

        if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
            executorService.shutdownNow();
        }

        System.out.println("All pool tasks finished.");
    }

    private static class PrintTask implements Runnable {
        private final int taskId;

        private PrintTask(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("Task " + taskId + " started by " + threadName);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Task " + taskId + " interrupted.");
                return;
            }

            System.out.println("Task " + taskId + " finished by " + threadName);
        }
    }
}
