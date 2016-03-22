/*
 * Copyright © 2015, Aditya Birla Money Limited
 *  Written under contract by Robosoft Technologies Pvt. Ltd.
 */

package fb.robo.com.gcmtestapplication;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.TypedValue;
import android.widget.RemoteViews;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Calendar;

/**
 * Created by varnesh on 2/7/15.
 */
public class NotificationDemoActivity extends Activity {

    private static final String TAG = NotificationDemoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gcmtest);
        testGCMMessage();
    }

    private void testGCMMessage() {
        GCMMessage gcmMessage = new GCMMessage();
        gcmMessage.setTitle("Test Notification");
        gcmMessage.setNotificationMessage("This is normal notification to test. It uses Android NotificationCompat.Builder to construct the notification and call Notification manager API to show the status bar notification.");
        gcmMessage.setId(String.valueOf(NotificationID.getID()));
        gcmMessage.setImageUrl("http://www.avsforum.com/photopost/data/2277869/9/9f/9f50538d_test.jpeg");
        // new ImageDownloadTask(gcmMessage).execute();
        // sendDefaultNotification(gcmMessage, null);
        // sendDefaultNotification(gcmMessage, null);
        // sendBiGPictureNotification(gcmMessage, null);
        // sendBiGPictureNotification(gcmMessage, null);
        // sendBiGViewStyleNotification(gcmMessage);
        //  sendBiGViewStyleNotification(gcmMessage);
        //sendButtonViewNotification(gcmMessage);
        // sendButtonViewNotification(gcmMessage);
       // sendCustomBigTextStyleNotification(gcmMessage);
       // sendCustomBigTextStyleNotification(gcmMessage);
        sendCustomNotification(gcmMessage, null);
        sendCustomNotification(gcmMessage, null);
    }

    private class ImageDownloadTask extends AsyncTask<Void, Void, Bitmap> {

        private GCMMessage mGCMMessage;

        public ImageDownloadTask(GCMMessage gcmMessage) {
            mGCMMessage = gcmMessage;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                return  Picasso.with(NotificationDemoActivity.this).load(mGCMMessage.getImageUrl()).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            Log.d(TAG, "onPostExecute: bitmap" + bitmap);
            // sendNotification(mGCMMessage, bitmap);
            // sendBiGPictureNotification(mGCMMessage, bitmap);
            // sendBiGPictureNotification(mGCMMessage, bitmap);
            //sendBiGViewStyleNotification(mGCMMessage);
            //sendButtonViewNotification(mGCMMessage);
            // sendButtonViewNotification(mGCMMessage);
            //sendCustomNotification(mGCMMessage, bitmap);
            //sendCustomNotification(mGCMMessage, bitmap);
        }
    }


    private void sendNotification(GCMMessage gcmMessage, Bitmap notifImage) {
        Intent intent = new Intent(this, NotificationDemoActivity.class);
        int notifId = NotificationID.getID();
        intent.putExtra(GCMConstants.MESSAGE_ID, gcmMessage.getId());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, notifId, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.drawable.common_ic_googleplayservices);
        notificationBuilder.setContentTitle(gcmMessage.getTitle());
        notificationBuilder.setContentText(gcmMessage.getNotificationMessage());
        notificationBuilder.setAutoCancel(false);
        notificationBuilder.setSound(defaultSoundUri);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notifId, notificationBuilder.build());
    }

    public static Bitmap getFontBitmap(Context context, String text, int color, float fontSizeSP) {
        int fontSizePX = convertDiptoPix(context, fontSizeSP);
        int pad = (fontSizePX / 9);
        Paint paint = new Paint();
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/NotoSans-Regular.ttf");
        paint.setAntiAlias(true);
        paint.setTypeface(typeface);
        paint.setColor(color);
        paint.setTextSize(fontSizePX);

        int textWidth = (int) (paint.measureText(text) + pad * 2);
        int height = (int) (fontSizePX / 0.75);
        Bitmap bitmap = Bitmap.createBitmap(textWidth, height, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        float xOriginal = pad;
        canvas.drawText(text, xOriginal, fontSizePX, paint);
        return bitmap;
    }


    public Bitmap buildUpdate(Context context, String text, int color, float fontSizeSP)  {
        Paint paint = new Paint();
        int fontSizePX = convertDiptoPix(context, fontSizeSP);
        int pad = (fontSizePX / 9);
        int bmpWidth = (int) (paint.measureText(text) + pad * 2);
        int bmpHeight = (int) (fontSizePX / 0.75);
        Bitmap myBitmap = Bitmap.createBitmap(bmpWidth, bmpHeight,
                Bitmap.Config.ARGB_8888);
        Canvas myCanvas = new Canvas(myBitmap);
        Typeface clock = Typeface.createFromAsset(context.getAssets(),
                "fonts/NotoSans-Regular.ttf");
        paint.setAntiAlias(true);
        paint.setSubpixelText(true);
        paint.setTypeface(clock);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        paint.setTextSize(fontSizePX);
        paint.setTextAlign(Paint.Align.CENTER);
        myCanvas.drawText(text, bmpWidth / 2, bmpHeight / 2 + (bmpHeight / 4),
                paint);
        return myBitmap;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent: Action: " + intent.getAction());
        Log.d(TAG, "onNewIntent: BTN2: " + intent.getStringExtra("BTN2"));
        Log.d(TAG, "onNewIntent: BTN3: " + intent.getStringExtra("BTN3"));
        super.onNewIntent(intent);
    }

    public static int convertDiptoPix(Context context, float dip) {
        int value = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
        return value;
    }

    /**
     * Create and show a simple notification containing the received GCM message.
     * @param gcmMessage received.
     * @param notifImage
     */
    private void sendDefaultNotification(GCMMessage gcmMessage, Bitmap notifImage) {
        Intent intent = new Intent(this, NotificationDemoActivity.class);
        int notifId = NotificationID.getID();
        intent.putExtra(GCMConstants.MESSAGE_ID, gcmMessage.getId());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, notifId, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        android.support.v4.app.NotificationCompat.Builder notificationBuilder = new android.support.v4.app.NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.drawable.common_ic_googleplayservices);
        notificationBuilder.setContentTitle("Default Notification");
        notificationBuilder.setContentText(gcmMessage.getNotificationMessage());
        notificationBuilder.setAutoCancel(false);
        notificationBuilder.setSound(defaultSoundUri);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notifId, notificationBuilder.build());
    }

    private PendingIntent getNotifBtnPendingIntent(int notifId, String key, String value) {
        Intent firstBtnIntent = new Intent(this, NotificationDemoActivity.class);
        firstBtnIntent.putExtra(key, value);
        firstBtnIntent.setAction(value);
        PendingIntent leftBtnPendingIntent = PendingIntent.getActivity(this, notifId, firstBtnIntent,
                PendingIntent.FLAG_ONE_SHOT);
        return leftBtnPendingIntent;
    }

    private RemoteViews getNotificationCollapsedView(GCMMessage gcmMessage) {
        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.custom_notification);
        contentView.setImageViewResource(R.id.notif_icon, R.drawable.common_ic_googleplayservices);
        //contentView.setImageViewBitmap(R.id.notif_image, bitmap);
        // Bitmap titleBitmap = getFontBitmap(this, gcmMessage.getTitle(), Color.BLACK, 14);
        // Bitmap textBitmap = getFontBitmap(this, "This is normal notification to test", R.color.login_desc_text_color, 13);
        contentView.setTextViewText(R.id.notif_title, "Custom Notification");
        contentView.setTextViewText(R.id.notif_text, gcmMessage.getNotificationMessage());
        contentView.setTextViewText(R.id.notif_timestamp, TimeStampUtils.getTimeInHHMM(
                Calendar.getInstance().getTime()));
       /* contentView.setImageViewBitmap(R.id.notif_title, titleBitmap);
        */
        return contentView;
    }

    private RemoteViews getNotificationExpandedView(int notifId, GCMMessage gcmMessage, Bitmap bitmap) {
        RemoteViews bigContentView = new RemoteViews(getPackageName(), R.layout.custom_notification_expanded);
        bigContentView.setImageViewResource(R.id.notif_icon, R.drawable.common_ic_googleplayservices);
        bigContentView.setTextViewText(R.id.notif_title, "Custom Notification");
        bigContentView.setTextViewText(R.id.notif_text, gcmMessage.getNotificationMessage());
        bigContentView.setImageViewResource(R.id.notif_image, R.drawable.register_banner);
        bigContentView.setTextViewText(R.id.notif_timestamp, TimeStampUtils.getTimeInHHMM(
                Calendar.getInstance().getTime()));
        bigContentView.setOnClickPendingIntent(R.id.left_btn, getNotifBtnPendingIntent(notifId, "BTN1", "ZING"));
        bigContentView.setOnClickPendingIntent(R.id.middle_btn, getNotifBtnPendingIntent(notifId, "BTN2", "LIFESTYLE"));
        bigContentView.setOnClickPendingIntent(R.id.last_btn, getNotifBtnPendingIntent(notifId, "BTN3", "SHOPPER STOP"));
        return bigContentView;
    }

    private void sendCustomNotification(GCMMessage gcmMessage, Bitmap bitmap) {
        Intent intent = new Intent(this, NotificationDemoActivity.class);
        int notifId = NotificationID.getID();
        intent.putExtra(GCMConstants.MESSAGE_ID, gcmMessage.getId());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, notifId, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.drawable.common_ic_googleplayservices);
        notificationBuilder.setAutoCancel(false);

        RemoteViews contentView = getNotificationCollapsedView(gcmMessage);
        notificationBuilder.setContent(contentView);
        // notificationBuilder.setContentTitle(gcmMessage.getTitle());
        //notificationBuilder.setContentText(gcmMessage.getNotificationMessage());
        notificationBuilder.setSound(defaultSoundUri);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        android.app.Notification notification = notificationBuilder.build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            RemoteViews bigContentView = getNotificationExpandedView(notifId, gcmMessage, bitmap);
            notification.bigContentView = bigContentView;
        }
        notificationManager.notify(notifId, notification);
    }

    private void sendBiGPictureNotification(GCMMessage gcmMessage, Bitmap notifImage) {
        Intent intent = new Intent(this, NotificationDemoActivity.class);
        int notifId = NotificationID.getID();
        intent.putExtra(GCMConstants.MESSAGE_ID, gcmMessage.getId());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, notifId, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.drawable.common_ic_googleplayservices);
        notificationBuilder.setContentTitle("Big Picture Style");
        notificationBuilder.setContentText(gcmMessage.getNotificationMessage());
        Bitmap decodedBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.register_banner);
        // if (notifImage != null) {
        NotificationCompat.BigPictureStyle  bigPictureStyle = new NotificationCompat.BigPictureStyle();
        bigPictureStyle.bigPicture(decodedBitmap);
        bigPictureStyle.setBigContentTitle("Big Picture Style");
        bigPictureStyle.setSummaryText(gcmMessage.getNotificationMessage());
        notificationBuilder.setStyle(bigPictureStyle);
        //}
        notificationBuilder.setAutoCancel(false);
        notificationBuilder.setSound(defaultSoundUri);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notifId, notificationBuilder.build());
    }

    private void sendBigTextStyleNotification(GCMMessage gcmMessage) {
        Intent intent = new Intent(this, NotificationDemoActivity.class);
        int notifId = NotificationID.getID();
        intent.putExtra(GCMConstants.MESSAGE_ID, gcmMessage.getId());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, notifId, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.drawable.common_ic_googleplayservices);
        notificationBuilder.setContentTitle("Big Text Style");
        notificationBuilder.setContentText(gcmMessage.getNotificationMessage());
        NotificationCompat.BigTextStyle  bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("Big Text Style");
        bigTextStyle.bigText(gcmMessage.getNotificationMessage());
        notificationBuilder.setStyle(bigTextStyle);
        notificationBuilder.setAutoCancel(false);
        notificationBuilder.setSound(defaultSoundUri);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notifId, notificationBuilder.build());
    }

    private SpannableStringBuilder getColoredLabel(String label) {
        final SpannableStringBuilder sb = new SpannableStringBuilder(label);

        final ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.bright_red_text));
        final StyleSpan bss = new StyleSpan(Typeface.BOLD);

        sb.setSpan(fcs, 0, sb.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sb.setSpan(bss, 0, sb.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
       // RelativeSizeSpan rzs = new RelativeSizeSpan(1.2f);
        //sb.setSpan(rzs, 0, sb.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return sb;
    }

    private SpannableStringBuilder getColoredLabels() {
        String label1 = "ZARA";
        String label2 = "LIFESTYLE";
        String label3 = "SHOPPER STOP";
        final SpannableStringBuilder sb = new SpannableStringBuilder(/*"\t\t\t\t\t"*/  label1 + "\t\t" + label2 + "\t\t" + label3);

        final ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.bright_red_text));
        final StyleSpan bss = new StyleSpan(Typeface.BOLD);

        sb.setSpan(fcs, 0, sb.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sb.setSpan(bss, 0, sb.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
       // RelativeSizeSpan rzs = new RelativeSizeSpan(1.2f);
       // sb.setSpan(rzs, 0, sb.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return sb;
    }

    private void sendCustomBigTextStyleNotification(GCMMessage gcmMessage) {
        Intent intent = new Intent(this, NotificationDemoActivity.class);
        int notifId = NotificationID.getID();
        intent.putExtra(GCMConstants.MESSAGE_ID, gcmMessage.getId());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, notifId, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.drawable.common_ic_googleplayservices);
        notificationBuilder.setContentTitle("Big Text Style");
        notificationBuilder.setContentText(gcmMessage.getNotificationMessage());
        NotificationCompat.BigTextStyle  bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("Big Text Style");
        bigTextStyle.bigText(gcmMessage.getNotificationMessage() /*+ "\n\n\n" + getColoredLabels()*/);
        bigTextStyle.setSummaryText(getColoredLabels());
        notificationBuilder.setStyle(bigTextStyle);
        notificationBuilder.setAutoCancel(false);
        notificationBuilder.setSound(defaultSoundUri);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notifId, notificationBuilder.build());
    }

    private void sendButtonViewNotification(GCMMessage gcmMessage) {
        Intent intent = new Intent(this, NotificationDemoActivity.class);
        int notifId = NotificationID.getID();
        intent.putExtra(GCMConstants.MESSAGE_ID, gcmMessage.getId());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, notifId, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Intent dismissIntent = new Intent(this, GCMListenerService.class);
        dismissIntent.setAction("action_dismiss");
        PendingIntent piDismiss = PendingIntent.getService(this, 0, dismissIntent, 0);

        Intent snoozeIntent = new Intent(this, GCMListenerService.class);
        snoozeIntent.setAction("action_snooze");
        PendingIntent piSnooze = PendingIntent.getService(this, 0, snoozeIntent, 0);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.drawable.common_ic_googleplayservices);
        notificationBuilder.setContentTitle("Action Buttons");
        notificationBuilder.setContentText(gcmMessage.getNotificationMessage());
        notificationBuilder.setAutoCancel(false);
        notificationBuilder.setSound(defaultSoundUri);
        notificationBuilder.setContentIntent(pendingIntent);
        notificationBuilder.addAction(0, getColoredLabel("ZARA"), piDismiss);
        notificationBuilder.addAction(0, getColoredLabel("LIFESTYLE"), piSnooze);
        notificationBuilder.addAction(0, getColoredLabel("SHOPPER STOP"), piSnooze);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        android.app.Notification notification = notificationBuilder.build();
        notificationManager.notify(notifId, notification);
    }
}
