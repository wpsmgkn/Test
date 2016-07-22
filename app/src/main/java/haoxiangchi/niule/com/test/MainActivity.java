package haoxiangchi.niule.com.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;

import java.util.List;

import haoxiangchi.niule.com.test.utils.MyViewGroup;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

//    private String[] tabs = {"乐器", "声乐", "戏曲", "舞蹈", "书画", "语言艺术", "吉他", "钢琴"};
    private String tabs = "{\n" +
        "    \"date\": [\n" +
        "        [\n" +
        "            \"钢琴\",\n" +
        "            \"吉他\",\n" +
        "            \"古筝\",\n" +
        "            \"琵琶\"\n" +
        "        ],\n" +
        "        [\n" +
        "            \"二胡\",\n" +
        "            \"提琴\",\n" +
        "            \"电子琴\",\n" +
        "            \"电贝司\"\n" +
        "        ],\n" +
        "        [\n" +
        "            \"架子鼓\",\n" +
        "            \"葫芦丝\",\n" +
        "            \"萨克斯\",\n" +
        "            \"尤克里里\"\n" +
        "        ],\n" +
        "        [\n" +
        "            \"声乐\",\n" +
        "            \"戏曲表演\",\n" +
        "            \"语音艺术\",\n" +
        "            \"街舞\"\n" +
        "        ],\n" +
        "        [\n" +
        "            \"芭蕾\",\n" +
        "            \"现代舞\",\n" +
        "            \"国际舞\",\n" +
        "            \"其他\"\n" +
        "        ]\n" +
        "    ]\n" +
        "}";
    private MyViewGroup mys;
    private LinearLayout llTab;
    private ListView listView;
//    private List<TabModel> tabModels;
    TabAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        llTab = (LinearLayout) findViewById(R.id.llTab);
//        Log.d("MainActivity", tabs);
//        mys = new MyViewGroup(this);
//        for (String tab1 : tabs) {
//            View view = LayoutInflater.from(this).inflate(R.layout.ch, null);
//            CheckBox textView = (CheckBox) view.findViewById(R.id.ch);
//            textView.setText(tab1);
//            textView.setOnCheckedChangeListener(this);
//            mys.addView(view);
//        }
        TabModel model = JSON.parseObject(tabs,TabModel.class);
        adapter = new TabAdapter(this,model.getDate());

        Log.d("MainActivity", model.toString());
//        llTab.addView(mys);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
