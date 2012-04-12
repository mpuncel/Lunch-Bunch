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
<<<<<<< HEAD
        Intent intent = new Intent(this, TabsActivity.class);
=======
        Intent intent = new Intent(this, SelectFriends.class);
>>>>>>> 7f4ddf81827a60ef6e706959f8d8d2982f1e4b3a
        startActivity(intent);
    }
}