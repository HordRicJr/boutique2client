/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package tg.univlome.epl.dad.boutique2.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import tg.univlome.epl.dad.boutique2.entites.Produit;

/**
 *
 * @author Hordric
 */
public class Boutique2Client {

    private static final String URL = "http://localhost:8080/boutique2/rs/produit";

    public static void main(String[] args) {
        Produit p1 = new Produit(1, "Kouklui", 200, LocalDate.of(2027, 06, 07), null);
        Produit p2 = new Produit(2, "Vodka", 300, LocalDate.of(2026, 11, 07), null);

        ajouter(p1);
        ajouter(p2);

        List<Produit> liste = lister();
        for (Produit produit : liste) {
            System.out.println(produit);
            compter();
        }
    }

    private static void ajouter(Produit p) {

        Client client = ClientBuilder.newClient();
        Response reponse = client.target(URL)
                .request()
                .post(Entity.json(p));

        if (reponse.getStatus() == 200) {
            System.out.println("Produit ajouter avec succès.");
        } else {
            System.out.println("Erreur:" + reponse.getStatus());
        }
    }

    private static List<Produit> lister() {

        Client client = ClientBuilder.newClient();
        Response reponse = client.target(URL)
                .path("lister")
                .request()
                .get();
        if (reponse.getStatus() == 200) {
            return reponse.readEntity(new GenericType<List<Produit>>() {
            });
        } else {
            System.out.println("Erreur:" + reponse.getStatus());
            return new LinkedList<>();
        }

    }

    private static void compter() {
        Client client = ClientBuilder.newClient();
        Response reponse = client.target(URL)
                .path("compter")
                .request()
                .get();
        if (reponse.getStatus() == 200) {
            System.out.println("Produits compter avec succès.");
        } else {
            System.out.println("Erreur:" + reponse.getStatus());
        }
    }

    private static void supprimer(Integer id) {
        Client client = ClientBuilder.newClient();
        Response reponse = client.target(URL)
                .path("supprimer")
                .path(String.valueOf(id))
                .request()
                .get();
        if (reponse.getStatus() == 200) {
            System.out.println("Produits supprimer avec succès.");
        } else {
            System.out.println("Erreur:" + reponse.getStatus());
        }
    }

    private static void trouver(Integer id) {
        Client client = ClientBuilder.newClient();
        Response reponse = client.target(URL)
                .path("lister")
                .path(String.valueOf(id))
                .request()
                .get();
        if (reponse.getStatus() == 200) {
            System.out.println("Produits compter avec succès.");
        } else {
            System.out.println("Erreur:" + reponse.getStatus());
        }
    }
}



