public class MultiArray {

    public static int[][][] sa = new int[10][10][10];

    public static void foo() {
        int[][][] la = new int[10][10][10];

        la[1][2][0] = sa[0][1][0];
    }
}