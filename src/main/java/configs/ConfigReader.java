package configs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties prop = new Properties();

    private static String getProperty(String name)
    {
        try(InputStream input = new FileInputStream("src/main/resources/config.properties"))
        {
            prop.load(input);
            return prop.getProperty(name);
        } catch (IOException e) {
            //e.printStackTrace();
            return null;
        }
    }

    public static void  readConfiguration() {

        Configuration.url = getProperty("url");
        Configuration.userName = getProperty("username");
        Configuration.password = getProperty("password");
    }
}
