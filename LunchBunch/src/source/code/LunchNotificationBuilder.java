package source.code;
import java.util.Calendar;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class LunchNotificationBuilder {

    //OS 4.0.3
//    private Notification notification;
//    public LunchNotificationBuilder(Lunch lunch, Context context) {
//        Builder builder = new Builder(context);
//        Calendar reminderTime = (Calendar)lunch.getReminderTime().clone();
//        builder.setWhen(reminderTime.getTimeInMillis());
//        builder.setTicker("Reminder for " + lunch.getTitle());
//        builder.setContentTitle("LunchBunch Notification");
//        builder.setContentText("Upcoming lunch at " + lunch.getTitle());
//        builder.setSmallIcon(R.drawable.ic_launcher);
//        Intent intent = new Intent(context, InviteDetails.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        builder.setContentIntent(PendingIntent.getActivity(context, 0, intent, 0));
//        this.notification = builder.getNotification();
//    }
    
    private Notification notification;
    public LunchNotificationBuilder(Lunch lunch, Context context) {
        int icon = R.drawable.ic_launcher;
        String tickerText = "Reminder for " + lunch.getTitle();
        long when = System.currentTimeMillis();
        this.notification = new Notification(icon, tickerText, when);
        Calendar reminderTime = (Calendar)lunch.getReminderTime().clone();
        String contentTitle = "LunchBunch Notification";
        String contentText = "Upcoming lunch at " + lunch.getTitle() + " at " + lunch.getTime();
        Intent intent = new Intent(context, InviteDetails.class);
        intent.putExtra("activity", "attending");
        intent.putExtra("lunch", lunch);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(lunch.getTitle());
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        notification.setLatestEventInfo(context, contentTitle, contentText, pendingIntent);
    }
    
    public Notification getNotification() {
        return this.notification;
    }

}
