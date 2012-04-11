package lunchbunch.createnewform;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class MyOnItemSelectedListener implements OnItemSelectedListener {



	@Override
    public void onItemSelected(AdapterView<?> parent,
            View view, int pos, long id) {
          Toast.makeText(parent.getContext(), "The planet is " +
              parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
        }

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}