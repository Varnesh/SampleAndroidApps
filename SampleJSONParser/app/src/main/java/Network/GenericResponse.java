package Network;


import com.google.gson.annotations.SerializedName;

/**
 * Generic class to handle JSON response from server.
 */
public class GenericResponse<T> {

    private static final String TAG = GenericResponse.class.getSimpleName();

    @SerializedName("statusCode")
    private int mStatusCode;

    //@SerializedName("errors")
    //private String[] mErrors;

    @SerializedName("isSuccessful")
    private boolean mIsSuccessful;

    @SerializedName("statusReason")
    private String mStatusReason;

    @SerializedName("code")
    private int mCode;

    @SerializedName("status")
    private String mStatus;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("response")
    private T mResponse;

    public int getStatusCode() {
        return mStatusCode;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        this.mStatus = status;
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        this.mCode = code;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public void setResponse(T response) {
        this.mResponse = response;
    }

    public boolean isSuccessful() {
        return mIsSuccessful;
    }

    public String getStatusReason() {
        return mStatusReason;
    }

    public T getResponse() {
        return mResponse;
    }

    @Override
    public String toString() {
      String responseStr = "statusCode: " + mStatusCode
              + "\nisSuccessful: " + mIsSuccessful
              + "\nstatusReason: " + mStatusReason
              + "\ncode: " + mCode
              + "\nstatus: " + mStatus
              + "\ndescription: " + mDescription
              + "\nresponse: " + mResponse;
        return responseStr;
    }
}
