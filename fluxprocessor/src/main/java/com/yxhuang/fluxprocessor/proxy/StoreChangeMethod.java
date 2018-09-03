package com.yxhuang.fluxprocessor.proxy;

import javax.lang.model.element.ExecutableElement;


public class StoreChangeMethod {

    private ExecutableElement mElement;

    private int mAction;

    public ExecutableElement getElement() {
        return mElement;
    }

    public void setElement(ExecutableElement element) {
        mElement = element;
    }

    public int getAction() {
        return mAction;
    }

    public void setAction(int action) {
        mAction = action;
    }
}
