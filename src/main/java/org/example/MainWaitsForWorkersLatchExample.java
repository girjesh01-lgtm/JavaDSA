package org.example;

import java.util.concurrent.CountDownLatch;

public class MainWaitsForWorkersLatchExample {

    /*
     * Scenario 4: Main Waits For 3 Worker Threads To Finish
     *
     * Problem:
     * Start three worker threads. Each worker does some work and then finishes.
     * The main thread should wait until all three workers have completed before it
     * continues.
     *
     * Requirement:
     * 1. main starts worker-1, worker-2, and worker-3.
     * 2. The workers may complete in any order.
     * 3. main should continue only after all three workers are done.
     *
     * Key idea:
     * CountDownLatch with count 3 means:
     * "Wait until countDown() has been called 3 times."
     *
     * CountDownLatch does not control the order of worker execution.
     * It only lets main wait for all completion signals.
     */
    public static void main(String[] args) throws InterruptedException {
        int workerCount = 3;
        CountDownLatch workersDone = new CountDownLatch(workerCount);

        for (int workerId = 1; workerId <= workerCount+1; workerId++) {
            int currentWorkerId = workerId;

            Thread workerThread = new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " started");
                    Thread.sleep(currentWorkerId * 500L);
                    System.out.println(Thread.currentThread().getName() + " finished");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    workersDone.countDown();
                }
            }, "worker-" + workerId);

            workerThread.start();
        }

        System.out.println(Thread.currentThread().getName() + " waits for all workers");
        workersDone.await();
        System.out.println(Thread.currentThread().getName() + " continues after all workers finished");
    }
}
