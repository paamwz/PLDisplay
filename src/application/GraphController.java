package application;

import java.io.File;
import java.nio.file.Path;

import application.Methods.CreateFolder;
import application.Methods.DispRate;
import application.Methods.GetNumber;
import application.Methods.GraphDisp;
import application.Methods.RateCalc;
import application.Methods.SaveFileName;
import application.Methods.SaveGraph;
import application.Methods.SaveTable;
import application.Methods.TotalCalc;
import application.Methods.WriteContents;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

public class GraphController {
	
	@FXML
	public Label corpNameIndex;
	@FXML
	public TextField corpName;
	
	@FXML 
	public Label salesIndex;
    @FXML
    public TextField sales;
    @FXML
    public Label nonOpeIncomeIndex;
    @FXML
    public TextField nonOpeIncome;
    @FXML
    public Label spProfitIndex;
    @FXML
    public TextField spProfit;
    @FXML
    public Label costOfSalesIndex;
    @FXML
    public TextField costOfSales;
    @FXML
    public Label SGandAIndex;
    @FXML
    public TextField SGandA;
    @FXML
    public Label nonOpeExpensesIndex;
    @FXML
    public TextField nonOpeExpenses;
    @FXML
    public Label extraLossIndex;
    @FXML
    public TextField extraLoss;
    @FXML
    public Label corpTaxEtcIndex;
    @FXML
    public TextField corpTaxEtc;
    @FXML
    public Label netProfitIndex;
    @FXML
    public TextField netProfit;
    
    @FXML
    public Label totalRevenueIndex;
    @FXML
    public Label totalRevenueLabel;
    @FXML
    public Label totalCostIndex;
    @FXML
    public Label totalCostLabel;
    
    
    @FXML
    public Label salesRate;
    @FXML
    public Label nonOpeIncomeRate;
    @FXML
    public Label spProfitRate;
    @FXML
    public Label costOfSalesRate;
    @FXML
    public Label SGandARate;
    @FXML
    public Label nonOpeExpensesRate;
    @FXML
    public Label extraLossRate;
    @FXML
    public Label corpTaxEtcRate;
    @FXML
    public Label netProfitRate;
    
    
    @FXML
    public TextField saveFolderName;
    
    @FXML
    private StackedBarChart<String, Number> controlledGraph;
    
    @FXML
    void onGetButtonClick(ActionEvent event) {
        // TextField 配列を作成
        TextField[] textFieldsIncome = {
        		sales, 
        		nonOpeIncome, 
        		spProfit
        };
        
        TextField[] textFields = {
        		sales, 
        		nonOpeIncome, 
        		spProfit, 
        		costOfSales, 
        		SGandA, 
        		nonOpeExpenses, 
        		extraLoss, 
        		corpTaxEtc, 
        		netProfit
        };
        
        Label[] labelFields = {
        		salesRate,
        		nonOpeIncomeRate,
        		spProfitRate,
        		costOfSalesRate,
        		SGandARate,
        		nonOpeExpensesRate,
        		extraLossRate,
        		corpTaxEtcRate,
        		netProfitRate
        };
        
        // GraphDataHandler を使ってデータを取得
        GetNumber getNumber = new GetNumber();
        double[] inputData = getNumber.getDataFromTextFields(textFields);
        
        // TotalCalcを使ってRevenueとCostの総額をそれぞれ計算
        TotalCalc totalCalc = new TotalCalc();
        double totalRevenue = totalCalc.totalRevenueCalc(inputData, textFieldsIncome);
        double totalCost = totalCalc.totalCostCalc(inputData, textFieldsIncome);
        
        totalRevenueLabel.setText(String.valueOf(totalRevenue));
        totalCostLabel.setText(String.valueOf(totalCost));
        
        
        // ChartDataUpdater を使ってグラフを更新
        GraphDisp graphDisp = new GraphDisp(controlledGraph);
        graphDisp.updateChart(inputData, corpName);
        
        
        // RateCalcを使って割合の計算
        
        RateCalc rateCalc = new RateCalc();
        double[] incomeRates = rateCalc.calcIncomeRate(inputData, textFieldsIncome);
        double[] expensesRates = rateCalc.calcExpensesRate(inputData, textFieldsIncome);
        
        //incomeとexpensesの各割合を計算してラベルに出力(小数第3位を四捨五入)
        DispRate dispRate = new DispRate();
        dispRate.setRateLabel(labelFields, inputData, incomeRates, expensesRates, textFieldsIncome);
    }
    @FXML
    void onSaveButtonClick(ActionEvent event) {
        TextField[] textFields = {
        		sales, 
        		nonOpeIncome, 
        		spProfit, 
        		costOfSales, 
        		SGandA, 
        		nonOpeExpenses, 
        		extraLoss, 
        		corpTaxEtc, 
        		netProfit
        };
    	GetNumber getNumber = new GetNumber();
        double[] inputData = getNumber.getDataFromTextFields(textFields);
    	
    	String[] corpLabels = {corpNameIndex.getText(), corpName.getText()};
    	String[] titleIndex = {
    			"項目", 
    			salesIndex.getText(), 
    			nonOpeIncomeIndex.getText(), 
    			spProfitIndex.getText(),
    			costOfSalesIndex.getText(),
    			SGandAIndex.getText(),
    			nonOpeExpensesIndex.getText(),
    			extraLossIndex.getText(),
    			corpTaxEtcIndex.getText(),
    			netProfitIndex.getText(),
    			totalRevenueIndex.getText(),
    			totalCostIndex.getText()
    			};
    	String[] priceParameters = {
    			"金額",
    			Double.valueOf(inputData[0]).toString(),
    			Double.valueOf(inputData[1]).toString(),
    			Double.valueOf(inputData[2]).toString(),
    			Double.valueOf(inputData[3]).toString(),
    			Double.valueOf(inputData[4]).toString(),
    			Double.valueOf(inputData[5]).toString(),
    			Double.valueOf(inputData[6]).toString(),
    			Double.valueOf(inputData[7]).toString(),
    			Double.valueOf(inputData[8]).toString(),
    			totalRevenueLabel.getText(),
    			totalCostLabel.getText()
    			};
    	String[] rateOutPuts = {
    			"割合",
    			salesRate.getText(),
    			nonOpeIncomeRate.getText(),
    			spProfitRate.getText(),
    			costOfSalesRate.getText(),
    			SGandARate.getText(),
    			nonOpeExpensesRate.getText(),
    			extraLossRate.getText(),
    			corpTaxEtcRate.getText(),
    			netProfitRate.getText()
    			};
    	try {
    		//pathに
    		DirectoryChooser directoryChooser = new DirectoryChooser();
    		directoryChooser.setTitle("出力先のファイルを選択");
    		Path choosedFolder = directoryChooser.showDialog(corpName.getScene().getWindow()).toPath();
    		
    		CreateFolder createFolder = new CreateFolder();
    		Path folderPath = createFolder.createFolder(saveFolderName, corpName, choosedFolder);
    		
    		SaveFileName saveFileName = new SaveFileName();
    		//saveFileNameという名前でグラフ保存
    		File file = saveFileName.saveFileName(folderPath, saveFolderName, corpName);
    		SaveGraph saveGraph = new SaveGraph();
    		saveGraph.saveChartAsImage(controlledGraph, file);    
    		
    		SaveTable saveTable = new SaveTable();
    		saveTable.saveTableAsCSV(folderPath, file, saveFolderName, corpName);
    		
    		
    		
    		WriteContents writeContents = new WriteContents();
    		writeContents.exportCSV(saveFolderName, corpName, folderPath, corpLabels, titleIndex, priceParameters, rateOutPuts);
    		
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
}