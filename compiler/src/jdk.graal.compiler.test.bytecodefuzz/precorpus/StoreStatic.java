public class StoreStatic {
    public static int x = 1;

    public static int foo() {
        System.out.println(x);
        int res = x;
        x++;
        return res;
    }
}