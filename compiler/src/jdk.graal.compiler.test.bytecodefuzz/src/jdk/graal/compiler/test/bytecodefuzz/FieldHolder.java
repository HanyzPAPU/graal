package jdk.graal.compiler.test.bytecodefuzz;

public class FieldHolder {
    public int i = 42;
    public float f = 3.14f;
    public long l = 365;
    public double d = 1.618;
    public String s = "Field Holder String";
    public Object o = new Object();
    public FieldHolder fh = this;
    public int[] ia = new int[10];
    public float[] fa = new float[10];
    public long[] la = new long[10];
    public double[] da = new double[10];
    public Object[] oa = new Object[10];
}
