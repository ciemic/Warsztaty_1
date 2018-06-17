package pl.coderslab.zadanie3;
// Komputer zgaduje

import java.util.Scanner;

public class computerGuess {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int min = 0, max = 1000, attempt = 1;
        boolean end = false;
        String startSequence = "";
        boolean trueFalse = false;
        System.out.println("pomyśl liczbę z zakresu 0 - 1000, a ja ją zgadnę w maksymalnie 10 próbach\n" +
                "jeżeli jesteś gotów wpisz \"start\"");
        while (!startSequence.equalsIgnoreCase("start")) {
            startSequence = scanner.next();
        }

        int guess = 0;
        while (!end) {
            guess = compGuess(min, max);
            System.out.println("czy tą liczbą jest: " + guess);

            trueFalse = trueOrFalse("tak", "nie");
            if (!trueFalse) {
                System.out.println("za dużo?");
                trueFalse = trueOrFalse("tak", "nie");
                if (!trueFalse) {
                    System.out.println("za mało?");
                    trueFalse = trueOrFalse("tak", "nie");
                    if (!trueFalse) {
                        System.out.println("Nie oszukuj! Spróbujmy jeszcze raz");
                        min = 0;
                        max = 1000;
                        attempt = 1;
                    } else if (trueFalse) {
                        min = guess;
                        attempt++;
                    }
                } else if (trueFalse) {
                    max = guess;
                    attempt++;
                }
            } else if (trueFalse) {
                end = true;
            }
            if (attempt > 10) {
                System.out.println("Oszukujesz! Spróbujmy jeszcze raz!");
                attempt = 1;
            }
        }
        System.out.println(String.format("Wygrałem!\nZajeło mi to %s prób", attempt));

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int compGuess(int min, int max) {

        return ((max - min) / 2) + min;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean trueOrFalse(String expectedEntryTrue, String expectedEntryFalse) {
        Scanner scanner = new Scanner(System.in);
        String entry = "";
        entry = scanner.next();
        while (!entry.equalsIgnoreCase(expectedEntryTrue) && !entry.equalsIgnoreCase(expectedEntryFalse)) {
            System.out.println(String.format("odpowiedz %s, lub %s", expectedEntryTrue, expectedEntryFalse));
            entry = scanner.next();
        }
        if (expectedEntryFalse.equalsIgnoreCase(entry))
            return false;
        else
            return true;

    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static int getInt() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        return scanner.nextInt();
    }
}
