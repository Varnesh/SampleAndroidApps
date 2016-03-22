/*
 * Copyright © 2015, Kotak Mahindra Bank
 * Written under contract by Robosoft Technologies Pvt. Ltd.
 */

package Network;

/**
 *  Interface to define filter for retrofit response callback.
 */
public interface ResponseFilter {
    boolean apply(RestCallback<?> retrofitCallback);
}
