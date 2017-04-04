package com.carlospienovi.mvpproject.utils;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class BusProvider {

    private static final Bus INSTANCE = new PostingBus();

    private BusProvider() {
    }

    public static Bus getInstance() {
        return INSTANCE;
    }

    public static void register(Object... objects) {
        for (Object o : objects) {
            if (o != null) {
                INSTANCE.register(o);
            }
        }
    }

    public static void unregister(Object... objects) {
        for (Object o : objects) {
            if (o != null) {
                INSTANCE.unregister(o);
            }
        }
    }

    private static class PostingBus extends Bus {

        private final Handler handler = new Handler(Looper.getMainLooper());

        PostingBus() {
            super(ThreadEnforcer.ANY);
        }

        @Override
        public void post(final Object event) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                super.post(event);
                return;
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    PostingBus.super.post(event);
                }
            });
        }

    }

}
