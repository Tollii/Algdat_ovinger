import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try{
            String filadresse = "/Users/tolli/Documents/program/algdat/oving7/src/data/L7g1.txt";
            BufferedReader bf = new BufferedReader(new FileReader(filadresse));
            Graf graf = new Graf();
            graf.ny_ugraf(bf);
            //graf.bfs(graf.node[5]);
            //graf.getInfo(5);
            graf.dfs(graf.node[5]);



        }catch(FileNotFoundException fnfe){
            System.out.println(fnfe);
        }
    }
}
