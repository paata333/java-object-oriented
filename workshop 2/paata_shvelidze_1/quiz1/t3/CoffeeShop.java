package paata_shvelidze_1.quiz1.t3;

public class CoffeeShop {
    int pricePerCoffee = 250; // tetri
    int numCoffeeSold = 100;
    int totalCostOfBeans = 15000; // tetri

    // Expenses from expenses.txt
    int tax = 5100;         // 51 lari = 5100 tetri
    int water = 75;         // 75 tetri
    int gas = 159;          // 159 tetri
    int electricity = 14800; // 148 lari = 14800 tetri
    int parking = 5100;     // 51 lari = 5100 tetri

    // Total expenses calculation
    int totalExpenses = totalCostOfBeans + tax + water + gas + electricity + parking;

    // Profit calculation
    double profit = calculateProfit(pricePerCoffee, numCoffeeSold, totalCostOfBeans, totalExpenses);

    public static double calculateProfit(int pricePerCoffee, int numCoffeeSold, int totalCostOfBeans, int totalExpenses) {
        int totalRevenue = pricePerCoffee * numCoffeeSold; // Total money earned
        int netProfit = totalRevenue - totalExpenses; // Revenue - Expenses

        // Convert profit from tetri to lari
        return netProfit / 100.0;
    }

    public static void main(String[] args) {
        CoffeeShop shop = new CoffeeShop();
        System.out.println("Total Profit: " + shop.profit + " Lari");
    }
}
