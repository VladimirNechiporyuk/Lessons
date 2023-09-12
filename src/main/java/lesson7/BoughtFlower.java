package lesson7;

import java.util.Objects;

public class BoughtFlower {

    private String name;
    private int amount;

    public BoughtFlower(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoughtFlower)) return false;
        BoughtFlower that = (BoughtFlower) o;
        return amount == that.amount && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount);
    }

    @Override
    public String toString() {
        return "Flower in the bouquet{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }

}
