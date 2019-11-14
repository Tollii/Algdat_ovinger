import java.io.*;

public class LZ77 {

    private final int DEFAULT_BUFFER = 1024;
    private int bufferSize;
    private PrintWriter outputFile;
    private StringBuffer searchBuffer;

    public LZ77(int bufferSize){
        this.bufferSize = bufferSize;
        searchBuffer = new StringBuffer(this.bufferSize);

    }

    public LZ77(String filename){
        this.bufferSize = DEFAULT_BUFFER;
        searchBuffer = new StringBuffer(this.bufferSize);
    }

    private void trimBuffer(){
        if(searchBuffer.length() > bufferSize) {
            searchBuffer = searchBuffer.delete(0, searchBuffer.length() - bufferSize);
        }
    }

    public void compress(String filename) throws IOException {

        File file = new File("src/ransom.txt");
        outputFile = new PrintWriter(new BufferedWriter(new FileWriter("src/" + filename + ".compressed")));

        byte[] bArray = readFileToByteArray(file);











        outputFile.flush();
        outputFile.close();
    }

    public void decompress(String filename) throws IOException{

        File file = new File("src/ransom.txt");
        outputFile = new PrintWriter(new BufferedWriter(new FileWriter("src/" + filename + ".decompressed")));

        byte[] bArray = readFileToByteArray(file);

        for(int i = 0; i < bArray.length; i++){
            System.out.println(bArray[i]);
            outputFile.print((char) bArray[i]);
        }
















        outputFile.flush();
        outputFile.close();
    }



    private static byte[] readFileToByteArray(File file){
        FileInputStream fis;
        byte[] bArray = new byte[(int) file.length()];

        try {
            fis = new FileInputStream(file);
            fis.read(bArray);
            fis.close();
        } catch (IOException ioe){
            System.out.println(ioe);
        }
        return bArray;
    }


    public static void main(String[] args){

        LZ77 compress = new LZ77(8192);
        LZ77 decompress = new LZ77(8192);
        try {
            // compress.compress("ransom.txt");
            decompress.decompress("ransom.txt");


        } catch (IOException ioe){
            System.out.println(ioe);
        }




    }


}