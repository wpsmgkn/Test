package daggertest.xrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.xrecyclerview.MyAdapter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

public class CollapActivity extends AppCompatActivity {
    private XRecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private ArrayList<String> listData;
    private int refreshTime = 0;
    private int times = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collap);

        mRecyclerView = (XRecyclerView)this.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallTrianglePath);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime ++;
                times = 0;
                new Handler().postDelayed(new Runnable(){
                    public void run() {

                        listData.clear();
                        for(int i = 0; i < 15 ;i++){
                            listData.add("item" + i + "after " + refreshTime + " times of refresh");
                        }
                        mAdapter.notifyDataSetChanged();
                        mRecyclerView.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                if(times < 2){
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            mRecyclerView.loadMoreComplete();
                            for(int i = 0; i < 15 ;i++){
                                listData.add("item" + (i + listData.size()) );
                            }
                            mRecyclerView.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for(int i = 0; i < 9 ;i++){
                                listData.add("item" + (1 + listData.size() ) );
                            }
                            mRecyclerView.setNoMore(true);
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
                times ++;
            }
        });

        listData = new ArrayList<String>();
        for(int i = 0; i < 15 ;i++){
            listData.add("item" + i);
        }
        mAdapter = new MyAdapter(listData);
        mRecyclerView.setAdapter(mAdapter);
    }
}
