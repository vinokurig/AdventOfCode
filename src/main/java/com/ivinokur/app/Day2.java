package com.ivinokur.app;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.io.Resources;

public class Day2 {
    private static List<int[]> reportsArray;
    
    public Day2() {
        System.out.println("\n>>>>>> Day 2: ");
        try {
			reportsArray = getInputData();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void part1() {
        Long number = reportsArray.stream().filter(report -> allIncreasingOrDecreasing(report) && differLessThenThree(report)).count();
        System.out.println(">>>>>> part 1: " + number);
    }

    public static void part2() {
        Long number = reportsArray.stream().filter(report -> tolerateASingleBadLevel(report)).count();
        System.out.println(">>>>>> part 2: " + number);
    }

    private static boolean tolerateASingleBadLevel(int[] report) {
        if (allIncreasingOrDecreasing(report) && differLessThenThree(report)) {
            return true;
        }
        int index = 0;
        for (int i = 0; i < report.length; i++) {
            List<Integer> list = IntStream.of(report).boxed().collect(Collectors.toList());
            list.remove(i);
            int[] array = list.stream().mapToInt(Integer::intValue).toArray();
            if (allIncreasingOrDecreasing(array) && differLessThenThree(array)) {
                return true;
            }
        }
        return false;
    }

    private static boolean allIncreasingOrDecreasing(int[] report) {
        for (int i = 0; i < report.length -1; i++) {
            int level = report[i];
            int nextLevel = report[i +1];
            if ((isIncrease(report) && nextLevel <= level) || (!isIncrease(report) && nextLevel >= level)) {
                return false;
            }
        }
        return true;
    }

    private static boolean differLessThenThree(int[] report) {
        for (int i = 0; i < report.length -1; i++) {
            int level = report[i];
            int nextLevel = report[i +1];
            if ((isIncrease(report) && nextLevel - level > 3)|| (!isIncrease(report) && level - nextLevel > 3)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isIncrease(int[] report) {
        return report[0] < report[1];
    }

    private static List<int[]> getInputData() throws IOException {
        String text = Resources.toString(Resources.getResource("day-2.txt"), UTF_8);
        String[] split = text.split("\n");
        return Arrays.asList(split)
            .stream()
            .map(i -> {
                List<String> ar = Arrays.asList(i.split(" "));
                return ar.stream()
                    .mapToInt(it -> Integer.valueOf(it))
                    .toArray();
            })
            .collect(Collectors.toList());
    }
}