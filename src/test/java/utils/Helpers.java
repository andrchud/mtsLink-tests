package utils;

public class Helpers {
    public static String getEventIdFromUrl(String url) {
        return url.split("/")[5];
    }
}