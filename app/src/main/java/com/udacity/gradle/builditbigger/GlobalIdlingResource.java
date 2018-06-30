package com.udacity.gradle.builditbigger;

import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class GlobalIdlingResource implements IdlingResource {

    private final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
    @Nullable
    private volatile ResourceCallback callback;

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return atomicBoolean.get();
    }

    @Override
    public void registerIdleTransitionCallback(IdlingResource.ResourceCallback callback) {
        this.callback = callback;
    }

    public void idleState(boolean state) {
        this.atomicBoolean.set(state);

        if (callback != null && state) {
            Objects.requireNonNull(callback).onTransitionToIdle();
        }
    }
}
