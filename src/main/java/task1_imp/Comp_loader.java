package task1_imp;

import configuration.Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Objects;

public class Comp_loader {

    private static Boolean checkComponent() {
        try {
//            String javaHome = "C:\\Program Files\\Java\\jdk-17.0.2";
//            if (System.getenv("JAVA_HOME") != null) {
//                javaHome = System.getenv("JAVA_HOME");
//            }
            ProcessBuilder processBuilder = new ProcessBuilder(Configuration.instance.pathToJarsigner, "-verify", Configuration.instance.pathToJavaArchive);
            Process process = processBuilder.start();
            process.waitFor();

            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            //boolean isComponentAccepted = false;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                if (line.contains("verified")) {
                    return true;
                }
            }
            return false;
//            if (isComponentAccepted) {
//                System.out.println("component accepted");
//                return true;
//            } else {
//                throw new Exception("component rejected");
//            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            //throw new RuntimeException("Maybe you need to define the JAVA_HOME environment variable!");
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public static Object loadComponent() {
        Object port = null;
        try {
            if(!checkComponent()) throw new Exception("JAR not verified - aborting!");
            URL[] urls=new URL[]{new File(Configuration.instance.pathToJavaArchive).toURI().toURL()};
            URLClassLoader loader = new URLClassLoader(urls, Comp_loader.class.getClassLoader());
            Class mixingUnitClass = Class.forName("MixingProcessor", true, loader);
            Object mixingUnitInstance = mixingUnitClass.getMethod("getInstance").invoke(null);
            port = mixingUnitClass.getDeclaredField("port").get(mixingUnitInstance);

            return port;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

//    public Object getPort(){
//        return port;
//    }
}
