package org.mrkagelui.ArithmeticEvaluator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Parser parser;
        while (true) {
            System.out.println("Please key in something to evaluate");
            String line = sc.nextLine();
            if (line.equalsIgnoreCase("q")) {
                System.out.println("Exiting..");
                break;
            }
            try {
                String tidied = line.replaceAll("\\s", "");
                parser = new SimpleParser(tidied);
                Node n = parser.parse();
                System.out.println("=" + n.evaluate());
            } catch (MalformedEquationException e) {
                System.out.println("Malformed equation: " + e.getMessage());
            } catch (ArithmeticException e) {
                System.out.println("Arithmetic exception: " + e.getMessage());
            }
        }
    }
}
