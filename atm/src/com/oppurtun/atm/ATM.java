package com.oppurtun.atm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {

	/**
	 * BILLS OF NOTES
	 * 
	 */
	private int[] bills = { 1, 5, 10, 20, 50, 100 };
	/**
	 * sumOfAllBills - stores number of notes in this atm. Each array position
	 * represent no of available notes. Example: in the begining all values are 0
	 * and there is no balance in this atm.
	 */
	private int[] sumOfAllBills = { 0, 0, 0, 0, 0, 0 };

	/**
	 * While you can use sumOfAllBills to find the total balance, this variable will
	 * be easy access total balance amount.
	 */
	private int totalBalance = 0;

	private List<Integer> permutation = new ArrayList<Integer>();

	/**
	 * this method will simulate and find the bills submitted by the user in the
	 * atm. in the real world case, the bill scanner will identify bill types and
	 * total amount deposited.
	 * 
	 * @param amount
	 */
	public void deposit(int oneDollarBills, int fiveDollarBills, int tenDollarBills, int twentyDollarBills) {
		// one dollar bills in this ATM
		sumOfAllBills[0] = sumOfAllBills[0] + oneDollarBills;
		// five dollar bills in this ATM
		sumOfAllBills[1] = sumOfAllBills[1] + fiveDollarBills;
		// ten dollar bills in this ATM
		sumOfAllBills[2] = sumOfAllBills[2] + tenDollarBills;
		// one dollar bills in this ATM
		sumOfAllBills[3] = sumOfAllBills[3] + twentyDollarBills;
		// total balance
		totalBalance = totalBalance + 1 * oneDollarBills + 5 * fiveDollarBills + 10 * tenDollarBills
				+ 20 * twentyDollarBills;
		print();
	}
	
	public int[] getSumOfBills() {
		return sumOfAllBills;
	}

	/**
	 * print the available new balances in each denomination and the total balance
	 * 
	 */
	private void print() {
		recalculate();
		System.out.println("--1--5--10--20--");
		System.out.println(
				"--" + sumOfAllBills[0] + "--" + sumOfAllBills[1] + "--" + sumOfAllBills[2] + "--" + sumOfAllBills[3]);
		System.out.println("totalBalance -" + totalBalance);
	}

	/**
	 * recalculate total
	 */
	private void recalculate() {
		totalBalance = 0;
		for (int i = 0; i < sumOfAllBills.length; i++) {
			totalBalance = totalBalance + sumOfAllBills[i] * bills[i];
		}
	}

	/**
	 * this method will simulate and find the bills submitted by the user in the
	 * atm. in the real world case, the bill scanner will identify bill types and
	 * total amount deposited.
	 * 
	 * @param amount
	 */
	public void depositAmountAndSimulate(int amount) {
		findSimulativeBillsByScanning(amount);
		printPermutation();
	}

	/**
	 * Simulate bills from total amount.
	 * 
	 * @TODO
	 * @param one
	 * @param two
	 * @param result
	 * @return
	 */
	private int[] findSimulativeBillsByScanning(int totalAmount) {
		// 1 - 0, 2 - 5, 5 - 4, 10 - 2, 50 - 1, 100 - 1
		System.out.println("totalAmount-" + totalAmount);
		int[] billCounter = new int[6];
		for (int i = 0; i < 5; i++) {
			if (totalAmount >= bills[i]) {
				billCounter[i] = totalAmount / bills[i];
				totalAmount = totalAmount - billCounter[i] * bills[i];
				System.out.println("totalAmount-" + totalAmount);
			}
		}
		for (int i = 0; i < 5; i++) {
			if (billCounter[i] != 0) {
				System.out.println(bills[i] + " : " + billCounter[i]);
			}
		}
		return billCounter;
	}

	public boolean withdraw(int totalAmount) {
		// check if we have enough funds.
		recalculate();
		if ((totalAmount > totalBalance) || totalAmount <= 0) {
			System.out.println("Incorrect or insufficient funds");
			return false;
		}
		int toDispense = totalAmount;
		//System.out.println("toDispense - " + toDispense);
		for (int i = sumOfAllBills.length - 1; i >= 0; i--) {
			System.out.println("toDispense - " + toDispense);
			if (toDispense >= bills[i] && sumOfAllBills[i] > 0 && toDispense > 0) {
				int quotient = toDispense / (bills[i]);
				//System.out.println("quotient=" + quotient);
				int maxBills = 0;
				if ((bills[i] * sumOfAllBills[i]) < toDispense) {
					maxBills = Math.min(quotient, sumOfAllBills[i]);
				} else {
					maxBills = quotient;
				}

				toDispense = toDispense - maxBills * bills[i];
				System.out.println("dispensed=" + maxBills + "*" + bills[i]);

				sumOfAllBills[i] = sumOfAllBills[i] - maxBills;
			}
			print();
		}
		// please note that some of the bills are already reduced &
		// that needs to be fixed. we can array copy and backtrack
		// if not possible
		// TODO
		if (toDispense > 0) {
			System.out.println("Requested withdraw amount is not dispensable");
			return false;
		}
		return true;
	}

	/**
	 * We simulate using backtracking example : how we sum input type bills and get
	 * the desired result of amount deposited. there are n no of ways however we
	 * stick to first matching result.
	 * 
	 * @param amount
	 * @return
	 */
	private int[] simulateBillsFromAmount(int amount) {
		// TODO
		return null;
	}

	public void printPermutation() {
		for (Integer num : permutation) {
			System.out.println(num);
		}
	}

	private void askInput(ATM instance) {
		Scanner scanner = new Scanner(System.in);
		boolean keepRunning = true;

		while (keepRunning) {
			System.out.println("Welcome to Oppurtun ATM : Choose the transaction");
			System.out.println("Deposit :");
			System.out.println("Withdraw :");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1: { // deposit
				int oneDollarBills = 0;
				int fiveDollarBills = 0;
				int tenDollarBills = 0;
				int twentyDollarBills = 0;
				boolean done = false;
				while (!done) {
					System.out.println("Enter Bill No :- 1 Or 5 Or 10 Or 20 Or -1 for done");
					int billType = scanner.nextInt();

					switch (billType) {
					case 1: {
						System.out.println("Enter No of 1 Dollar Bills :-");
						// Enter no of 1 dollar bills
						oneDollarBills = scanner.nextInt();
						if (oneDollarBills == 0) {
							System.out.println("Deposit amount cannot be zero :-");
							oneDollarBills = scanner.nextInt();
						}
						if (oneDollarBills < 0) {
							System.out.println("Incorrect deposit amount :-");
							oneDollarBills = scanner.nextInt();
						}
						break;
					}
					case 5: {
						System.out.println("Enter No of 5 Dollar Bills :-");
						// Enter no of 5 dollar bills
						fiveDollarBills = scanner.nextInt();
						if (fiveDollarBills == 0) {
							System.out.println("Deposit amount cannot be zero :-");
							fiveDollarBills = scanner.nextInt();
						}
						if (fiveDollarBills < 0) {
							System.out.println("Incorrect deposit amount :-");
							fiveDollarBills = scanner.nextInt();
						}
						break;
					}
					case 10: {
						System.out.println("Enter No of 10 Dollar Bills :-");
						// Enter no of 10 dollar bills
						tenDollarBills = scanner.nextInt();
						if (tenDollarBills == 0) {
							System.out.println("Deposit amount cannot be zero :-");
							tenDollarBills = scanner.nextInt();
						}
						if (tenDollarBills < 0) {
							System.out.println("Incorrect deposit amount :-");
							tenDollarBills = scanner.nextInt();
						}
						break;
					}
					case 20: {
						System.out.println("Enter No of 20 Dollar Bills :-");
						// Enter no of 10 dollar bills
						twentyDollarBills = scanner.nextInt();
						if (twentyDollarBills == 0) {
							System.out.println("Deposit amount cannot be zero :-");
							twentyDollarBills = scanner.nextInt();
						}
						if (twentyDollarBills < 0) {
							System.out.println("Incorrect deposit amount :-");
							twentyDollarBills = scanner.nextInt();
						}
						break;
					}
					default: {
						done = true;
						break;
					}
					}
				}
				instance.deposit(oneDollarBills, fiveDollarBills, tenDollarBills, twentyDollarBills);
				break;
			}
			case 2: { // withdraw
				System.out.println("Enter Amount to Withdraw:-");
				int amount = scanner.nextInt();
				instance.withdraw(amount);
				break;
			}
			default:
				keepRunning = false;
				System.out.println("Sorry Your choice is invalid");
				break;
			}
		}

		scanner.close();
		System.out.println("Thank you!");
	}

	public static void main(String[] args) {
		ATM instance = new ATM();
		instance.askInput(instance);

	}

}
