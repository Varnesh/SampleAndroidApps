package fb.robo.com.gcmtestapplication;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by varnesh on 29/5/15.
 */
public class NotificationID {
    private final static AtomicInteger c = new AtomicInteger(0);
    public static int getID() {
        return c.incrementAndGet();
    }
}
