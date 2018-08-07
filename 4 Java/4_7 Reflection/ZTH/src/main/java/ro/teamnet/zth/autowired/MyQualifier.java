package ro.teamnet.zth.autowired;

import java.lang.annotation.Target;

//@Target()
public @interface MyQualifier {

    public int value() default 0;
}
