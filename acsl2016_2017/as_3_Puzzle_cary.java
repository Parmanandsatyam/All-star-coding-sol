import java.io.*;
import java.util.*;

public class Puzzle {

  static final int T = 10;

  static BufferedReader in;
  static PrintWriter out;

  static int W, H;
  static boolean[][] B;
  static String[] P = new String[10];
  static boolean[] used;

  static int area(String s) {
    int w = s.charAt(0) - '0';
    int h = s.charAt(1) - '0';
    int A;

    if (s.length() == 2) {
      A = w * h;
    } else {
      int wv = s.charAt(2) - '0';
      A = (wv * h) + (w - wv);
    }

    return (1000 * A + w);
  }

  static boolean stamp(int x, int y, boolean checking, boolean setTo, String s) {
    int w = s.charAt(0) - '0';
    int h = s.charAt(1) - '0';

    if (x + w > W || y + h > H) return false;

    if (s.length() == 2) {
      for (int cx = x; cx < x + w; cx++) {
        for (int cy = y; cy < y + h; cy++) {
          if (checking) {
            if (B[cx][cy]) return false;
          } else {
            B[cx][cy] = setTo;
          }
        }
      }
    } else {
      int wv = s.charAt(2) - '0';
      for (int cy = y; cy < y + h; cy++) {
        for (int cx = x; cx < x + (cy == y ? w : wv); cx++) {
          if (checking) {
            if (B[cx][cy]) return false;
          } else {
            B[cx][cy] = setTo;
          }
        }
      }
    }

    return true;
  }

  static void init(String s) {
    StringTokenizer st = new StringTokenizer(s.replace(" ", ""), ",");

    for (int i = 0; i < 10; i++)
      P[i] = st.nextToken();

    Arrays.sort(P, (String a, String b) -> {
      int c = area(a);
      int d = area(b);
      if (c < d) return 1;
      if (c > d) return -1;
      return 0;
    });
  }

  static boolean recur(int t, int d) {
    boolean done = true;
    for (int x = 0; x < W; x++) {
      for (int y = 0; y < H; y++) {
        done &= B[x][y];
      }
    }
    if (done) {
      int u = 0;
      for (int i = 0; i < 10; i++) u += used[i] ? 1 : 0;
      return u > 1;
    }

    for (int i = d; i < 10; i++) {
      if (used[i] || P[i].length() > 2 && t <= 5) continue;
      boolean found = false;
      for (int y = 0; y < H && !found; y++){
        for (int x = 0; x < W; x++) {
          if (!found && stamp(x, y, true, false, P[i])){
            found = true;
            used[i] = true;
            stamp(x, y, false, true, P[i]);
            if (recur(t, i + 1)) return true;
            used[i] = false;
            stamp(x, y, false, false, P[i]);
          }
        }
      }
    }

    return false;
  }

  public static void main(String[] args) throws IOException {
    in = new BufferedReader(new FileReader("puzzle.in"));
    out = new PrintWriter(new BufferedWriter(new FileWriter("puzzle.out")));
    StringTokenizer st;

    init(in.readLine());
    for (int t = 0; t < T; t++) {
      try {
        st = new StringTokenizer(in.readLine().replace(" ", ""), ",");
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        used = new boolean[10];
        B = new boolean[W][H];

        boolean b = recur(t + 1, 0);

        String res = "";
        if (!b) {
          res = "NONE";
        } else {
          for (int i = 0; i < 10; i++)
            if (used[i]) res += ", " + P[i];
          res = res.substring(2);
        }

        out.printf("%d. %s%n", t + 1, res);
      } catch (Exception e) { out.printf("%d. whoops%n", t + 1); }
    }

    out.close();
  }
}
