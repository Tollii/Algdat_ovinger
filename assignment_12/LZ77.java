import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LZ77 {

    private final byte MATCH_LENGTH = 3;

    private byte[] bytesFromFile = new byte[0];
    private byte[] bytesToFile = new byte[0];
    private int outputLength;

    public void compress(String filename) throws IOException {

        readFile(filename);

        byte compressedCount = 0;
        int uncompressedStartIndex = -1;

        for (int i = 0; i < bytesFromFile.length; i++) {

            byte matchLength = 0;
            int matchIndex = -1;
            int newOutputLength = outputLength + 2; // Reserve 2 bytes for offset and length

            // Searches for similar pattern 127 bytes behind itself.
            // Cannot be negative.
            int searchStart = i - 127;
            if (searchStart < 0) {
                searchStart = 0;
            }

            // Iterate through search area.
            for (int j = searchStart; j < i; j++) {
                if (i + matchLength >= bytesFromFile.length)
                    break;

                if (bytesFromFile[j] == bytesFromFile[i + matchLength]) {
                    if (matchIndex == -1) {
                        matchIndex = j;
                    }

                    matchLength++;

                    bytesToFile[newOutputLength++] = bytesFromFile[j];

                } else if (matchIndex != -1) {

                    if (matchLength >= MATCH_LENGTH) {
                        break;
                    }
                    // Reset
                    matchIndex = -1;
                    matchLength = 0;
                }
            }

            // If Match found.
            if (matchIndex != -1 && matchLength >= MATCH_LENGTH) {
                if (compressedCount > 0) {

                    bytesToFile[uncompressedStartIndex] = (byte) -compressedCount;
                    uncompressedStartIndex = -1;
                    compressedCount = 0;
                    outputLength++;
                }

                bytesToFile[outputLength] = matchLength;
                outputLength++;
                bytesToFile[outputLength] = (byte) (i - matchIndex);
                outputLength++;

                // -1 because i++ in for-loop
                i += matchLength - 1;

            } else {
                // Didn't find any matches
                if (uncompressedStartIndex == -1) {
                    uncompressedStartIndex = outputLength;
                }
                compressedCount++;
                bytesToFile[++outputLength] = bytesFromFile[i]; // Reserve 1 byte for length
            }

            // Check if uncompressed block is full
            if (compressedCount == 127) {

                // Finish block and reset counters
                bytesToFile[uncompressedStartIndex] = -127;
                uncompressedStartIndex = -1;
                compressedCount = 0;
                outputLength++; // 1 byte for length
            }
        }

        // Check if any leftover uncompressed data
        if (compressedCount > 0) {
            // Finish block
            bytesToFile[uncompressedStartIndex] = (byte) -compressedCount;
            outputLength++; // 1 byte for length
        }

        // Write to new compressed file.
        writeFile();
        System.out.println("Compressed!");
    }

    private void readFile(String infile) {
        String path = "src/Øving12/testfiles/" + infile;
        try {
            bytesFromFile = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bytesToFile = new byte[bytesFromFile.length * 2];
    }

    private void writeFile() {
        try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("src/Øving12/compressed/testfile.LZ77")))) {
            dos.write(bytesToFile, 0, outputLength);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}