class Node {
  private Node next;
  private int data;

  public Node(int data){
    this.data = data;
  }

  public Node next(){
    return next;
  }

  public void setNext(Node next){
    this.next = next;
  }

  public int getData(){
    return data;
  }
}

public class HashTable {
  private Node[] hashTable = new Node[6000000];

  public boolean addToTable(int data){
    int index = divHash(data);
    Node entry = new Node(data);
    if(hashTable[index] != null){
      if(hashTable[index].next() != null){
        Node n1 = hashTable[index].next();
        int counter = 1;
        while(n1.next() != null){
          n1 = n1.next();
          counter++;
        }
        n1.setNext(entry);
        return false;
      } else {
        hashTable[index].setNext(entry);
        return false;
      }
    } else {
      hashTable[index] = entry;
      return true;
    }
  }

  public double getLastFaktor(int antall){
    return (double)antall / (double)hashTable.length;
  }

  public int lookUp(int num){
      int index = divHash(num);
      if(hashTable[index].getData().equals(num)){
        return index;
      } else if(hashTable[index].next().getData().equals(num)){
        return index;
      } else {
        Node n1 = hashTable[index].next();
        while(n1.getData().equals(num)){
          if(n1.next() != null){
            n1 = n1.next();
          } else {
            return -1;
          }
        }
        return index;
      }
  }
  
 // Finds hash by adding together the ascii values of the characters in the string, then dividing by 65
  public int hash1(int str){
    return -1;
  }

  public int hash2(int str){
    return -1;
  }

  public static void main(String[] args) throws IOException{
    HashTable table = new HashTable();

  }
}
