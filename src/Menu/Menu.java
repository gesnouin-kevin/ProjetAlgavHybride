package Menu;
import java.io.IOException;
import java.util.Scanner;

import Fichier.CreationFichier;


public class Menu {

	public static void main(String args[]) throws IOException{
		menu();
	}
	public static void menu() throws IOException{
		Scanner clavier= new Scanner(System.in);
		System.out.println("|------------------------- MENU -------------------------------------------------|");
		System.out.println("|-------------------- 0 °) Quitter ----------------------------------------------|");
		System.out.println("|-------------------- 1 °) Creer un Fichier -------------------------------------|");
		System.out.println("|-------------------- 2 °) Menu ArbreBriandais ----------------------------------|");
		System.out.println("|-------------------- 3 °) Menu ArbreHybride  -----------------------------------|");
		System.out.println("|-------------------- 4 °) Comparaison  -----------------------------------------|");
		System.out.println("|--------------------------------------------------------------------------------|");
		int choix = clavier.nextInt();
		switch(choix){
		case 0:
			System.out.println("Merci d'avoir utilisé notre application, Au revoir.");
			System.exit(0);
			break;
		case 1:
			CreationFichier fich =new CreationFichier();
			fich.menuCreationFichier();
			menu();
			break;
		case 2:
			MenuBriandais.menuArbreBriandais();
			break;
		case 3:
			MenuHybride.menuArbreHybride();
		break;
		case 4:
			MenuComparaison.menuComparaison();
		break;
		default:
			System.out.println("Erreur dans votre choix recommencer : ");
			menu();
			break;
		}
	}

}
