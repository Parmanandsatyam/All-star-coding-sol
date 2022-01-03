import java.io.*;
import java.util.*;

public class ascending {

  static final int T = 10;

  static BufferedReader in;
  static PrintWriter out;
  static boolean [] used;
  static int [] inputs;
  static int length;

  public static void main(String[] args) throws IOException {
    in = new BufferedReader(new FileReader("ascending.in"));
    out = new PrintWriter(new BufferedWriter(new FileWriter("ascending.out")));

    for (int t = 0; t < T; t++) {
      try {
        char[] input = in.readLine().trim().toCharArray();

        length = input[0] - '0';

        inputs = new int[input.length - 1];
        used = new boolean[inputs.length];

        for (int i = 0; i < input.length - 1; i++) {
          inputs[i] = input[i + 1] - '0';
        }

        Arrays.sort(inputs);

        int R = -1;

        String res = "";

        while ((R = r(R, 0, 0)) > -1) res += " " + R;
        res = res.substring(1);

        out.printf("%d. %s%n", t + 1, res);
      } catch (Exception e) { out.printf("%d. whoops%n", t + 1); }
    }

    out.close();
  }

  public static int r(int last, int cur, int depth){
    if (depth == length){
      if (cur > last) return cur;
      else return -1;
    } else {
      for (int i = 0; i < inputs.length; i++){
        if (!used[i]){
          if (depth == 0 && inputs[i] == 0) continue;
          used[i] = true;
          int newCur = 10 * cur + inputs[i];
          int r = r(last, newCur, depth + 1);
          if (r > -1) return r;
          used[i] = false;
        }
      }
    }
    return -1;
  }
}
