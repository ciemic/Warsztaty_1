package pl.coderslab.zadanie1;

import java.util.Random;
import java.util.Scanner;

public class GuessNumber {


    public static void main(String[] args) {
        guessNumber();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void guessNumber() {
        int randomNumber = randomInt(100);
        int guess = 0;

        guess = getInt("podaj liczbę z zakresu od 1 - 100","to nie jest liczba");
        while (guess != randomNumber) {
            if (guess < randomNumber)
                guess = getInt("Za mało!","");
            else
                guess = getInt("podałeś zbyt dużą wartość","");

        }
        System.out.println("Zgadłeś!");

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static int randomInt(int size) {
        Random random = new Random();

        return random.nextInt(size + 1);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static int getInt(String name, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(String.format("podaj %s: ", name));
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println(String.format("%s\n%s: ", errorMessage, name));
        }
        return scanner.nextInt();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////


}
