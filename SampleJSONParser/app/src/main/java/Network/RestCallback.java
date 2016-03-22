/*
 * Copyright © 2015, Kotak Mahindra Bank
 * Written under contract by Robosoft Technologies Pvt. Ltd.
 */

package Network;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Generic class for REST API callback.
 */
public class RestCallback<T> implements CancellableCallback<T> {

    private static final String TAG = RestCallback.class.getSimpleName();
    private Class<?> mClazz;

    private ResponseCallback<T> mResponseCallback;
    private T mGenericType;
    private boolean mIsCancelled;
    private  Object mTag;

    public RestCallback(ResponseCallback<T> callback) {
        mResponseCallback = callback;
    }

    public RestCallback(ResponseCallback<T> callback, Class<?> clazz, Object tag) {
        mClazz = clazz;
        mResponseCallback = callback;
        mTag = tag;
    }

    public void setCancelled(boolean isCanceled) {
        mIsCancelled = isCanceled;
    }

    @Override
    public boolean isCancelled() {
        return mIsCancelled;
    }

    @Override
    public Object getTag() {
        return mTag;
    }

    @Override
    public void setTag(String tag) {
        mTag = tag;
    }

    private class ClassTypeAdapter extends TypeAdapter<Class<?>> {
        @Override
        public void write(JsonWriter jsonWriter, Class<?> clazz) throws IOException {
            if(clazz == null){
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.value(clazz.getName());
        }

        @Override
        public Class<?> read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            Class<?> clazz = null;
            try {
                clazz = Class.forName(jsonReader.nextString());
            } catch (ClassNotFoundException exception) {
                throw new IOException(exception);
            }
            return clazz;
        }
    }

    public class ClassTypeAdapterFactory implements TypeAdapterFactory {
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if(!Class.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            return (TypeAdapter<T>) new ClassTypeAdapter();
        }
    }

    public void dummyCall() {
        String responseText = "{\"statusCode\": 200,\"isSuccessful\": true,\"statusReason\": \"OK\",\"code\": 101,\"status\": \"success\",\"description\":\"This is test message\",\"response\": {\"dc\": \"http://purl.org/dc/elements/1.1/\",\"content\": \"http://purl.org/rss/1.0/modules/content/\",\"atom\": \"http://www.w3.org/2005/Atom\"}}";
        if (!TextUtils.isEmpty(responseText)) {
            Class<T> classT = (Class<T>) mClazz.getClass();
           GenericResponse<T> genericResponse = null;
            try {
               /* GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapterFactory(new ClassTypeAdapterFactory());
                Gson gson = gsonBuilder.create();*/
                Gson gson = new Gson();
                Type type = new TypeToken<GenericResponse<T>>() {}.getType();
              //  TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
                Log.d(TAG, "type: " + type);

                genericResponse = gson.fromJson(responseText, type);
                Log.d(TAG, "genericResponse: " + genericResponse);
                Object object = gson.fromJson((String) genericResponse.getResponse(), classT);
                genericResponse.setResponse((T) object);

               /* JSONObject jsonObject = new JSONObject(responseText);
                if (jsonObject.has("response")) {
                    JSONObject modeljson = (JSONObject) jsonObject.get("response");
                    String modelResponse = modeljson.toString();
                    Log.d(TAG, "modelResponse: " + modelResponse);
                    Type type2 = new TypeToken<T>() {
                    }.getType();
                    Object object = gson.fromJson(modelResponse, type2);
                    genericResponse.setResponse((T) object);
                }*/
            } catch (JsonSyntaxException jse) {
                Log.e(TAG, "JsonSyntaxException: " + jse.getMessage());
                jse.printStackTrace();
            }/* catch (JSONException jse) {
                jse.printStackTrace();
            }*/
            if (mResponseCallback != null) {
                mResponseCallback.success((T) genericResponse);
            }
        }
    }
}
