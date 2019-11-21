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

        int from = 30236;
        int to = 14416;

        Graph graph = new Graph();

        System.out.println("Loading file...");
        graph.fillGraph(br1, br2);
        System.out.println("File loaded.");

        System.out.println("Calculating shortest path with a*...");
        graph.dijkstra(from, to);
        System.out.println("Finished");
        graph.reconstruct_path(from, to);




    }



}
