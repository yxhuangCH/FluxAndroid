package com.yxhuang.fluxtodo;

import android.util.Log;

import com.yxhuang.flux.IFluxView;
import com.yxhuang.flux.base.BaseStore;
import com.yxhuang.fluxtodo.action.MainAction;
import com.yxhuang.fluxtodo.data.DataType;
import com.yxhuang.fluxtodo.data.TodoData;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yxhuang
 * Date: 2018/9/3
 * Description:
 */
public class MainStore extends BaseStore {

    private static final String TAG = "MainStore";

    private List<TodoData> mDatas = new ArrayList();
    private List<TodoData> mWorkDatas = new ArrayList<>();

    public MainStore(WeakReference<IFluxView> view) {
        super(view);
    }

    public void addMessage(String msg){
        Log.i(TAG, "addMessage msg= " + msg);
        TodoData todoData = new TodoData(msg);
        mDatas.add(todoData);
        mWorkDatas.add(todoData);
        updataView();
    }

    public void selectAll(){
        mWorkDatas.clear();
        mWorkDatas.addAll(mDatas);
        updataView();
    }

    public void selectActive(){
        mWorkDatas.clear();
        for (int i = 0; i < mDatas.size(); i++){
            TodoData data = mDatas.get(i);
            if (data.getType() == DataType.ACTIVE){
                mWorkDatas.add(data);
            }
        }
        updataView();
    }

    public void selectComplete(){
        mWorkDatas.clear();
        for (int i = 0; i < mDatas.size(); i++){
            TodoData data = mDatas.get(i);
            if (data.getType() == DataType.COMPLETE){
                mWorkDatas.add(data);
            }
        }
        updataView();
    }

    public void setSelect(TodoData data){
        if (data != null && mDatas.contains(data)){
            int indexOf = mDatas.indexOf(data);
            TodoData msg = mDatas.get(indexOf);
            if (msg != null){
                msg.setType(DataType.COMPLETE);
            }
        }
    }


    private void updataView(){
        emitStoreChange(MainAction.SELECT_ALL, mWorkDatas);
    }


}
