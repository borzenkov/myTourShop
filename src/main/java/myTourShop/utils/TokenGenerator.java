package myTourShop.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by imac on 02.12.16.
 */
public class TokenGenerator {
    private static SecureRandom random = new SecureRandom();

    public static String nextToken() {
        return new BigInteger(130, random).toString(32);
    }
}
