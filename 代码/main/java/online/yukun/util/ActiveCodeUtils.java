package online.yukun.util;

import java.util.UUID;

public class ActiveCodeUtils {
    public static String createActiveCode() {
        return UUID.randomUUID().toString();
    }
}
