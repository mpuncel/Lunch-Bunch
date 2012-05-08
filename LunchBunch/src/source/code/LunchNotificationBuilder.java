package source.code;
import java.util.Calendar;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class LunchNotificationBuilder {
    
    private Lunch lunch;
    private Builder builder;
    private Notification notification;
    public LunchNotificationBuilder(Lunch lunch, Context context) {
        this.lunch = lunch;
        this.builder = new Builder(context);
        Calendar reminderTime = (Calendar)this.lunch.getReminderTime().clone();
        builder.setWhen(reminderTime.getTimeInMillis());
        builder.setTicker("Reminder for " + this.lunch.getTitle());
        builder.setContentTitle("LunchBunch Notification");
        builder.setContentText("Upcoming lunch at " + this.lunch.getTitle());
        builder.setSmallIcon(R.drawable.ic_launcher);
        Intent intent = new Intent(context, InviteDetails.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        builder.setContentIntent(PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT));
        this.notification = builder.getNotification();
    }
    
    public Notification getNotification() {
        return this.notification;
    }

}
