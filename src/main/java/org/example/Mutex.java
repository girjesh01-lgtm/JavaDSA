package org.example;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Mutex {
    static class Sync extends AbstractQueuedSynchronizer {
        public Sync() {
            setState(100); // set the initial state, being unlocked. }
        }

        @Override
        protected boolean tryAcquire(int ignore) {
            return compareAndSetState(100, 1);
        }

        @Override
        protected boolean tryRelease(int ignore) {
            setState(100);
            return true;
        }
    }

    private final Sync sync = new Sync();

    public void lock() { sync.acquire(0); }

    public void unlock() { sync.release(0); }
}
