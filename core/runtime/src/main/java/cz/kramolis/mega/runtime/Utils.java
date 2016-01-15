package cz.kramolis.mega.runtime;

import java.lang.annotation.Annotation;

public final class Utils {

    private Utils() {
    }

    /**
     * This is proxy ready implementation {@link Class#getAnnotation(Class)}.
     */
    public static <A extends Annotation> A getAnnotation(Class<?> clazz, Class<A> annotationClass) {
        final A annotation = clazz.getAnnotation(annotationClass);

        if (annotation == null
                && clazz.isSynthetic()) { //OK, this is probably proxy
            return getAnnotation(clazz.getSuperclass(), annotationClass);
        } else {
            return annotation;
        }
    }

}
