package org.example;

public class AlternateOddEvenWaitNotifyExample {

    /*
     * Scenario 2: Print 1 To 20 Alternately Using Two Threads
     *
     * Problem:
     * Use two separate threads to print numbers from 1 to 20 in order.
     *
     * Requirement:
     * 1. oddThread should print odd numbers: 1, 3, 5, ..., 19
     * 2. evenThread should print even numbers: 2, 4, 6, ..., 20
     * 3. Output should be alternating and ordered:
     *    1 2 3 4 5 6 ... 20
     *
     * Key idea:
     * Both threads share one current number.
     *
     * - oddThread waits while the current number is even.
     * - evenThread waits while the current number is odd.
     * - after one thread prints, it increments the number and notifies the other.
     */
    public static void main(String[] args) {
        NumberPrinter numberPrinter = new NumberPrinter(20);

        Thread oddThread = new Thread(numberPrinter::printOddNumbers, "odd-thread");
        Thread evenThread = new Thread(numberPrinter::printEvenNumbers, "even-thread");

        oddThread.start();
        evenThread.start();
    }

    private static class NumberPrinter {
        private final Object lock = new Object();
        private final int max;
        private int current = 1;

        private NumberPrinter(int max) {
            this.max = max;
        }

        private void printOddNumbers() {
            while (true) {
                synchronized (lock) {
                    while (current <= max && current % 2 == 0) {
                        waitForTurn();
                    }

                    if (current > max) {
                        lock.notifyAll();
                        return;
                    }

                    System.out.println(Thread.currentThread().getName() + " -> " + current);
                    current++;
                    lock.notifyAll();
                }
            }
        }

        private void printEvenNumbers() {
            while (true) {
                synchronized (lock) {
                    while (current <= max && current % 2 != 0) {
                        waitForTurn();
                    }

                    if (current > max) {
                        lock.notifyAll();
                        return;
                    }

                    System.out.println(Thread.currentThread().getName() + " -> " + current);
                    current++;
                    lock.notifyAll();
                }
            }
        }

        private void waitForTurn() {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
