/*
TJHSST Junior 5
ACSL All-Stars 2012-2013
ACSL Gold
*/

import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
import static java.lang.System.out;

class Gold {
   public static DecimalFormat df = new DecimalFormat("0.00");
   public static void main(String[] args) throws IOException {
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      Map<String,Double> carat = new TreeMap<String,Double>();
      carat.put("24",1.0);
      carat.put("22",0.917);
      carat.put("18",0.75);
      carat.put("14",0.583);
      carat.put("12",0.5);
      carat.put("8",0.333);
      carat.put("6",0.25);
      carat.put("1",0.04167);
      Map<String,Double> troy = new TreeMap<String,Double>();
      troy.put("GN",0.0021);
      troy.put("GM",0.0321);
      troy.put("PN",0.05);
      troy.put("TR",1.0);
      troy.put("ON",0.9115);
      double dpert = Double.parseDouble(f.readLine());
      for(int i=0;i<5;i++){
         StringTokenizer st = new StringTokenizer(f.readLine().replace(" ",""),",");
         double am = Double.parseDouble(st.nextToken());
         String s = st.nextToken();
         String car = st.nextToken();
         double buy = 1-Double.parseDouble(st.nextToken())/100;
         double finVal = (dpert*am)*troy.get(s);
         finVal = finVal*buy*carat.get(car);
         finVal = Math.round(finVal*100)/100.0;
         out.println(df.format(finVal));
      }
      System.exit(0);
   }
}