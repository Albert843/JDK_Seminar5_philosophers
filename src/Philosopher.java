import java.util.concurrent.atomic.AtomicReference;

public class Philosopher implements Runnable{
    private final Object leftFork;
    private final Object rightFork;

    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep((long) (Math.random() * 1000));
    }

    @Override
    public void run() {
        while (true) {
            try {
                doAction(" Thinking");
                synchronized (leftFork) {
                    doAction(" Picked up left fork");
                    synchronized (rightFork) {
                        doAction(" Picked up right fork and eating");
                        doAction(" Put down right fork");
                    }
                    doAction(" put down left fork, finish eating");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
