/*
 * Copyright © 2015, Kotak Mahindra Bank
 * Written under contract by Robosoft Technologies Pvt. Ltd.
 */

package Network;

/**
 * Listener Interface for getting API response.
 */
public interface ResponseCallback<T> {

    void success(T response);

    void failure(RestError error);
}
