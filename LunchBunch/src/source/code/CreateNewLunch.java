package source.code;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class CreateNewLunch extends Activity {
    /** Called when the activity is first created. */


    private EditText mPickDate;
    private EditText mPickTime;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

    static final int TIME_DIALOG_ID = 1;

    
    static final int DATE_DIALOG_ID = 0;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createlunch);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
        
        mPickDate = (EditText)findViewById(R.id.pickDate);
        mPickDate.setText("Click to set Date", TextView.BufferType.EDITABLE);

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
        
        mPickTime = (EditText) findViewById(R.id.pickTime);
        mPickTime.setText("Click to set Time.", TextView.BufferType.EDITABLE);


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

        





    }
    private void updateTimeDisplay() {

    	mPickTime.setText(
            new StringBuilder()
                    .append(pad(mHour)).append(":")
                    .append(pad(mMinute)));
    }
    private void updateDateDisplay() {

    	mPickTime.setText(
            new StringBuilder()
                    .append(pad(mHour)).append(":")
                    .append(pad(mMinute)));
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
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

				@Override
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

				@Override
            	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        	        mHour = hourOfDay;
        	        mMinute = minute;
        	        updateTimeDisplay();

        	}
            };

}
