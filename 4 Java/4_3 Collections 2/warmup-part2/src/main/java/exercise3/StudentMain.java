package exercise3;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StudentMain {
    Map<Exercise3, BigDecimal> mapa = new HashMap<Exercise3, BigDecimal>();
    Map<Exercise3b, BigDecimal> mapb = new HashMap<Exercise3b, BigDecimal>();
    Map<Exercise3c, BigDecimal> mapc = new HashMap<Exercise3c, BigDecimal>();
    Map<Exercise3d, BigDecimal> mapd = new HashMap<Exercise3d, BigDecimal>();

    public void main() {
        Iterator<Exercise3> ita = mapa.keySet().iterator();
        Iterator<Exercise3b> itb = mapb.keySet().iterator();
        Iterator<Exercise3c> itc = mapc.keySet().iterator();
        Iterator<Exercise3d> itd = mapd.keySet().iterator();

        System.out.println("Exercise 3 a");
        while (ita.hasNext()) {
            Exercise3 x = ita.next();
            System.out.println(x + " " + mapa.get(ita));
        }

        System.out.println("Exercise 3 b");
        while (ita.hasNext()) {
            Exercise3b x = itb.next();
            System.out.println(x + " " + mapb.get(itb));
        }

        System.out.println("Exercise 3 c");
        while (itc.hasNext()) {
            Exercise3c x = itc.next();
            System.out.println(x + " " + mapc.get(itc));
        }

        System.out.println("Exercise 3 d");
        while (itd.hasNext()) {
            Exercise3d x = itd.next();
            System.out.println(x + " " + mapd.get(itd));
        }
    }
}
