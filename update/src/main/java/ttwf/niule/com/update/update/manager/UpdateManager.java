package ttwf.niule.com.update.update.manager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import ttwf.niule.com.update.update.service.DownLoadService;
import ttwf.niule.com.update.update.utils.DeviceUtils;

/**
 * 作者： Hokas
 * 时间： 2016/7/15
 * 类别：
 */

public class UpdateManager {

    private Context mContext;

    public UpdateManager(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 检测软件更新
     */
    public void checkUpdate(boolean isToast){
        // 版本的更新信息
        String versionInfo = "更新内容\n" + "    1. 车位分享异常处理\n" + "    2. 发布车位折扣格式统一\n" + "    ";
        int mVersionCode = DeviceUtils.getVersionCode(mContext);
        int nVersionCode = 2;
        if(mVersionCode<nVersionCode){
            // 显示提示对话
            showNoticeDialog(versionInfo);
        }else {
            if (isToast) {
                Toast.makeText(mContext, "已经是最新版本", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showNoticeDialog(String info){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("更新提示");
        builder.setMessage(info);
        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mContext.startService(new Intent(mContext, DownLoadService.class));
            }
        });
        builder.setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }
}
