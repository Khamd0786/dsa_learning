package backtracking_practice.maze;

import java.util.ArrayList;
import java.util.HashMap;

public class Path {

    public static void main(String[] args) {
        ArrayList<String> list = pathWithAllDirections("", 2, 2);
        for (String s : list) {
            System.out.println(s);
        }
    }


    private static ArrayList<String> printAllPossiblePath(String p, int r, int c, int mr, int mc, HashMap<String, ArrayList<String>> map) {
        ArrayList<String> list = new ArrayList<>();

        if (r == mr && c == mc) {
            list.add(p);
            return list;
        }

//        if (map.containsKey(r + "," + c)) {
//            return map.get(r + "," + c);
//        }

        if (r < mr || c < mc) {
            return list;
        }

        list.addAll(printAllPossiblePath(p + 'D', r - 1, c, mr, mc, map));
        list.addAll(printAllPossiblePath(p + 'R', r, c - 1, mr, mc, map));

        map.put(r + "," + c, list);
        return list;
    }


    private static ArrayList<String> pathWithAllDirections(String p, int r, int c) {
        ArrayList<String> stringArrayList = new ArrayList<>();
        if (r == 1 && c == 1) {
            stringArrayList.add(p);
            return stringArrayList;
        }

        if (r < 1 || c < 1 || r > 3 || c > 3) {
            return stringArrayList;
        }

        stringArrayList.addAll(pathWithAllDirections(p + "L,", r - 1, c)); //Left
        stringArrayList.addAll(pathWithAllDirections(p + "U,", r, c - 1)); //Up
        stringArrayList.addAll(pathWithAllDirections(p + "R,", r + 1, c)); //Right
        stringArrayList.addAll(pathWithAllDirections(p + "D,", r, c + 1)); //Down

        //Diagonals
        stringArrayList.addAll(pathWithAllDirections(p + "LU,", r - 1, c - 1));
        stringArrayList.addAll(pathWithAllDirections(p + "RU,", r + 1, c - 1));
        stringArrayList.addAll(pathWithAllDirections(p + "LD,", r - 1, c + 1));
        stringArrayList.addAll(pathWithAllDirections(p + "RD,", r + 1, c + 1));

        return stringArrayList;
    }
}
