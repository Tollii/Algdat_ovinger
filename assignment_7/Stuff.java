class Kant{
    Kant neste;
    Node til;

    public Kant(Node n, Kant nst){
        til = n;
        neste = nst;
    }
}

class Node{
    Kant kant1;
    Object d;
}

class Topo_lst{
    boolean funnet;
    Node neste;
}

class Dfs_forgj extends Forgj{
    int funnet_tid, ferdig_tid;
    static int tid;
    static void null_tid() {tid = 0;}
    static int les_tid() {return ++tid;}
}

class Forgj {
    int dist;
    Node forgj;
    static int uendelig = 1000000000;
    public int finn_dist() {return dist;}
    public Node finn_forgj() {return forgj;}

    public Forgj(){
        dist = uendelig;
    }
}
