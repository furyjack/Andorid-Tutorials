package com.example.admin.weartry;

import android.content.Intent;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.WearableListenerService;

public class MyService extends WearableListenerService {


    @Override
    public void onDataChanged(DataEventBuffer dataEventBuffer) {
        super.onDataChanged(dataEventBuffer);
        for(DataEvent dataevent:dataEventBuffer)
        {
          if(dataevent.getType()==DataEvent.TYPE_CHANGED)
          {
              DataMap map= DataMapItem.fromDataItem(dataevent.getDataItem()).getDataMap();
              String path= dataevent.getDataItem().getUri().getPath();
              if(path.equals("/int"))
              {
                  int num=map.getInt("num");
                  Intent myintent = new Intent("com.example.admin.weartry");
                  myintent.putExtra("message",""+ num);
                  myintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//You might need this
                  getApplicationContext().sendBroadcast(myintent);


              }


          }



        }
    }
}
