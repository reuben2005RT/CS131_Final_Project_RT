package VendingMachine;

public class Candy extends Snack {
	/**
	 * Candy snack type.
	 */

	public Candy(String name, double price) {
		super(name, price);
	}
	 @Override
	   public String toString() {
		 return "[Candy] " + super.toString();
	    }
	 @Override
	 public String purchaseMessage() {
	     return "Sugar rush! Enjoy your candy!";
	 }

}
