import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Crash_7865f06dbed6bd2c949045e5b60a3813458cf3b8 {
    static final String base64Bytes = String.join("", "yv66vgAAADcAGQEABER1ZmYHAAEBABBqYXZhL2xhbmcvT2JqZWN0BwADAQAJRHVmZi5qYXNtAQAEZGF0YQEAAltJAQAGPGluaXQ+AQADKClWDAAIAAkKAAQACgMAAABkDAAGAAcJAAIADQMAAAAAAwAAAAEHAAcBAAdkdWZmU3VtAQADKClJAwAAAAgBAARDb2RlAQANU3RhY2tNYXBUYWJsZQEAClNvdXJjZUZpbGUBABRTb3VyY2VEZWJ1Z0V4dGVuc2lvbgABAAIABAAAAAEAAAAGAAcAAAACAAEACAAJAAEAFQAAAEYABAACAAAAJCpZWbcACxIMvAq1AA4SDzy0AA5ZG1lPWb4bEhBgWTyj//RXsQAAAAEAFgAAABAAAf8AEwACBwACAQABBwARAAEAEgATAAEAFQAAANMABAADAAAAjCq0AA5ZEg9ZPD2+EhRwqgAAAAB8AAAAAAAAAAcAAAB2AAAAbQAAAGQAAABbAAAAUgAAAEkAAABAAAAAN1kcLhtgPIQCAVkcLhtgPIQCAVkcLhtgPIQCAVkcLhtgPIQCAVkcLhtgPIQCAVkcLhtgPIQCAVkcLhtgPIQCAVkcLhtgPIQCAVm+HKP/tRusAAAAAQAWAAAANQAK/wA8AAMHAAIBAQABBwARSAcAEUgHABFIBwARSAcAEUgHABFIBwARSAcAEUgHABFFBwARAAIAFwAAAAIABQAYAAAAAA==");

    public static void main(String[] args) throws Throwable {
        Crash_7865f06dbed6bd2c949045e5b60a3813458cf3b8.class.getClassLoader().setDefaultAssertionStatus(true);
        try {
            Method fuzzerInitialize = jdk.graal.compiler.test.bytecodefuzz.FuzzTarget.class.getMethod("fuzzerInitialize");
            fuzzerInitialize.invoke(null);
        } catch (NoSuchMethodException ignored) {
            try {
                Method fuzzerInitialize = jdk.graal.compiler.test.bytecodefuzz.FuzzTarget.class.getMethod("fuzzerInitialize", String[].class);
                fuzzerInitialize.invoke(null, (Object) args);
            } catch (NoSuchMethodException ignored1) {
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                System.exit(1);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.exit(1);
        }
        byte[] input = java.util.Base64.getDecoder().decode(base64Bytes);
        jdk.graal.compiler.test.bytecodefuzz.FuzzTarget.fuzzerTestOneInput(input);
    }
}