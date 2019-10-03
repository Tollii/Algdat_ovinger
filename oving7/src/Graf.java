import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Graf {

    int N, K;
    Node[] node;

    public void initforgj(Node s){
        for(int i = N; i -->0;){
            node[i].d = new Forgj();
        }
        ((Forgj)s.d).dist = 0;
    }

    public void ny_ugraf(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        node = new Node[N];
        for(int i = 0; i < N; ++i) node[i] = new Node();
        K = Integer.parseInt(st.nextToken());
        for(int i = 0; i < K; ++i){
            st = new StringTokenizer(br.readLine());
            int fra = Integer.parseInt(st.nextToken());
            int til = Integer.parseInt(st.nextToken());
            Kant k = new Kant(node[til], node[fra].kant1);
            node[fra].kant1 = k;
        }
    }

    public void bfs(Node s){
        initforgj(s);
        Kø kø = new Kø(N - 1);
        kø.leggIKø(s);
        while(!kø.tom()){
            Node n = (Node)kø.nesteIKø();
            for(Kant k = n.kant1; k != null; k = k.neste){
                Forgj f = (Forgj)k.til.d;
                if(f.dist == f.uendelig){
                    f.dist =((Forgj)n.d).dist + 1;
                    f.forgj = n;
                    kø.leggIKø(k.til);
                }
            }
        }
    }

    public void getInfo(int startnode){
        System.out.println("Node Forgj Dist");
        for(int i = 0; i < node.length; i++){
            for(int x = 0; x < node.length; x++){
                if(((Forgj)node[i].d).forgj == node[x]){
                    System.out.println(i + "     " + x + "     " + ((Forgj)node[i].d).finn_dist() + "\n");
                }
            }
            if(i == startnode){
                System.out.println(i + "     " + "     " + ((Forgj)node[i].d).finn_dist() + "\n");
            }
        }
    }






}
