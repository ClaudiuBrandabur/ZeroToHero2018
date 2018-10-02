package ro.teamnet.zerotohero.reflection;


import ro.teamnet.zerotohero.reflection.demoobjects.*;
import java.lang.String;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.*;


import jdk.nashorn.internal.objects.NativeDebug;


/**
 * TODO
 * in order to resolve the exercises below, you will have to create
 * all the needed clasees, with their members and methods
 * (in ro.teamnet.zerotohero.reflection.demoobjects package)
 */
public class ClassReflectionDemoMain {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        //TODO get the class for a String object, and print it

		String string = new String();
        System.out.println("The name of the class is: " + string.getClass());

        //TODO get the class of an Enum, and print it

        System.out.println("The name of the class is is: " + Enum.x.getClass());

        //TODO get the class of a collection, and print it

        List list = new List();
        System.out.println("The name of class is: " + list.getClass());

        //TODO get the class of a primitive type, and print it

        System.out.println("Th name of the class is: " + int.class);

        //TODO get and print the class for a field of primitive type

        System.out.println(Field.class);

        //TODO get and print the class for a primitive type, using the wrapper class

        String string2 = new String();
        System.out.println(string.getClass());

        //TODO get the class for a specified class name

        SpecifiedClass specifiedClass = new SpecifiedClass();
        System.out.println(specifiedClass.getClass());

        //TODO get the superclass of a class, and print it

        Superclass superclass = new Superclass();
        System.out.println(superclass.getClass());

        //TODO get the superclass of the superclass above, and print it


        //TODO get and print the declared classes within some other class
        Class [] classes = DemoClass.class.getDeclaredClasses();
        int i = 0;
        for(i = 0; i < classes.length; i++){
            System.out.println(classes[i]);
        }

        //TODO print the number of constructors of a class
        System.out.println(DemoClass.class.getConstructors().length);

        //TODO get and invoke a public constructor of a class
        DemoClass demoClass = DemoClass.class.getConstructor().newInstance();
        System.out.println(demoClass.getClass());

        //TODO get and print the class of one private field
        System.out.println(demoClass.getDeclaredField("name"));

        //TODO set and print the value of one private field for an object
        Field f;
        f = DemoClass.class.getDeclaredField("name");
        System.out.println(f);

        //TODO get and print only the public fields class
        f = DemoClass.class.getField("name");
        System.out.println(f);

        //TODO get and invoke one public method of a class
        Method method1 = DemoClass.class.getMethod("method1");
        method1.invoke("obj", 20);

        //TODO get and invoke one inherited method of a class
       
		
		//TODO invoke a method of a class the classic way for ten times, and print the timestamp (System.currentTimeMillis())
		//TODO invoke a method of a class by Reflection for 100 times, and print the timestamp
		//what do you observe?
		
    }
    public enum Enum{
        x, y, z
    }


}
