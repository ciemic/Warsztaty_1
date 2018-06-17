package pl.coderslab.Zadanie2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class lottoSymulator {

    public static void main(String[] args) {

        int result = 0;
        int[] LottoResults = lottoResult();
        //System.out.println(Arrays.toString(LottoResults));
        int[] userChoice = enterUserNumbers(6, "Podaj liczbę od 1-49");
        System.out.println("wybrałeś następujące liczby: " + Arrays.toString(userChoice));
        System.out.println("Wygrywają: " + Arrays.toString(LottoResults));
        result = compareResults(LottoResults, userChoice);

        if (result >= 3) {
            System.out.println(String.format("trafiłeś %s liczb", result));
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////
    public static int compareResults(int[] lottoResults, int[] userNumbers) {       // porównanie dwóch tablic
        int result = 0;

        for (int userNumber : userNumbers) {
            if (isElementInArray(lottoResults, userNumber)) {
                result += 1;
            }
        }
        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////

    public static int[] enterUserNumbers(int arraySize, String message) {
        int number = 0;
        int[] returnArray = new int[arraySize];
        int index = 0;
        while (index < arraySize) {
            number = getNumber(message, "Podaj poprawną liczbę");       // pobieramy nową liczbę od użytkownika
            if (!isElementInArray(returnArray, number)) {                             // jeżeli liczba nie została jeszcze pobrana dopisujemy ją do tablicy
                returnArray[index] = number;
                index++;
            }

        }
        return returnArray;
    }

    ///////////////////////////////////////////////////////////////////////////////
    public static int[] lottoResult() {
        int[] tabLotto = new int[49];
        Random rand = new Random();
        int[] resultArray = new int[6];

        for (int i = 0; i < tabLotto.length; i++) {
            tabLotto[i] = i + 1;
        }
        for (int i = 49; i > 49 - 6; i--) {
            int idx = rand.nextInt(i);      // losowanie indeksu
            resultArray[49 - i] = tabLotto[idx];   // przypisanie do tablicy wyników
            tabLotto[idx] = tabLotto[i - 1];      // zamiana aktualnego indeksu  (dla 49 nie ma żadnej zmiany) z wart ostatniego indexu, w kolejnej iteracji losujemy liczby bez ostatniej wylosowanej
        }

        return resultArray;
    }

    ///////////////////////////////////////////////////////////////////////////////
    public static boolean isElementInArray(int[] array, int newInput) {
        for (int number : array) {
            if (number == newInput)               // sprawdzamy czy element znajduje się w tablicy
                return true;
        }
        return false;
    }


    //////////////////////////////////////////////////////////////////////////////
    public static int getNumber(String name, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(String.format("%s: ", name));
        int number = 0;
        do {
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println(String.format("%s\n%s: ", errorMessage, name));
            }
            number = scanner.nextInt();
        } while (number < 1 && number > 49);

        return number;
    }
}


