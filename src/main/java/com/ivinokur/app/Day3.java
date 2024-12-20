package com.ivinokur.app;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.io.Resources;

public class Day3 {
    private static String inputData;
    
    public Day3() {
        System.out.println("\n>>>>>> Day 3: ");
        try {
			inputData = getInputData();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void part1() {
        Pattern pattern = Pattern.compile("mul\\((?<value1>[0-9]*),(?<value2>[0-9]*)\\)");
        Matcher matcher = pattern.matcher(inputData);
        int result = 0;
        while (matcher.find()) {
            int value1 = Integer.valueOf(matcher.group("value1"));
            int value2 = Integer.valueOf(matcher.group("value2"));
            result += value1 * value2;
          }
        System.out.println(">>>>>> part 1: " + result);
    }

    public static void part2() {
        int result = 0;
        String[] split = inputData.split("\n");
        for (int i = 0; i < split.length; i++) {
            Pattern pattern = Pattern.compile("(((?<dont>don't)|(?<do>do))\\(\\)((?!mul).)*)?mul\\((?<value1>[0-9]*),(?<value2>[0-9]*)\\)");
            Matcher matcher = pattern.matcher(split[i]);
            boolean mul = true;
            while (matcher.find()) {
                boolean doMul = !isNullOrEmpty(matcher.group("do"));
                boolean dontMul = !isNullOrEmpty(matcher.group("dont"));
                mul = (mul || doMul) && !dontMul;
                int value1 = Integer.valueOf(matcher.group("value1"));
                int value2 = Integer.valueOf(matcher.group("value2"));
                result += mul ? value1 * value2 : 0;
            }
        }
        
        System.out.println(">>>>>> part 2: " + result);
    }

    private static String getInputData() throws IOException {
        return Resources.toString(Resources.getResource("day-3.txt"), UTF_8);
    }
}