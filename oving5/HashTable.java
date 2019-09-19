import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Node {
  private Node next;
  private String data;

  public Node(String data){
    this.data = data;
  }

  public Node next(){
    return next;
  }

  public void setNext(Node next){
    this.next = next;
  }

  public String getData(){
    return data;
  }
}

public class HashTable {
  private Node[] hashTable = new Node[193];

  // Adds a new element to the hash with linked lists for collision handling
  public boolean addToTable(String data){
    int index = divHash(data);
    Node entry = new Node(data);
    if(hashTable[index] != null){
      System.out.println("Collision! at #" + index + " med navn " + hashTable[index].getData() + " og " + entry.getData());
      if(hashTable[index].next() != null){
        System.out.println("Collision! at #" + index + " med navn " + hashTable[index].next().getData() + " og " + entry.getData());
        Node n1 = hashTable[index].next();
        int counter = 1;
        while(n1.next() != null){
          n1 = n1.next();
          counter++;
        }
        System.out.println("Index #" + index + " linked list length: " + counter);
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
    return (double) antall / hashTable.length;
  }

  // Gets the hash the given name is
  public int lookUp(String navn){
      int index = divHash(navn);
      if(hashTable[index].getData().equals(navn)){
        return index;
      } else if(hashTable[index].next().getData().equals(navn)){
        System.out.println("\tKollisjon på søk mellom navnene " + navn + " og " + hashTable[index].getData());
        return index;
      } else {
        Node n1 = hashTable[index].next();
        while(n1.getData().equals(navn)){
          System.out.println("\tKollisjon på søk mellom navnene " + navn + " og " + n1.getData());
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
  public int divHash(String str){
    int hash = 0;
    char[] letters  = str.toCharArray();
    for(int i = 0; i < letters.length; i++){
      hash += ((int) letters[i]) * i - ((int) 'a');
    }
    return hash % hashTable.length;
  }

  public static void main(String[] args) throws IOException{
    HashTable table = new HashTable();
    int collisions = 0;
    int numberOfLines = 0;
    FileReader fr = new FileReader("navn.txt");
    BufferedReader reader = new BufferedReader(fr);
    String read = "";

    while((read = reader.readLine()) != null){
      if(!table.addToTable(read)){
        collisions++;
      }
      numberOfLines++;
    }
    double avg = (double) collisions / numberOfLines;
    System.out.println("\n");
    System.out.println("\tNavnet ligger på #" + table.lookUp("Younger,Eric"));
    System.out.println("\tAntall kollisjoner: " + collisions);
    System.out.println("\tGjennomsnitt kollisjon per person: " + avg);
    System.out.println("\tLastfaktor:" + table.getLastFaktor(numberOfLines));
  }
}
