package com.task1.app;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.io.Resources;

public class Day2 {
    private static List<Integer[]> inputData;
    
    public Day2() {
        try {
			inputData = getInputData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public static void part1() {
        inputData.forEach(i -> System.out.println(">>>>> " + i[0]));
    }

    private static List<Integer[]> getInputData() throws IOException {
        String text = Resources.toString(Resources.getResource("day-2.txt"), UTF_8);
        String[] split = text.split("\n");
        return Arrays.asList(split)
            .stream()
            .map(i -> {
                List<String> ar = Arrays.asList(i.split(" "));
                return ar.stream()
                    .map(it -> Integer.valueOf(it)).collect(Collectors.toList())
                    .toArray(new Integer[ar.size()]);
            })
            .collect(Collectors.toList());
    }
}