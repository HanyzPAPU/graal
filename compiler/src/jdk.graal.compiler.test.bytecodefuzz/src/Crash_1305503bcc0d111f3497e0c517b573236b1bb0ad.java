import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Crash_1305503bcc0d111f3497e0c517b573236b1bb0ad {
    static final String base64Bytes = String.join("", "yv66vgAAADcAJQEABER1ZmYHAAEBABBqYXZhL2xhbmcvT2JqZWN0BwADAQAJRHVmZi5qYXNtAQAEZGF0YQEAAltJAQAGPGluaXQ+AQADKClWDAAIAAkKAAQACgwABgAHCQACAAwHAAcBAAdkdWZmU3VtAQADKClJAwAAAGQDE0gBCwPOIKDlA14SD38DgsxtvQPCRx8tAwAAAAgBADFqZGsvZ3JhYWwvY29tcGlsZXIvYXBpL2RpcmVjdGl2ZXMvR3JhYWxEaXJlY3RpdmVzBwAYAQAJYmxhY2tob2xlAQAEKEkpVgwAGgAbCgAZABwBABUoTGphdmEvbGFuZy9PYmplY3Q7KVYMABoAHgoAGQAfAQAEQ29kZQEADVN0YWNrTWFwVGFibGUBAApTb3VyY2VGaWxlAQAUU291cmNlRGVidWdFeHRlbnNpb24AAQACAAQAAAABAAAABgAHAAAAAgABAAgACQABACEAAABEAAQAAgAAACIqWVm3AAsRAGS8CrUADQM8tAANWRtZT1m+hAEBG6P/9lexAAAAAQAiAAAAEAAB/wATAAIHAAIBAAEHAA4AAQAPABAAAQAhAAABoAAFAAkAAAEMEhE8EhE9EhE+KrQADVkSEhITEhRgghIVKrQADToEEhZkflk2BTYGvhIXcKoAAADeAAAAAAAAAAcAAADXAAAAtwAAAJwAAACQAAAAfAAAAGkAAABXAAAASVmEAv8cnABrFQYuFQVgNgUqtAANEAUuNgeEBgFZFQYuFQVgBGg2BYQGAVkVBi4VBSq0AA06CGA2BYQGAVkVBoQD/x2cAAMuFQVgNgWEBgFZFQYuEB68ChAJLoIVBWA2BYQGAVkVBi4VBWA2BYQGAVkVBi4qEBC8CrUADRUFYIQB/xuc/+U2BYQGAVkVBi4VBRUGX7gAHWA2BSoQSx1gvAq1AA1ZuAAghAYBWb4VBqP/UhUFrAAAAAEAIgAAAIIADf8AWAAHBwACAQEBBwAOAQEAAQcADlsHAA5NBwAOUQcADv8ACQAHBwACAQEBBwAOAQEAAwcADgcADgFIBwAOUwcADv8AAwAHBwACAQEBBwAOAQEAAgcADgFHBwAO/wAAAAcHAAIBAQEHAA4BAQACBwAOBwAOWQcADl8HAA5GBwAOAAIAIwAAAAIABQAkAAAAAA==");

    public static void main(String[] args) throws Throwable {
        Crash_1305503bcc0d111f3497e0c517b573236b1bb0ad.class.getClassLoader().setDefaultAssertionStatus(true);
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