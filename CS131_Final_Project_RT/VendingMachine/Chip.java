package VendingMachine;

public class Chip extends Snack {

/**
 * Chip snack type.
 */

	public Chip(String name, double price) {
		super(name, price);
	}
	 @Override
	   public String toString() {
		 return "[Chip] " + super.toString();
	    }
	 @Override
	 public String purchaseMessage() {
	     return "Crunchy choice! Enjoy your chips!";
	 }

}