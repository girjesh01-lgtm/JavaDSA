package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureReturnValueExample {

    /*
     * Scenario 7: Return Values From Threads Using Future
     *
     * Problem:
     * Submit multiple tasks to a thread pool. Each task should calculate and return
     * a value. The main thread should collect and print all returned values.
     *
     * Requirement:
     * 1. Use ExecutorService to run tasks.
     * 2. Each task should return the square of a number.
     * 3. Use Future.get() to retrieve each result.
     *
     * Key idea:
     * Runnable does not return a value.
     * Callable returns a value.
     *
     * executorService.submit(Callable<T>) returns Future<T>.
     * future.get() waits for the task to finish and returns the task result.
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future<Integer>> futures = new ArrayList<>();

        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            for (int number = 1; number <= 5; number++) {
                int currentNumber = number;

                Future<Integer> future = executorService.submit(() -> {
                    System.out.println(Thread.currentThread().getName()
                            + " calculating square of " + currentNumber);

                    Thread.sleep(1000);

                    return currentNumber * currentNumber;
                });

                futures.add(future);
            }

            for (Future<Integer> future : futures) {
                int result = future.get();
                System.out.println("Result = " + result);
            }
        }

        System.out.println(Thread.currentThread().getName() + " finished");
    }
}
