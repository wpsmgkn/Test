package ttwf.niule.com.expandable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.andexert.expandablelayout.library.ExpandableLayout;
import com.andexert.expandablelayout.library.ExpandableLayoutListView;

public class MainActivity extends AppCompatActivity {

    private final String[] array = {"Hello", "World", "Android", "is", "Awesome", "World",
            "Android", "is", "Awesome", "World", "Android", "is", "Awesome", "World",
            "Android", "is", "Awesome"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.view_row, R.id.header_text, array);

        final ExpandableLayoutListView expandableLayoutListView = (ExpandableLayoutListView) findViewById(R.id.listview);

        expandableLayoutListView.setAdapter(arrayAdapter);

        ExpandableLayout expandableLayout = (ExpandableLayout) findViewById(R.id.first);
        TextView textView = (TextView) expandableLayout.findViewById(R.id.header_text);
        EditText editText = (EditText) expandableLayout.findViewById(R.id.text);
        textView.setText("sdjkfhjskdhfjkhejwkf");
        editText.setText("sdfkjklasjkdlfje");
    }
}
