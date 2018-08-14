package ro.teamnet.zerotohero.reflection;


import ro.teamnet.zerotohero.reflection.demoobjects.MyClass;
import ro.teamnet.zerotohero.reflection.demoobjects.SpecifiedClass;
import ro.teamnet.zerotohero.reflection.demoobjects.SubClass;
import ro.teamnet.zerotohero.reflection.demoobjects.SuperClass;

import javax.lang.model.type.PrimitiveType;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.Format;
import java.util.Collection;

/**
 * TODO
 * in order to resolve the exercises below, you will have to create
 * all the needed clasees, with their members and methods
 * (in ro.teamnet.zerotohero.reflection.demoobjects package)
 */
public class ClassReflectionDemoMain {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //TODO get the class for a String object, and print it
		String string = new String();
		System.out.println(string.getClass());

        //TODO get the class of an Enum, and print it
        System.out.println(Enm.B.getClass());

        //TODO get the class of a collection, and print it
        List list = new List();
        System.out.println(list.getClass());

        //TODO get the class of a primitive type, and print it
        System.out.println(int.class);

        //TODO get and print the class for a field of primitive type
        System.out.println(Field.class);
        //TODO get and print the class for a primitive type, using the wrapper class
        Integer integer = new Integer(56);
        System.out.println(integer.getClass());

        //TODO get the class for a specified class name
        SpecifiedClass specifiedClass = new SpecifiedClass();
        System.out.println(specifiedClass.getClass());

        //TODO get the superclass of a class, and print it
        //TODO get the superclass of the superclass above, and print it
        SuperClass sup = new SuperClass();
        SubClass sub = new SubClass();
        Class cls;
        cls = sub.getClass();
        cls = cls.getSuperclass();
        System.out.println(cls.getName());

        //TODO get and print the declared classes within some other class
        Class [] classes = MyClass.class.getDeclaredClasses();
        int i = 0;
        for(i = 0; i < classes.length; i++){
            System.out.println(classes[i]);
        }
        //TODO print the number of constructors of a class

        System.out.println(MyClass.class.getConstructors().length);

        //TODO get and invoke a public constructor of a class
        MyClass myC = MyClass.class.getConstructor().newInstance();
        System.out.println(myC.getClass());

        //TODO get and print the class of one private field 
        Field field = MyClass.class.getDeclaredField("field2");
        System.out.println(field.getClass());

		
		//TODO set and print the value of one private field for an object
//        Field f;
//        f = MyClass.class.getDeclaredField("field2");
//        System.out.println(f);

        //TODO get and print only the public fields class
        

        //TODO get and invoke one public method of a class
        

        //TODO get and invoke one inherited method of a class
       
		
		//TODO invoke a method of a class the classic way for ten times, and print the timestamp (System.currentTimeMillis())
		//TODO invoke a method of a class by Reflection for 100 times, and print the timestamp
		//what do you observe?

        }
        public enum Enm{
        A, B, C
    }
}
