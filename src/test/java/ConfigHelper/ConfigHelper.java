package ConfigHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigHelper {
    private static final String CONFIG_FILE = "src/main/resources/testdata.properties";

    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream(CONFIG_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getBaseURL() {
        String BaseURL = properties.getProperty("baseurl");

        return BaseURL;
    }

    public static String getXPathJSDelays() {
        String XPathJSDelays = properties.getProperty("xpathJSDelay");

        return XPathJSDelays;
    }

    public static String getXpathFormField(){
        String XpathFormField = properties.getProperty("xpathFormField");

        return XpathFormField;
    }

    public static String getXpathPopup(){
        String XpathPopup = properties.getProperty("xpathPopup");
        return XpathPopup;
    }
}
