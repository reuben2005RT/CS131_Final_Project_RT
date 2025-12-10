package VendingMachine;

import java.util.ArrayList;

/**
 * Represents a store containing snacks and its history.
 * Provides methods to add snacks,and track purchases.
 */


public class Store {

	private String storeName;
	private ArrayList<Snack>Snacks;
	private ArrayList<Snack> purchases;
	
	public Store(String storeName) {
		this.storeName = storeName;
		this.Snacks = new ArrayList<>();
	    this.purchases = new ArrayList<>();
}
	 public Snack getSnack(int index) {
	        if (index >= 0 && index < Snacks.size()) {
	            return Snacks.get(index);
	        } else {   return null; }}
	public  void addSnacks(Snack snack) {
		Snacks.add(snack);
	}
    public void addPurchase(Snack snack) {
        purchases.add(snack);
    }
    public ArrayList<Snack> getPurchases() {
        return purchases;
    }

	
	public ArrayList<Snack> getSnack() {
		return Snacks;
	}


	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	} 
	//public toString() {
    public Object[] showMenu() {
       System.out.println("\nWelcome to " + storeName + "!");
       for (int i = 0; i < Snacks.size(); i++) {
            System.out.println((i + 1) + ". " + Snacks.get(i));
       }
		return null;
   }
        // menu 
        public void showMenu1() {
        	char row = 'A';
        	int col = 1;
        	for (int i = 0; i < Snacks.size(); i++) {
              System.out.printf("%c%d - %s\n", row, col, Snacks.get(i).getName());
                col++;

              if (col > 4) {
                    row++;
                    col = 1;
                }
        }
       }
        	// snacks index 
        	public int codeIndex(String code) {
                if (code.length() != 2) return -1;
                char rowChar = Character.toUpperCase(code.charAt(0));
                char colChar = code.charAt(1);
                // this will make my column to index
                int colNum = colChar - '1'; 
                // only rows (a to c) and (3 colums a rol)
                if (rowChar < 'A' || rowChar > 'C' || colNum < 0 || colNum > 2) {
                    return -1; 
                }
                int row = rowChar - 'A';
                int index = row * 3 + colNum ;
                if (index >= 0 && index < Snacks.size()) return index;
                return -1;
            }
        
    }	
	

