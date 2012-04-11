package source.code;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.SearchView;

public class SelectFriends extends Activity {
    
    ScrollView mScrollView;
    SearchView mSearchView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectfriends);
        
        mScrollView = (ScrollView) findViewById(R.id.scrollView1);
        mSearchView = (SearchView) findViewById(R.id.searchView1);
        
        mSearchView.requestFocus();
        

    }

}
