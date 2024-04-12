import java.util.Scanner;

public class PB01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numBiscuitsPerDayPerWorker = Integer.parseInt(scanner.nextLine());
        int numWorkers = Integer.parseInt(scanner.nextLine());
        int numBiscuitsCompTotal = Integer.parseInt(scanner.nextLine());
        int numBiscuitsTotal = 0;
        for (int i = 1; i <= 30; i++) {
            if (i % 3 != 0) {
                numBiscuitsTotal += numBiscuitsPerDayPerWorker*numWorkers;
            } else {
                numBiscuitsTotal += numBiscuitsPerDayPerWorker*numWorkers * 3 / 4;
            }
        }
        System.out.printf("You have produced %d biscuits for the past month.%n",numBiscuitsTotal);
        double percentage =((numBiscuitsTotal - numBiscuitsCompTotal)/ (double )numBiscuitsCompTotal)*100;


        if (numBiscuitsTotal> numBiscuitsCompTotal)   {
            System.out.printf("You produce %.2f percent more biscuits.",Math.abs(percentage));
        } else {
            System.out.printf("You produce %.2f percent less biscuits.",Math.abs(percentage));
        }
    }
}
