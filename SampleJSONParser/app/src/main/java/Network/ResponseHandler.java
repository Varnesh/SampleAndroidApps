/*
 * Copyright © 2015, Kotak Mahindra Bank
 * Written under contract by Robosoft Technologies Pvt. Ltd.
 */

package Network;

import java.util.HashSet;
import java.util.Set;

/**
 * Class for storing response callbacks and contains methods for adding, deleting
 * and retrieving those callbacks.
 */
public class ResponseHandler {

    private Set<RestCallback<?>> mRestCallbacks = null;
    private static ResponseHandler mResponseHandler;

    private ResponseHandler() {
        if (mRestCallbacks == null) {
            mRestCallbacks = new HashSet<>();
        }
    }

    public static ResponseHandler get() {
        if (mResponseHandler == null) {
            mResponseHandler = new ResponseHandler();
        }
        return mResponseHandler;
    }

    /**
     * Method to put all response callbacks in a set.
     *
     * @param retrofitCallback Response listener.
     */
    public void addToCallbackList(RestCallback<?> retrofitCallback) {
        synchronized (mRestCallbacks) {
            mRestCallbacks.add(retrofitCallback);
        }
    }

    /**
     * Method to tag a particular response callback as cancelled matching filter criteria.
     *
     * @param filter Filter to apply.
     */
    private void cancelAll(ResponseFilter filter) {
        synchronized (mRestCallbacks) {
            for (RestCallback requestCallback : mRestCallbacks) {
                if (filter.apply(requestCallback)) {
                    requestCallback.setCancelled(true);
                }
            }
        }
    }

    /**
     * Method to remove given item from retrofit response callback set.
     *
     * @param responseCallback Response listener.
     */
    public void finishRequest(RestCallback<?> responseCallback) {
        synchronized (mRestCallbacks) {
            mRestCallbacks.remove(responseCallback);
        }
    }

    /**
     * Method to cancel all API response callbacks associated with a tag
     *
     * @param tag Unique name to tag API request.
     */
    public void cancelAllRequestsWithTag(final Object tag) {
        if (tag == null) {
            throw new IllegalArgumentException("Can not cancel with null a tag");
        }
        ResponseFilter filter = new ResponseFilter() {
            @Override
            public boolean apply(RestCallback<?> responseCallback) {
                return responseCallback.getTag() == tag;
            }
        };
        cancelAll(filter);
    }

}
