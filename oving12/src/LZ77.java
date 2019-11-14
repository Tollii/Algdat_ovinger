import java.io.*;

public class LZ77 {

    private final int DEFAULT_BUFFER = 1024;
    private int bufferSize;
    private Reader inputFile;
    private PrintWriter outputFile;
    private StringBuffer searchBuffer;

    public LZ77(int bufferSize){
        this.bufferSize = bufferSize;
        searchBuffer = new StringBuffer(this.bufferSize);

    }

    public LZ77(){
        this.bufferSize = DEFAULT_BUFFER;
        searchBuffer = new StringBuffer(this.bufferSize);

    }

    private void trimBuffer(){
        if(searchBuffer.length() > bufferSize) {
            searchBuffer = searchBuffer.delete(0, searchBuffer.length() - bufferSize);
        }
    }

    public void compress(String filename) throws IOException {
        inputFile = new BufferedReader(new FileReader("src/" + filename));
        outputFile = new PrintWriter(new BufferedWriter(new FileWriter("src/" + filename + ".compressed")));

        StringBuilder match = new StringBuilder();
        int intCurrentChar;
        int searchResult, matchIndex = 0;


        // Reads one character each loop
        while((intCurrentChar = inputFile.read()) != -1){
            char currentChar = (char) intCurrentChar;
            searchResult = searchBuffer.indexOf(match.toString() + currentChar);

            // Checks if match is found, else, encode.
            if(searchResult != -1){
                match.append(currentChar);
                matchIndex = searchResult - match.length();
            } else {
                String encoded = "~" + matchIndex + "~" + match.length() + "~" + currentChar;
                StringBuilder originalText = match.append(currentChar);

                // If encoded is shorter than original, print encoded.
                if(encoded.length() < originalText.length()){
                    outputFile.print(encoded);
                    searchBuffer.append(originalText);
                    match = new StringBuilder();
                    matchIndex = 0;
                } else {
                    // print original until empty or new match
                    match = originalText;
                    matchIndex = -1;
                    while(match.length() > 1 && matchIndex == -1){
                        outputFile.print(match.charAt(0));
                        searchBuffer.append(match.charAt(0));
                        match.deleteCharAt(0);
                        matchIndex = searchBuffer.indexOf(match.toString());
                    }
                }
                trimBuffer();
            }
        }

        if(matchIndex != -1){
            String encoded = "~" + matchIndex + "~" + match.length();
            if(encoded.length() <= match.length()){
                outputFile.print("~" + matchIndex + "~" + match.length());
            } else {
                outputFile.print(match);
            }
        }
        inputFile.close();
        outputFile.flush();
        outputFile.close();
    }

    public void decompress(String filename) throws IOException{

        inputFile = new BufferedReader(new FileReader("src/" + filename));
        outputFile = new PrintWriter(new BufferedWriter(new FileWriter("src/" + filename + ".decompressed")));


        inputFile.close();
        outputFile.flush();
        outputFile.close();
    }




    public static void main(String[] args){

        LZ77 lz = new LZ77(8192);
        try {
            // lz.compress("sicko.txt");
            lz.decompress("sicko.txt.compressed");


        } catch (IOException ioe){
            System.out.println(ioe);
        }




    }


}

