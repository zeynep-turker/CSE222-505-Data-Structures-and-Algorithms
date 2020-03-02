public class Main { //File names are taken as parameters.
    public static void main(String[] args) {
        for(int n=0; n<args.length; ++n) {
            Matrix m = new Matrix(args[n]);
        }
    }
}