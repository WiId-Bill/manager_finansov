package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Statistics {

    public static Map<String, String> allCategory = new HashMap<>();
    public static Map<String, Integer> sumCategory = new HashMap<>();
    public static Map<String, Integer> histore = new HashMap<>();
    public String title;

    public String date;
    public int sum;


    public Statistics(String title, String date, int sum) {
        this.title = title;
        this.date = date;
        this.sum = sum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Object max() {
        Map<String, MaxCategory> request = new HashMap<>();
        MaxCategory maxCategory = (MaxCategory) categotyTotalSum();
        request.put("maxCategory", maxCategory);
        return request;
    }

    public  Object categotyTotalSum() {

        String name = getTitle();
        int sum = getSum();
        if (allCategory.containsKey(name)) {

            String category = allCategory.get(name);

            if (histore.containsKey(category)) {

                sum += histore.get(category);
                histore.put(category, sum);

            } else {
                histore.put(category, sum);
            }
        } else if (histore.containsKey("другое")) {

            sum += histore.get("другое");
            histore.put("другое", sum);
        } else {
            histore.put("другое", sum);
        }

        //       }
        String category = histore.keySet().stream()
                .max(Comparator.comparing(histore::get))
                .orElse(null);
        return new MaxCategory(category, histore.get(category));

    }

    public static void loadTsvFile(String file) {
        try (BufferedReader TSVFile =
                     new BufferedReader(new FileReader(file))) {

            while (TSVFile.ready()) {
                String row = TSVFile.readLine();
                String[] str = row.split("\t");
                allCategory.put(str[0], str[1]);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String toString() {
        return "title: " + title + " date: " + date + " sum: " + sum;

    }



}
