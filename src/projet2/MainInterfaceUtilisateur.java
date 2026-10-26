package projet2;
import java.util.Scanner;
import projet2.GestionEmprunt;
import projet2.Livre;

public class MainInterfaceUtilisateur {

    private static GestionLivres gestionLivres = new GestionLivres();
    private static GestionEmprunt gestionEmprunt = new GestionEmprunt();
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        afficherMenuPrincipal();

        // Add some books to the library
        gestionEmprunt.ajouterLivreDisponible(new Livre("123", "Book Title 1", "Author 1", "Genre 1"));
        gestionEmprunt.ajouterLivreDisponible(new Livre("456", "Book Title 2", "Author 2", "Genre 2"));

        // Emprunt a book
        gestionEmprunt.emprunterLivre("123");
    }

    private static void afficherMenuPrincipal() {
        int choix;
        do {
            System.out.println("\n\n------------------------------------------------------------------------------------");
            System.out.println("\tUQTR\t--- STRUCTURES DE DONNEES ET ALGORITHMES\t---DEVELOPPEMENT DE LOGICIELS");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("\t\t\t\t |Khadidjaa|");
            System.out.println("\t\t\t\t Projet2| Java");
            System.out.println("-------------------------------------------------------------------------------------\n");
            System.out.println("----------------------------------  MENU  -------------------------------------------\n");
            System.out.println("\n=== Gestion de Bibliothèque ===");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Rechercher un livre");
            System.out.println("3. Gérer les emprunts");
            System.out.println("0. Quitter");
            System.out.println("--------------------------------");
            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne après nextInt()

            switch (choix) {
                case 1:
                    ajouterLivre();
                    break;
                case 2:
                    menuRechercherLivre();
                    break;
                case 3:
                    gererEmprunts();
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 0);
    }

    private static void ajouterLivre() {
        System.out.println("\n=== Ajouter un livre ===");
        System.out.print("ISBN : ");
        String isbn = scanner.nextLine();
        System.out.print("Titre : ");
        String titre = scanner.nextLine();
        System.out.print("Auteur : ");
        String auteur = scanner.nextLine();
        System.out.print("Genre : ");
        String genre = scanner.nextLine();

        gestionLivres.ajouterLivre(isbn, titre, auteur, genre);
    }

    private static void menuRechercherLivre() {
        int choix;
        do {
            System.out.println("\n=== Menu Recherche de Livre ===");
            System.out.println("1. Rechercher par ISBN");
            System.out.println("2. Rechercher par Titre");
            System.out.println("3. Rechercher par Auteur");
            System.out.println("4. Rechercher par Genre");
            System.out.println("0. Retour au menu principal");
            System.out.println("--------------------------------");
            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne après nextInt()

            switch (choix) {
                case 1:
                    rechercherParISBN();
                    break;
                case 2:
                    rechercherParTitre();
                    break;
                case 3:
                    rechercherParAuteur();
                    break;
                case 4:
                    rechercherParGenre();
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 0);
    }

    private static void rechercherParISBN() {
        System.out.println("\n=== Rechercher un livre par ISBN ===");
        System.out.print("ISBN du livre : ");
        String isbn = scanner.nextLine();

        Livre livre = gestionLivres.rechercherLivreParISBN(isbn);
        if (livre != null) {
            System.out.println("Livre trouvé :");
            System.out.println(livre);
        } else {
            System.out.println("Livre non trouvé.");
        }
    }

    private static void rechercherParTitre() {
        System.out.println("\n=== Rechercher un livre par Titre ===");
        System.out.print("Titre du livre : ");
        String titre = scanner.nextLine();

        Livre livre = gestionLivres.rechercherParTitre(titre);
        if (livre != null) {
            System.out.println("Livre trouvé :");
            System.out.println(livre);
        } else {
            System.out.println("Livre non trouvé.");
        }
    }

    private static void rechercherParAuteur() {
        System.out.println("\n=== Rechercher un livre par Auteur ===");
        System.out.print("Auteur du livre : ");
        String auteur = scanner.nextLine();

        Livre livre = gestionLivres.rechercherParAuteur(auteur);
        if (livre != null) {
            System.out.println("Livre trouvé :");
            System.out.println(livre);
        } else {
            System.out.println("Livre non trouvé.");
        }
    }

    private static void rechercherParGenre() {
        System.out.println("\n=== Rechercher un livre par Genre ===");
        System.out.print("Genre du livre : ");
        String genre = scanner.nextLine();

        Livre livre = gestionLivres.rechercherParGenre(genre);
        if (livre != null) {
            System.out.println("Livre trouvé :");
            System.out.println(livre);
        } else {
            System.out.println("Livre non trouvé.");
        }
    }

    private static void gererEmprunts() {
        int choix;
        do {
            System.out.println("\n=== Gérer les emprunts ===");
            System.out.println("1. Emprunter un livre");
            System.out.println("2. Rendre un livre");
            System.out.println("0. Retour au menu principal");
            System.out.println("--------------------------------");
            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne après nextInt()

            switch (choix) {
                case 1:
                    emprunterLivre();
                    break;
                case 2:
                    rendreLivre();
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 0);
    }

    private static void emprunterLivre() {
        System.out.println("\n=== Emprunter un livre ===");
        System.out.print("ISBN du livre à emprunter : ");
        String isbn = scanner.nextLine();

        gestionEmprunt.emprunterLivre(isbn);
    }

    private static void rendreLivre() {
        System.out.println("\n=== Rendre un livre ===");
        System.out.print("ISBN du livre à rendre : ");
        String isbn = scanner.nextLine();

        gestionEmprunt.rendreLivre(isbn);
    }
}
