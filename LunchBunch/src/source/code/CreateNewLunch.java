package source.code;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.TimePicker;
import android.widget.Toast;

public class CreateNewLunch extends Activity {
    /** Called when the activity is first created. */

    private Button mPickDate;
    private Button mPickTime;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private Lunch thisLunch;
    private boolean isEditing = false;
    private String[] dateInfo;

    static final int TIME_DIALOG_ID = 1;

    
    static final int DATE_DIALOG_ID = 0;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String activity = intent.getStringExtra("activity");
        Global state = (Global) getApplication();
        
        //TODO: fix layout, the button on the bottom is cut off
        setContentView(R.layout.createlunch);
        
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
        
        mPickDate = (Button)findViewById(R.id.pickDate);
        mPickDate.setText("Click to set date", TextView.BufferType.EDITABLE);

        // add a click listener to the button
        mPickDate.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("deprecation")
			public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        
        mPickTime = (Button) findViewById(R.id.pickTime);
        mPickTime.setText("Click to set time", TextView.BufferType.EDITABLE);
        
        EditText where = ((EditText) findViewById(R.id.pickWhere));
        where.setOnEditorActionListener(new DoneOnEditorActionListener());

        if (activity != null)
        {
            thisLunch = state.getCurrentCreatingLunch();
            isEditing = true;
            String fullDate = thisLunch.getDate().toString();
    	    String[] splitDate = fullDate.split(" ");
    	    String date = splitDate[1] + " " + Integer.parseInt(splitDate[2]) + ", " + mYear;
    	   
        	mPickTime.setText(thisLunch.getTime());
        	mPickDate.setText(date);
            where = ((EditText) findViewById(R.id.pickWhere));
            where.setText(thisLunch.getTitle());
            String comments = thisLunch.getComments();
            EditText commentsField = (EditText) findViewById(R.id.comments);
            if (comments != null || !(comments.equals("")))
            {
            	commentsField.setText(comments);
            }
            Calendar reminderTime = thisLunch.getReminderTime();
            Spinner reminder = (Spinner) findViewById(R.id.spinner);
        			
            if (reminderTime != null)
            {
            	int reminderOffset = thisLunch.getReminderOffset();
            	switch (reminderOffset)
            	{
            	case 30: reminder.setSelection(0); break;
            	case 60: reminder.setSelection(1); break;
            	case 120: reminder.setSelection(2); break;
            	case 1440: reminder.setSelection(3); break;
            	}
            	
            }
            else
            {
            	RadioButton noButton = (RadioButton) findViewById(R.id.noReminder);
            	noButton.setChecked(true);
            	RadioButton yesButton = (RadioButton) findViewById(R.id.yesReminder);
            	yesButton.setChecked(false);
            	((TextView)findViewById(R.id.sendreminderlabel)).setVisibility(TextView.GONE);
    			((Spinner)findViewById(R.id.spinner)).setVisibility(Spinner.GONE);
    			((TextView)findViewById(R.id.confirmation)).setVisibility(TextView.GONE);
    			((RadioGroup)findViewById(R.id.confirmgroup)).setVisibility(RadioGroup.GONE);
            }

        }
        // add a click listener to the button
        mPickTime.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("deprecation")
			public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });

        // get the current time
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // add a click listener to the reminder radio buttons
       RadioButton no = ((RadioButton) findViewById(R.id.noReminder));
       RadioButton.OnClickListener noListener = new RadioButton.OnClickListener(){
    		  public void onClick(View v) {
    			((TextView)findViewById(R.id.sendreminderlabel)).setVisibility(TextView.GONE);
    			((Spinner)findViewById(R.id.spinner)).setVisibility(Spinner.GONE);
    			((TextView)findViewById(R.id.confirmation)).setVisibility(TextView.GONE);
    			((RadioGroup)findViewById(R.id.confirmgroup)).setVisibility(RadioGroup.GONE);
    		  }
       };
       no.setOnClickListener(noListener);
       RadioButton yes = ((RadioButton) findViewById(R.id.yesReminder));
       RadioButton.OnClickListener yesListener = new RadioButton.OnClickListener(){
    		  public void onClick(View v) {
    			((TextView)findViewById(R.id.sendreminderlabel)).setVisibility(TextView.VISIBLE);
    			((Spinner)findViewById(R.id.spinner)).setVisibility(Spinner.VISIBLE);
    			((TextView)findViewById(R.id.confirmation)).setVisibility(TextView.VISIBLE);
    			((RadioGroup)findViewById(R.id.confirmgroup)).setVisibility(RadioGroup.VISIBLE);
    		  }
       };
       yes.setOnClickListener(yesListener);
        

    }
    
    class DoneOnEditorActionListener implements OnEditorActionListener {
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;    
            }
            return false;
        }
    }
    
    public void onDoneClicked(View v) {
        String errortext = "";
        String time = ((Button) findViewById(R.id.pickTime)).getText().toString();
        if (time.equals("Click to set time")) {
            errortext = "time";
        }        
        String date = ((Button) findViewById(R.id.pickDate)).getText().toString();
        if (date.equals("Click to set date")) {
            errortext = "date";
        }
        String where = ((EditText) findViewById(R.id.pickWhere)).getText().toString();
        if (where.equals("")) {
            errortext = "location";
        }

        if (errortext.equals("")) {
            Calendar rightNow = Calendar.getInstance();
            dateInfo = date.split(" ");
            int month = getMonthNum(dateInfo[0]);
            System.out.println(dateInfo[0]);
            System.out.println(date);
            System.out.println(month);
            Calendar lunchTime = Calendar.getInstance();
            int offset = 0;
            if (time.split(" ")[1].equals("pm")) {
                offset = 12;
            }
            lunchTime.set(Integer.valueOf(dateInfo[2]), 
                    Integer.valueOf(month-1), 
                    Integer.valueOf(dateInfo[1].split(",")[0]),
                    Integer.valueOf(time.split(":")[0]) + offset,
                    Integer.valueOf(time.split(":")[1].split(" ")[0]),
                    0);
            
            if (rightNow.after(lunchTime)) {
                Toast.makeText(this, "The selected date has already occurred!", Toast.LENGTH_LONG).show();
            }
            else {
                String comments = ((EditText) findViewById(R.id.comments)).getText().toString();
                RadioButton yesButton = (RadioButton) findViewById(R.id.yesReminder);
                String reminder = null;
                int minutes = 15;
                boolean confirmRequested = false;
            	if(yesButton.isChecked())
            	{
            		reminder = ((Spinner) findViewById(R.id.spinner)).getSelectedItem().toString();
            		minutes = getMinutes(reminder);
            		RadioButton yesconfirm = (RadioButton) findViewById(R.id.yesConfirmation);
            		if (yesconfirm.isChecked())
            		{
            			confirmRequested = true;
            		}
            	}

            	if(isEditing)
            	{
            		thisLunch.setTitle(where);
            		thisLunch.setLunchTime(lunchTime);
            		thisLunch.setComments(comments);
            		thisLunch.setConfirmationRequested(confirmRequested);
            		if(reminder != null)
            		{
            			thisLunch.setReminderTime(minutes);
            		}
            	}
            	else
            	{
            		Lunch createdLunch = new Lunch(where);
            		((Global)getApplication()).setCurrentCreatingLunch(createdLunch);
            		createdLunch.setLunchTime(lunchTime);
            		createdLunch.setComments(comments);
            		createdLunch.setConfirmationRequested(confirmRequested);
            		System.out.println("confimation requested: " + confirmRequested);
            		if(reminder != null)
            		{
            			createdLunch.setReminderTime(minutes);
            		}
                    createdLunch.setMine(true);
            	}
            	
                Intent selectFriendsIntent = new Intent(this, SelectFriends.class);
                if(isEditing)
                {
                	selectFriendsIntent.putExtra("activity", "isEditing");
                }
                startActivityForResult(selectFriendsIntent, 0);
                
            }
        }
        
        else {
            Toast.makeText(this, "Please input a valid " + errortext, Toast.LENGTH_LONG).show();
        }
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            finish();
        }
        
        else if (resultCode == Activity.RESULT_CANCELED) {
        }
        
        else {
            throw new RuntimeException ("People to invite screen failed");
        }
    }
   

    
    private void updateTimeDisplay() {
    	
    	String AmPm = " am";
    	if (mHour > 12)
    	{
    		AmPm = " pm";
    	}
    	if (mHour == 12)
    	{
    		AmPm = " pm";
    	}
    	
    	int hour = mHour%12;
    	if (hour == 0){
    		hour = 12;
    	}

    	mPickTime.setText(
            new StringBuilder()
            .append((hour)).append(":")
            .append(pad(mMinute)).append(AmPm));

    }
    private void updateDateDisplay() {
    	String month = "";
    	switch(mMonth)
    	{
    	case 0: month = "Jan"; break;
    	case 1: month = "Feb"; break;
    	case 2: month = "Mar"; break;
    	case 3: month = "Apr"; break;
    	case 4: month = "May"; break;
    	case 5: month = "Jun"; break;
    	case 6: month = "Jul"; break;
    	case 7: month = "Aug"; break;
    	case 8: month = "Sep"; break;
    	case 9: month = "Oct"; break;
    	case 10: month = "Nov"; break;
    	case 11: month = "Dec"; break;
    	}
    	

    	/*Calendar lunchTime = Calendar.getInstance();
    	lunchTime.set(Integer.valueOf(mYear), 
                Integer.valueOf(mMonth), 
                Integer.valueOf(mDay),
                Integer.valueOf(0),
                Integer.valueOf(0),
                0);
    	String dayOfWeek = lunchTime.getTime().toString().split(" ")[0];*/
    	
    	mPickDate.setText(month + " " + mDay + ", " + pad(mYear));

    	/*mPickDate.setText(
            new StringBuilder()
                    .append((mMonth+1)).append("/")
                    .append((mDay)).append("/")
                    .append(pad(mYear)));*/
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
    
    public int getMonthNum(String month)
    {
	    if(month.equals("Jan")) return 1;
	    if(month.equals("Feb")) return 2;
	    if(month.equals("Mar")) return 3;
	    if(month.equals("Apr")) return 4;
	    if(month.equals("May")) return 5;
	    if(month.equals("Jun")) return 6;
	    if(month.equals("Jul")) return 7;
	    if(month.equals("Aug")) return 8;
	    if(month.equals("Sep")) return 9;
	    if(month.equals("Oct")) return 10;
	    if(month.equals("Nov")) return 11;
	    else return 12;





    }
    public int getMinutes(String time)
    {
	    if(time.equals("30 minutes")) return 30;
	    if(time.equals("1 hour")) return 60;
	    if(time.equals("2 hours")) return 120;
	    else return 24*60;
    }
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case DATE_DIALOG_ID:
            return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        case TIME_DIALOG_ID:
        	return new TimePickerDialog(this,
                         mTimeSetListener, mHour, mMinute, false);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

				public void onDateSet(DatePicker view, int year, 
                        int monthOfYear, int dayOfMonth) {
						mYear = year;
						mMonth = monthOfYear;
						mDay = dayOfMonth;
						updateDateDisplay();
     
				}
            };
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
           new TimePickerDialog.OnTimeSetListener() {

				
            	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        	        mHour = hourOfDay;
        	        mMinute = minute;
        	        updateTimeDisplay();

        	}
            };

            
}
