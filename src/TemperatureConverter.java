import java.util.Scanner;

public class TemperatureConverter {

    // This method takes a temperature and its unit, then returns the converted value
    public static double convertTemperature(double temperature, String unit) {

        double result = 0;

        if (unit.equals("C")) {
            // Celsius to Fahrenheit formula
            result = (temperature * 9 / 5) + 32;
        } else {
            // Fahrenheit to Celsius formula
            result = (temperature - 32) * 5 / 9;
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String input = "";
        boolean running = true;

        while (running) {

            // Ask the user to enter a temperature or stop
            System.out.print("Enter a temperature (or type 'stop' to quit): ");
            input = scanner.nextLine();

            // Check if the user wants to stop
            if (input.equals("stop")) {
                running = false;

            } else {

                // Sometimes the input has the unit stuck on the end like "100c"
                // If the last character is a letter, we split it off
                String embeddedUnit = "";
                char lastChar = input.charAt(input.length() - 1);
                if (Character.isLetter(lastChar)) {
                    embeddedUnit = String.valueOf(lastChar).toUpperCase();
                    input = input.substring(0, input.length() - 1);
                }

                // Check if the input is actually a number
                boolean isNumber = true;

                // We allow a leading minus sign for negative numbers
                String checkInput = input;
                if (checkInput.startsWith("-") && checkInput.length() > 1) {
                    checkInput = checkInput.substring(1);
                }

                // Check each character to make sure it's a digit or a decimal point
                int dotCount = 0;
                for (int i = 0; i < checkInput.length(); i++) {
                    char c = checkInput.charAt(i);
                    if (c == '.') {
                        dotCount++;
                        if (dotCount > 1) {
                            isNumber = false;
                        }
                    } else if (c < '0' || c > '9') {
                        isNumber = false;
                    }
                }

                if (checkInput.length() == 0) {
                    isNumber = false;
                }

                if (!isNumber) {
                    // Tell the user their input wasn't a valid number
                    System.out.println("Invalid input. Please enter a numeric temperature value.");

                } else {

                    // Convert the string to an actual number
                    double temperature = Double.parseDouble(input);

                    // If the unit was already attached to the number, use it
                    // Otherwise ask the user for it
                    String unit = embeddedUnit;

                    if (unit.equals("")) {
                        System.out.print("Enter unit (C or F): ");
                        unit = scanner.nextLine().toUpperCase();
                    }

                    // Keep asking until the user gives us a valid unit
                    boolean validUnit = false;

                    if (unit.equals("C") || unit.equals("F")) {
                        validUnit = true;
                    } else {
                        System.out.println("Invalid unit. Please enter C for Celsius or F for Fahrenheit.");
                    }

                    while (!validUnit) {
                        System.out.print("Enter unit (C or F): ");
                        unit = scanner.nextLine().toUpperCase();

                        if (unit.equals("C") || unit.equals("F")) {
                            validUnit = true;
                        } else {
                            System.out.println("Invalid unit. Please enter C for Celsius or F for Fahrenheit.");
                        }
                    }

                    // Do the conversion
                    double converted = convertTemperature(temperature, unit);

                    // Figure out what unit to show in the output
                    String toUnit = "";
                    if (unit.equals("C")) {
                        toUnit = "F";
                    } else {
                        toUnit = "C";
                    }

                    // Print the result formatted to 2 decimal places
                    System.out.printf("%.2f\u00b0%s is equal to %.2f\u00b0%s%n",
                            temperature, unit, converted, toUnit);
                }
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }
}