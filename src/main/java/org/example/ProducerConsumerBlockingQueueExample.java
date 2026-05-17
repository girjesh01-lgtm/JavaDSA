package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerBlockingQueueExample {

    /*
     * Scenario 3: One Producer Thread And One Consumer Thread
     *
     * Problem:
     * Use one producer thread to produce numbers from 1 to 10 and one consumer
     * thread to consume those numbers.
     *
     * Requirement:
     * 1. producerThread should add numbers into a shared queue.
     * 2. consumerThread should take numbers from the shared queue.
     * 3. If the queue is full, producerThread should wait.
     * 4. If the queue is empty, consumerThread should wait.
     * 5. consumerThread should stop after receiving a special stop signal.
     *
     * Key idea:
     * BlockingQueue handles the waiting internally.
     *
     * - put() waits when the queue is full.
     * - take() waits when the queue is empty.
     *
     * This avoids writing wait(), notify(), and synchronized manually.
     */
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);
        int stopSignal = -1;

        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    queue.put(i);
                    System.out.println(Thread.currentThread().getName() + " produced " + i);
                }

                queue.put(stopSignal);
                System.out.println(Thread.currentThread().getName() + " sent stop signal");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "producer-thread");

        Thread consumerThread = new Thread(() -> {
            try {
                while (true) {
                    int item = queue.take();

                    if (item == stopSignal) {
                        System.out.println(Thread.currentThread().getName() + " received stop signal");
                        return;
                    }

                    System.out.println(Thread.currentThread().getName() + " consumed " + item);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "consumer-thread");

        producerThread.start();
        consumerThread.start();
    }
}
