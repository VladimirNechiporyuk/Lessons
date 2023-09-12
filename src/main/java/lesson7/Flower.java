package lesson7;

import java.util.Objects;

public class Flower {

    private String name;
    private int price;
    private int amount;
    private int number;

    public Flower(String name, int price, int amount, int number) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flower)) return false;
        Flower flower = (Flower) o;
        return price == flower.price && amount == flower.amount && Objects.equals(name, flower.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, amount);
    }

    @Override
    public String toString() {
        return "Flower{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", number=" + number +
                '}';
    }
}
