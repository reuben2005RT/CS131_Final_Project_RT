package VendingMachine;


import java.util.Scanner;
/**
 * Launches the vending machine simulation.
 * 
 */


public class Main{


	public static void main(String[] args) {
		
		
		Scanner input = new Scanner(System.in); 
	   
		

		
		Store store = new Store("Reuben Vending machine!");
		
		//Store candies
        store.addSnacks(new Candy("Snickers", 2.55));
        store.addSnacks(new Candy("M&M's Peanut", 1.75));
        store.addSnacks(new Candy("Twix", 2.85));
        
        //Cookies
        store.addSnacks(new Cookie("Nutter Butter", 1.85));
        store.addSnacks(new Cookie("Oreos", 2.25));
        store.addSnacks(new Cookie("Girl Scout Cookie", 3.00));
        
       // chips * price ? 
        store.addSnacks(new Chip("Lays Classic", 1.75));
        store.addSnacks(new Chip("Doritos", 2.15));
        store.addSnacks(new Chip("Fritos Original", 1.60));

      
	
	//user balance 
		int snacksBought = 0;
		double balance = 8.12;
     	boolean running = true;
     
     		
     	while(running) {
            System.out.println("\n===> Welcome to " + store.getStoreName() + " <===");
            System.out.println("          -------------------  ");
            System.out.printf("            balance: $%.2f\n", balance);
            System.out.println("          -------------------");
            System.out.println("");
            System.out.println("Objective: Buy 3 snacks before running out of money");
            System.out.println("");
            System.out.println("       Click Enter to see the store menu...");
            input.nextLine();
     	//	store.showMenu();
            
         Menu();
         try {

     		System.out.println("\nEnter item code or X to exit: ");
     		String code = input.nextLine();
     		
     		//String code = null;
     		if(code.equalsIgnoreCase("X")) {
     			System.out.println("Exiting......");
     			break;
     		}
     		
     	    int index = store.codeIndex(code);
     	    Snack selected = store.getSnack(index);
     	        
     	    //if my user was to selected something that not on the row
     	    if (index == -1|| selected == null) {
     	    throw new IllegalArgumentException("Invalid code entered: " + code);
         }

     	    // to show price 
     	    System.out.printf("You selected %s. The price is $%.2f\n", selected.getName(), selected.getPrice());
     	    System.out.print("Do you want to buy (Y) or exit (N)? ");
     	    String choice = input.nextLine();
     	    
     		// user input and Y/N
     	    if (choice.equalsIgnoreCase("Y")) {
     			if (balance >= selected.getPrice()) {
                    balance -= selected.getPrice();
                    snacksBought++;
                    store.addPurchase(selected);
                    System.out.println("");
                    System.out.println("Loading........");
                    System.out.println("");
                    System.out.printf("You bought %s! Remaining balance: $%.2f\n", selected.getName(), balance);
                } else {
                	throw new RuntimeException("Not enough balance!");
                }
     	    } else if (choice.equalsIgnoreCase("N")){
                balance -= 0.25;
                System.out.printf("You exited this item. $0.25 deducted. Remaining balance: $%.2f\n", balance);
                
     	    } else {
     	    throw new IllegalArgumentException("Invalid choice: " + choice);
            }
     	    
	     		if (balance < 1.50) {
	                System.out.println(" You're out of balance! Game over.");
	                running = false;
	     		}
	     		
         }catch (IllegalArgumentException e) {
        	    System.out.println("Error: " + e.getMessage());
        	} catch (RuntimeException e) {
        		 System.out.println("Error: " + e.getMessage());
        		running = false;
        	}


            
     	     		System.out.println("\n============================================");
     	     		 System.out.printf("You bought %d snack%s.\n", snacksBought, snacksBought == 1 ? "" : "s");
     	     		Candy selected = null;
     	     		if(selected != null) {
					System.out.println(selected.purchaseMessage());
     	     		}
     	     		System.out.printf("Final balance: $%.2f\n", balance);

     	     		//purchase history
     	     		System.out.println("Your purchases:");
     	     		for (Snack s : store.getPurchases()) {
     	     		    System.out.println(" - " + s);
     	     		}

     	     		System.out.println("thank you for using REU REU Vending Machine");
     	     		System.out.println("============================================\n");
        }

	
        // end of code msg
	System.out.println("\n============================================");
	System.out.printf("You bought %d snack%s.\n", snacksBought, snacksBought == 1 ? "" : "s");
	System.out.printf("Final balance: $%.2f\n", balance);
	System.out.println("Thank you for using REU REU Vending Machine!");
	System.out.println("============================================\n");
	input.close();
	}
     	

		private static void Menu() {	
        
        System.out.println("_____________________________________________");
         System.out.println("                 **** Menu ****              | ");
         System.out.println("_____________________________________________|");
        System.out.println("   Snickers  |"+"M&M's Peanut |"+"       Twix      |");
        System.out.println("    $2.55    |"+"    $1.75    |"+"       $2.85     |");
        System.out.println("______A1_____|"+ "_____A2______|"+"________A3_______|");
        //B
        System.out.println("Nutter Butter|"+"    Oreos    |"+"Girl Scout Cookie|");
        System.out.println("    $1.85    |"+"    $2.25    |"+"      $3.00      |");
        System.out.println("_____B1______|"+ "_____B2______|"+"_______B3________|");
        //C 
        System.out.println("Lays Classic |"+"   Doritos   |"+" Fritos Original |");
        System.out.println("   $1.75     |"+"    $2.15    |"+"      $1.60      |");
        System.out.println("____C1_______|"+ "_____C2______|"+ "________C3_______|");
        
 
        
	}
}


