package utils;

public class Helpers {
    public static String getEventIdFromUrl(String url) {
        return url.split("/")[5];
    }

    public static long getCourseIdFromUrl(String url) {
        String idAsString = url.split("/")[4];
        return Long.parseLong(idAsString);
    }
}