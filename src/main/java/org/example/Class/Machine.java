package org.example.Class;

import java.util.Arrays;
import java.util.List;

public class Machine {
    public int coins = 0;
    public String[][] matrix;
    private InfosJoueur infosJoueur;

    public  Machine(InfosJoueur infosJoueur){
        this.infosJoueur = infosJoueur;
        matrix = new String[3][3];
    }

    private final String MESSAGE_VICTOIRE_BLEU = "\u001B[34m !!! BRAVO !!!\u001B[0m";
    private final String MESSAGE_DEFAITE_ROUGE = "\n\u001B[31mVous avez perdu\u001B[0m\n";

    /**
     * Gère le fonctionnement d'un tour du jeu.
     * @param columnsHandler
     * @param prixPartie
     */
    public void lancerMachine(ColumnsHandler columnsHandler, int prixPartie){
        infosJoueur.setCoins(infosJoueur.getCoins() - prixPartie);
        infosJoueur.setGames_played(infosJoueur.getGames_played() + 1);
        List<List<String>> listeValues = columnsHandler.get9Values();
        chargerMatrix(listeValues);
        afficherMatrix();
        gagner(prixPartie);
    }

    /**
     * Affecte les valeur reçu en parametre dans la matrix.
     * @param colonnes
     */
    private void chargerMatrix(List<List<String>> colonnes){
        int j = 0;
        for(List<String> c : colonnes){
            int i = 0;
            for (String str : c){
                switch (str){
                    case "7" :
                        str = "*7*";
                        break;
                    case "BAR":
                        break;
                    default:
                        str = "("+str+")";
                }
                matrix[i][j] = str;
                i++;
            }
            j++;
        }
    }

    /**
     * Affiche chaque élément de la matrix afin de former un carré
     */
    private void afficherMatrix(){
        Arrays.stream(matrix).forEach(row -> {
            Arrays.stream(row).forEach(cell -> {
                System.out.print(cell + " | ");
            });
            System.out.println();
        });
    }

    private void gagner(Integer cout){
        switch (cout){
            case 1:
                cas1Jeton();
                break;
            case 2:
                cas2Jeton();
                break;
            case 3:
                cas3Jeton();
                break;
            default:
                System.out.println("Saisie incorrecte /!\\");
        }
    }

    private void attribuerCoins(String str){
        switch (str){
            case "*7*":
                modifierInfoJoueur(300);
                break;
            case "BAR":
                modifierInfoJoueur(100);
                break;
            case "(R)", "(P)", "(T)":
                modifierInfoJoueur(15);
                break;
            case "(C)":
                modifierInfoJoueur(8);
                break;
        }
    }

    private void modifierInfoJoueur(int coins){
        infosJoueur.setCoins(infosJoueur.getCoins() + coins);
        infosJoueur.setGames_won(infosJoueur.getGames_won() + 1);
        infosJoueur.setCoins_spent(infosJoueur.getCoins_spent() + coins);
        System.out.print("\nGains : " + coins);
    }

    private void cas1Jeton(){
        if(isLigne2()){
            System.out.println(MESSAGE_VICTOIRE_BLEU);
        }
        else {
            System.out.println(MESSAGE_DEFAITE_ROUGE);
        }
    }
    private void cas2Jeton(){
        if(isLigne1() || isLigne2() || isLigne3()){
            System.out.println(MESSAGE_VICTOIRE_BLEU);
        }
        else {
            System.out.println(MESSAGE_DEFAITE_ROUGE);
        }
    }
    private void cas3Jeton(){
        if(isLigne1() || isLigne2() || isLigne3() || isDiagonale1() || isDiagonale2()){
            System.out.println(MESSAGE_VICTOIRE_BLEU);
        }
        else {
            System.out.println(MESSAGE_DEFAITE_ROUGE);
        }
    }

    private boolean isLigne1(){
        String str = matrix[0][0];
        if(str == matrix[0][1] && str == matrix[0][2]){
            attribuerCoins(str);
            return true;
        }
        return false;
    }
    private boolean isLigne2(){
        String str = matrix[1][0];
        if(str == matrix[1][1] && str == matrix[1][2]){
            attribuerCoins(str);
            return true;
        }
        return false;
    }
    private boolean isLigne3(){
        String str = matrix[2][0];
        if(str == matrix[2][1] && str == matrix[2][2]){
            attribuerCoins(str);
            return true;
        }
        return false;
    }
    private boolean isDiagonale1(){
        String str = matrix[0][0];
        if(str == matrix[1][1] && str == matrix[2][2]){
            attribuerCoins(str);
            return true;
        }
        return false;
    }
    private boolean isDiagonale2(){
        String str = matrix[2][0];
        if(str == matrix[1][1] && str == matrix[0][2]){
            attribuerCoins(str);
            return true;
        }
        return false;
    }
}
