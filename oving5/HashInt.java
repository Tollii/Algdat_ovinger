import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class HashInt {

    private Integer[] list;
    private int collisions = 0;

    public HashInt(int size) {
        list = new Integer[size];
    }

    public int addToTable(int key) {
        int m = list.length;
        int h1 = find_h1(key, m);
        int h2 = find_h2(key, m);
        for (int i = 0; i < m; i++) {
            int j = probe(h1, h2, i, m);
            if (list[j] == null) {
                list[j] = key;
                return j;
            }
            collisions++;
        }
        return -1;
    }

    private int probe(int h1, int h2, int i, int m) {
        return (h1 + i * h2) % m;

    }

    private int find_h1(int k, int m) {
        return k % m;
    }

    private int find_h2(int k, int m) {
        return k % (m - 1) + 1;
    }

    public int countUsed() {
        int sum = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                sum++;
            }
        }
        return sum;
    }

    public int getCollisions() {
        return collisions;
    }

    public static void main(String[] args) {
      int size = 6000011;
      HashInt hs = new HashInt(size);
      Random rnd = new Random();
      int randNumSize = 5000000;
      int[] randomArr = new int[randNumSize];
      for (int i = 0; i < randomArr.length; i++) {
          randomArr[i] = rnd.nextInt(size * 100);
      }

      Date start = new Date();
      int runder = 0;
      double tid;
      Date slutt;
      do {
          for (int i = 0; i < randNumSize; i++) {
              hs.addToTable(randomArr[i]);
          }
          slutt = new Date();
          runder++;
      } while (slutt.getTime() - start.getTime() < 1000);
      tid = (double)
              (slutt.getTime() - start.getTime()) / runder;
      System.out.println("---Custom Hash---");
      System.out.println("Millisekund pr. runde:" +  tid);

      int amountElements = hs.countUsed();
      int collisions = hs.getCollisions();
      double avgCollision = (double) collisions / randNumSize;

      System.out.println("\tAntall brukte plasser: " + amountElements);
      System.out.println("\tLastefaktor: " + (double) amountElements / size);
      System.out.println("\tAntall kollisjoner: " + collisions);
      System.out.println("\tGjennomsnittlig antall kollisjoner: " + avgCollision);

      System.out.println("\n---Hashmap---");

      HashMap<Integer,Integer> hmap = new HashMap<Integer,Integer>();

      start = new Date();
      runder = 0;
      do {
          for (int i = 0; i < randNumSize; i++) {
              hmap.put(randomArr[i],randomArr[i]);
          }
          slutt = new Date();
          runder++;
      } while (slutt.getTime() - start.getTime() < 1000);
      tid = (double)
              (slutt.getTime() - start.getTime()) / runder;
      System.out.println("Millisekund pr. runde:" + tid);
    }
}
