package application.Methods;

import javafx.scene.control.TextField;

public class RateCalc{
	
	//incomeの各割合を計算
	public double[] calcIncomeRate(double[] inputData, TextField[] incomeList) {
		double incomeSum = 0.0;
		for(int i = 0; i < incomeList.length; i++) {
			incomeSum += inputData[i];
		} 
		double[] incomeRates = {inputData[0], inputData[1], inputData[2], 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		
		for(int i = 0; i < incomeList.length ; i++) {
			incomeRates[i] = incomeRates[i]*10000/incomeSum;
		}
		return incomeRates;
	}
	
	
	
	//expensesの各割合を計算
	public double[] calcExpensesRate(double[] inputData, TextField[] incomeList) {
		double expensesSum = 0.0;
		for(int i = incomeList.length; i < inputData.length; i++) {
			expensesSum += inputData[i];			
		}
		
		double[] expensesRates = { 0.0, 0.0, 0.0, inputData[3], inputData[4], inputData[5], inputData[6], inputData[7], inputData[8]};
		for(int i = incomeList.length; i < inputData.length; i++ ) {
			expensesRates[i] = expensesRates[i]*10000/expensesSum;
		}
		return expensesRates;
	}
	
	
}