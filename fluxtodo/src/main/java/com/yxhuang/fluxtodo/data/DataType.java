package com.yxhuang.fluxtodo.data;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by yxhuang
 * Date: 2018/8/29
 * Description:
 */
public class DataType {

    @IntDef({ALL, ACTIVE, COMPLETE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type{}

    // 全部
    public static final int ALL = 0;

    // 未完成
    public static final int ACTIVE = 1;

    // 完全
    public static final int COMPLETE = 2;


}
