package com.example.administrator.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> titleList;
    private List<String> contentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleList = new ArrayList<>();
        contentList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            titleList.add("title" + i);
            contentList.add("content" + i);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        设置为列表布局
//        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

 //        设置为瀑布流布局,构造器中，第一个参数表示列数或者行数3，第二个参数表示滑动方向
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

         final MyAdapter adapter = new MyAdapter(titleList, contentList, MainActivity.this);
        recyclerView.setAdapter(adapter);

//        方法一：利用View.onClickListener及onLongClickListener，在Adapter中处理RecyclerView的点击事件
        adapter.setOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "单击" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
//                Toast.makeText(MainActivity.this, "长按了" + position, Toast.LENGTH_SHORT).show();
//          长按删除条目
                 adapter.removeData(position);
            }
        });

//        方法二：利用内部接口RecyclerView.OnItemTouchListener（通过手势检测类：GestrueDetector）
//      大体思路是：我们可以在onInterceptTouchEvent获取当前触摸位置对应的子item view，
//      根据点击状态决定是否要把事件拦截，在拦截的时候同时添加一个回调方法，这样我们自己实现的监听器接口就能在这里得到回调。
//       存在的问题：子View获得了其内部控件点击事件的焦点
//        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity.this, recyclerView, new RecyclerItemClickListener.OnRecyclerItemClickListener() {
//            @Override
//            public void onClickListener(View view, int positon) {
//                Toast.makeText(MainActivity.this,"单击了第"+positon+"的条目",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onLongClickListener(View view, int positon) {
//                Toast.makeText(MainActivity.this,"长按了第"+positon+"的条目",Toast.LENGTH_SHORT).show();
//
//            }
//        }));

    }
}
