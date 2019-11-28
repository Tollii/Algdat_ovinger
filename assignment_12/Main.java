import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //testfile2 = 11 448 bytes
        LZ77 compress = new LZ77();
        LZ77decompress decompress = new LZ77decompress();

        //compress.compress("testfile2.txt");
        decompress.decompress("testfile.zipzap");
    }