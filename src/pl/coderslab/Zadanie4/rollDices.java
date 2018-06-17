package pl.coderslab.Zadanie4;

import java.util.Random;
import java.util.Scanner;

public class rollDices {
    public static void main(String[] args) {
        String entry = "";
        int indexD = 0;
        int indexPlusMinus = 0;
        int result = 0;
        int x = 0, y = 0, z = 0;
        boolean sign = true;// dla sprawdzenia znaku +/-

        System.out.println("Podaj wartości według wzoru: \n" +
                "xDy+z\n" +
                "Gdzie :\n" +
                "y – rodzaj kostek, których należy użyć (np. D6, D10),\n" +
                "x – liczba rzutów kośćmi; jeśli rzucamy raz, ten parametr jest pomijalny,\n" +
                "z – liczba, którą należy dodać (lub odjąć) do wyniku rzutów (opcjonalnie)");
        do {
            entry = getStringSeq();
            indexD = getIndexNumber(entry, 'D');
            if (indexD < 0)
                System.out.println("niepoprawna składnia. spróbuj jeszcze raz");
        } while (indexD < 0);

        x = calculateMultiplier(entry, 0, indexD);             // obliczamy wartość x
        if (x == 0)                                                  // jeżeli nie podano x
            x = 1;                                                   // x wynosi 1

        indexPlusMinus = getIndexNumber(entry, '+');        // sprawdzamy czy i na którym miejscu w ciągu znaków znajduje się '+'
        if (indexPlusMinus < 0) {                                   // jeżeli nie ma znaku plus
            indexPlusMinus = getIndexNumber(entry, '-');     // sprawdzamy czy znajduje się znak '-'
            sign = false;                                           // zmieniamy wartość sign na false - znak minus
        }

        if (indexPlusMinus > 0) {                                   // jeżeli podano z
            y = calculateMultiplier(entry, indexD + 1, indexPlusMinus); // obliczmy y
            z = calculateMultiplier(entry, indexPlusMinus + 1, entry.length());     // obliczamy z
            if (sign == false)              // sprawdzamy czy z jest ujemne
                z = -1 * z;
        } else {
            y = calculateMultiplier(entry, indexD + 1, entry.length());     // obliczamy y jeżeli nie podano z
        }

        try {
            for (int i = 0; i < x; i++) { // obliczamy wynik
                int roll = randomRoll(y);
                result += roll;
                System.out.println("kolejne rzuty kości: " + roll);
            }
            result += z;
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(String.format("Suma rzutów wynosi:  %s", result));

    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    public static int randomRoll(int boundary) {
        Random rand = new Random();
        return rand.nextInt(boundary) + 1;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    public static String getStringSeq() {
        Scanner scan = new Scanner(System.in);
        return scan.next();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    public static int getIndexNumber(String stringSeq, char character) {
        for (int i = 0; i < stringSeq.length(); i++) {
            if (stringSeq.charAt(i) == character) {
                return i;
            }
        }
        return -1;                                                          // zwracamy minus 1 jeżeli nie znaleziono elementu
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    public static int calculateMultiplier(String stringSeq, int index1, int index2) {
        int returnValue = 0;
        String tempString = "";
        for (int i = index1; i < index2; i++) {
            tempString = String.valueOf(stringSeq.charAt(i));       // sprawdzamy znak o indeksie i w naszym ciągu znaków
            try {
                returnValue += Integer.parseInt(tempString) * Math.pow(10, index2 - (i + 1));       // zamieniamy go na wartość int i mnożymy odpowiednią potęgą 10
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return returnValue;
    }
}
