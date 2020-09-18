package com.windmill.common.util;

public class Id {
    private static final IdWorker IDWORKER = new IdWorker();

    public static long nextId() {
        return IDWORKER.getId();
    }
}
