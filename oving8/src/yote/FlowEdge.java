package yote;

public class FlowEdge {

    FlowEdge residual;
    int orig, dest, cap;

    public FlowEdge(int u, int v, int cap){
        this.orig = u;
        this.dest = v;
        this.cap = cap;
    }

    public void push(int flow){
        cap -= flow;
        residual.cap += flow;
    }


}
