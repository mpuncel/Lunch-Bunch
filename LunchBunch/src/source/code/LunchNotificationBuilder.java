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
    public LunchNotificationBuilder(Lunch lunch, Context context, String type) {
        int icon = R.drawable.ic_launcher;
        String contentTitle = "LunchBunch";
        Intent intent = new Intent(context, InviteDetails.class);
        intent.putExtra("lunch", lunch);
        intent.setAction(lunch.getTitle());
        
        long when = System.currentTimeMillis();
        if (type.equals("reminder")) {
            String tickerText = "Reminder for " + lunch.getTitle();
            this.notification = new Notification(icon, tickerText, when);
            String contentText = "Upcoming lunch at " + lunch.getTitle() + " at " + lunch.getTime();
            intent.putExtra("activity", "attending");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            notification.setLatestEventInfo(context, contentTitle, contentText, pendingIntent);
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
        }
        
        if (type.equals("invite")) {
            String tickerText = "You've been invited to " + lunch.getTitle();
            this.notification = new Notification(icon, tickerText, when);
            String contentText = "Invite for lunch at " + lunch.getTitle();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("activity", "invites");
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            notification.setLatestEventInfo(context, contentTitle, contentText, pendingIntent);
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
        }
    }
    
    public Notification getNotification() {
        return this.notification;
    }

}
