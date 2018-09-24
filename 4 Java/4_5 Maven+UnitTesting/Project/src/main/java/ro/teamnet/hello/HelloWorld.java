package ro.teamnet.hello;

import java.util.logging.Logger;

public class HelloWorld {

    public void sayHello(){
        System.out.println("Hello World!");
//        logger.debug("DEBUG -> Enters in sayHello() method from HelloWorld");
        logger.info("INFO -> Enters in returnHelloKey from HelloWorld");

    }

    public String returnHelloKey(){
        return "HelloKey";
    }

    public static void main(String[] args){
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.sayHello();
    }
    static final Logger logger = Logger.getLogger(HelloWorld.class.getName());
}
