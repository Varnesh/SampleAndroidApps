package com.sample.json;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import Network.GenericResponse;
import Network.ResponseCallback;
import Network.RestCallback;
import Network.RestError;
import model.Rss;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ResponseCallback<GenericResponse<Rss>> responseResponseCallback = new ResponseCallback<GenericResponse<Rss>>() {

            @Override
            public void success(GenericResponse<Rss> response) {
                Log.d(TAG, "ResponseCallback: genericResponse: " + response);
                if (response.getResponse() instanceof Rss) {
                    Log.d(TAG, "Instance of Type RSS");
                }
                Rss rss =  response.getResponse();
                Log.d(TAG, "dc: " + rss.getDc());
                Log.d(TAG, "atom: " + rss.getAtom());

                Log.d(TAG, "getSecretData: response: " + rss.toString());
            }

            @Override
            public void failure(RestError error) {

            }
        };
        RestCallback<GenericResponse<Rss>> restCallback = new RestCallback<GenericResponse<Rss>>(responseResponseCallback, Rss.class, null);
        restCallback.dummyCall();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
