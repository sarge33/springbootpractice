import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyReentrantLockBolckingQueue {
    private AtomicInteger counter = new AtomicInteger();
    private Queue<Integer> queue = new LinkedList<>();
    private Lock lock = new ReentrantLock();
    private Condition emptyCond = lock.newCondition();
    private Condition fullCond = lock.newCondition();
    private int capacity;


    public MyReentrantLockBolckingQueue(int capacity) {
        this.capacity = capacity;
    }
    public int size() {
        return counter.get();
    }

    public void put(Integer val) {
        lock.lock();
        try {
            while (counter.get() == capacity) {
                try {
                    fullCond.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //1 add new element
            queue.offer(val);
            //2 increment counter
            counter.incrementAndGet();
            //3 singnal other threads
            emptyCond.signalAll();
        } finally {
             lock.unlock();
        }
    }

    public Integer get() {
        lock.lock();
        Integer val = null;
        try {
            while(counter.get() == 0) {
                try {
                    emptyCond.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            val = queue.poll();
            counter.decrementAndGet();
            fullCond.signalAll();
        } finally {
            lock.unlock();
        }
        return val;
    }

}

