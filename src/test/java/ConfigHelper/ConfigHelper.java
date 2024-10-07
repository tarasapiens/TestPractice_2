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

        return properties.getProperty("baseurl");
    }

    public static String getXPathJSDelays() {

        return properties.getProperty("xpathJSDelay");
    }

    public static String getXpathFormField(){

        return properties.getProperty("xpathFormField");
    }

    public static String getXpathPopup(){
        return properties.getProperty("xpathPopup");
    }

    public static String getXpathSlider(){
        return properties.getProperty("xpathSlider");
    }

    public static String getXpathCalendars(){ return properties.getProperty("xpathCalendars"); }

    public static String getXpathModals(){return properties.getProperty("xpathModals");}
    public static String getXpathTables(){return properties.getProperty("xpathTables");}
    public static String getXpathWindow(){return properties.getProperty("xpathWindow");}


}
