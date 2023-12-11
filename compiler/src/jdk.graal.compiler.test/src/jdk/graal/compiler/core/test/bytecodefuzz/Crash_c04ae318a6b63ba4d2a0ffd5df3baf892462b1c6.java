import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Crash_c04ae318a6b63ba4d2a0ffd5df3baf892462b1c6 {
    static final String base64Bytes = String.join("", "yv66vgAAAEEAHAoAAgADBwAEDAAFAAYBABBqYXZhL2xhbmcvT2JqZWN0AQAGPGluaXQ+AQADKClWCQAIAAkHAAoMAAsADAEAEGphdmEvbGFuZy9TeXN0ZW0BAANvdXQBABVMamF2YS9pby9QcmludFN0cmVhbTsIAA4BAAxIZWxsbyBXb3JsZCEKABAAEQcAEgwAEwAUAQATamF2YS9pby9QcmludFN0cmVhbQEAB3ByaW50bG4BABUoTGphdmEvbGFuZy9TdHJpbmU7KVYHABYBAAVIZWxsbwEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAAVoZWxsbwEAClNvdXJjZUJpbGUBAApIZWxsby5qYXZhACEAFQACAAAAAAACAAEABQAGAAEAFwAAAB0AAQABAAAABSq3CAGxAAAAAQAYAAAABgABAAAAAQABABkABgABABcAAAAlAAIAAQAAAAmyAAcSDbYAD7EAAAABABgAAAAKAAIAAAADAAgABAABABoAAAACABs=");

    public static void main(String[] args) throws Throwable {
        Crash_c04ae318a6b63ba4d2a0ffd5df3baf892462b1c6.class.getClassLoader().setDefaultAssertionStatus(true);
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