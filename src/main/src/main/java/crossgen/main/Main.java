package crossgen.main;

public class Main {

    public static void main(String[] args) {
        int width = 7;
        int height = 3;
        char ch = 'A';
        String[][] array = new String[height][];
        for( int i = 0 ; i < height ; i++ ) {
            array[i] = new String[width];
            for( int j = 0 ; j < width ; j++, ch++ ) {
                array[i][j] = "" + ch;
            }
        }

        for(int i = 1 ; i <= width + height - 3; i++) {
            for(int j = 0 ; j <= i ; j++) {
                int k = i - j;
                if(k < height && j < width) {
                    System.out.print( array[k][j] + " " );
                }
            }
            System.out.print(i);
            System.out.println();
        }
    }

}
