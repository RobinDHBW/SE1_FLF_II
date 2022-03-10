package task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Comp_loader {
    private Class mixingUnitClass;
    private Object mixingUnitInstance;
    private Object port;

    public Comp_loader() {
        checkComponent();
        loadComponent();
        loadPort();
    }

    private void checkComponent() {
        try {
            String javaHome = "C:\\Program Files\\Java\\jdk-17.0.2";
            if (System.getenv("JAVA_HOME") != null) {
                javaHome = System.getenv("JAVA_HOME");
            }
            ProcessBuilder processBuilder = new ProcessBuilder(javaHome + "\\bin\\jarsigner", "-verify", "task1\\jar\\task1.jar");
            Process process = processBuilder.start();
            process.waitFor();

            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            boolean isComponentAccepted = false;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                if (line.contains("verified")) {
                    isComponentAccepted = true;
                }
            }

            if (isComponentAccepted) {
                System.out.println("component accepted");
            } else {
                throw new RuntimeException("component rejected");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Maybe you need to define the JAVA_HOME environment variable!");
        }
    }

    @SuppressWarnings("unchecked")
    private void loadComponent() {
        try {
            URLClassLoader loader = new URLClassLoader(new URL[]{new File("task1\\jar\\task1.jar").toURI().toURL()});
            mixingUnitClass = Class.forName("mixingUnit.PipeDistribution", true, loader);
            mixingUnitInstance = mixingUnitClass.getMethod("getInstance").invoke(null);

        } catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void loadPort() {
        try {
            port = mixingUnitClass.getDeclaredField("port").get(mixingUnitInstance);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Object getPort(){
        return port;
    }
}
