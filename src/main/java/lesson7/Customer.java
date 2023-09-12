package lesson7;

import java.util.Arrays;
import java.util.Objects;

public class Customer {

    private int walletAmount;
    private BoughtFlower[] bouquet;

    public Customer(int walletAmount) {
        this.walletAmount = walletAmount;
        bouquet = new BoughtFlower[5];
    }

    public int getWalletAmount() {
        return walletAmount;
    }

    public void setWalletAmount(int walletAmount) {
        this.walletAmount = walletAmount;
    }

    public BoughtFlower[] getBouquet() {
        return bouquet;
    }

    public void setBouquet(BoughtFlower[] bouquet) {
        this.bouquet = bouquet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return walletAmount == customer.walletAmount && Arrays.equals(bouquet, customer.bouquet);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(walletAmount);
        result = 31 * result + Arrays.hashCode(bouquet);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "walletAmount=" + walletAmount +
                ", bouquet=" + Arrays.toString(bouquet) +
                '}';
    }
}
