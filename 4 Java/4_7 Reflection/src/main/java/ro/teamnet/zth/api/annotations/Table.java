package ro.teamnet.zth.api.annotations;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

    @Target(TYPE)
    @Retention(RUNTIME)
    public @interface Table {

        String name() default "";
    }


