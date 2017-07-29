package loklam.reloadclassdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.FileWriter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

//This test will fail if there is another test has "init" the service.
//To fix this issue, we need to use SeparateClassloaderTestRunner, instead of MockitoJUnitRunner.

@RunWith(SeparateClassloaderTestRunner.class)
public class MyService_SayHello_Test {

    @Mock FileWriter fw;

    @Test
    public void doSomeService_SayHello() throws Exception {
        MyService service = MyService.getInstance();
        service.init("Hello!");
        service.doSomeService(fw);
        verify(fw).write("Hello!");
    }

}