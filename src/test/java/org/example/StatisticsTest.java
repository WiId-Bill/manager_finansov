package org.example;

import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.example.Statistics.histore;

class StatisticsTest {
    private static String[] jsonStrings;

    @Test
    public void categotyTotalSum() {
        Statistics.loadTsvFile("categories.tsv");
        Statistics statistics = null;

        jsonStrings = new String[]{
                "{\"title\": \"сухарики\", \"date\": \"2022.02.08\", \"sum\": 300}",
                "{\"title\": \"мыло\", \"date\": \"2022.02.08\", \"sum\": 200}",
                "{\"title\": \"акции\", \"date\": \"2022.02.08\", \"sum\": 500}",
                "{\"title\": \"булка\", \"date\": \"2022.02.08\", \"sum\": 300}",
                "{\"title\": \"сухарики\", \"date\": \"2021.02.08\", \"sum\": 300}"
        };

        for (String jsonString : jsonStrings) {
            Gson gson = new Gson();
            statistics = gson.fromJson(jsonString, Statistics.class);
            statistics.categotyTotalSum();
        }

        int actualSum = histore.get("еда");

        int expectedSum = 900;
        Assertions.assertEquals(actualSum, expectedSum);
//
    }

    @Test
    void loadTsvFile() {
        Statistics.loadTsvFile("categories.tsv");
        int actual = Statistics.allCategory.size();
        int expected = 8;

        String actualKey = "булка";
        String actualValue = "быт";

        Assertions.assertEquals(expected, actual);
        Assertions.assertTrue(Statistics.allCategory.containsKey(actualKey));
        Assertions.assertTrue(Statistics.allCategory.containsValue(actualValue));

    }
}