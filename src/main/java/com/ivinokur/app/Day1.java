package com.ivinokur.app;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.io.Resources;

public class Day1 {
    private static List<Integer> leftList;
    private static List<Integer> rightList;
    
    public Day1() {
        try {
            System.out.println("\n>>>>>> Day 1: ");
			List<Integer[]> inputData = getInputData();
            leftList = inputData.stream().map(i -> i[0]).collect(Collectors.toList());
            rightList = inputData.stream().map(i -> i[1]).collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public static void part1() {
        leftList.sort(Comparator.naturalOrder());
        rightList.sort(Comparator.naturalOrder());
        int res = 0;
        for (int i = 0; i < leftList.size(); i++) {
            res += Math.abs(leftList.get(i) - rightList.get(i));
        }
        System.out.println(">>>>>> part 1 " + res);
    }

    public static void part2() {
        int res = 0;
        for (int i = 0; i < leftList.size(); i++) {
            Integer element = leftList.get(i);
            int frequency = Collections.frequency(rightList, element);
            res += frequency * element;
        }
        System.out.println(">>>>>> part 2 " + res);
    }

    private static List<Integer[]> getInputData() throws IOException {
        String text = Resources.toString(Resources.getResource("day-1.txt"), UTF_8);
        String[] split = text.split("\n");
        return Arrays.asList(split)
            .stream()
            .map(item -> {
                return new Integer[]{Integer.valueOf(item.substring(0, 5)),
                    Integer.valueOf(item.substring(8, 13))};
            })
            .collect(Collectors.toList());
    }
}
