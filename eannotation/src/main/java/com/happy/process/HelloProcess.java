package com.happy.process;

import com.google.auto.service.AutoService;
import com.happy.annotaion.DIView;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

/**
 * Created by Administrator on 2017/7/17 0017.
 */

@AutoService(Processor.class)
public class HelloProcess extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(DIView.class.getCanonicalName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        MethodSpec methodSpec = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC,Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class,"args",Modifier.FINAL)
//                .addStatement( String.format("%s.out.println(%s)",System.class,"Hello ,javapoet"))
//                .build();
        .addStatement("$T.out.println($S)",System.class,"Hello,JavaPoet").build();
//                .addStatement(String.format("%s.out.println(%s)",System.class,"Hello ,javapoet")).build();
        TypeSpec typeSpec = TypeSpec.classBuilder("HelloWord")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(methodSpec)
                .build();

        try {
            JavaFile.builder("com.happy",typeSpec).build().writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_7;
    }
}
