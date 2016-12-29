package com.example.admin.weartry;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.util.Random;

public class MainActivity extends Activity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

    Button mbutton;
    GoogleApiClient mapiclient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mbutton=(Button)findViewById(R.id.btnsend);
                mbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sendint(new Random().nextInt(15));
                    }
                });
            }
        });
        mapiclient=new GoogleApiClient.Builder(this).addApi(Wearable.API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
        mapiclient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    void sendint(int i)
    {
        PutDataMapRequest putDataMapRequest=PutDataMapRequest.create("/int");
        putDataMapRequest.getDataMap().putInt("num",i);
        PutDataRequest putDataRequest=putDataMapRequest.asPutDataRequest();
        Wearable.DataApi.putDataItem(mapiclient,putDataRequest).setResultCallback(new ResultCallbacks<DataApi.DataItemResult>() {
            @Override
            public void onSuccess(@NonNull DataApi.DataItemResult dataItemResult) {


            }

            @Override
            public void onFailure(@NonNull Status status) {

            }
        });



    }
}
