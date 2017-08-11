/*
 * Practice 009.2_Changing machine
 * Date 20170810
 */

import java.util.Scanner;

public class CalculatorTest {

	static CCalculator obj;
	static Scanner scanner = new Scanner(System.in);
	static int input, amount, status;
	
	public static void main(String[] args) {
		
		int[] iniCoin = initialCoins();
		obj = new CCalculator(iniCoin[0], iniCoin[1], iniCoin[2]);
		
		status = 0;
		do {
			switch (status) {
			case 0: //Ask the user to change or not and do next step.
				doOption();
				break;
				
			case 1: //Insert cash and calculate amount.
				insertCash();
				break;
				
			case 2: //Calculate change and print out.
				int[] result = obj.change(amount);
				if (result[2] == -1) {
					System.out.print("\n現金不足，無法找零！");
					status = -1;
				}
				else {
					System.out.printf("\n兌換%d元。\n退回", amount);
					obj.printOut(result);
					status = 0;
				}
				break;
				
			}
		} while (status != -1);
		
		System.out.print("\nProgram terminate.");
		

	}
	
	//Initialize number of each type of coins.
	public static int[] initialCoins() { 
		
		int[] input = new int[3];

		System.out.print("1元硬幣個數：");
		input[0] = scanner.nextInt();
		while (input[0] < 0) {
			System.out.println("Input error!");
			System.out.print("1元硬幣個數：");
			input[0] = scanner.nextInt();
		}
		System.out.print("5元硬幣個數：");
		input[1] = scanner.nextInt();
		while (input[1] < 0) {
			System.out.println("Input error!");
			System.out.print("5元硬幣個數：");
			input[1] = scanner.nextInt();
		}
		System.out.print("10元硬幣個數：");
		input[2] = scanner.nextInt();
		while (input[2] < 0) {
			System.out.println("Input error!");
			System.out.print("10元硬幣個數：");
			input[2] = scanner.nextInt();
		}
		
		return input;
	}
	
	//Prompt the user to input option and return next status.
	public static void doOption() {
		System.out.print("換零錢 1)是 2)否?");
		input = scanner.nextInt();
		if (input == 1) {
			amount = 0;
			status = 1;
		}
		else if (input == 2)
			status = -1;
		else {
			System.out.print("Input error!");
			status = 0;
		}
	}
	
	//Allow the user to insert cash and calculate amount.
	public static void insertCash() {
		System.out.print("請投入現金： 1) 50元 2) 100元 3) 500元4) 1000元 5)結束 -1)取消: ");
		input = scanner.nextInt();
		if (input == 1)
			amount += 50;
		else if (input == 2)
			amount += 100;
		else if (input == 3)
			amount += 500;
		else if (input == 4)
			amount += 1000;
		else if (input == 5)
			status = 2;
		else if (input == -1){
			if (amount > 0) 
				System.out.printf("退您%d元。\n", amount);
			status = 0;
		}
		else
			System.out.print("Input error!");
			
	}

}

class CCalculator {
	private int coin10, coin5, coin1;
	
	public CCalculator(int initial1, int initial5, int initial10) {
		if (initial10 > 0)
			coin10 = initial10;
		if (initial5 > 0)
			coin5 = initial5;
		if (initial1 > 0)
			coin1 = initial1;
	}
	
	public int[] change(int amount) {
		int[] result = new int[3];
		//Calculate amount of 10.
		result[0] = amount / 10;
		if (result[0] > coin10) {
			result[0] = coin10;
			amount -= 10 * coin10;
			coin10 = 0;
		}
		else {
			amount %= 10;
			coin10 -= result[0];
		}
		
		//Calculate amount of 5.
		result[1] = amount / 5;
		if (result[1] > coin5) {
			result[1] = coin5;
			amount -= 5 * coin5;
			coin5 = 0;
		}
		else	 {
			amount %= 5;
			coin5 -= result[1];
		}
		
		//Calculate amount of 1.
		result[2] = amount;
		if (result[2] > coin1)
			result[2] = -1;
		else
			coin1 -= result[2];
		
		return result;
	}
	
	public void printOut (int[] coins) {
		int[] printCoin = {10, 5, 1};
		for (int i = 0; i < 3; i++) {
			if (coins[i] != 0) {
				System.out.printf("%d元硬幣%d個", printCoin[i], coins[i]);
				if (i != 2 && coins[i + 1] > 0)
					System.out.print("，");
				else
					System.out.print("。\n");
			}
		}
		
	}
}