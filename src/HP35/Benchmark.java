package HP35;

import java.util.Arrays;

public class Benchmark {

    static int iter = 1000;
    static int times = 1;

    static double[] pushTimes = new double[times];
    static double[] popTimes = new double[times];
    static double[] totalTime = new double[times];

    public static double push(Stack stack) {
        long t1 = System.nanoTime();
        for (int i = 0; i < iter; i++) {
            stack.push(0);
        }
        long t2 = System.nanoTime();

        // The average time for 1 push when it's done iter number of times
        return (t2 - t1);
    }

    public static double pop(Stack stack) {
        long t1 = System.nanoTime();
        for (int i = 0; i < iter; i++) {
            stack.pop();
        }
        long t2 = System.nanoTime();

        // The average time for 1 pop when it's done iter number of times
        return (t2 - t1);
    }

    public static double total(Stack stack) {
        long t1 = System.nanoTime();
        for (int i = 0; i < iter; i++) {
            stack.push(0);
        }
        for (int i = 0; i < iter; i++) {
            stack.pop();
        }
        long t2 = System.nanoTime();

        // The average time for 1 pop when it's done iter number of times
        return (t2 - t1);
    }


    public static void bench(Stack stack) {
        for (int i = 0; i < times; i++) {
            pushTimes[i] = push(stack);
            popTimes[i] = pop(stack);
            totalTime[i] = total(stack);
        }

        Arrays.sort(pushTimes);
        Arrays.sort(popTimes);
        Arrays.sort(totalTime);

        double avgPush = 0;
        double avgPop = 0;
        double avgTotal = 0;

        for (int i = 0; i < times; i++) {
            avgPush += pushTimes[i];
            avgPop += popTimes[i];
            avgTotal += totalTime[i];
        }

        avgPush /= times;
        avgPop /= times;
        avgTotal /= times;


        System.out.println("A " + iter + " pushes and pops done " + times + " times.");
        System.out.println("---Push time---");
        System.out.println("Min time: " + pushTimes[0] + ", Max time: " + pushTimes[times - 1]
                + ", Median: " + pushTimes[times/2] + ", Average: " + avgPush);

        System.out.println("---Pop time---");
        System.out.println("Min time: " + popTimes[0] + ", Max time: " + popTimes[times - 1]
                + ", Median: " + popTimes[times/2] + ", Average: " + avgPop);

        System.out.println("---Total time---");
        System.out.println("Min time: " + totalTime[0] + ", Max time: " + totalTime[times - 1]
                + ", Median: " + totalTime[times/2] + ", Average: " + avgTotal);
        System.out.println();

        System.out.println(pushTimes[0] + "  &  " + pushTimes[times/2] + "  &  " + (int) avgPush + "  \\\\");
        System.out.println(popTimes[0] + "  &  " + popTimes[times/2] + "  &  " + (int) avgPop + "  \\\\");
        System.out.println(totalTime[0] + "  &  " + totalTime[times/2] + "  &  " + (int) avgTotal + "  \\\\");

    }

    public static void main(String[] args) {
        int staticSize = iter + 1;

        Stack staticStack = new Static(staticSize);
        Stack dynamicStack = new Dynamic(4);

        bench(staticStack);
        bench(dynamicStack);
    }
}
