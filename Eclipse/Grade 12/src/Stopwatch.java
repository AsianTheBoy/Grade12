public class Stopwatch {

    /**
     * duration is the cumulative amount of time, in milliseconds, that has passed on the stopwatch between start() and stop() calls.
     * duration does have the amount of time since the last start() call if stop() has not been called after it at some point.
     * 
     * start is the system time, in milliseconds, for when start() was called.
     */
    private long duration = 0;
    private long start = 0;
    
    /**
     * When this flag is true, the stopwatch is still going (stop() hasn't been called since the last start() call),
     * and the duration doesn't update until it's stopped, so the time returned needs account for the actively counting time.
     */
    private boolean active = false;

    /**
     * Starts the stopwatch.
     */
    public void start() {
        this.start = System.currentTimeMillis();
        this.active = true;
    }
    
    /**
     * Stops the stopwatch.
     */
    public void stop() {
        if (this.active) this.duration += (System.currentTimeMillis() - this.start);
        this.active = false;
    }

    /**
     * Stops the stopwatch and resets the duration back to 0.
     */
    public void reset() {
        this.duration = 0;
        this.start = 0;
        this.active = false;
    }
    
    /**
     * Restarts the stopwatch by calling reset() and then start(). Resets the stopwatch back to 0 and then starts it again.
     */
    public void restart() {
        this.reset();
        this.start();
    }
    
    /**
     * Returns the duration of the stopwatch as a long, in milliseconds.
     * The duration starts when the stopwatch is first started using start() and is finished when the stopwatch is lastly stopped using stop(),
     * or when this method is called if the stopwatch was not stopped since most recently started.
     * 
     * @return the duration of the stopwatch, in milliseconds, since it was started until it was stopped (or until this method is called if it was not stopped)
     */
    public long getMilliseconds() {
        if (this.active) return this.duration + System.currentTimeMillis() - this.start;
        else return this.duration;
    }
    
    /**
     * Returns the duration of the stopwatch as a double, in seconds.
     * The duration starts when the stopwatch is first started using start() and is finished when the stopwatch is lastly stopped using stop(),
     * or when this method is called if the stopwatch was not stopped since most recently started.
     * 
     * @return the duration of the stopwatch, in seconds, since it was started until it was stopped (or until this method is called if it was not stopped)
     */
    public double getSeconds() {
        if (this.active) return (this.duration + System.currentTimeMillis() - this.start) / 1000d;
        else return this.duration / 1000d;
    }
    
    /**
     * Returns the duration of the stopwatch as a double, in minutes.
     * The duration starts when the stopwatch is first started using start() and is finished when the stopwatch is lastly stopped using stop(),
     * or when this method is called if the stopwatch was not stopped since most recently started.
     * 
     * @return the duration of the stopwatch, in minutes, since it was started until it was stopped (or until this method is called if it was not stopped)
     */
    public double getMinutes() {
        if (this.active) return (this.duration + System.currentTimeMillis() - this.start) / 60000d;
        else return this.duration / 60000d;
    }
    
    /**
     * Returns whether or not the stopwatch is currently active (has been started but not stopped since the most recent start() call).
     * 
     * @return true if stopwatch is currently active (has been started but not stopped since the most recent start() call), and false otherwise.
     */
    public boolean isGoing() {
        return this.active;
    }
}