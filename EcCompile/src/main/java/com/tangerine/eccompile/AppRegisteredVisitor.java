package com.tangerine.eccompile;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

public final class AppRegisteredVisitor extends SimpleAnnotationValueVisitor7<Void,Void> {
    private Filer mFiler = null;
    private TypeMirror mTypeMirror = null;
    private String mPackageName = null;
    public void setmFiler(Filer filer){
        this.mFiler =filer;
    }

    @Override
    public Void visitString(String s, Void aVoid) {
        mPackageName = s;
        return aVoid;
    }

    @Override
    public Void visitType(TypeMirror typeMirror, Void aVoid) {
        mTypeMirror = typeMirror;
        generateJavaCode();
        return aVoid;
    }
    private void generateJavaCode(){
        final TypeSpec targetActivity =
                TypeSpec.classBuilder("AppRegister")
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.FINAL)
                .superclass(TypeName.get(mTypeMirror))
                .build();
        final JavaFile javaFile =JavaFile.builder(mPackageName + ".wxapi",targetActivity)
                .addFileComment("WeChatBroadCast")
                .build();
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
