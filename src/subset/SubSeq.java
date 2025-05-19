package subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSeq {

    public static void main(String[] args) {
//        subSeq("abc");

        List<String> subSeqs = subSeqAscii("abc");
        for (String subSeq : subSeqs) {
            System.out.println(subSeq);
        }
    }


    public static void subSeq(String str) {
        subSeq("", str);
    }

    private static void subSeq(String p, String up) {
        if (up.isEmpty()) {
            System.out.println(p);
            return;
        }

        char ch = up.charAt(0);

        subSeq(p + ch, up.substring(1));
        subSeq(p, up.substring(1));
    }

    public static List<String> subSeq2(String str) {
        return subSeq2("", str);
    }

    private static List<String> subSeq2(String p, String up) {
        List<String> result = new ArrayList<>();

        if (up.isEmpty()) {
            result.add(p);
            return result;
        }

        char ch = up.charAt(0);

        result.addAll(subSeq2(p + ch, up.substring(1)));
        result.addAll(subSeq2(p, up.substring(1)));

        return result;
    }

    public static List<String> subSeqAscii(String str) {
        return subSeqAscii("", str);
    }

    private static List<String> subSeqAscii(String p, String up) {
        List<String> result = new ArrayList<>();
        if (up.isEmpty()) {
            result.add(p);
            return result;
        }

        char ch = up.charAt(0);

        result.addAll(subSeqAscii(p + ch, up.substring(1)));
        result.addAll(subSeqAscii(p + (ch + 0), up.substring(1)));
        result.addAll(subSeqAscii(p, up.substring(1)));

        return result;
    }

}
