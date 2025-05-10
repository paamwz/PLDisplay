package application.Methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

public class GraphDisp {

    private StackedBarChart<String, Number> chart;

    public GraphDisp(StackedBarChart<String, Number> chart) {
        this.chart = chart;
    }

    public void updateChart(double[] inputNumberList, TextField corpName) {
        final String income = "利益";
        final String expenses = "損失";
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        // グラフをクリア
        chart.getData().clear();
        List<XYChart.Series<String, Number>> seriesList = new ArrayList<>();

        String[] labels = {
        		"売上高", "営業外収益", "特別利益",
        		"売上原価", "販管費", "営業外費用",
        		"特別損失", "法人税等", "当初純利益"
        };
        // 9つのシリーズを作成
        for (int i = 0; i < labels.length; i++) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("series" + String.valueOf(i));
            seriesList.add(series);
        }

        // 支出と収入のデータをセット
        Double[] expensesList = { 0.0, 0.0, 0.0, inputNumberList[3], inputNumberList[4], inputNumberList[5], inputNumberList[6], inputNumberList[7], inputNumberList[8]};
        Double[] incomeList = {inputNumberList[0],inputNumberList[1], inputNumberList[2], 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

        // タイトルと軸ラベルの設定
        chart.setTitle(corpName.getText()+"-PL");
        xAxis.setLabel("Category");
        xAxis.setCategories(FXCollections.observableArrayList(Arrays.asList(expenses, income)));
        yAxis.setLabel("Value");

        // ラベル

        // シリーズにデータを追加
        for (int i = 0; i < seriesList.size(); i++) {
            XYChart.Series<String, Number> series = seriesList.get(i);
            series.setName(labels[i]);
            series.getData().add(new XYChart.Data<>(expenses, expensesList[i]));
            series.getData().add(new XYChart.Data<>(income, incomeList[i]));
        }

        // グラフに追加（逆順で追加）
        for (int i = seriesList.size() - 1; i >= 0; i--) {
            chart.getData().add(seriesList.get(i));
        }
    }
}