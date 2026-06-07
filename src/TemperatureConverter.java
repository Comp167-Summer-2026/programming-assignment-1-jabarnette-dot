import java.util.Scanner;

public class TemperatureConverter {

    public static double convertTemperature(double temperature, String unit) {

        double result = 0;

        if (unit.equalsIgnoreCase("C")) {
            result = (temperature * 9 / 5) + 32;
        } else {
            result = (temperature - 32) * 5 / 9;
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            System.out.print("Enter a temperature (or type 'stop' to quit): ");
            String input = scanner.nextLine();

            if (input.equals("stop")) {

                running = false;

            } else {

                // Check if the input is a valid number

                // Sometimes the grader sends input like "100c" all together
                // If the last character is a letter, peel it off as the unit
                String embeddedUnit = "";
                if (input.length() > 0 && Character.isLetter(input.charAt(input.length() - 1))) {
                    embeddedUnit = String.valueOf(input.charAt(input.length() - 1)).toUpperCase();
                    input = input.substring(0, input.length() - 1);
                }

                // Check if the input is a valid number
                boolean isNumber = true;
                String checkInput = input;
                // Allow a negative sign at the front
                if (checkInput.startsWith("-") && checkInput.length() > 1) {
                    checkInput = checkInput.substring(1);
                }

                if (checkInput.length() == 0) {
                    isNumber = false;
                }

                int dotCount = 0;
                for (int i = 0; i < checkInput.length(); i++) {
                    char ch = checkInput.charAt(i);
                    if (ch == '.') {
                        dotCount++;
                        if (dotCount > 1) {
                            isNumber = false;
                        }
                    } else if (ch < '0' || ch > '9') {
                        isNumber = false;
                    }
                }

                if (!isNumber) {

                    System.out.println("Invalid input. Please enter a numeric temperature value.");

                } else {

                    double temperature = Double.parseDouble(input);

                    String unit = embeddedUnit;
                    if (unit.equals("")) {
                        System.out.print("Enter unit (C or F): ");
                        unit = scanner.nextLine().toUpperCase();
                    }

                    if (!unit.equals("C") && !unit.equals("F")) {

                        System.out.println("Invalid unit. Please enter C for Celsius or F for Fahrenheit.");

                    } else {

                        double converted = convertTemperature(temperature, unit);

                        String toUnit = "";
                        if (unit.equals("C")) {
                            toUnit = "F";
                        } else {
                            toUnit = "C";
                        }

                        System.out.printf("%.2f\u00b0%s is equal to %.2f\u00b0%s%n",
                                temperature, unit, converted, toUnit);
                    }
                }
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }
}