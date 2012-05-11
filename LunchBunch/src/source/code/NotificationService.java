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
    private int lunch_id = 0;
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
            Lunch reminder;
            Calendar rightNow;
            while(true) {
                rightNow = Calendar.getInstance();
                if (state.numLunchReminders() > 0) {
                    reminder = state.getNextReminder();
                    Calendar reminderTime = reminder.getReminderTime();
                    if (reminderTime != null && reminderTime.before(rightNow)) {
                        showNotification(reminder, "reminder");
                        state.lunchReminded();
                    }
                }
                rightNow.add(Calendar.MINUTE, -2);
                if (rightNow.after(state.getStartTime()) && state.getFakeInviteBool() == false) {
                   showNotification(state.getFakeLunch(), "invite");
                   state.setFakeInviteBool(true);
                }
            }
        }
        
        public void showNotification(Lunch lunch, String type) {
            if (type.equals("invite")) {
                state.addLunchInvite(state.getFakeLunch());
            }
            LunchNotificationBuilder notificationBuilder = new LunchNotificationBuilder(lunch, getApplicationContext(), type);
            Notification dhabaNotification = notificationBuilder.getNotification();
            notificationManager.notify(lunch_id, dhabaNotification);
            lunch_id++;
            
        }
    }

}
