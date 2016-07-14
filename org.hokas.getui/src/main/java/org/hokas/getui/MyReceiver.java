package org.hokas.getui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.igexin.sdk.PushConsts;

/**
 * 作者： Hokas
 * 时间： 2016/7/12
 * 类别：
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MyReceiver", "jinru");
        Bundle bundle = intent.getExtras();

        switch (bundle.getInt(PushConsts.CMD_ACTION)) {
            case PushConsts.GET_CLIENTID:
                Log.d("MyReceiver", "aa");
                String cid = bundle.getString("clientid");
                // TODO:处理cid返回
                break;
            case PushConsts.GET_MSG_DATA:

                String taskid = bundle.getString("taskid");
                String messageid = bundle.getString("messageid");
                byte[] payload = bundle.getByteArray("payload");
                if (payload != null) {
                    String data = new String(payload);
                    // TODO:接收处理透传（payload）数据
                    Log.d("MyReceiver", data);
                    String title="标题";//标题
                    String content="内容";//内容

                    Intent i = new Intent(context, LoginActivity.class);
                    PendingIntent pi= PendingIntent.getActivity(context, 0, i, 0);

                    Notification notification = new Notification.Builder(context)
                            .setContentText(title)
                            .setContentTitle(content)
                            .setContentIntent(pi)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .build();

                    NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.notify(0, notification);

                    notif
                }
                break;
            default:
                break;
        }
    }

}
