import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Crash_09a8ae19d170ab2e6385acb8df45d3e945e55203 {
    static final String base64Bytes = String.join("", "yv66vgAAAEEAHAMCAAoABwAEDAAFAAYBABBqYXZhL2xhbmcvT2JqZWN0AQAGPGluaXQ+AQADKClWCQAIAAkHAAoMAAsADAEAEGphdmEvbGFuZy9TeXN0ZW0BAANvdXQBABVMamF2YS9pby9QcmludFN0cmVhbTsIAA4BAAxIZWxsbyBXb3JsZF0KABAAEQcAEgwAEwAUAQATamF2YS9pby9QcmludFN0cmVhbQEAB3ByaW50bG4BABUoTGphdmEvbGFuZy9TdHJpbmc7KVYHABYBAAVIZWxsbwEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAAVoZWxsbwEAClNvdXJjZUZpbGUBAApIZWxsby5qYXZhACEAFQACAAAAAAACAAEABQAGAAEAFwAAAB0AAQABAAAABSq3AAGxAAAAAQAYAAAABgABAAAAAQABABkABgABABcAAAAlAAIAAQAAAAmyAAcSDbYAD7EAAAABABgAAAAKAAIAAAADAAgABAABABoAAAACABs=");

    public static void main(String[] args) throws Throwable {
        Crash_09a8ae19d170ab2e6385acb8df45d3e945e55203.class.getClassLoader().setDefaultAssertionStatus(true);
        try {
            Method fuzzerInitialize = jdk.graal.compiler.core.test.bytecodefuzz.FuzzSandbox.class.getMethod("fuzzerInitialize");
            fuzzerInitialize.invoke(null);
        } catch (NoSuchMethodException ignored) {
            try {
                Method fuzzerInitialize = jdk.graal.compiler.core.test.bytecodefuzz.FuzzSandbox.class.getMethod("fuzzerInitialize", String[].class);
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
        jdk.graal.compiler.core.test.bytecodefuzz.FuzzSandbox.fuzzerTestOneInput(input);
    }
}