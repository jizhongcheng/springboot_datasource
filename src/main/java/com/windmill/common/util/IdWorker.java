package com.windmill.common.util;

import java.util.Random;

public class IdWorker {
    private final long workerId;
    private final long datacenterId;
    private final long idepoch;
    private static final long workerIdBits = 5L;
    private static final long datacenterIdBits = 5L;
    private static final long maxWorkerId = 31L;
    private static final long maxDatacenterId = 31L;
    private static final long sequenceBits = 12L;
    private static final long workerIdShift = 12L;
    private static final long datacenterIdShift = 17L;
    private static final long timestampLeftShift = 22L;
    private static final long sequenceMask = 4095L;
    private long lastTimestamp = -1L;
    private long sequence;
    private static final Random r = new Random();

    public IdWorker() {
        this(1344322705519L);
    }

    public IdWorker(long idepoch) {
        this(r.nextInt(31), r.nextInt(31), 0L, idepoch);
    }

    public IdWorker(long workerId, long datacenterId, long sequence) {
        this(workerId, datacenterId, sequence, 1344322705519L);
    }

    public IdWorker(long workerId, long datacenterId, long sequence, long idepoch) {
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
        this.idepoch = idepoch;
        if ((workerId < 0L) || (workerId > 31L)) {
            throw new IllegalArgumentException("workerId is illegal: " + workerId);
        }
        if ((datacenterId < 0L) || (datacenterId > 31L)) {
            throw new IllegalArgumentException("datacenterId is illegal: " + workerId);
        }
        if (idepoch >= System.currentTimeMillis()) {
            throw new IllegalArgumentException("idepoch is illegal: " + idepoch);
        }
    }

    public long getDatacenterId() {
        return this.datacenterId;
    }

    public long getWorkerId() {
        return this.workerId;
    }

    public long getTime() {
        return System.currentTimeMillis();
    }

    public long getId() {
        long id = nextId();
        return id;
    }

    private synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < this.lastTimestamp) {
            throw new IllegalStateException("Clock moved backwards.");
        }
        if (this.lastTimestamp == timestamp) {
            this.sequence = (this.sequence + 1L & 0xFFF);
            if (this.sequence == 0L) {
                timestamp = tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0L;
        }
        this.lastTimestamp = timestamp;
        long id = timestamp - this.idepoch << 22 | this.datacenterId << 17 | this.workerId << 12 | this.sequence;

        return id;
    }

    public long getIdTimestamp(long id) {
        return this.idepoch + (id >> 22);
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdWorker{");
        sb.append("workerId=").append(this.workerId);
        sb.append(", datacenterId=").append(this.datacenterId);
        sb.append(", idepoch=").append(this.idepoch);
        sb.append(", lastTimestamp=").append(this.lastTimestamp);
        sb.append(", sequence=").append(this.sequence);
        sb.append('}');
        return sb.toString();
    }
}
