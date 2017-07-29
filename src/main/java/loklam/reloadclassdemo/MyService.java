package loklam.reloadclassdemo;

import java.io.FileWriter;
import java.io.IOException;

/**
 * An example of service class to demonstrate JUnit tests of singleton.
 *
 */
public class MyService
{


    private static MyService instance = new MyService();

    private String someConfig;

    // For singleton, construct should be private.
    private MyService() { }

    /**
     * get an instance of MyService.
     */
    static public MyService getInstance() {
        return instance;
    }

    /**
     * Init the service which a config string.
     * @param config
     */
    public void init(String config) {
        if (someConfig == null)
            someConfig = config;
        else
            throw new IllegalStateException("MyService has already been initialized.");
    }

    /**
     * ask MyService to do something. In this example, it writes the value of someConfig
     * via FileWriter parameter.
     * @param w
     * @throws IOException
     */
    public void doSomeService(FileWriter w) throws IOException {
        w.write(someConfig);
    }
}
