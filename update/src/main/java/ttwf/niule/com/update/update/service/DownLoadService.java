package ttwf.niule.com.update.update.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import ttwf.niule.com.update.R;
import ttwf.niule.com.update.update.fileload.FileCallback;
import ttwf.niule.com.update.update.fileload.FileResponseBody;
import ttwf.niule.com.update.update.utils.DeviceUtils;

/**
 * 作者： Hokas
 * 时间： 2016/7/15
 * 类别： 下载apk
 */

public class DownLoadService extends Service {
    /**
     * 目标文件存储的文件夹路径
     */
    private String destFileDir = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + "update";
    /**
     * 目标文件存储的文件名
     */
    private String destFileName = "三趣.apk";
    private Context mContext;
    private int preProgress = 0;
    private int NOTIFY_ID = 1000;
    private NotificationCompat.Builder builder;
    private NotificationManager mNotifacationManager;
    private Retrofit.Builder retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mContext = this;
        loadFile();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 下载文件
     */
    private void loadFile() {
        initNotification();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder();
        }
        retrofit.baseUrl("http://112.124.9.133:8080/parking-app-admin-1.0/android/manager/adminVersion/")
                .client(initOkHttpClient())
                .build()
                .create(IFileLoad.class)
                .loadFile()
                .enqueue(new FileCallback(destFileDir, destFileName) {
                    @Override
                    public void onLoading(long progress, long total) {
                        Log.e("zs", progress + "----" + total);
                        updateNotification(progress * 100 / total);
                    }

                    @Override
                    public void onSuccess(File file) {
                        Log.e("zs", "请求成功");
                        cancelNotification();
                        installApk(file);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("zs", "请求失败");
                        cancelNotification();
                    }
                });
    }

    /**
     * 更新通知
     */
    private void updateNotification(long progress) {
        int currProgress = (int) progress;
        if (preProgress < currProgress) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, (int) progress, false);
            mNotifacationManager.notify(NOTIFY_ID, builder.build());
        }
        preProgress = (int) progress;
    }

    /**
     * 安装文件
     *
     * @param file
     */
    private void installApk(File file) {
        Uri uri = Uri.fromFile(file);
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        install.setDataAndType(uri, "application/vnd.android.package-archive");
        mContext.startActivity(install);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    /**
     * 初始化Notification通知
     */
    private void initNotification() {
        builder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("0%")
                .setContentTitle("三趣更新")
                .setProgress(100, 0, false);
        mNotifacationManager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        mNotifacationManager.notify(NOTIFY_ID, builder.build());
    }

    /**
     * 初始化OkHttpClient
     */
    private OkHttpClient initOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(100000, TimeUnit.SECONDS);
        builder.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                return originalResponse.newBuilder().body(new FileResponseBody(originalResponse))
                        .build();
            }
        });
        return builder.build();
    }

    public interface IFileLoad {
        @GET("download")
        Call<ResponseBody> loadFile();
    }

    /**
     * 取消通知
     */
    public void cancelNotification() {
        mNotifacationManager.cancel(NOTIFY_ID);
    }
}