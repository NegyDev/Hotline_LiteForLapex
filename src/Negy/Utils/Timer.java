package Negy.Utils;

public class Timer {
    private int tick = 0;
    private long lastMS = 0;
    private long previousTime = 0;

    public void update() {
        tick++;
    }

    public void reset() {
        tick = 0;
        lastMS = getCurrentMS();
    }

    public boolean hasTimePassed(int ticks) {
        return tick >= ticks;
    }

    public Timer() {
        lastMS = 0L;
        previousTime = -1L;
    }

    public boolean check(float milliseconds) {
        return System.currentTimeMillis() - previousTime >= milliseconds;
    }

    public boolean delay(double milliseconds) {
        return (getCurrentMS() - lastMS) >= milliseconds;
    }

    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }
}
