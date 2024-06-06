import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String,ArrayList<ArrayList>> map1 = new HashMap<>();
        ArrayList<ArrayList> lista1 = new ArrayList<>();
        ArrayList<ArrayList> lista2 = new ArrayList<>();
        String key = "apple";
        ArrayList l1 = new ArrayList();
        l1.add("green");
        l1.add("yellow");
        lista1.add(l1);
        ArrayList l2 = new ArrayList();
        l2.add("Big");

        lista1.add(l2);

        map1.put(key,lista1);

         key = "blueberry";
        ArrayList l3 = new ArrayList();
        l3.add("x");
        l3.add("y");
        lista2.add(l3);
        ArrayList l4 = new ArrayList();
        l4.add("Black");
        lista2.add(l3);
        lista2.add(l4);
        map1.put(key,lista2);
        ArrayList tempList = new ArrayList();
        System.out.println(map1.get("blueberry").toString());
        System.out.println(map1.get("apple").toString());
        ArrayList lista3 =lista1;
        lista1.add(l4);

        map1.put("apple",lista3);
        System.out.println(map1.get("apple").toString());
        lista2 = tempList;
        lista2.add(l4);
        map1.put(key,lista2);
        System.out.println(map1.get("blueberry").toString());
    }
}
