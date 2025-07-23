package utils;
import utils.ApiConfigReader;

public class ApiHelper {
    public static String getBaseApiUrl() {
        return ApiConfigReader.getProperty("api.base.url.test");
    }

    public static String getApiAuthKey() {
        return ApiConfigReader.getProperty("api.auth.key");
    }
}
