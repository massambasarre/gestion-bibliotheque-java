package projet2;
import projet2.Livre;
import java.util.HashMap;
import java.util.Map;

public class GestionEmprunt {
    private Map<String, Livre> livresDisponibles;
    private Map<String, Livre> livresEmpruntes;

    public GestionEmprunt() {
        livresDisponibles = new HashMap<>();
        livresEmpruntes = new HashMap<>();
        livresDisponibles.put("", new Livre("", "", "", "")); // Initialize with an empty Livre object
        livresEmpruntes.put("", new Livre("", "", "", "")); // Initialize with an empty Livre object
    }

    // Ajouter un livre disponible pour l'emprunt
    public void ajouterLivreDisponible(Livre livre) {
        livresDisponibles.put(livre.getIsbn(), livre);
        System.out.println("Livre ajouté aux livres disponibles : " + livre);
        System.out.println("Livres disponibles après l'ajout : " + livresDisponibles.keySet());
    }

    // Emprunter un livre
    public void emprunterLivre(String isbn) {
        System.out.println("Livres disponibles avant l'emprunt : " + livresDisponibles.keySet()); // Afficher les ISBN des livres disponibles avant l'emprunt

        Livre livre = livresDisponibles.get(isbn);
        if (livre != null && livre.isDisponible()) {
            livresDisponibles.remove(isbn);
            livresEmpruntes.put(isbn, livre);
            livre.setDisponible(false); // Marquer le livre comme emprunté
            System.out.println("Livre emprunté avec succès : " + livre);
        } else {
            if (livre == null) {
                System.out.println("Livre non trouvé dans les livres disponibles.");
            } else {
                System.out.println("Livre déjà emprunté.");
            }
        }

        System.out.println("Livres disponibles après l'emprunt : " + livresDisponibles.keySet()); // Afficher les ISBN des livres disponibles après l'emprunt
    }

    // Rendre un livre emprunté
    public void rendreLivre(String isbn) {
        Livre livre = livresEmpruntes.get(isbn);
        if (livre != null) {
            livresEmpruntes.remove(isbn);
            livresDisponibles.put(isbn, livre);
            livre.setDisponible(true); // Marquer le livre comme disponible
            System.out.println("Livre rendu avec succès : " + livre);
        } else {
            System.out.println("Livre non trouvé ou déjà rendu.");
        }
    }

    // Afficher les livres disponibles pour l'emprunt
    public void afficherLivresDisponibles() {
        System.out.println("Livres disponibles pour l'emprunt :");
        for (Livre livre : livresDisponibles.values()) {
            System.out.println(livre);
        }
    }

    // Afficher les livres empruntés
    public void afficherLivresEmpruntes() {
        System.out.println("Livres empruntés :");
        for (Livre livre : livresEmpruntes.values()) {
            System.out.println(livre);
        }
    }
}