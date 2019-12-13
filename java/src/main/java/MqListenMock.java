import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author thinkam
 * @date 2019/7/15 20:35
 */
public class MqListenMock {
    private ExecutorService executor1 = Executors.newFixedThreadPool(1);
    private ExecutorService executor2 = Executors.newFixedThreadPool(1);
    private ExecutorService executor3 = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws InterruptedException {
        MqListenMock mqListenMock = new MqListenMock();

        mqListenMock.listen(1);
        mqListenMock.listen(1);
        mqListenMock.listen(1);
        mqListenMock.listen(2);
        mqListenMock.listen(2);
        mqListenMock.listen(2);
        mqListenMock.listen(3);
        mqListenMock.listen(3);
        mqListenMock.listen(3);

        mqListenMock.listen(1);
        mqListenMock.listen(1);
        mqListenMock.listen(1);
        mqListenMock.listen(1);
        mqListenMock.listen(1);
        mqListenMock.listen(1);
        mqListenMock.listen(1);
        mqListenMock.listen(1);
        mqListenMock.listen(1);

        mqListenMock.listen(1);
        mqListenMock.listen(2);
        mqListenMock.listen(3);
        mqListenMock.listen(1);
        mqListenMock.listen(2);
        mqListenMock.listen(3);
        mqListenMock.listen(1);
        mqListenMock.listen(2);
        mqListenMock.listen(3);
        mqListenMock.listen(1);
        mqListenMock.listen(2);
        mqListenMock.listen(3);
    }

    private Semaphore semaphore = new Semaphore(3);

    private void listen(int message) throws InterruptedException {
        semaphore.acquire();
        System.out.println("...called");
        if (message == 1) {
            executor1.execute(() -> {
                try {
                    process(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }
        if (message == 2) {
            executor2.execute(() -> {
                try {
                    process(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }
        if (message == 3) {
            executor3.execute(() -> {
                try {
                    process(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }
    }

    private void process(int message) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(2000);
        System.out.println("message -- " + message);
        TimeUnit.MILLISECONDS.sleep(20);
    }

    public static int hashHandle(String uniqueKey) {
        return uniqueKey.hashCode() % 3;
    }
}
