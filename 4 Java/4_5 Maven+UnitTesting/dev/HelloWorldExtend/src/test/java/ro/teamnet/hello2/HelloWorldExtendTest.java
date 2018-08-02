package ro.teamnet.hello2;

import org.testng.annotations.Test;

/**
 * Created by Petrus.Prangate on 7/4/2016.
 */
public class HelloWorldExtendTest {
    @Test
    public void test() throws Exception {
        new HelloWorldExtend().extendSayHello();
    }
}
