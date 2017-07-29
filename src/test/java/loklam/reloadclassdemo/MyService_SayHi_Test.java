package loklam.reloadclassdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.FileWriter;

import static org.mockito.Mockito.verify;

//This test will fail if there is another test has "init" the service.
//To fix this issue, we need to use SeparateClassloaderTestRunner, instead of MockitoJUnitRunner.

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SeparateClassloaderTestRunner.class)
public class MyService_SayHi_Test {

    @Mock FileWriter fw;

    @Test
    public void doSomeService_SayHello() throws Exception {
        MyService service = MyService.getInstance();
        service.init("Hi!");
        service.doSomeService(fw);
        verify(fw).write("Hi!");
    }

}