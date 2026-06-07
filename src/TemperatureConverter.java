import java.util.Scanner;

public class TemperatureConverter {

    // This method takes a temperature and its unit, then returns the converted value
    public static double convertTemperature(double temperature, String unit) {

        // Handle lowercase and uppercase units
        unit = unit.toUpperCase();

        if (unit.equals("C")) {
            // Celsius to Fahrenheit formula
            return (temperature * 9.0 / 5.0) + 32;
        } else {
            // Fahrenheit to Celsius formula
            return (temperature - 32) * 5.0 / 9.0;
        }
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
            if (input.equalsIgnoreCase("stop")) {
                running = false;

            } else {

                // Sometimes the input has the unit stuck on the end like "100c"
                String embeddedUnit = "";
                char lastChar = input.charAt(input.length() - 1);

                if (Character.isLetter(lastChar)) {
                    embeddedUnit = String.valueOf(lastChar).toUpperCase();
                    input = input.substring(0, input.length() - 1);
                }

                // Check if the input is actually a number
                boolean isNumber = true;

                String checkInput = input;

                // Allow negative numbers
                if (checkInput.startsWith("-") && checkInput.length() > 1) {
                    checkInput = checkInput.substring(1);
                }

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

                    System.out.println("Invalid input. Please enter a numeric temperature value.");

                } else {

                    double temperature = Double.parseDouble(input);

                    String unit = embeddedUnit;

                    if (unit.equals("")) {
                        System.out.print("Enter unit (C or F): ");
                        unit = scanner.nextLine().toUpperCase();
                    }

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

                    // Figure out output unit
                    String toUnit;

                    if (unit.equals("C")) {
                        toUnit = "F";
                    } else {
                        toUnit = "C";
                    }

                    System.out.printf("%.2f°%s is equal to %.2f°%s%n",
                            temperature, unit, converted, toUnit);
                }
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }
}