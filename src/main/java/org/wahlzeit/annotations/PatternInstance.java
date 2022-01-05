package org.wahlzeit.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

// Annotation for design patterns

@Target(ElementType.TYPE) // only apply annotation to classes.
public @interface PatternInstance {
    String patternName();
    String[] participants();
}
