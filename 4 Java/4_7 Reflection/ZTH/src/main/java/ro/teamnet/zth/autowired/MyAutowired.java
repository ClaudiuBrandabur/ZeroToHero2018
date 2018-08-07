package ro.teamnet.zth.autowired;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface MyAutowired {


    public int value() default 0;
}
