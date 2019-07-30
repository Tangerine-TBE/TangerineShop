package com.tangerine.eccompile;

import com.google.auto.service.AutoService;
import com.tangerine.ecannotations.AppRegisterGenerator;
import com.tangerine.ecannotations.EntryGenerator;
import com.tangerine.ecannotations.PayEntryGenerator;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;


@AutoService(Processor.class)
public class PublicProcessor extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportAnnotations = getSupportedAnnotations();
        for (Class<? extends Annotation> annotation : supportAnnotations) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }
    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        final Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(EntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        annotations.add(PayEntryGenerator.class);
        return annotations;
    }

    private void scan(RoundEnvironment env, Class<? extends Annotation> annotation
            , AnnotationValueVisitor visitor) {
        for (Element typeElement : env.getElementsAnnotatedWith(annotation)) {
            final List<? extends AnnotationMirror> annotationMirrors =
                    typeElement.getAnnotationMirrors();

            for (AnnotationMirror annotationMirror : annotationMirrors) {

                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValves
                        = annotationMirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry
                        : elementValves.entrySet()) {
                    entry.getValue().accept(visitor, null);
                }
            }
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        generateEntryCode(roundEnvironment);
        generateAppRegisterCode(roundEnvironment);
        generatePayEntryCode(roundEnvironment);
        return true;
    }

    private void generatePayEntryCode(RoundEnvironment env) {
        final PayEntryVisitor payEntryVisitor = new PayEntryVisitor();
        payEntryVisitor.setmFiler(processingEnv.getFiler());
        scan(env, PayEntryGenerator.class, payEntryVisitor);
    }

    private void generateAppRegisterCode(RoundEnvironment env) {
        final AppRegisteredVisitor appRegisteredVisitor = new AppRegisteredVisitor();
        appRegisteredVisitor.setmFiler(processingEnv.getFiler());
        scan(env, AppRegisterGenerator.class, appRegisteredVisitor);
    }

    private void generateEntryCode(RoundEnvironment env) {
        final EntryVisitor entryVisitor = new EntryVisitor();
        entryVisitor.setFiler(processingEnv.getFiler());
        scan(env, EntryGenerator.class, entryVisitor);
    }
}
