package com.yxhuang.fluxandroid.data;

/**
 * Created by yxhuang
 * Date: 2018/8/29
 * Description:
 */
public class TodoData {

    private String mContent;
    private int mType;

    public TodoData(String content) {
        mContent = content;
        mType = DataType.ACTIVE;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }
}
