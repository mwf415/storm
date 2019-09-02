package com.youyicn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        String a = "1301, 1317, 1356, 1369, 1368, 1433, 1449, 1454, 1407, 1419, 1386, 1501";

        getIds(a);


    }

    public static List<Long> getIds(String string) {
        String[] splitRoleIds = string.split(",");
        List<String> strings = Arrays.asList(splitRoleIds);

        for (String s : strings) {
            System.out.println(s.charAt(0));
            System.out.println(s.length());
        }

        for (String s : strings) {
            s=s.trim();
            System.out.println(s.charAt(0));
            System.out.println(s.length());
        }


        return null;
    }
}
