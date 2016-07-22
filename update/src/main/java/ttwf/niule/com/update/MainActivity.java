package ttwf.niule.com.update;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ttwf.niule.com.update.update.manager.UpdateManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new UpdateManager(this).checkUpdate(false);
    }
}
