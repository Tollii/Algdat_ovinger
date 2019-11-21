public class Edge {
    private Node from;
    private Node to;
    private int travelTime;
    private int length;
    private int speedLimit;


    public Edge(Node from, Node to, int travelTime, int length, int speedLimit){
        this.from = from;
        this.to = to;
        this.travelTime = travelTime;
        this.length = length;
        this.speedLimit = speedLimit;

    }

    public Node getNodeTo(){
        return to;
    }

    public Node getNodeFrom(){
        return from;
    }

    public void setNodeTo(Node n){
        this.to = n;
    }

    public void setNodeFrom(Node n){
        this.from = n;
    }

    public int getLength(){
        return length;
    }

    public int getDriveTime(){
        return travelTime;
    }

    public int getSpeedLimit(){
        return speedLimit;
    }

    public String toString(){
        return "Travel time:" + this.travelTime + " Length: " + this.length + " Speedlimit: " + this.speedLimit;
    }
}

