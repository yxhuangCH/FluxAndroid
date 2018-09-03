package com.yxhuang.flux;

import android.content.Context;
import android.support.annotation.Nullable;

public interface IFluxView {

    boolean onStoreChanged(int action, Object... args);

    @Nullable
    Context getContext();
}

