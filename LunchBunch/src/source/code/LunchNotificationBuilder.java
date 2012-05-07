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
        Calendar lunchTime = (Calendar)this.lunch.getLunchTime().clone();
        lunchTime.add(Calendar.MINUTE, -1 * this.lunch.getReminderTime());
        builder.setWhen(lunchTime.getTimeInMillis());
        builder.setTicker("Reminder for " + this.lunch.getTitle());
        builder.setContentTitle("LunchBunch Notification");
        builder.setContentText("Upcoming lunch at " + this.lunch.getTitle());
        Intent intent = new Intent(context, BrowseAttending.class);
        builder.setContentIntent(PendingIntent.getActivity(context, 0, intent, 0));
        this.notification = builder.getNotification();
    }
    
    public Notification getNotification() {
        return this.notification;
    }

}
