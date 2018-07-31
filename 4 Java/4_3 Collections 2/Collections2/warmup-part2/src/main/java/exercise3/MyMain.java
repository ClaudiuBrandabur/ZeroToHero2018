package exercise3;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyMain {

    public static void main(String[] args){

        HashMap<MyStudent1,BigDecimal> s1 = new HashMap<>();
        HashMap<MyStudent2,BigDecimal> s2 = new HashMap<>();
        HashMap<MyStudent3,BigDecimal> s3 = new HashMap<>();
        HashMap<MyStudent4,BigDecimal> s4 = new HashMap<>();

        s1.put(new MyStudent1("Madalina","Barburescu"), BigDecimal.valueOf(20));
        s1.put(new MyStudent1("Tudor","Stadiu"), BigDecimal.valueOf(21));
        s1.put(new MyStudent1("Alexandru","Ionita"), BigDecimal.valueOf(19));
        s1.put(new MyStudent1("Vlad","Vitan"), BigDecimal.valueOf(22));
        s1.put(new MyStudent1("Teodor","Apostol"), BigDecimal.valueOf(23));

        s2.put(new MyStudent2("Madalina","Barburescu"), BigDecimal.valueOf(20));
        s2.put(new MyStudent2("Tudor","Stadiu"), BigDecimal.valueOf(21));
        s2.put(new MyStudent2("Alexandru","Ionita"), BigDecimal.valueOf(19));
        s2.put(new MyStudent2("Vlad","Vitan"), BigDecimal.valueOf(22));
        s2.put(new MyStudent2("Teodor","Apostol"), BigDecimal.valueOf(23));

        s3.put(new MyStudent3("Madalina","Barburescu"), BigDecimal.valueOf(20));
        s3.put(new MyStudent3("Tudor","Stadiu"), BigDecimal.valueOf(21));
        s3.put(new MyStudent3("Alexandru","Ionita"), BigDecimal.valueOf(19));
        s3.put(new MyStudent3("Vlad","Vitan"), BigDecimal.valueOf(22));
        s3.put(new MyStudent3("Teodor","Apostol"), BigDecimal.valueOf(23));

        s4.put(new MyStudent4("Madalina","Barburescu"), BigDecimal.valueOf(20));
        s4.put(new MyStudent4("Tudor","Stadiu"), BigDecimal.valueOf(21));
        s4.put(new MyStudent4("Alexandru","Ionita"), BigDecimal.valueOf(19));
        s4.put(new MyStudent4("Vlad","Vitan"), BigDecimal.valueOf(22));
        s4.put(new MyStudent4("Teodor","Apostol"), BigDecimal.valueOf(23));

        Iterator it1 = s1.entrySet().iterator();
        Iterator it2 = s2.entrySet().iterator();
        Iterator it3 = s3.entrySet().iterator();
        Iterator it4 = s4.entrySet().iterator();

        while (it1.hasNext()){
            Map.Entry<MyStudent1,BigDecimal> e1 = (Map.Entry<MyStudent1, BigDecimal>)it1.next();
            System.out.println("First:" + e1.getKey().getFirstName() +" Last:"+ e1.getKey().getLastName()+" Age:"+e1.getValue());
        }

        while (it2.hasNext()){
            Map.Entry <MyStudent2,BigDecimal> e2 = (Map.Entry<MyStudent2, BigDecimal>) it2.next();
            System.out.println("First:" + e2.getKey().getFirstName() +" Last:"+ e2.getKey().getLastName()+" Age:"+e2.getValue());
        }

        while (it3.hasNext()){
            Map.Entry <MyStudent3,BigDecimal> e3 = (Map.Entry<MyStudent3, BigDecimal>) it3.next();
            System.out.println("First:" + e3.getKey().getFirstName() +" Last:"+ e3.getKey().getLastName()+" Age:"+e3.getValue());
        }

        while (it4.hasNext()){
            Map.Entry <MyStudent4,BigDecimal> e4 = (Map.Entry<MyStudent4, BigDecimal>) it4.next();
            System.out.println("First:" + e4.getKey().getFirstName() +" Last:"+ e4.getKey().getLastName()+" Age:"+e4.getValue());
        }

    }
}
