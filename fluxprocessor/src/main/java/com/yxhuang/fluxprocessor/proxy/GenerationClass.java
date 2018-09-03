package com.yxhuang.fluxprocessor.proxy;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;


public class GenerationClass {

    public static final String SUFFIX = "_DoStoreChange";
    public static final ClassName FLUX_VIEW = ClassName.get("com.yxhuang.flux", "IFluxView");

    private String mPackageName;
    private String mTargetClassName;
    public String mStoreChangeClassName;

    private ClassName mClassName;

    public GenerationClass(String packageName, String targetClassName) {
        this.mPackageName = packageName;
        this.mTargetClassName = targetClassName;
        this.mStoreChangeClassName = targetClassName + SUFFIX;
        mClassName = ClassName.get(packageName, targetClassName);
    }

    public TypeSpec.Builder createClass() {
        TypeSpec.Builder builder = TypeSpec.classBuilder(mStoreChangeClassName)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(FLUX_VIEW);

        FieldSpec mTarget = FieldSpec.builder(mClassName, "mTarget", Modifier.PRIVATE).build();
        builder.addField(mTarget);

        MethodSpec constructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(mClassName, "target")
                .addStatement("this.$N = $N", "mTarget", "target")
                .build();

        builder.addMethod(constructor);

        return builder;
    }

}
