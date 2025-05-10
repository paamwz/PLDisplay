package application.Methods;

import javafx.scene.control.TextField;

public class TotalCalc{
	double totalRevenue = 0.0;
	public double totalRevenueCalc(double[] inputData, TextField[] textFieldlIncome) {
		for(int i = 0; i < textFieldlIncome.length; i++) {
			totalRevenue += inputData[i];
		}
		return totalRevenue;	
	}
	double totalCost = 0.0;
	public double totalCostCalc(double[] inputData, TextField[] textFieldlIncome) {
		for(int i = textFieldlIncome.length; i < inputData.length; i++) {
			totalCost += inputData[i];
		}
		return totalCost;	
	}
}