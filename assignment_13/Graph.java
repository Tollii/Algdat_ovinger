import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import eu.jacquet80.minigeo.MapWindow;
import eu.jacquet80.minigeo.POI;
import eu.jacquet80.minigeo.Point;
import eu.jacquet80.minigeo.Segment;


import javax.swing.*;

class Graph extends JFrame {
    private Node[] nodes;
    private Edge[] edges;

    @SuppressWarnings("Duplicates")
    public void dijkstra(int start, int goal) {
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        Node startNode = nodes[start];
        Node stopNode = nodes[goal];
        Node current;

        startNode.setCost(0);
        queue.add(startNode);

        while(!queue.isEmpty()) {
            current = queue.poll();
            current.setExpanded(true);

            if(current.equals(stopNode)) break;

            // Iterate current node's edges
            for(Edge e : current.getEdge()) {
                Node toNode = e.getNodeTo();

                if(toNode.getCost() > current.getCost() + e.getDriveTime()) {
                    toNode.setCost(current.getCost() + e.getDriveTime());
                    toNode.setPreviousNode(current);
                } else {
                    continue;
                }

                toNode.setPriority(toNode.getCost());

                // Adds or removes from queue based on if they have been expanded or discovered
                if(!toNode.isExpanded()) {
                    if(toNode.isDiscovered()) queue.remove(toNode);
                    toNode.setDiscovered(true);
                    queue.add(toNode);
                }
            }
        }
    }

    @SuppressWarnings("Duplicates")
    public void a_Star(int start, int goal){
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        Node startNode = nodes[start];
        Node stopNode = nodes[goal];
        Node current;
        startNode.setCost(0);
        queue.add(startNode);

        while(!queue.isEmpty()) {
            current = queue.poll();

            if(current.equals(stopNode)) break;

            // Iterate current node's edges
            current.setExpanded(true);
            for(Edge e : current.getEdge()) {
                Node toNode = e.getNodeTo();

                if(toNode.getCost() > current.getCost() + e.getDriveTime()) {
                    toNode.setCost(current.getCost() + e.getDriveTime());
                    toNode.setPreviousNode(current);
                } else {
                    continue;
                }

                // Set cost from startnode if distance is infinity. Also changes based on other routes.
                if(toNode.isDistanceCalculated()) toNode.setPriority((int) toNode.getDirectDistance() / 130*3600 + toNode.getCost());
                else {
                    toNode.setDirectDistance(stopNode);
                    toNode.setPriority((int) toNode.getDirectDistance() / 130 * 3600 + toNode.getCost());
                }

                // Adds or removes from queue based on if they have been expanded or discovered
                if(!toNode.isExpanded()) {
                    if(toNode.isDiscovered()) queue.remove(toNode);
                    toNode.setDiscovered(true);
                    queue.add(toNode);
                }
            }
        }
    }

    public void fillGraph(BufferedReader nodeReader, BufferedReader edgeReader) throws IOException {
        StringTokenizer st = new StringTokenizer(nodeReader.readLine());
        int numberOfNodes = Integer.parseInt(st.nextToken());
        int numberOfEdges;
        nodes =  new Node[numberOfNodes];

        // Fill array with empty nodes
        for(int i = 0; i < numberOfNodes; i++) {
            nodes[i] = new Node();
        }

        // Read data from node file and initialize all nodes
        for(Node n : nodes) {
            st = new StringTokenizer(nodeReader.readLine());
            int nodeNumber = Integer.parseInt(st.nextToken());
            double latitude = Double.parseDouble(st.nextToken());
            double longitude = Double.parseDouble(st.nextToken());
            n.setVariables(longitude, latitude, nodeNumber);
        }

        st = new StringTokenizer(edgeReader.readLine());
        numberOfEdges = Integer.parseInt(st.nextToken());
        edges = new Edge[numberOfEdges];

        // Read and add edges to nodes
        for(int i = 0; i < numberOfEdges; i++) {
            st = new StringTokenizer(edgeReader.readLine());
            int fromNode = Integer.parseInt(st.nextToken());
            int toNode = Integer.parseInt(st.nextToken());
            int travelTime = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            int speedLimit = Integer.parseInt(st.nextToken());

            Edge e = new Edge(nodes[fromNode], nodes[toNode], travelTime, length, speedLimit);
            edges[i] = e;
            nodes[fromNode].addEdge(e);
        }
        nodeReader.close();
        edgeReader.close();
    }

    public void reconstruct_path(int start, int stop) {
        ArrayList<Node> path = new ArrayList<Node>();
        ArrayList<Point> points = new ArrayList<Point>();
        Node startNode = nodes[start];
        Node stopNode = nodes[stop];
        MapWindow window = new MapWindow();
        int totalTime = stopNode.getCost() / 100;
        int hours = totalTime / 3600;
        int minutes = (totalTime % 3600) / 60;
        int seconds = totalTime % 60;
        int travelDistance = 0;

        Node current = stopNode;
        path.add(stopNode);
        points.add(new Point(current.getLatitude(), current.getLongitude()));

        Point n;
        Node prevNode;

        while(!current.equals(startNode)) {
            prevNode = current.getPreviousNode();
            for(Edge e : prevNode.getEdge()) {
                if(e.getNodeTo() == current) travelDistance += e.getLength();
            }

            current = current.getPreviousNode();
            path.add(current);
            n = new Point(current.getLatitude(), current.getLongitude());
            points.add(n);
        }

        window.addPOI(new POI(new Point(stopNode.getLatitude(), stopNode.getLongitude()), stopNode.nodeNrAsString()));

        for(int i = 0; i < points.size() - 1; i++) window.addSegment(new Segment(points.get(i), points.get(i + 1), Color.RED));

        window.addPOI(new POI(new Point(startNode.getLatitude(), startNode.getLongitude()), startNode.nodeNrAsString()));

        System.out.println("\nPath Information:\n");
        System.out.println("Time: " + hours +":" + minutes+":"+seconds);
        System.out.println("Nodes in path " + path.size());
        System.out.println("Distance between nodes: " + travelDistance /1000 + " km");

        window.setVisible(true);
    }
}
