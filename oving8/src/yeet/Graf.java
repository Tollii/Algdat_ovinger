package yeet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
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

    public void ny_graf(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        node = new Node[N];
        for(int i = 0; i < N; ++i) node[i] = new Node();
        K = Integer.parseInt(st.nextToken());
        for(int i = 0; i < K; ++i){
            st = new StringTokenizer(br.readLine());
            int fra = Integer.parseInt(st.nextToken());
            int til = Integer.parseInt(st.nextToken());
            int vekt = Integer.parseInt(st.nextToken());
            Vkant k = new Vkant(node[til], (Vkant)node[fra].kant1, vekt);
            node[fra].kant1 = k;
        }
    }

    public void bfs(Node s, Node e){
        initforgj(s);
        Kø kø = new Kø(N - 1);
        ArrayList<Vkant> yeet = new ArrayList<>();
        kø.leggIKø(s);
        while(!kø.tom()){
            Node n = (Node)kø.nesteIKø();
            for(Vkant k = (Vkant)n.kant1; k != null; k = (Vkant)k.neste){
                if(k.vekt - k.flyt > 0 && n == e){
                    Forgj f = (Forgj)k.til.d;
                    if(f.dist == f.uendelig){
                        f.dist =((Forgj)n.d).dist + 1;
                        f.forgj = n;
                        kø.leggIKø(k.til);
                    }
                }
            }
        }
    }

    public void edmonds(Node s, Node e){
        bfs(s, e);


    }
}
