import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Crash_00f521f45437ed3f4c4bedf886a4e6d8c07357cf {
    static final String base64Bytes = String.join("", "yv66vgAAAEEAMwEAPWpkay9ncmFhbC9jb21waWxlci90ZXN0L2J5dGVjb2RlZnV6ei9nZW4vY29ycHVzL0NhdGNoX05QRV8wMTAHAAEBABBqYXZhL2xhbmcvT2JqZWN0BwADAQASQ2F0Y2hfTlBFXzAxMC5qYXZhAQABYQEAAUkBAAY8aW5pdD4BAAMoKVYMAAgACQoABAAKAQAEdGhpcwEAP0xqZGsvZ3JhYWwvY29tcGlsZXIvdGVzdC9ieXRlY29kZWZ1enovZ2VuL2NvcnB1cy9DYXRjaF9OUEVfMDEwOwEABHRlc3QBAAMoKUkBAB5qYXZhL2xhbmcvTnVsbFBvaW50ZXJFeGNlcHRpb24HABADAAAAaAMiLXcIAyItdqQMAAYABwkAAgAVAQAxamRrL2dyYWFsL2NvbXBpbGVyL2FwaS9kaXJlY3RpdmVzL0dyYWFsRGlyZWN0aXZlcwcAFwEACWJsYWNraG9sZQEABChJKVYMABkAGgoAGAAbCgARAAoBADBqZGsvZ3JhYWwvY29tcGlsZXIvdGVzdC9ieXRlY29kZWZ1enovRmllbGRIb2xkZXIHAB4KAB8ACgEAAmZoAQAyTGpkay9ncmFhbC9jb21waWxlci90ZXN0L2J5dGVjb2RlZnV6ei9GaWVsZEhvbGRlcjsMACEAIgkAHwAjAQACaWEBAAJbSQwAJQAmCQAfACcBAANucGUBACBMamF2YS9sYW5nL051bGxQb2ludGVyRXhjZXB0aW9uOwEAAltKBwArAQAIPGNsaW5pdD4BAARDb2RlAQAPTGluZU51bWJlclRhYmxlAQASTG9jYWxWYXJpYWJsZVRhYmxlAQANU3RhY2tNYXBUYWJsZQEAClNvdXJjZUZpbGUAIQACAAQAAAABAAkABgAHAAAAAwABAAgACQABAC4AAAAvAAEAAQAAAAUqtwALsQAAAAIALwAAAAYAAQAAAAQAMAAAAAwAAQAAAAUADAANAAAACQAOAA8AAQAuAAAAzgADAAQAAABdEhI7EhMSFF+EAP8anAADZFmyABZ8VzyyABZZuAAcsgAWEFy8C008mwAUuwARWbcAHYQB/xtZPJwAB7+nACCyABZXTrIAFle7AB9ZtwAgtAAktAAktAAoEAAurAKsAAEAFwA7AD4AEQADADEAAAAlAAT/AA8AAQEAAgEB/QArAQcALP8AAgACAQEAAQcAEfwAHAcALAAvAAAAGgAGABcACQAqAAoAOwAOAD4ADABDAA0AWwAPADAAAAAMAAEAQwAYACkAKgADAAgALQAJAAEALgAAAB0AAQAAAAAABQWzABaxAAAAAQAvAAAABgABAAAABQABADIAAAACAAU=");

    public static void main(String[] args) throws Throwable {
        Crash_00f521f45437ed3f4c4bedf886a4e6d8c07357cf.class.getClassLoader().setDefaultAssertionStatus(true);
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