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

    Iterator<Exercise3> ita = mapa.keySet().iterator();
    Iterator<Exercise3b> itb = mapb.keySet().iterator();
    Iterator<Exercise3c> itc = mapc.keySet().iterator();
    Iterator<Exercise3d> itd = mapd.keySet().iterator();
}
