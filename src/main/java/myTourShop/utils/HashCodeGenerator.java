package myTourShop.utils;

/**
 * Created by imac on 02.12.16.
 */
public class HashCodeGenerator {

    private static final String salt = "asXYLJuaa68GQRrESZtnzskh9SkTU2toH6p2NoKDkFQkoHjTfDvRjDedJ7wAyDHJ";

    public static String getHashCode(String string) {
        return (string.hashCode() + salt).hashCode() + "";
    }
}
