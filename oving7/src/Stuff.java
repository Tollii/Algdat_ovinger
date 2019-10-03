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
