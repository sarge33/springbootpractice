import static java.lang.Thread.*;

public class DeadLockExample {

    public static void main(String[] args) {
        Object lockA = new Object();
        Object lockB = new Object();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockA) {
                    System.out.println("Thread A" + Thread.currentThread() + " run, locked A ");
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lockB){
                        System.out.println("Thread A" + Thread.currentThread() + " run, locked B ");
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Thread A" + Thread.currentThread() + " done, unlocked B ");
                    }
                    System.out.println("Thread A" + Thread.currentThread() + " done, unlocked A");
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (lockB) {
                System.out.println("Thread B" + Thread.currentThread() + " run, locked B ");
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockA) {
                    System.out.println("Thread B" + Thread.currentThread() + " run, locked A ");
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Thread B" + Thread.currentThread() + " done, unlocked A");
                }
                System.out.println("Thread B" + Thread.currentThread() + " done, unlocked B ");
            }

        }
        );

        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
