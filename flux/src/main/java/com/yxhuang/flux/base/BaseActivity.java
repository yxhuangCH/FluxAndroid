package com.yxhuang.flux.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yxhuang.flux.FluxBinder;
import com.yxhuang.flux.IFluxView;

import java.lang.ref.WeakReference;

/**
 * Created by yxhuang
 * Date: 2018/9/2
 * Description:
 */
public abstract class BaseActivity<T extends BaseStore> extends Activity implements IFluxView{

    protected T mStore;
    protected IFluxView mFluxViewBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initStore();
        if (mStore != null) {
            mFluxViewBinder = FluxBinder.bind(this);
            mStore.onCreate();
        }
    }

    private void initStore() {
        mStore = createStore(new WeakReference<IFluxView>(this));
    }

    public abstract T createStore(WeakReference<IFluxView> view);


    @Override
    public boolean onStoreChanged(int action, Object... data) {
        if (mFluxViewBinder != null) {
            return mFluxViewBinder.onStoreChanged(action, data);
        }
        return false;
    }

//    protected void dispatchAction(BaseAction action) {
//        CoreUtils.send(action);
//    }



}
