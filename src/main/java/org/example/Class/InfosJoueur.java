package org.example.Class;

import com.google.gson.Gson;

import java.io.*;

public class InfosJoueur {
    private int coins;
    private int games_played;
    private int games_won;
    private int coins_spent;

    public InfosJoueur(){
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getGames_played() {
        return games_played;
    }

    public void setGames_played(int games_played) {
        this.games_played = games_played;
    }

    public int getGames_won() {
        return games_won;
    }

    public void setGames_won(int games_won) {
        this.games_won = games_won;
    }

    public int getCoins_spent() {
        return coins_spent;
    }

    public void setCoins_spent(int coins_spent) {
        this.coins_spent = coins_spent;
    }

    /**
     *Récupère les valeurs contenues dans un fichier json afin de construire un objet "InfosJoueur".
     * @return Retourne un objet "InfosJoueur" qui stocke les informations concernant les parties effectuées par ce dernier.
     * @throws IOException
     */
    public InfosJoueur deserializeInfosJoueurJson() throws IOException {
        Gson gson = new Gson();
        InputStream inputStream = ColumnsHandler.class.getResourceAsStream("/infos.json");
        return gson.fromJson(new InputStreamReader(inputStream), InfosJoueur.class);
    }

    /**
     *Enregistre les informations de l'objet "InfosJoueur" en format json.
     * @param infosJoueur
     * @throws IOException
     */
    public void serializeInfosJoueur(InfosJoueur infosJoueur) throws IOException {
        Gson gson = new Gson();
        String str = gson.toJson(infosJoueur);
        try(FileWriter writer = new FileWriter("C:\\Git\\MachineSousCasino\\src\\main\\resources\\infos.json")){
            writer.write(str);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
