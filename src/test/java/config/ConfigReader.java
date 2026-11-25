package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties props = new Properties();

    static {
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(in);
        } catch (Exception e) {
            throw new RuntimeException("config.properties оқылмады", e);
        }
    }

    /** Алдымен -D жүйелік параметр, содан соң файл мәні. */
    public static String get(String key) {
        return System.getProperty(key, props.getProperty(key));
    }

    public static String baseUrl()     { return get("baseUrl"); }
    public static String browser()     { return get("browser"); }
    public static boolean headless()   { return Boolean.parseBoolean(get("headless")); }
    public static int timeout()        { return Integer.parseInt(get("timeout")); }
}
