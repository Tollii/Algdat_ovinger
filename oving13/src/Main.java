import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {
        String nodes = "src/nordics/noder.txt";
        String edges = "src/nordics/kanter.txt";

        FileReader fr1 = new FileReader(nodes);
        FileReader fr2 = new FileReader(edges);
        BufferedReader br1 = new BufferedReader(fr1);
        BufferedReader br2 = new BufferedReader(fr2);

        Graph graph = new Graph();
        Date startTime;
        Date stopTime;

        int from = 5709083;
        int to = 5108028;

        System.out.println("Loading file...");
        graph.fillGraph(br1, br2);
        System.out.println("File loaded.");

        System.out.println("Calculating shortest path...");
        startTime = new Date();
        graph.a_Star(from, to);
        stopTime = new Date();
        System.out.println("Algorithm took " + (double) (stopTime.getTime()-startTime.getTime()) + "ms");

        graph.reconstruct_path(from, to);
    }
}
