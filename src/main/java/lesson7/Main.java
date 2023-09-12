package lesson7;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

// Уявімо що ви працюєте в квітковому магазині
// Написати програму в якій:
// створити мінімум 5 класів квітки (наприклад троянди, тюльпани та інші)
// кожна квітка має свою назву, кількість, ціну (приблизно яка відповідає риночній)
// є покупець (клас), в якого лімітовано 1000 грн
// задача: вводити з клавіатури яку квітку покупець хоче додати в букет, а програма має сказати чи цей вид квіток все ще є на прилавку і чи достатньо в покупця грошей на те щоб додати цю квітку в букет
// варто опрацювати різні сценарії поведінки програми

    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        Flower[] stock = initializeStock();
        Customer customer = new Customer(1000);
        boolean finish = true;
        while (finish) {
            int flowerNumber = askWhatFlowerCustomerWantToBuy(stock);
            if (!isChosenNumberIsCorrect(stock, flowerNumber)) {
                System.out.println(ANSI_RED + "You choose wrong number of flower\n" + ANSI_RESET);
                continue;
            }
            Flower chosenFlower = getSelectedFlower(stock, flowerNumber);
            int amountToBuy = askHowMuchFlowersCustomerWantToBuy();
            if (!isStockHasEnoughFlowers(chosenFlower, amountToBuy)) {
                System.out.println(ANSI_RED + "You choose wrong amount of flower\n" + ANSI_RESET);
                continue;
            }
            int resultPrice = calculateResultPrice(chosenFlower, amountToBuy);
            buyFlower(chosenFlower, amountToBuy, resultPrice, customer);
            finish = askToContinue();
        }
        printResult(stock, customer);
    }

    private static int calculateResultPrice(Flower chosenFlower, int amountToBuy) {
        return chosenFlower.getPrice() * amountToBuy;
    }

    private static void printResult(Flower[] stock, Customer customer) {
        System.out.println(customer.toString());
        System.out.println(Arrays.toString(stock));
    }

    private static int askHowMuchFlowersCustomerWantToBuy() {
        System.out.println("How much flowers do you want to buy?");
        System.out.print("Answer: ");
        return keyboard.nextInt();
    }

    private static void buyFlower(Flower chosenFlower, int amountToBuy, int resultPrice, Customer customer) {
        if (customer.getWalletAmount() >= resultPrice) {
            addFlowerToCustomer(chosenFlower, amountToBuy, resultPrice, customer);
        } else {
            System.out.println("The customer has not enough money?");
        }
    }

    private static void addFlowerToCustomer(Flower chosenFlower, int amountToBuy, int resultPrice, Customer customer) {
        for (int flowerIndex = 0; flowerIndex < customer.getBouquet().length; flowerIndex++) {
            if (customer.getBouquet()[flowerIndex] != null) {
                if (chosenFlower.getName().equals(customer.getBouquet()[flowerIndex].getName())) {
                    customer.getBouquet()[flowerIndex].setAmount(customer.getBouquet()[flowerIndex].getAmount() + amountToBuy);
                    break;
                }
            } else {
                customer.getBouquet()[flowerIndex] = new BoughtFlower(chosenFlower.getName(), amountToBuy);
                break;
            }
        }
        chosenFlower.setAmount(chosenFlower.getAmount() - amountToBuy);
        customer.setWalletAmount(customer.getWalletAmount() - resultPrice);
    }

    private static boolean isStockHasEnoughFlowers(Flower chosenFlower, int amountToBuy) {
        return chosenFlower.getAmount() >= amountToBuy;
    }

    //    Виконанням методу isChosenNumberIsCorrect я гарантую що в цьому методі flowerNumber точно буде правильний
    private static Flower getSelectedFlower(Flower[] stock, int flowerNumber) {
        Flower flower = null;
        for (int flowerIndex = 0; flowerIndex < stock.length; flowerIndex++) {
            if (stock[flowerIndex].getNumber() == flowerNumber) {
                flower = stock[flowerIndex];
            }
        }
        return flower;
    }

    private static boolean isChosenNumberIsCorrect(Flower[] stock, int flowerNumber) {
        boolean result;
        for (int flowerIndex = 0; flowerIndex < stock.length; flowerIndex++) {
            result = stock[flowerIndex].getNumber() == flowerNumber;
            if (result) {
                return true;
            }
        }
        return false;
    }

    private static boolean askToContinue() {
        System.out.println("Do you want to continue?");
        System.out.print("Answer: ");
        String answer = keyboard.next();
        return answer.contains("Y") || answer.contains("y");
    }

    private static Flower[] initializeStock() {
        Flower rose = new Flower("Rose", 59, 20, 1);
        Flower carnation = new Flower("Carnation", 50, 17, 2);
        Flower amaryllis = new Flower("Amaryllis", 385, 5, 3);
        Flower peony = new Flower("Peony", 150, 10, 4);
        Flower hydrangea = new Flower("Hydrangea", 500, 1, 5);
        return new Flower[]{rose, carnation, amaryllis, peony, hydrangea};
    }

    private static int askWhatFlowerCustomerWantToBuy(Flower[] stock) {
        showFlowersOnTheStock(stock);
        System.out.println("\nWhat flower do you want to buy? Select a number from the list.");
        System.out.print("Answer: ");
        return keyboard.nextInt();
    }

    private static void showFlowersOnTheStock(Flower[] stock) {
        System.out.println("We have next flowers on the stock:");
        for (int flowerIndex = 0; flowerIndex < stock.length; flowerIndex++) {
            System.out.println(stock[flowerIndex].toString());
        }
    }

}
