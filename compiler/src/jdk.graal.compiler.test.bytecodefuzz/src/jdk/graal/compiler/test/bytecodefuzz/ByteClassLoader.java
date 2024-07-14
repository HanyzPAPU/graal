package jdk.graal.compiler.test.bytecodefuzz;

public class ByteClassLoader extends ClassLoader {
    public Class<?> LoadFromBytes(String name, byte[] bytes){
        return defineClass(name, bytes, 0, bytes.length);
    }

    public void ResolveClass(Class<?> clazz){
        resolveClass(clazz);
    }
}
