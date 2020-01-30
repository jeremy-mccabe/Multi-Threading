package interrupting;

import java.util.Random;
import java.util.concurrent.*;

public class App2 {

    public static void main(String[] args) {

        System.out.println("Starting.");

        ExecutorService executor = Executors.newCachedThreadPool();

        Future<?> future = executor.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {

                Random random = new Random();

                for (int i = 0; i < 1E7; i++) {

                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted!");
                        break;
                    }

                    Math.sin(random.nextDouble());
                }
                return null;
            }
        });

        executor.shutdown();

        try {

            Thread.sleep(500);

            future.cancel(true);

            executor.awaitTermination(1, TimeUnit.DAYS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished.");
    }
}
