package com.werpindia.internnigeria;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;


import androidx.test.espresso.IdlingResource;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;

public class RxIdlingResource implements IdlingResource, Function<Runnable, Runnable> {

    private static final String TAG = RxIdlingResource.class.getSimpleName();

    private static final ReentrantReadWriteLock IDLING_STATE_LOCK = new ReentrantReadWriteLock();

    // Guarded by IDLING_STATE_LOCK
    private int taskCount = 0;

    // Guarded by IDLING_STATE_LOCK
    private ResourceCallback transitionCallback;

    @Override
    public String getName() {
        return TAG;
    }

    @Override
    public boolean isIdleNow() {

        boolean result;

        IDLING_STATE_LOCK.readLock().lock();
        result = taskCount == 0;
        IDLING_STATE_LOCK.readLock().unlock();

        return result;
    }

    @Override
    public void registerIdleTransitionCallback(final ResourceCallback callback) {
        IDLING_STATE_LOCK.writeLock().lock();
        this.transitionCallback = callback;
        IDLING_STATE_LOCK.writeLock().unlock();
    }

    @Override
    public Runnable apply(final Runnable runnable) throws Exception {
        IDLING_STATE_LOCK.writeLock().lock();
        taskCount++;
        IDLING_STATE_LOCK.writeLock().unlock();
        return () -> {
            try {
                runnable.run();
            } finally {
                IDLING_STATE_LOCK.writeLock().lock();

                try {
                    taskCount--;

                    if (taskCount == 0 && transitionCallback != null) {
                        transitionCallback.onTransitionToIdle();
                    }
                } finally {
                    IDLING_STATE_LOCK.writeLock().unlock();
                }
            }
        };
    }

    public void waitForIdle() {
        if (!isIdleNow()) {
            CountDownLatch latch = new CountDownLatch(1);
            registerIdleTransitionCallback(latch::countDown);
            try {
                latch.await();
            } catch (InterruptedException e) {
            }
        }
    }

    public void register() {
        RxJavaPlugins.setScheduleHandler(this);
    }
}
