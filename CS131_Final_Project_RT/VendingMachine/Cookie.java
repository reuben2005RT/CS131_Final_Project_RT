package VendingMachine;

public class Cookie extends Snack {
	/**
	 * Cookie snack type.
	 */

	public Cookie(String name, double price) {
		super(name, price);
	}
	 @Override
	   public String toString() {
		 return "[Cookie] " + super.toString();
	    }
	 @Override
	 public String purchaseMessage() {
	     return "Sweet treat! Enjoy your cookie!";
	 }

}