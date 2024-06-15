// Feven numbers are numbers divisible by two or five
public class FevenSum {
    int [] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

    public int sum() {
        int s = 0;
        for(int i = 0; i < data.length; ++i) {
            if (i % 2 == 0 || i % 5 == 0) {
                s  += data[i];
            }
        }
        return s;
    }
}