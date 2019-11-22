import java.util.ArrayList;

public class Node implements Comparable<Node>{

    private double latitude; // Breddegrad
    private double longitude; // Lengdegrad
    private int nodeNr;
    private Node previousNode;
    private int cost = 1000000000; // To infinity and beyond
    private int priority = 1000000000; // To infinity and beyond
    private boolean directDistanceCalculated = false;
    private boolean discovered = false;
    private boolean expanded = false;
    private double directDistance = 0;

    private ArrayList<Edge> edges = new ArrayList<Edge>();


    public void setVariables(double longitude, double latitude, int nodeNr){
        this.latitude = latitude;
        this.longitude = longitude;
        this.nodeNr = nodeNr;
    }

    public boolean isDistanceCalculated(){
        return this.directDistanceCalculated;
    }

    public void distanceCalculated(boolean calculated){
        this.directDistanceCalculated = calculated;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public int getNodeNr(){
        return nodeNr;
    }

    public ArrayList<Edge> getEdge(){
        return this.edges;
    }

    public void addEdge(Edge edge){
        this.edges.add(edge);
    }

    public void setCost(int cost){
        this.cost = cost;
    }

    public int getCost(){
        return this.cost;
    }

    public boolean isExpanded(){
        return this.expanded;
    }

    public void setExpanded(boolean expanded){
        this.expanded = expanded;
    }

    public Node getPreviousNode(){
        return this.previousNode;
    }

    public void setPreviousNode(Node previous){
        this.previousNode = previous;
    }

    public void setPriority(int prior){
        this.priority = prior;
    }

    public int getPriority(){
        return this.priority;
    }

    public void setDiscovered(boolean discovered){
        this.discovered = discovered;
    }

    public boolean isDiscovered(){
        return this.discovered;
    }

    public void setDirectDistance(Node n){
        distanceCalculated(true);
        final int earthRadius = 6371;
        double x = Math.toRadians((this.latitude - n.getLatitude()) / 2);
        double y = Math.toRadians((this.longitude - n.getLongitude()) / 2);

        this.directDistance = (2 * earthRadius * Math.asin(Math.sqrt(
                Math.sin(x) * Math.sin(x) +
                        Math.cos(Math.toRadians(this.latitude)) *
                                Math.cos(Math.toRadians(n.getLatitude())) *
                                Math.sin(y) * Math.sin(y))));

    }

    public double getDirectDistance(){
        return this.directDistance;
    }

    public String nodeNrAsString(){
        StringBuilder sb = new StringBuilder();
        sb.append(nodeNr);
        return sb.toString();
    }

    public String print(){
        return nodeNr + ", latitude: " + latitude + ", longitude: " + longitude;
    }

    public int compareTo(Node n) {
        return this.priority-n.getPriority();
    }
}
