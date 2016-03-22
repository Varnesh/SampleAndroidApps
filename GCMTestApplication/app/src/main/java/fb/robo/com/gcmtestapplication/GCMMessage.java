/*
 * Copyright © 2015, Aditya Birla Money Limited
 * Written under contract by Robosoft Technologies Pvt. Ltd.
 */

package fb.robo.com.gcmtestapplication;

/**
 * Created by Girish K N on 08-Jun-15.
 */
public class GCMMessage {
    private String id;
    private String actionStatus;
    private String notificationMessage;
    private String title;
    private String imageUrl;
    private long timestamp;

    public String getId() {
        return id;
    }

    public String getActionStatus() {
        return actionStatus;
    }
    public String getNotificationMessage() {
        return notificationMessage;
    }

    public String getTitle() {
        return title;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }
}
