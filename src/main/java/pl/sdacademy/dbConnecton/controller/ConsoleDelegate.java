package pl.sdacademy.dbConnecton.controller;

import java.util.Scanner;

public class ConsoleDelegate {

    public void printMessage(String message) {
        System.out.println(message);
    }


    public String askUserForText(String question) {
        System.out.print(" > " + question + ": ");
        return getUserText();
    }

    private String getUserText() {
        return new Scanner(System.in).nextLine();
    }

    public Long askUserForNumber(String question) {
        Long number = null;
        while (number == null) {
            System.out.print(" > " + question + ": ");
            number = getUserNumber();
        }
        return number;
    }

    private Long getUserNumber() {
        try {
            return Long.parseLong(getUserText());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Try again.");
        }
        return null;
    }
}
