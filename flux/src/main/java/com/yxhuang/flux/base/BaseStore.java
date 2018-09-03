package com.yxhuang.flux.base;

import android.os.Looper;
import android.util.Log;

import com.yxhuang.flux.IFluxView;

import java.lang.ref.WeakReference;

/**
 * Created by yxhuang
 * Date: 2018/9/2
 * Description:
 */
public abstract class BaseStore {

    private static final String TAG = "BaseStore";

    protected WeakReference<IFluxView> mView;


    public BaseStore(WeakReference<IFluxView> view) {
        mView = view;
    }


    public void onCreate() {
//        CoreUtils.register(this);
//        if (mStoreDelegates != null) {
//            for (ILifeCycle store : mStoreDelegates) {
//                store.onCreate();
//            }
//        }
    }

    protected void emitStoreChange(int action, Object... args) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            Log.e(TAG,"emitStoreChange must be call in MainThread");
            return;
        }
        try {
            if (mView.get() != null) {
                mView.get().onStoreChanged(action, args);
            }
        } catch (Exception e) {
            Log.e(TAG, "emitStoreChange exception " +  e.getMessage());
        }
    }
}
