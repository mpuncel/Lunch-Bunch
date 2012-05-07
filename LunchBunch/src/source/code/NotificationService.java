package source.code;

import java.util.Calendar;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class NotificationService extends Service {

    private NotificationManager notificationManager;
    private final static int LUNCH_ID = 0;
    public final IBinder binder = new NotificationBinder();
    @Override
    public IBinder onBind(Intent arg0) {
        return binder;
    }
    
    public class NotificationBinder extends Binder {
        NotificationService getService() {
            return NotificationService.this;
        }
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Thread notificationThread = new NotificationThread((Global)getApplication());
        notificationThread.start();
        return Service.START_STICKY;
    }
    
    public void onCreate() {
        String ns = Context.NOTIFICATION_SERVICE;
        notificationManager = (NotificationManager) getSystemService(ns);
    }
    
    public class NotificationThread extends Thread {
        
        Global state;
        public NotificationThread(Global state) {
            this.state = state;
        }
        
        public void run() {
            System.out.println(state.getNextReminder().getTitle());
            while(true) {
                if (state.numLunchesAttending() > 0) {
                    Lunch reminder = state.getNextReminder();
                    Calendar rightNow = Calendar.getInstance();
                    if (reminder.getReminderTime().after(rightNow)) {
                        showNotification(reminder);
                    }
                }
            }
        }
        
        public void showNotification(Lunch lunch) {
            LunchNotificationBuilder notificationBuilder = new LunchNotificationBuilder(lunch, getApplicationContext());
            Notification dhabaNotification = notificationBuilder.getNotification();
            notificationManager.notify(LUNCH_ID, dhabaNotification);
        }
    }

}
