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

  public void addToTable(String data){
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
      } else {
        hashTable[index].setNext(entry);
      }
    } else {
      hashTable[index] = entry;
    }
  }

  public int lookUp(String navn){
    return -1;
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
    String str1 = "abcd";
    String str2 = "cbda";
    String str3 = "bdac";

    FileReader fr = new FileReader("navn.txt");
    BufferedReader reader = new BufferedReader(fr);
    String read = "";

    while((read = reader.readLine()) != null){
      table.addToTable(read);
    }


  }


}
