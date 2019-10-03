import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void breddeFørstSøk(Graf graf, int startnode){
        System.out.println("Bredde først søk");
        graf.bfs(graf.node[startnode]);
        graf.getInfo(startnode);
    }

    public static void topoSort(Graf graf, int startnode){
        System.out.println("\nTopological sort");
        graf.dfs(graf.node[startnode]);
        Node start = graf.topologisort();
        graf.topologiPrint(start);

    }

    public static void main(String[] args) throws IOException {
        try{
            String filadresse = "PATH";
            BufferedReader bf = new BufferedReader(new FileReader(filadresse));
            Graf graf = new Graf();

            int startNode= 5;
            breddeFørstSøk(graf,startNode);
            topoSort(graf,startNode);



        }catch(FileNotFoundException fnfe){
            System.out.println(fnfe);
        }
    }
}
