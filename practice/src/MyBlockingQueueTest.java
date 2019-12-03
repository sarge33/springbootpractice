class Producer implements Runnable
{
    private MyReentrantLockBolckingQueue queue;
    private int seed = 1;

    Producer(MyReentrantLockBolckingQueue queue) {
        this.queue = queue;
    }

    public void run()
    {
        while (true)
        {
            queue.put(getResource());
            System.out.println("Thread <" +Thread.currentThread() + "> Produced resource - Queue size now = "  + queue.size());
        }
    }

    int getResource()
    {
        try
        {
            Thread.sleep(100); // simulate time passing during read
        }
        catch (InterruptedException ex)
        {
            System.out.println("Producer Read INTERRUPTED");
        }
        return seed++;
    }
}

class Consumer implements Runnable
{
    private MyReentrantLockBolckingQueue queue;

    Consumer(MyReentrantLockBolckingQueue queue) {
        this.queue = queue;
    }

    public void run()
    {
        while (true)
        {
            int val = queue.get();
            System.out.println("Thread <" +Thread.currentThread() + "> Consumed resource - Queue size now = "  + queue.size());
        }
    }

    void take(int val)
    {
        try
        {
            Thread.sleep(100); // simulate time passing
        }
        catch (InterruptedException ex)
        {
            System.out.println("Consumer Read INTERRUPTED");
        }
        System.out.println("Consuming object " + val);
    }
}

public class MyBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException  {
        int numProducers = 4;
        int numConsumers = 3;

        MyReentrantLockBolckingQueue myQueue = new MyReentrantLockBolckingQueue(20);
//        MySynchronizedBolckingQueue myQueue = new MyReentrantLockBolckingQueue(20);

        for (int i = 0; i < numProducers; i++){
            new Thread(new Producer(myQueue)).start();
        }

        for (int i = 0; i < numConsumers; i++){
            new Thread(new Consumer(myQueue)).start();
        }

        // Let the simulation run for, say, 10 seconds
        Thread.sleep(10 * 1000);

        // End of simulation - shut down gracefully
        System.exit(0);
    }
}
