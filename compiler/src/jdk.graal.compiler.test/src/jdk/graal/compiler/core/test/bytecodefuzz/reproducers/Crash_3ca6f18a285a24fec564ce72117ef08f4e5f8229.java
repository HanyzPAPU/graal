import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Crash_3ca6f18a285a24fec564ce72117ef08f4e5f8229 {
    static final String base64Bytes = String.join("", "yv66vgAAAEEAHAoAAgADBwAEDAAFAAYBABBqYXZhL2xhbmcvT2JqZWN0AQAGPGluaXQ+AQADKClWCQAIAAkHAAoMAAsADAEAEGphdmEvbGFuZy9TeXN0ZW0BAANvdXQBABVMamF2YS9pby9QcmludFN0cmVhbTsIAA4BAAxIZWxsbyBXb3JsZCEKABAAEQcAEgwAEwAUAQATamF2YS9pby9QcmludFN0cmVhbQEAB3ByaTB0bG4BABUoTGphdmEvbGFuZy9TdHJpbmc7KVYHABYBAAVIZWxsbwEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAAVoZWxsbwEACihbQltCKUZpbGUBAApIY2xsby5qYXZhACEAFQACAAAAAAACAAEABQAGAAEAFwAAAB0AAQABAAAABSq3AIexAAAAAQAYAAAABgABAAAAAQABABkABgABABcAAAAlAAIAAQAAAAmyAAcSDbYAD7EAAAABABgAAAAKAAIAAAADAAgABAABABoAAAACABs=");

    public static void main(String[] args) throws Throwable {
        Crash_3ca6f18a285a24fec564ce72117ef08f4e5f8229.class.getClassLoader().setDefaultAssertionStatus(true);
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