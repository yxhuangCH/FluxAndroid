package com.yxhuang.fluxandroid;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yxhuang.fluxandroid.adapter.TodoAdapter;
import com.yxhuang.fluxandroid.data.DataType;
import com.yxhuang.fluxandroid.data.TodoData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private static final String TAG = "TodoMainActivity";

    private EditText edt_input;
    private Button btn_add;
    private RecyclerView recycler_view;
    private Button btn_all;
    private Button btn_active;
    private Button btn_complete;


    private List<TodoData> mDatas = new ArrayList();
    private TodoAdapter mAdapter;

    private List<TodoData> mWorkDatas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_input = findViewById(R.id.edt_input);
        btn_add = findViewById(R.id.btn_add);
        recycler_view = findViewById(R.id.recycler_view);
        btn_all = findViewById(R.id.btn_all);
        btn_active = findViewById(R.id.btn_active);
        btn_complete = findViewById(R.id.btn_complete);

        mDatas.clear();
        mWorkDatas.clear();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(manager);

        mAdapter = new TodoAdapter(this, mWorkDatas);
        recycler_view.setAdapter(mAdapter);

        mAdapter.setCheckboxSelect(new TodoAdapter.ICheckboxSelect() {
            @Override
            public void onSelected(int index, TodoData data) {
                if (data != null && mDatas.contains(data)){
                  int indexOf = mDatas.indexOf(data);
                    TodoData msg = mDatas.get(indexOf);
                    if (msg != null){
                        msg.setType(DataType.COMPLETE);
                    }
                }

                Log.i(TAG, "onSelected index=" + index);
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(edt_input.getText().toString())){
                    addMessage(edt_input.getText().toString());
                    clearInput();
                }
            }
        });

        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAll();

            }
        });
        btn_active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectActive();
            }
        });
        btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectComplete();
            }
        });

    }

    private void addMessage(String msg){
        Log.i(TAG, "addMessage msg= " + msg);
        TodoData todoData = new TodoData(msg);
        mDatas.add(todoData);

        mWorkDatas.add(todoData);
        mAdapter.notifyDataSetChanged();
    }

    private void clearInput(){
        edt_input.getText().clear();
    }

    private void selectAll(){
        mWorkDatas.clear();
        mWorkDatas.addAll(mDatas);
        mAdapter.notifyDataSetChanged();
    }

    private void selectActive(){
        mWorkDatas.clear();
        for (int i = 0; i < mDatas.size(); i++){
            TodoData data = mDatas.get(i);
            if (data.getType() == DataType.ACTIVE){
                mWorkDatas.add(data);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    private void selectComplete(){
        mWorkDatas.clear();
        for (int i = 0; i < mDatas.size(); i++){
            TodoData data = mDatas.get(i);
            if (data.getType() == DataType.COMPLETE){
                mWorkDatas.add(data);
            }
        }
        mAdapter.notifyDataSetChanged();
    }
}
