import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LZ77 {

    private static final byte MATCH_LENGTH = 5;
    private byte[] output;
    private byte[] input;
    private int outputLength;

    public void compress(String filename){

        // Reads file and converts to an array of bytes
        try {
            input = Files.readAllBytes(Paths.get(filename));
            output = new byte[input.length];
        } catch(IOException ioe){
            System.out.println(ioe);
        }

        byte uncompressedCount = 0;
        int uncompressedStartIndex = -1;
        for(int i = 0; i < input.length; i++){
            byte matchLength = 0;
            int matchIndex = -1;
            int newOutputLength = outputLength + 2; // Reserve 2 bytes for offset and length

            final int searchStartIndex = Math.max(0, i - 127);
            for(int j = searchStartIndex; j < i; j++){
                if(i + matchLength >= input.length) break;
                if(input[j] == input[i + matchLength]) {
                    if(matchIndex == -1) matchIndex = j;
                    matchLength++;
                    if(newOutputLength >= output.length) System.out.println("Error");
                    if(j >= input.length) System.out.println("Error");
                    output[newOutputLength++] = input[j];
                } else if(matchLength >= -1){
                    if(matchLength >= MATCH_LENGTH)break;
                    matchIndex = -1;
                    matchLength = 0;
                }
            }

            if(matchIndex != -1 && matchLength >= MATCH_LENGTH){
                if(uncompressedCount > 0){
                    output[uncompressedStartIndex] = (byte) -uncompressedCount;
                    uncompressedStartIndex = -1;
                    uncompressedCount = 0;
                    outputLength++;
                }

                output[outputLength++] = matchLength;
                output[outputLength++] = (byte)(i - matchIndex);

                System.out.println("Found match! \"" + convertToString(input, i, matchLength) + "\" (" + i + ")"
                        + " with \"" + convertToString(input, matchIndex, matchLength) + "\" (" + matchIndex + ")");

                i += matchLength - 1;


            } else {


                if(uncompressedStartIndex == -1) uncompressedStartIndex = outputLength;
                uncompressedCount++;
                output[++outputLength] = input[i];
            }

            if(uncompressedCount == 127){
                output[uncompressedStartIndex] = -127;
                uncompressedStartIndex = -1;
                uncompressedCount = 0;
                outputLength++;

            }
        }

        if(uncompressedCount > 0){
            output[uncompressedCount] = (byte)-uncompressedCount;
            outputLength++;
        }

        try {
            writeFile(filename + ".compressed");
        } catch(IOException ioe){
            System.out.println(ioe);
        }

    }

    private void writeFile(String filename) throws IOException {
        DataOutputStream dos = null;
        dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
        dos.write(output, 0, outputLength);
        dos.close();

    }

    private String convertToString(byte[] buffer, int startIndex, int count){
        StringBuilder s = new StringBuilder();
        for(int i = startIndex; i < startIndex + count; i++){
            s.append((char)(buffer[i]));
        }
        return s.toString();
    }

    public static void main(String[] args){
        LZ77 lz = new LZ77();
        lz.compress("src/opg12.txt");
    }
}

class LZ77Decompress {

    private byte[] input;
    private byte[] output;
    private int outputLength;

    public void decompress(String filename){
        try {
            input = Files.readAllBytes(Paths.get(filename));
            output = new byte[input.length*5];
        } catch(IOException ioe){
            System.out.println(ioe);
        }

        System.out.println("INPUT SIZE: " + input.length);
        System.out.println("OUTPUT SIZE: " + output.length);

        for(int i = 0; i< input.length; i++){
            byte currentByte = input[i];
            if(currentByte > 0) {
                // Compressed data
                byte length = currentByte;
                byte offset = input[++i];
                int startIndex = outputLength - offset;
                if (startIndex < 0) System.out.println("Error! Negative start index!");
                if (startIndex + length >= output.length) System.out.println("Error!");
                for (int j = startIndex; j < startIndex + length; j++) output[outputLength++] = output[j];
                System.out.println(i + ": " + convertToString(output, outputLength - length, length));
            } else if(currentByte < 0) {
                int length = -currentByte;
                for(int j = i + 1; j <= i + length; j++){
                    output[outputLength++] = input[j];
                }
                System.out.println(i + ": " + convertToString(output, outputLength - length, length));
                i += length;
            } else {
                System.out.println("Parse error");
            }
        }

        try {
            writeFile(filename + ".decompressed");
        } catch(IOException ioe){
            System.out.println(ioe);
        }

    }

    private String convertToString(byte[] buffer, int startIndex, int count){
        StringBuilder s = new StringBuilder();
        for(int i = startIndex; i < startIndex + count; i++){
            s.append((char)(buffer[i]));
        }
        return s.toString();
    }

    private void writeFile(String filename) throws IOException {
        DataOutputStream dos = null;
        dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
        dos.write(output, 0, outputLength);
        dos.close();

    }

    public static void main(String[] args){
        LZ77Decompress lz = new LZ77Decompress();
        lz.decompress("src/opg12.txt.compressed");
    }



}