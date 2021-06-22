import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class B {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        int producerCnt = 6;
        int consumerCnt = 4;
        Thread[] producers = new Thread[producerCnt];
        Thread[] consumers = new Thread[consumerCnt];
        for (int i = 0; i < producerCnt; i++) {
            producers[i] = new Thread(new Producer(queue, i));
            producers[i].start();
        }
        for (int i = 0; i < consumerCnt; i++) {
            consumers[i] = new Thread(new Consumer(queue, i));
            consumers[i].start();
        }
    }


}

class Producer implements Runnable {

    private BlockingQueue<Integer> queue;

    private int index;

    public Producer(BlockingQueue<Integer> queue, int index) {
        this.queue = queue;
        this.index = index;
    }

    @Override
    public void run() {
        while (true) {
            // 生产一个数字
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Integer r = new Random().nextInt(100);
            System.out.println("生产者" + index + "生产了：" + r + "， 队列长度：" + queue.size());
            try {
                queue.put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class Consumer implements Runnable {

    private BlockingQueue<Integer> queue;

    private int index;

    public Consumer(BlockingQueue<Integer> queue, int index) {
        this.queue = queue;
        this.index = index;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Integer take = queue.take();
                Thread.sleep(1000);
                System.out.println("消费者" + index + "消费了：" + take + "， 队列长度：" + queue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
