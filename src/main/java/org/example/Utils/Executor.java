package org.example.Utils;

import java.util.concurrent.*;

public class Executor {
    public static boolean runWithTimeout(Runnable task, int seconds) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(task);
        try {
            future.get(seconds, TimeUnit.SECONDS);
            executor.shutdown();
            return true; // completed in time
        } catch (TimeoutException e) {
            future.cancel(true);
            executor.shutdownNow();
            return false; // timeout
        } catch (Exception e) {
            executor.shutdownNow();
            return false; // error
        }
    }

}
