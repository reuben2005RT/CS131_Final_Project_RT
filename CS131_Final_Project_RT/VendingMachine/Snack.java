
package VendingMachine;

public class Snack {
	/**
	 * Base class for all snack types.
	 * Subclasses override purchaseMessage() for different msg.
	 */


protected String name;
protected double price; 

public Snack(String name, double price) {
	this.name = name;
	this.price = price ;
}

public String getName() {
	return name;
}
public double getPrice() {
	return price; 
}
public void setName(String name) {
	this.name = name;
}
public void setPrice(double price) {
	this.price = price; 
}
@Override
public String toString() {
    return name + " - $" + String.format("%.2f", price);
}
public String purchaseMessage() {
    return "You bought a snack!";
}


}

