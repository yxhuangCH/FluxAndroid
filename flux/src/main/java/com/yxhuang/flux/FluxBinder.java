package com.yxhuang.flux;

import java.lang.reflect.Constructor;


public class FluxBinder {

    private static final String TAG = "FluxBinder";

    public static IFluxView bind(IFluxView view) {
        String clsName = view.getClass().getName() + "_DoStoreChange";
        try {
            Class clazz = Class.forName(clsName);
            Constructor<? extends IFluxView> bindingCtor = (Constructor<? extends IFluxView>) clazz.getConstructor(view.getClass());
            return bindingCtor.newInstance(view);
        } catch (Exception e) {
            return null;
        }
    }


}
