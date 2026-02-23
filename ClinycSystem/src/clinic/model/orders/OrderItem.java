package clinic.model.orders;

import clinic.enums.OrderItemType;

public abstract class OrderItem {

    protected int itemNumber; 
    protected String name;
    protected double cost;
    protected OrderItemType type;

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}
