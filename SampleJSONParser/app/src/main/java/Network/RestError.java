/*
 * Copyright © 2015, Kotak Mahindra Bank
 * Written under contract by Robosoft Technologies Pvt. Ltd.
 */

package Network;

import java.io.Serializable;

/**
 * Class for handling common Rest errors such as server down or connection errors.
 */
public class RestError implements Serializable {

    private int mCode;
    private String mTitle;
    private String mMessage;

    public RestError(int errorCode, String message) {
        this.mCode = errorCode;
        this.mMessage = message;
    }

    public RestError(int errorCode, String mTitle, String message) {
        this.mCode = errorCode;
        this.mTitle = mTitle;
        this.mMessage = message;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getCode() {
        return mCode;
    }

    public String getMessage() {
        return mMessage;
    }
}
