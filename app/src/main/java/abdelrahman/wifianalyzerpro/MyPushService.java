package abdelrahman.wifianalyzerpro;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.huawei.hms.push.HmsMessageService;
import com.huawei.hms.push.RemoteMessage;

public class MyPushService  extends HmsMessageService {
    private static final String TAG = "PushDemoLog";
    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.d(TAG, "receive token:" + token);
        Toast.makeText(this, token, Toast.LENGTH_SHORT).show();
        sendTokenToDisplay(token);
    }
    @Override
    public void onTokenError(Exception e)
    {
        super.onTokenError(e);
        System.out.println("exception == " + e);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        System.out.println("blabla");
        sendTokenToDisplay(remoteMessage.getNotification().getBody());

        if (remoteMessage.getData().length() > 0) {
            Log.i(TAG, "Message data payload: " + remoteMessage.getData());
        }
        if (remoteMessage.getNotification() != null) {
            Log.i(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }

    @Override
    public void onMessageSent(String s) {
    }

    @Override
    public void onSendError(String s, Exception e) {
    }

    private void sendTokenToDisplay(String token) {
        Intent intent=new Intent("com.huawei.push.codelab.ON_NEW_TOKEN");
        intent.putExtra("token", token);
        System.out.println("token from push service == " + token);
        sendBroadcast(intent);
    }
}
