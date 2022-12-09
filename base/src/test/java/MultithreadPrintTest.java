import org.junit.jupiter.api.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultithreadPrintTest {

    private volatile int count = 0;
    private final Object lockObj = new Object();

    @Test
    public void bestSolve() throws InterruptedException {
        int threadNum = 3;
        Thread t = null;
        for (int j = 1; j <= threadNum; j++) {
            final int order = j;
            t = new Thread(() -> {
                while (true) {
                    // 所有逻辑是一个完整的状态机,由synchronized与以下判断条件共同作用保证任何时刻总有一个线程
                    // 执行notifyAll()函数
                    synchronized (lockObj) {
                        try {
                            if (count % threadNum == order - 1) {
                                ++count;
                                System.out.println(Thread.currentThread().getName() + " count: " + count);
                                //通知后释放所持对象锁,如果不是再次竞争obj锁，
                                //不管线程初始化任意延迟，只要保证任何时刻能有某个线程能notifyAll(),则逻辑自洽，
                                //因为判断条件保证3线程无法同一时间进入wait()方法
                                lockObj.notifyAll();
                            } else {
                                lockObj.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, "printThread-" + j);
            t.start();
            Thread.sleep(1000);
        }
        t.join();
    }

    Semaphore semaphore = new Semaphore(1);

    @Test
    public void useSemaphore() throws InterruptedException {
        int threadNum = 3;
        Thread t = null;
        for (int j = 1; j <= threadNum; j++) {
            final int order = j;
            t = new Thread(() -> {
                while (true) {
                    try {
                        semaphore.acquire();
                        if (count % threadNum == order - 1) {
                            ++count;
                            System.out.println(Thread.currentThread().getName() + " count: " + count);
                        }
                        semaphore.release();
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                    }
                }
            }, "printThread-" + j);
            t.start();
            Thread.sleep(1000);
        }
        t.join();
    }

    private static Lock lock = new ReentrantLock();


    @Test
    public void useLock() throws InterruptedException {
        int threadNum = 3;
        Thread t = null;
        for (int j = 1; j <= threadNum; j++) {
            final int order = j;
            t = new Thread(() -> {
                while (true) {
                    try {
                        boolean locked = lock.tryLock();
                        if (!locked) {
                            continue;
                        }
                        if (count % threadNum == order - 1) {
                            ++count;
                            System.out.println(Thread.currentThread().getName() + " count: " + count);
                        }
                        lock.unlock();
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                    }
                }
            }, "printThread-" + j);
            t.start();
            Thread.sleep(1000);
        }
        t.join();
    }

    //CyclicBarrier使用场景较少= =


}
