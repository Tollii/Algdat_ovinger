import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {

        String nodes = "src/iceland/noder.txt";
        String edges = "src/iceland/kanter.txt";

        FileReader fr1 = new FileReader(nodes);
        FileReader fr2 = new FileReader(edges);
        BufferedReader br1 = new BufferedReader(fr1);
        BufferedReader br2 = new BufferedReader(fr2);


    }



}
