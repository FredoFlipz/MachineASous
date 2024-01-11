package org.example.Class;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ColumnsHandler {
    public List<String> c1;
    public List<String> c2;
    public List<String> c3;

    public ColumnsHandler(List<String> c1, List<String> c2, List<String> c3) {
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
    }

    public ColumnsHandler(){}

    /**
     * Récupère les valeurs contenues dans un fichier json afin de construire une liste de liste de chaine de caractère.
     * Cette dernière permettra de stocker les différentes valeurs pour le jeu du casino.
     * @throws IOException
     */
    public void deserializeValuesJson() throws IOException {
        Gson gson = new Gson();
        InputStream inputStream = ColumnsHandler.class.getResourceAsStream("/columns.json");
        List<List<String>> list = gson.fromJson(new InputStreamReader(inputStream), new TypeToken<List<List<String>>>() {}.getType());

        c1 = list.get(0);
        c2 = list.get(1);
        c3 = list.get(2);
    }

    private List<String> get3RandomValues(List<String> list, Random random) {
        int randValue = random.nextInt(list.size());
        List<String> liste = new ArrayList<>();

        if(randValue < 13){
            liste.add(list.get(randValue));
            liste.add(list.get(++randValue));
            liste.add(list.get(++randValue));
        }
        else if (randValue == 13){
            liste.add(list.get(randValue));
            liste.add(list.get(++randValue));
            liste.add(list.get(0));
        }
        else {
            liste.add(list.get(randValue));
            liste.add(list.get(0));
            liste.add(list.get(1));
        }
        return liste;
    }

    /**
     * Récupère 3 valeurs de façon au hasard parmis la première liste de string afin d'afficher la première colonne du jeu
     * @return
     */
    public List<String> getRandomValuesC1(){
        return get3RandomValues(c1, new Random());
    }

    /**
     * Récupère 3 valeurs de façon au hasard parmis la deuxième liste de string afin d'afficher la deuxième colonne du jeu
     * @return
     */
    public List<String> getRandomValuesC2(){
        return get3RandomValues(c2, new Random());
    }

    /**
     * Récupère 3 valeurs de façon au hasard parmis la troisième liste de string afin d'afficher la troisième colonne du jeu
     * @return
     */
    public List<String> getRandomValuesC3(){
        return get3RandomValues(c3, new Random());
    }

    /**
     * R
     * @return Une liste de liste de string correspondant aux valeur qui seront afficher pour le jeu du casino
     */
    public List<List<String>> get9Values(){
        return Arrays.asList(
                getRandomValuesC1(),
                getRandomValuesC2(),
                getRandomValuesC3()
                );
    }
}
