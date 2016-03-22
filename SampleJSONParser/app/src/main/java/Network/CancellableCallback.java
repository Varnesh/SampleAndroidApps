/*
 * Copyright © 2015, Kotak Mahindra Bank
 * Written under contract by Robosoft Technologies Pvt. Ltd.
 */

package Network;

/**
 * Interface for defining cancellable tags.
 */
public interface CancellableCallback<T> {

    void setCancelled(boolean isCanceled);

    boolean isCancelled();

    Object getTag();

    void setTag(String tag);


}
