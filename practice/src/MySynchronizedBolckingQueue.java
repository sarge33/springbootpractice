import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MySynchronizedBolckingQueue {
    private AtomicInteger counter = new AtomicInteger();
    private Queue<Integer> queue = new LinkedList<>();
    private int capacity;
    private Object obj = new Object();


    public MySynchronizedBolckingQueue(int capacity) {
        this.capacity = capacity;
    }

    public int size() {
        return counter.get();
    }

    public void put(Integer val) {
        synchronized (obj) {
            while (counter.get() == capacity) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //1 加入元素
            queue.offer(val);
            //2 计数器累加
            counter.incrementAndGet();
            //3 通知另外一个线程（唤醒）
            obj.notify();
        }
    }

    public Integer get() {
        Integer val = null;
        synchronized (obj) {
            while(counter.get() == 0) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            val = queue.poll();
            counter.decrementAndGet();
            obj.notify();
        }
        return val;
    }
}

