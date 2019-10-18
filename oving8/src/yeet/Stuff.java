package yeet;

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

class Vkant extends Kant{

    int flyt = 0;
    int vekt;
    public Vkant (Node n, Vkant nst, int vkt){
        super(n, nst);
        vekt = vkt;
    }




}