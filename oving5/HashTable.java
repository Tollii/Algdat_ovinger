class Node {
  private Node next;
  private String data;

  public Node(String data){
    this.data = data;
  }

}

public class HashTable {
  private Node[] hashTable = new Node[100];

  public void addToTable(String data){
    int index = divHash(data);
    Node entry = new Node(data);

    if(hashTable[index] != null){
      if(hashTable[index].next == null){
          hashTable[index].next = entry;
      } else {
        Node n1 = hashTable[index];

        while(n1.next != null){
          n1 = n1.next;
        }
        n1.next = entry;
      }
    } else {
      hashTable[index] = entry;
    }
    System.out.println("Index #" + index);
  }

  public int lookUp(String navn){

  }

 // Finds hash by adding together the ascii values of the characters in the string, then dividing by 65
  private int divHash(String str){
    int hash = 0;
    char[] letters  = str.toCharArray();
    for(int i = 0; i < letters.length; i++){
      hash += ((int) letters[i]) * i;
    }
    return hash % hashTable.length;
  }

  public static void main(String[] args){
    HashTable table = new HashTable();
    String str1 = "abcd";
    String str2 = "cbda";
    String str3 = "bdac";

    table.addToTable(str1);
    table.addToTable(str2);
    table.addToTable(str3);
  }


}
