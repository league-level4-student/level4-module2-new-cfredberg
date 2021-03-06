package _06_Console_Store;

import java.util.ArrayList;
import java.util.Scanner;

import _02_Generics_Store.Candy;
import _02_Generics_Store.Cart;
import _02_Generics_Store.Cereal;
import _02_Generics_Store.Clothing;
import _02_Generics_Store.Food;
import _02_Generics_Store.Toy;

public class ConsoleStore {
	
	
    /*
     * Write a program that simulates shopping in a store using the Scanner and
     * the classes in Generics_Store.
     * 
     * Note: You may need to modify existing code in Generics Store and/or add
     * additional items and pictures to fulfill all the requirements. You are
     * also free to add any additional methods or classes in Console Store that
     * might be helpful to you.
     * 
     * Requirements:
     * 
     * -Use Ternary operators in place of simple if/else statements and do-while
     * loops instead of while loops where appropriate.
     * 
     * - There should be at least four unique items the user can buy. These can
     * be food items, nonfood items or both.
     * 
     * - The user should have a stipend of money to spend and each item should
     * have its own price.
     * 
     * -The user should have the ability to add items to their cart, remove
     * items, view items or check out.
     * 
     * -The program should continue until the user chooses to check out.
     * 
     * -When the user checks out you should let them know if they do not have
     * enough money to purchase all their items and offer to put items back.
     * 
     * -If the user successfully purchases the items you should remove the
     * amount from their stipend, show them the pictures of what they bought and
     * print out a receipt showing their name, the individual prices of the
     * items and their total.
     */

    public static void main(String[] args) {
    	ConsoleStore cs = new ConsoleStore();
    	cs.run();
    }

    Scanner scanner = new Scanner(System.in);
    Cart cart = new Cart();
    String name = "";
    
    final int CANDY = 1;
    final int CEREAL = 2;
    final int CLOTHING = 3;
    final int TOY = 4;
    
    final double CANDY_PRICE = 17.99;
    final double CEREAL_PRICE = 9.99;
    final double CLOTHING_PRICE = 30.99;
    final double TOY_PRICE = 0.99;
    
	public void run() {
		greeting();
		boolean cont = false;
		do {
			shop();
			cont = checkOut();
			if (cont) cont = doMoreShopping();
		}while (!cont);
		System.exit(0);
	}
	
	double money = -1;
	public void greeting() {
		do {
			System.out.println("Hello.  How much money do you have today?");
			money = scanner.nextFloat();
		}while(money < 0);
		
		System.out.println("Thank you.  Have a nice time shopping!");
	}
	
	String firstChoice = "";
	String secondChoice = "";
	public void shop() {
		do {
			System.out.println("You can either add an item to your cart, remove an item from your cart, view the contents in your cart, view the prices of items, or check out.  What do you choose? [a, r, vc, vp, c]");
			firstChoice = scanner.nextLine();
			if (firstChoice.equals("a")) {
				do {
					System.out.println("You can either add candy, cereal, clothing, a toy, or nothing to your cart.  What do you choose? [ca, ce, cl, t, n]");
					secondChoice = scanner.nextLine();
				} while (!secondChoice.equals("ca") && !secondChoice.equals("ce") && !secondChoice.equals("cl") && !secondChoice.equals("t") && !secondChoice.equals("n"));
				
				if (secondChoice.equals("ca")) {
					cart.add(new Candy());
				}else if (secondChoice.equals("ce")) {
					cart.add(new Cereal());
				}else if (secondChoice.equals("cl")) {
					cart.add(new Clothing());
				}else if (secondChoice.equals("t")) {
					cart.add(new Toy());
				}
			}else if (firstChoice.equals("r")) {
				do {
					System.out.println("You can either remove candy, cereal, clothing, a toy, or nothing from your cart.  What do you choose? [ca, ce, cl, t, n]");
					secondChoice = scanner.nextLine();
				}while (!secondChoice.equals("ca") && !secondChoice.equals("ce") && !secondChoice.equals("cl") && !secondChoice.equals("t") && !secondChoice.equals("n"));
				
				if (secondChoice.equals("ca")) {
					if (itemCount(CANDY) > 0) {
						cart.removeItem(findFirstItem(CANDY));
					}else {
						System.out.println("I am sorry.  You have no candy in your cart, so you can't remove any.");
					}
				}else if (secondChoice.equals("ce")) {
					if (itemCount(CEREAL) > 0) {
						cart.removeItem(findFirstItem(CEREAL));
					}else {
						System.out.println("I am sorry.  You have no cereal in your cart, so you can't remove any.");
					}
				}else if (secondChoice.equals("cl")) {
					if (itemCount(CLOTHING) > 0) {
						cart.removeItem(findFirstItem(CLOTHING));
					}else {
						System.out.println("I am sorry.  You have no clothing in your cart, so you can't remove any.");
					}
				}else if (secondChoice.equals("t")) {
					if (itemCount(TOY) > 0) {
						cart.removeItem(findFirstItem(TOY));
					}else {
						System.out.println("I am sorry.  You don't have any toys in your cart, so you can't remove any.");
					}
				}
			}else if (firstChoice.equals("vc")) {
				cart.showCart();
			}else if (firstChoice.equals("vp")) {
				System.out.println("Candy Price: $" + CANDY_PRICE);
				System.out.println("Cereal Price: $" + CEREAL_PRICE);
				System.out.println("Clothing Price: $" + CLOTHING_PRICE);
				System.out.println("Toy Price: $" + TOY_PRICE);
			}
		}while (!firstChoice.equals("c"));
	}
	
	public boolean checkOut() {
		System.out.println("Hello customer.  What is your name?");
		String name = scanner.nextLine();
		System.out.println("Nice to meet you " + name + ".  My name is Java.  I love coffee.");
		System.out.println("You have $" + money + " and your cart is valued at $" + calcTotal() + ".");
		if (money > calcTotal()) {
			String cont = "";
			do {
				System.out.println("Would you like to continue? [y, n]");
				cont = scanner.nextLine();
			}while (!cont.equals("y") && !cont.equals("n"));
			if (cont.equals("y")) {
				money = money - calcTotal();
				System.out.println("You now have $" + money + " left.");
				return true;
			}else {
				return false;
			}
		}
		System.out.println("I am sorry.  Your cart is too expensive.  Please remove items, and come back when you are done.");
		return false;
	}
	
	public boolean doMoreShopping() {
		String cont = "";
		do {
			System.out.println("Would you like to continue shopping? [y, n]");
			cont = scanner.nextLine();
		} while(!cont.equals("y") && !cont.equals("n"));
		if (cont.equals("y")) {
			cart = new Cart();
			return false;
		}
		System.out.println("Thank you for shopping with us!");
		return true;
	}
	
	public int itemCount(int checkItem) {
		if (checkItem == CANDY) {
			int candyCount = 0;
			for (int i = 0; i < cart.length(); i++) {
				if (cart.viewCart()[i] instanceof Candy) {
					candyCount++;
				}
			}
			return candyCount;
		}else if (checkItem == CEREAL) {
			int cerealCount = 0;
			for (int i = 0; i < cart.length(); i++) {
				if (cart.viewCart()[i] instanceof Cereal) {
					cerealCount++;
				}
			}
			return cerealCount;
		}else if (checkItem == CLOTHING) {
			int clothingCount = 0;
			for (int i = 0; i < cart.length(); i++) {
				if (cart.viewCart()[i] instanceof Clothing) {
					clothingCount++;
				}
			}
			return clothingCount;
		}else if (checkItem == TOY) {
			int toyCount = 0;
			for (int i = 0; i < cart.length(); i++) {
				if (cart.viewCart()[i] instanceof Toy) {
					toyCount++;
				}
			}
			return toyCount;
		}
		
		return -1;
	}
	
	public int findFirstItem(int item) {
		if (item == CANDY) {
			for (int i = 0; i < cart.length(); i++) {
				if (cart.viewCart()[i] instanceof Candy) {
					return i;
				}
			}
		} else if (item == CEREAL) {
			for (int i = 0; i < cart.length(); i++) {
				if (cart.viewCart()[i] instanceof Cereal) {
					return i;
				}
			}
		} else if (item == CLOTHING) {
			for (int i = 0; i < cart.length(); i++) {
				if (cart.viewCart()[i] instanceof Clothing) {
					return i;
				}
			}
		} else if (item == TOY) {
			for (int i = 0; i < cart.length(); i++) {
				if (cart.viewCart()[i] instanceof Toy) {
					return i;
				}
			}
		}
		
		return -1;
	}
	
	public double calcTotal() {
		double tempTotal = 0;
		for (int i = 0; i < cart.length(); i++) {
			if (cart.viewCart()[i] instanceof Candy) {
				tempTotal = tempTotal + CANDY_PRICE;
			}else if (cart.viewCart()[i] instanceof Cereal) {
				tempTotal = tempTotal + CEREAL_PRICE;
			}else if (cart.viewCart()[i] instanceof Clothing) {
				tempTotal = tempTotal + CLOTHING_PRICE;
			}else if (cart.viewCart()[i] instanceof Toy) {
				tempTotal = tempTotal + TOY_PRICE;
			}
		}
		return tempTotal;
	}
}
