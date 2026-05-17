package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolTasksExample {

    /*
     * Scenario 6: Run 5 Tasks Using A Pool Of 2 Threads
     *
     * Problem:
     * Create five independent tasks, but run them using a thread pool that has only
     * two worker threads.
     *
     * Requirement:
     * 1. Submit five tasks to the pool.
     * 2. Only two tasks should run at the same time because the pool size is 2.
     * 3. Remaining tasks should wait in the executor's queue.
     * 4. The executor should be shut down after tasks are submitted.
     *
     * Key idea:
     * ExecutorService separates task submission from thread creation.
     *
     * - You submit Runnable tasks.
     * - The pool decides which worker thread runs each task.
     * - With newFixedThreadPool(2), at most 2 tasks run concurrently.
     */
    public static void main(String[] args) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            for (int taskId = 1; taskId <= 5; taskId++) {
                int currentTaskId = taskId;

                executorService.submit(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName()
                                + " started task " + currentTaskId);

                        Thread.sleep(1000);

                        System.out.println(Thread.currentThread().getName()
                                + " finished task " + currentTaskId);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
        }

        System.out.println(Thread.currentThread().getName() + " finished");
    }
}
