package loklam.reloadclassdemo;

import org.junit.runners.model.InitializationError;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.InvocationTargetException;
import java.net.URLClassLoader;

/**
 * This an customized TestRunner of JUnit to enforce separated ClassLoader for each JUnit test class.
 */
public class SeparateClassloaderTestRunner extends MockitoJUnitRunner {

    public SeparateClassloaderTestRunner(Class<?> clazz) throws InitializationError, InvocationTargetException {
        super(getFromTestClassloader(clazz));
    }

    private static Class<?> getFromTestClassloader(Class<?> clazz) throws InitializationError {
        try {
            ClassLoader testClassLoader = new TestClassLoader();
            return Class.forName(clazz.getName(), true, testClassLoader);
        } catch (ClassNotFoundException e) {
            throw new InitializationError(e);
        }
    }

    public static class TestClassLoader extends URLClassLoader {
        public TestClassLoader() {
            super(((URLClassLoader)getSystemClassLoader()).getURLs());
        }

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            if (name.startsWith("loklam.")) { //TODO update the correct package to fit your case.
                return super.findClass(name);
            }
            return super.loadClass(name);
        }
    }
}
