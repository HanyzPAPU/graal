package jdk.graal.compiler.core.test.bytecodefuzz;

public final class Mutator {
    

    public byte[] Mutate(byte[] data, int maxSize, int seed){
        System.out.println("Hello from Java Mutate!");
        return data;
    }

    public void Init(int seed){
        return;
    }
}
