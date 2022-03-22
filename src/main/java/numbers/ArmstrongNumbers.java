package numbers;

public class ArmstrongNumbers {

    public boolean isArmstrongNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number can't be negative: " + number);
        }
        return getSum(number) == number;
    }

    private int getSum(int number) {
        int sum = 0;
        String numberString = String.valueOf(number);
        int n = numberString.length();
        for (Character digit: numberString.toCharArray()) {
            sum += Math.pow(Integer.parseInt(digit.toString()), n);
        }
        return sum;
    }
}
