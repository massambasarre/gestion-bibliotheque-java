package projet2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

// Classe représentant le gestionnaire des livres
public class GestionLivres {
    private List<Livre> livres;

    public GestionLivres() {
        livres = new ArrayList<>();
    }

 // Méthode pour rechercher un livre par ISBN
    public Livre rechercherParISBN(String isbn) {
        for (Livre livre : livres) {
            if (livre.getIsbn().equals(isbn)) {
                return livre;
            }
        }
        return null; // Livre non trouvé
    }
    // Méthode pour ajouter un livre dans la bibliothèque
    public void ajouterLivre(String isbn, String titre, String auteur, String genre) {
        if (livreExiste(isbn)) {
            System.out.println("Livre déjà existant.");
            return;
        }
        Livre livre = new Livre(isbn, titre, auteur, genre);
        livres.add(livre);
        System.out.println("Livre ajouté avec succès : " + livre);
    }

    // Méthode pour mettre à jour les informations d'un livre existant
    public void mettreAJourLivre(String isbn, String nouveauTitre, String nouveauAuteur, String nouveauGenre) {
        Livre livre = rechercherLivreParISBN(isbn);
        if (livre == null) {
            System.out.println("Livre non trouvé.");
            return;
        }
        
        livre.setTitre(nouveauTitre);
        livre.setAuteur(nouveauAuteur);
        livre.setGenre(nouveauGenre);
        System.out.println("Livre mis à jour avec succès : " + livre);
    }

    // Méthode pour trier les livres par critère (titre, auteur, genre, disponibilité)
    public void trierLivres(String critere) {
        Comparator<Livre> comparator;
        switch (critere.toLowerCase()) {
            case "titre":
                comparator = Comparator.comparing(Livre::getTitre);
                break;
            case "auteur":
                comparator = Comparator.comparing(Livre::getAuteur);
                break;
            case "genre":
                comparator = Comparator.comparing(Livre::getGenre);
                break;
            case "disponibilite":
                comparator = Comparator.comparing((Livre livre) -> livre.isDisponible() ? 0 : 1);
                break;
            default:
                throw new IllegalArgumentException("Critère de tri invalide.");
        }
        livres.sort(comparator);
    }

    // Méthode pour rechercher un livre par ISBN
    public Livre rechercherLivreParISBN(String isbn) {
        return livres.stream()
                .filter(livre -> livre.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    // Méthode pour vérifier si un livre existe déjà dans la bibliothèque
    public boolean livreExiste(String isbn) {
        return rechercherLivreParISBN(isbn) != null;
    }

    // Méthode pour rechercher un livre par titre
    public Livre rechercherParTitre(String titre) {
        return livres.stream()
                .filter(livre -> livre.getTitre().equalsIgnoreCase(titre))
                .findFirst()
                .orElse(null);
    }

    // Méthode pour rechercher un livre par auteur
    public Livre rechercherParAuteur(String auteur) {
        return livres.stream()
                .filter(livre -> livre.getAuteur().equalsIgnoreCase(auteur))
                .findFirst()
                .orElse(null);
    }

    // Méthode pour rechercher un livre par genre
    public Livre rechercherParGenre(String genre) {
        return livres.stream()
                .filter(livre -> livre.getGenre().equalsIgnoreCase(genre))
                .findFirst()
                .orElse(null);
    }

    // Méthode pour afficher les détails d'un livre spécifique
    public void afficherDetailsLivre(UUID id) {
        Livre livre = rechercherLivreParId(id);
        if (livre == null) {
            System.out.println("Livre non trouvé.");
        } else {
            System.out.println(livre);
        }
    }

    // Méthode pour rechercher un livre par son ID
    private Livre rechercherLivreParId(UUID id) {
        return livres.stream()
                .filter(livre -> livre.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
 // Méthode pour afficher tous les livres de la bibliothèque
    public void afficherLivres() {
        System.out.println("Liste des livres dans la bibliothèque:");
        for (Livre livre : livres) {
            System.out.println(livre);
        }
    }

    // Méthode pour afficher les premiers livres disponibles
    public void afficherPremiersLivresDisponibles(int n) {
        List<Livre> livresDisponibles = livres.stream()
                .filter(Livre::isDisponible)
                .limit(n)
                .collect(Collectors.toList());

        System.out.println("Premiers livres disponibles :");
        livresDisponibles.forEach(System.out::println);
    }
}
