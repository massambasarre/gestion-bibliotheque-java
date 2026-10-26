package projet2;

import java.util.*;

//Classe représentant un livre
class Livre {
    private UUID id;
    private String isbn;
    private String titre;
    private String auteur;
    private String genre;
    private boolean disponible;

    public Livre(String isbn, String titre, String auteur, String genre) {
        this.id = UUID.randomUUID();
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.genre = genre;
        this.disponible = true; // Initially set as available
    }

    public UUID getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", genre='" + genre + '\'' +
                ", disponible=" + disponible +
                '}';
    }
}

//Classe pour représenter un nœud de l'arbre binaire
class NoeudArbre {
 Livre livre;
 NoeudArbre gauche;
 NoeudArbre droit;

 public NoeudArbre(Livre livre) {
     this.livre = livre;
     this.gauche = null;
     this.droit = null;
 }
}

//Classe représentant une bibliothèque
public class Bibliotheque {
 // Recherche linéaire avec une liste chaînée pour les petites bibliothèques
 private List<Livre> livresLineaire;

 // Recherche binaire pour les bibliothèques triées par titre
 private List<Livre> livresTrieTitre;

 // Recherche binaire pour les bibliothèques triées par auteur
 private List<Livre> livresTrieAuteur;

 // Utilisation d'une table de hachage pour des recherches rapides basées sur l'ISBN
 private Map<String, Livre> livresHachage;

 // Recherche dans un arbre binaire pour les bibliothèques organisées par catégorie ou genre
 private NoeudArbre racineArbre;

 public Bibliotheque() {
     livresLineaire = new ArrayList<>();
     livresTrieTitre = new ArrayList<>();
     livresTrieAuteur = new ArrayList<>();
     livresHachage = new HashMap<>();
     racineArbre = null;
 }

 // Méthode pour ajouter un livre à la bibliothèque
 
 // Méthode récursive pour insérer un livre dans l'arbre binaire par genre
 private NoeudArbre insererDansArbre(NoeudArbre noeud, Livre livre) {
     if (noeud == null) {
         return new NoeudArbre(livre);
     }

     // Comparaison pour décider du côté où insérer le nouveau livre dans l'arbre par genre
     if (livre.getGenre().compareTo(noeud.livre.getGenre()) < 0) {
         noeud.gauche = insererDansArbre(noeud.gauche, livre);
     } else {
         noeud.droit = insererDansArbre(noeud.droit, livre);
     }
     return noeud;
 }

 // Méthode pour effectuer une recherche linéaire par ISBN
 public Livre rechercherParISBN(String isbn) {
     return livresHachage.get(isbn);
 }

 // Méthode pour effectuer une recherche binaire par titre
 public Livre rechercherParTitre(String titre) {
     int index = Collections.binarySearch(livresTrieTitre, new Livre("", titre, "", ""), Comparator.comparing(Livre::getTitre));
     if (index >= 0) {
         return livresTrieTitre.get(index);
     }
     return null; // Livre non trouvé
 }

 // Méthode pour effectuer une recherche binaire par auteur
 public Livre rechercherParAuteur(String auteur) {
     int index = Collections.binarySearch(livresTrieAuteur, new Livre("", "", auteur, ""), Comparator.comparing(Livre::getAuteur));
     if (index >= 0) {
         return livresTrieAuteur.get(index);
     }
     return null; // Livre non trouvé
 }

 // Méthode pour effectuer une recherche dans l'arbre binaire par genre
 public Livre rechercherParGenre(String genre) {
     return rechercherDansArbre(racineArbre, genre);
 }

 // Méthode récursive pour effectuer une recherche dans l'arbre binaire par genre
 private Livre rechercherDansArbre(NoeudArbre noeud, String genre) {
     if (noeud == null) {
         return null; // Livre non trouvé
     }

     int comparaison = genre.compareTo(noeud.livre.getGenre());
     if (comparaison == 0) {
         return noeud.livre;
     } else if (comparaison < 0) {
         return rechercherDansArbre(noeud.gauche, genre);
     } else {
         return rechercherDansArbre(noeud.droit, genre);
     }
 }

 // Méthode pour afficher tous les livres de la bibliothèque
 public void afficherLivres() {
     System.out.println("Liste des livres dans la bibliothèque:");
     for (Livre livre : livresLineaire) {
         System.out.println(livre);
     }
 }
}
