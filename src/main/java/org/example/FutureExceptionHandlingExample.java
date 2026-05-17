package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExceptionHandlingExample {

    /*
     * Scenario 8: Handle Exception From A Thread Pool Task
     *
     * Problem:
     * Submit multiple tasks to a thread pool. Some tasks may fail with an
     * exception. The main thread should detect which tasks succeeded and which
     * tasks failed.
     *
     * Requirement:
     * 1. Submit five tasks to an ExecutorService.
     * 2. Make one task throw an exception.
     * 3. Use Future.get() to observe the success or failure of each task.
     *
     * Key idea:
     * If a task submitted with submit() throws an exception, the exception is
     * captured inside the Future.
     *
     * The exception is re-thrown to the caller when future.get() is called, wrapped
     * inside ExecutionException.
     */
    public static void main(String[] args) {
        List<Future<Integer>> futures = new ArrayList<>();

        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            for (int number = 1; number <= 5; number++) {
                int currentNumber = number;

                Future<Integer> future = executorService.submit(() -> {
                    System.out.println(Thread.currentThread().getName()
                            + " processing " + currentNumber);

                    if (currentNumber == 3) {
                        throw new IllegalStateException("Task " + currentNumber + " failed");
                    }

                    return currentNumber * currentNumber;
                });

                futures.add(future);
            }

            for (int i = 0; i < futures.size(); i++) {
                Future<Integer> future = futures.get(i);
                int taskNumber = i + 1;

                try {
                    int result = future.get();
                    System.out.println("Task " + taskNumber + " result = " + result);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                } catch (ExecutionException e) {
                    System.out.println("Task " + taskNumber + " failed: "
                            + e.getCause().getMessage());
                }
            }
        }

        System.out.println(Thread.currentThread().getName() + " finished");
    }
}
