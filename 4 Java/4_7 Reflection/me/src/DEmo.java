import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

//import static com.sun.corba.se.impl.io.IIOPInputStream.getDeclaredField;

public class DEmo {

    private int number = 8;
    public int ok = 10;


    public static void main(String []args) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        //TODO get the class for a String object, and print it
        System.out.println("hello".getClass());
        System.out.println("hello".getClass().getCanonicalName());
        System.out.println("hello".getClass().getSimpleName());


        //TODO get the class of an Enum, and print it
        System.out.println(MyEnum.CARAMEL.getClass());


        //TODO get the class of a collection, and print it
        ArrayList list = new ArrayList();
        System.out.println(ArrayList.class);
        System.out.println(list.getClass().getCanonicalName());

        //TODO get the class of a primitive type, and print it
        System.out.println(int.class.getCanonicalName());

        //TODO get and print the class for a field of primitive type
        System.out.println(Field.class.getCanonicalName());

        Field privateField = DEmo.class.getDeclaredField("number");
        privateField.setAccessible(true);
        System.out.println(privateField.getType());



        //TODO get and print the class for a primitive type, using the wrapper class
        System.out.println(Integer.class);

        //TODO get the class for a specified class name
        System.out.println(Animal.class);

        //TODO get the superclass of a class, and print it
        Animal animal = new Animal();
        Dog dog = new Dog();
        System.out.println(dog.getClass().getSuperclass().getCanonicalName());

        //TODO get the superclass of the superclass above, and print it
        System.out.println(dog.getClass().getSuperclass().getSuperclass().getCanonicalName());

        //TODO get and print the declared classes within some other class
        System.out.println("-----");
        Class [] classes = Dog.class.getDeclaredClasses();
        for(int i = 0; i < classes.length; i++){
            System.out.println("-->" + classes[i].getSimpleName());
        }

        //TODO print the number of constructors of a class
        System.out.println(Being.class.getConstructors().length);


        //TODO get and invoke a public constructor of a class
        Being be = Being.class.getConstructor().newInstance();
        System.out.println(be.getClass().getSimpleName());

        //TODO get and print the class of one private field
        Being myBeing = new Being("bat");
        Field privateF = Being.class.getDeclaredField("age");
        System.out.println(privateF.getClass());

        //TODO set and print the value of one private field for an object
        privateF.setAccessible(true);
        int fieldVal = (int) privateF.get(myBeing);
        System.out.println(fieldVal);
        privateF.set(myBeing,7);
        int newField = (int) privateF.get(myBeing);
        System.out.println(newField);

        //TODO get and print only the public fields class
        Field[] fields = DEmo.class.getFields();
        for(int i = 0; i < fields.length; i++)
            System.out.println("here: " + fields[i]);

        //TODO get and invoke one public method of a class
        System.out.println(Being.class.getMethods()[0]);

        //TODO get and invoke one inherited method of a class
        Animal animal1 = new Animal();
        animal1.getClass().getMethod("print",null).invoke(animal1,null);

        //TODO invoke a method of a class the classic way for ten times, and print the timestamp (System.currentTimeMillis())


        System.out.println(System.currentTimeMillis());//1533720382316

        Being b = new Being();
        for(int i = 0; i < 10; i++ ){
            b.print();
        }

        System.out.println(System.currentTimeMillis()); //1533720382317

        //TODO invoke a method of a class by Reflection for 100 times, and print the timestamp
        //what do you observe?
        for(int i = 0; i < 100; i++){
            Being.class.getMethod("print",null).invoke(b,null);
        }

        System.out.println(System.currentTimeMillis()); //1533720382330
    }
}
