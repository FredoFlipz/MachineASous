package org.example;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.util.Scanner;
import org.example.Class.ColumnsHandler;
import org.example.Class.InfosJoueur;
import org.example.Class.Machine;
import java.io.IOException;

public class Program {
    private static final String PRESENTATION = "°º¤ø,¸¸,ø¤º°`°º¤ø,¸,ø¤°º¤ø,¸¸,ø¤º°`\nBienvenue au Casino de Céladopole !\n°º¤ø,¸¸,ø¤º°`°º¤ø,¸,ø¤°º¤ø,¸¸,ø¤º°`\n";

    public static void main(String[] args) throws IOException {

        ColumnsHandler columnsHandler = new ColumnsHandler();
        columnsHandler.deserializeValuesJson();

        InfosJoueur infosJoueur = new InfosJoueur();
        infosJoueur = infosJoueur.deserializeInfosJoueurJson();


        System.out.println(PRESENTATION);

        Machine machine = new Machine(infosJoueur);

        while (true){
            System.out.println("Vous possédez ["+ infosJoueur.getCoins() + "] jeton(s)");
            int nb = choixPartie();
            if(nb == 0){
                System.out.println("Vous avez quitté le jeu");
                infosJoueur.serializeInfosJoueur(infosJoueur);
                System.exit(0);
            }
            machine.lancerMachine(columnsHandler, nb);
        }
    }

    private static int choixPartie(){
        Scanner scanner = new Scanner(System.in);
        // Saisie du nombre de jetons
        int selectedTokens = promptForTokens(scanner);

        // Vérification avec StringUtils
        if (isValidTokenSelection(selectedTokens) && selectedTokens == 0) {
            return selectedTokens;
        } else if (isValidTokenSelection(selectedTokens)) {
            System.out.println("Sélection valide : " + selectedTokens + " jeton(s)");
        } else {
            System.out.println("Sélection invalide. Veuillez choisir 1, 2 ou 3 jetons.");
        }
        return selectedTokens;
    }

    private static int promptForTokens(Scanner scanner) {
        int selectedTokens;

        while (true) {
            System.out.print("\nChoisissez le nombre de jetons (1, 2 ou 3) : ");
            String input = scanner.nextLine();

            // Vérification si la saisie est un entier
            try {
                if(input.equals("quit")){
                    selectedTokens = 0;
                    break;
                }
                else{
                    selectedTokens = Integer.parseInt(input);
                }

                // Vérification si la saisie est 1, 2 ou 3
                if (selectedTokens >= 1 && selectedTokens <= 3) {
                    break;
                } else {
                    System.out.println("Veuillez choisir 1, 2 ou 3 jetons.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Veuillez saisir un chiffre 1, 2 ou 3.");
            }
        }
        return selectedTokens;
    }

    private static boolean isValidTokenSelection(int selectedTokens) {
        String validOptions = "0123";
        return StringUtils.contains(validOptions, Integer.toString(selectedTokens));
    }
}