import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Graf {

    int N, K;
    Node[] node;

    public Graf(int n, int k, Node[] node) {
        N = n;
        K = k;
        this.node = node;
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
}
