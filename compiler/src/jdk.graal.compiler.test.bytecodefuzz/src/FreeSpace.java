package jdk.graal.compiler.test.bytecodefuzz;

public class FreeSpace {
    private int freeSpace;
    
    public FreeSpace(int space) {
        this.freeSpace = space;
    }

    public FreeSpace() {
        this.freeSpace = 0;
    }

    // Adds space to the free space, can be also used to remove from the free space
    public void Add(int space) {
        freeSpace += space;
        if (freeSpace < 0) {
            throw new Error("Free Space Less Than 0!!!");
        }
    }

    public int Amount() {
        return freeSpace;
    }
}
