package _06_Console_Store;

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
	public void run() {
		shop();
	}
	
	String choice = "";
	public void shop() {
		do {
			System.out.println("You can either add an item to your cart, remove an item from your cart, or check out.  What do you choose? [a, r, c]");
			choice = scanner.nextLine();
			if (choice.equals("a")) {
				do {
					System.out.println("You can either add candy, cereal, clothing, a toy, or nothing to your cart.  What do you choose? [ca, ce, cl, t, n]");
					choice = scanner.nextLine();
				} while (!choice.equals("ca") && !choice.equals("ce") && !choice.equals("cl") && !choice.equals("t") && !choice.equals("n"));
				
				if (choice.equals("ca")) {
					cart.add(new Candy());
				}else if (choice.equals("ce")) {
					cart.add(new Cereal());
				}else if (choice.equals("cl")) {
					cart.add(new Clothing());
				}else if (choice.equals("t")) {
					cart.add(new Toy());
				}
			}
		}
	}

}
