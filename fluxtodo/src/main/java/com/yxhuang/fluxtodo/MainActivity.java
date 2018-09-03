package com.yxhuang.fluxtodo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yxhuang.flux.base.BaseActivity;
import com.yxhuang.fluxannotation.FluxAnnotation;
import com.yxhuang.fluxtodo.action.MainAction;
import com.yxhuang.fluxtodo.adapter.TodoAdapter;
import com.yxhuang.fluxtodo.data.TodoData;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainStore>{
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
    public void onCreate(Bundle savedInstanceState) {
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
              mStore.setSelect(data);
              Log.i(TAG, "onSelected index=" + index);
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(edt_input.getText().toString())){
                    mStore.addMessage(edt_input.getText().toString());
                    clearInput();
                }
            }
        });

        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStore.selectAll();

            }
        });
        btn_active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStore.selectActive();
            }
        });
        btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStore.selectComplete();
            }
        });

    }

    @Override
    public MainStore createStore(WeakReference view) {
        return new MainStore(view);
    }


    @FluxAnnotation(action = MainAction.SELECT_ALL)
    public void updataView(List<TodoData> datas){
        Log.i(TAG, "MainActivity updataView" + datas.size());
        mWorkDatas.clear();
        mWorkDatas.addAll(datas);
        mAdapter.notifyDataSetChanged();
    }

    private void clearInput(){
        edt_input.getText().clear();
    }


    @Nullable
    @Override
    public Context getContext() {
        return this;
    }
}
