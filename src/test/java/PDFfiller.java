public class PDFfiller {

    public static void main(String[] args) {
        System.out.println(y(8));
    }

    public static int y(int x) {
        if (x<2)
            return 1;
        else return y(x-2)+y(x-1);
    }
}