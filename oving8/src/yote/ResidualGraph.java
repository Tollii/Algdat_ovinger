package yote;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ResidualGraph {

    LinkedList<FlowEdge>[] outE;
    int n;


    public ResidualGraph(int n) {
        this.n = n;
        outE = new LinkedList[n];
        for(int u = 0; u < n; u++){
            outE[u] = new LinkedList<>();
        }
    }

    public void connect(int u, int v, int cap){
        FlowEdge e = new FlowEdge(u, v, cap);
        FlowEdge r = new FlowEdge(v, u, 0);
        e.residual = r;
        r.residual = e;
        outE[u].add(e);
        outE[v].add(r);
    }

    static int maxflowEK(ResidualGraph g, int s, int t){
        int maxflow = 0;
        int flow;

        while((flow = augmentFlowBFS(g, s, t)) != 0){
            System.out.println("\nØkning " + flow + "\n");
            maxflow += flow;
        }
        return maxflow;
    }

    static int augmentFlowBFS(ResidualGraph g, int s, int t){
        // Find path from s to t in g
        int[] pathcap = new int[g.n];
        pathcap[s] = Integer.MAX_VALUE;
        FlowEdge[] parent = new FlowEdge[g.n];
        Queue<Integer> Q = new LinkedList<>();
        Q.add(s);
        while(!Q.isEmpty() && parent[t] == null){
            int cur = Q.poll();
            for(FlowEdge e : g.outE[cur]){
                if(e.dest != s && e.cap > 0 && parent[e.dest] == null){
                    parent[e.dest] = e;
                    pathcap[e.dest] = Math.min(pathcap[cur], e.cap);
                    Q.add(e.dest);
                }
            }
        }
        if(parent[t] == null) return 0;
        int flow = pathcap[t];
        int cur = t;
        System.out.print(t);
        while(parent[cur] != null){
            System.out.print((parent[cur].orig));
            parent[cur].push(flow);
            cur = parent[cur].orig;
        }
        return flow;
    }

    public static void main(String[] args){
        try{
            String filename = "src/yote/flytgraf1.txt";
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            ResidualGraph g = new ResidualGraph(n);
            int k = Integer.parseInt(st.nextToken());
            System.out.println(n + " " + k);
            for(int i = 0; i < k; i++){
                st = new StringTokenizer(br.readLine());
                int fra = Integer.parseInt(st.nextToken());
                int til = Integer.parseInt(st.nextToken());
                int vekt = Integer.parseInt(st.nextToken());
                System.out.println(fra + " " + til + " " + vekt);
                g.connect(fra, til, vekt);
            }
            System.out.println("\n");
            System.out.println("Maksimal flyt ble " + maxflowEK(g,0,7));
        } catch(FileNotFoundException fnfe){
            System.out.println(fnfe);
        } catch (IOException ioe){
            System.out.println(ioe);
        }
    }
}
