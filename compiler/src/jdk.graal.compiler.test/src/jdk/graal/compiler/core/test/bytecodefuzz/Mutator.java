package jdk.graal.compiler.core.test.bytecodefuzz;

public final class Mutator {

    public byte[] Mutate(byte[] data, int maxSize, int seed){
        System.out.println("Hello from Java Mutate!");

        for (int i = 0; i < 100; ++i) {
            System.out.printf("%02X ", data[i]);
            
            if (i % 16 == 15) {
                System.out.println();
                continue;
            }
            if (i % 4 == 3){
                System.out.print("   ");
            }
        }

        return data;
    }

    public void Init(int seed){
        return;
    }
}
