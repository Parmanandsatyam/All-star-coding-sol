import java.io.*;
import java.util.*;

public class Numbers36 {

  static final int T = 2;
  static final int B = 36;

  static BufferedReader in;
  static PrintWriter out;

  static int N, K;
  static long[] inputs;
  static boolean[] used;
  static ArrayList<Long> P;
  static ArrayList<Long> Ps;

  static void init(String s) {
    StringTokenizer st = new StringTokenizer(s.toUpperCase().replace(" ", ""), ",");
    String data = st.nextToken();

    N = data.length();
    inputs = new long[N];
    used = new boolean[N];
    P = new ArrayList<>();
    Ps = new ArrayList<>();

    for (int i = 0; i < N; i++)
      inputs[i] = Long.valueOf(s.substring(i, i + 1), B);

    K = Integer.parseInt(st.nextToken());
  }

  static void gen(int d, long l) {
    if (d == N) { P.add(l); return; }
    for (int i = 0; i < N; i++) {
      if (used[i]) continue; //  || d == 0 && inputs[i] == 0
      used[i] = true;
      long newl = B * l + inputs[i];
      gen(d + 1, newl);
      used[i] = false;
    }
  }

  static int cmp(long a, long b, long m2) {
    long c = Math.abs(a * 2 - m2);
    long d = Math.abs(b * 2 - m2);
    if (c < d) return -1;
    if (c > d) return 1;
    return 0;
  }

  public static void main(String[] args) throws IOException {
    in = new BufferedReader(new FileReader("numbers36.in"));
    out = new PrintWriter(new BufferedWriter(new FileWriter("numbers36.out")));
    StringTokenizer st;

    for (int t = 0; t < T; t++) {
      try {
        init(in.readLine());

        gen(0, 0);

        Collections.sort(P);
        for (int i = 0, j = 0; i < P.size(); i = j) {
          while (j < P.size() && P.get(i).equals(P.get(j))) j++;
          Ps.add(P.get(i));
        }

        try { out.printf("%d. %s%n", 5 * t + 1, Long.toString(Ps.get(0), B).toUpperCase()); }
        catch (Exception e) { out.printf("%d. whoops%n", 5 * t + 1); }
        try { out.printf("%d. %s%n", 5 * t + 2, Long.toString(Ps.get(Ps.size() - 1), B).toUpperCase()); }
        catch (Exception e) { out.printf("%d. whoops%n", 5 * t + 2); }
        try { out.printf("%d. %s%n", 5 * t + 3, Long.toString(Ps.get(Ps.size() - 50), B).toUpperCase()); }
        catch (Exception e) { out.printf("%d. whoops%n", 5 * t + 3); }
        try { out.printf("%d. %s%n", 5 * t + 4, Long.toString(Ps.get(K - 1), B).toUpperCase()); }
        catch (Exception e) { out.printf("%d. whoops%n", 5 * t + 4); }
        try {
          String res = "";

          long m2 = Ps.get(0) + Ps.get(Ps.size() - 1);

          int i = 0, j;

          while (i < Ps.size() - 1 && Ps.get(i + 1) * 2 < m2) i++;

          if (i < Ps.size() - 1) j = i + 1; else j = i;

          long c = Math.abs(Ps.get(i) * 2 - m2);
          long d = Math.abs(Ps.get(j) * 2 - m2);

          if (c == d) {
            res = Long.toString(Ps.get(i), B) + ", " + Long.toString(Ps.get(j), B);
          } else {
            res = Long.toString(Ps.get(c < d ? i : j), B);
          }

          out.printf("%d. %s%n", 5 * t + 5, res.toUpperCase());
        } catch (Exception e) { out.printf("%d. whoops%n", 5 * t + 5); }



      } catch (Exception e) { out.printf("(data %d-%d): whoops%n", 5 * t + 1, 5 * t + 5); }
    }

    out.close();
  }
}
