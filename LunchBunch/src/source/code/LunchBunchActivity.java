package source.code;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LunchBunchActivity extends Activity {
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public void onButtonClicked(View v) {
        // Do something when the button is clicked
        Toast.makeText(LunchBunchActivity.this, "Button clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CreateNewLunch.class);
        startActivity(intent);
    }
}