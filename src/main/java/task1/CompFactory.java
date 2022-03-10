package task1;

import configuration.Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;

public class CompFactory {

    private static Boolean checkComponent() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(Configuration.instance.pathToJarsigner, "-verify", Configuration.instance.pathToJavaArchive);
            Process process = processBuilder.start();
            process.waitFor();

            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("verified")) {
                    return true;
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public static Object build() {
        Object compPort = null;

        try {
            if(!checkComponent()) throw  new Exception("JAR not verified - aborting!");
            URL[] urls = {new File(Configuration.instance.pathToJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, CompFactory.class.getClassLoader());
            Class clazz = Class.forName("MixingProcessor", true, urlClassLoader);
            Object mixingProcessorInstance = clazz.getMethod("getInstance").invoke(null);
            compPort = clazz.getDeclaredField("port").get(mixingProcessorInstance);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }

        return compPort;
    }
}
