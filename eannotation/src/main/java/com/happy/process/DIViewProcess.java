package com.happy.process;

import com.google.auto.service.AutoService;
import com.happy.annotaion.DIView;
import com.happy.base.interfaces.IBindView;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * Created by Administrator on 2017/7/17 0017.
 */
@AutoService(Processor.class)
public class DIViewProcess extends AbstractProcessor{

    private Filer filer;
    private Elements elementUtils;
    private Map<TypeElement,LinkedList<FeildClass>> map = new HashMap<>();

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(DIView.class.getCanonicalName());
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        elementUtils = processingEnvironment.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(DIView.class);
        for(Element element:elements){
            DIView annotation = element.getAnnotation(DIView.class);
            if(annotation == null){
                continue;
            }
            TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
            if(enclosingElement.getKind().isClass()){
                LinkedList<FeildClass> feildClasses = map.get(enclosingElement);
                if(feildClasses == null){
                    feildClasses = new LinkedList<>();
                    map.put(enclosingElement,feildClasses);
                }
                FeildClass feildClass = new FeildClass();
                feildClass.feildId = annotation.value();
                feildClass.feildName = element.getSimpleName().toString();
                feildClass.typeName = TypeName.get(element.asType());
                feildClasses.add(feildClass);
            }
        }

        for(Map.Entry<TypeElement,LinkedList<FeildClass>> entry:map.entrySet()){
            TypeElement key = entry.getKey();
            LinkedList<FeildClass> value = entry.getValue();
            MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("bindView")
                    .addModifiers(Modifier.PUBLIC)//,Modifier.STATIC)
                    .addAnnotation(Override.class)
                    .addParameter(TypeName.get(key.asType()),"target");
            for(FeildClass feildClass:value){
//                TextView tx = (TextView)target.findViewById(id);
                methodBuilder.addStatement(String.format("target.%s = (%s)target.findViewById(%s)",feildClass.feildName,feildClass.typeName,feildClass.feildId));
//                methodBuilder.addStatement("target.$L = ($T)target.findViewById($L)",feildClass.feildName,feildClass.typeName,feildClass.feildId);
            }
            TypeSpec typeSpec = TypeSpec.classBuilder(key.getSimpleName()+"$$BindView")
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(methodBuilder.build())
                    .addTypeVariable(TypeVariableName.get("T",TypeName.get(key.asType())))
                    .addSuperinterface(ParameterizedTypeName.get(ClassName.get(IBindView.class), TypeName.get(key.asType())))
                    .build();
            String packageName = elementUtils.getPackageOf(key).getQualifiedName().toString();
            try {
                JavaFile.builder(packageName,typeSpec).build().writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_7;
    }

    private static class FeildClass{
        public TypeName typeName;
        public String feildName;
        public int feildId;
    }
}
