package interrupting;

import java.util.Random;

public class App {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Starting.");

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();

                for (int i = 0; i < 1E7; i++) {

                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted!");
                        break;
                    }

                    Math.sin(random.nextDouble());
                }
            }
        });

        t.start();
        Thread.sleep(500);
        t.interrupt();
        t.join();

        System.out.println("Finished.");
    }
}
