import java.io.IOException;
import java.util.Random;

public class HashInt {
  private Integer[] table = new Integer[6000011];

  public int addToTable(int data){
    int index = hash1(data);
    for(int i = 0; i < table.length; i++){
      int j = probe(index, hash2(data), i);
      if(table[j] == null){
        table[j] = data;
        return j;
      }
    }
    return -1;
  }

  public double getLastFaktor(int antall){
    return (double)antall / (double)table.length;
  }

 // Finds hash by adding together the ascii values of the characters in the string, then dividing by 65
  public int hash1(int num){
    return num % table.length;
  }

  public int hash2(int num){
    return num % (table.length - 1) + 1;
  }

  public int probe(int data, int i, int m){
    return (hash1(data) + i * hash2(data)) % m;
  }

  public static void main(String[] args) throws IOException{
    HashTable table = new HashTable();
    Random random = new Random();
    int[] numbers = new int[5000000];
    for(int i = 0; i < 5000000; i++){
      numbers[i] = random.nextInt(60000000);
    }

    for(int i = 0; i < table.length; i++){
      
    }






  }
}
