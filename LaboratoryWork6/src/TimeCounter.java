public class TimeCounter {
    private int counter;

    public TimeCounter() {
        this.counter = 0;
    }

    public synchronized void increment() { ++counter; }

    public synchronized int getCounter() { return this.counter; }


    @Override public String toString() { return this.counter + " m"; }

}
