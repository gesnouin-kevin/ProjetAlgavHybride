package Menu;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Scanner;

import Briandais.Briandais;
import Fichier.CreationFichier;
import Hybride.Fenetre;
import Hybride.Hybride;
import StockageArbreHybride.LectureArbreObjetHybride;
import StockageArbreHybride.StockArbreObjetHybride;


public class MenuHybride {
	private static Hybride abrH1 =new Hybride();

	public static void menuArbreHybride() throws IOException { 

		Scanner clavier5 = new Scanner(System.in);
		Scanner clavier6 = new Scanner(System.in);
		Fenetre fen;
		ArrayList<String> motRecup = new ArrayList<String>();
		CreationFichier cf = new CreationFichier();
		Briandais abrB1=new Briandais();

		int choix2=0;
		String mot;
		String nomArbre;
		long debutTime;
		long time;
		long sumtime = 0;
		System.out.println("|------------------------------------ MENU HYBRIDE ----------------------------------------|");
		System.out.println("|-------------------- 0 °) Quitter --------------------------------------------------------|");
		System.out.println("|-------------------- 1 °) Créer l'Arbre à partir d'un Fichier ----------------------------|");
		System.out.println("|-------------------- 2 °) Ajout d'un nouveau mot -----------------------------------------|");
		System.out.println("|-------------------- 3 °) Afficher Arbre Hybride -----------------------------------------|");
		System.out.println("|-------------------- 4 °) Supprimer un mot -----------------------------------------------|");
		System.out.println("|-------------------- 5 °) Rechercher mot -------------------------------------------------|");
		System.out.println("|-------------------- 6 °) Compter mot(s) -------------------------------------------------|");
		System.out.println("|-------------------- 7 °) lister mot(s) (par ordre alphabetique) -------------------------|");
		System.out.println("|-------------------- 8 °) Compter nil(s) -------------------------------------------------|");
		System.out.println("|-------------------- 9 °) Hauteur arbre Hybride ------------------------------------------|");
		System.out.println("|-------------------- 10 °) Profondeur Moyenne --------------------------------------------|");
		System.out.println("|-------------------- 11 °) Prefixe -------------------------------------------------------|");
		System.out.println("|-------------------- 12 °) Conversion en Hybride -> Briandais ----------------------------|");
		System.out.println("|-------------------- 13 °) Oeuvres Shakespeare -------------------------------------------|");
		System.out.println("|-------------------- 14 °) Sauvegarder Arbre Hybride -------------------------------------|");
		System.out.println("|-------------------- 15 °) Charger Arbre Hybride -----------------------------------------|");
		System.out.println("|-------------------- 16 °) Reinitialisation ----------------------------------------------|");
		System.out.println("|-------------------- 17 °) Retour Menu General -------------------------------------------|");
		System.out.println("|------------------------------------------------------------------------------------------|");
		choix2 = clavier5.nextInt();

		switch (choix2) {

		case 0:
			System.out.println("Merci d'avoir utilisé notre application, Au revoir.");
			System.exit(0);
			break;
		case 1:
			motRecup=cf.recupMotFichier(); // recuperation de ts les mots dans une arraylist
			System.out.println("taille"+motRecup.size());
			debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();

			for (int i=0;i<motRecup.size()-1;i++){

				abrH1.ajout(motRecup.get(i));
			}
			time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
			System.out.println("temps d'execution : "+time+" en NANOsecondes");
			System.out.printf("temps d'execution : %.3f en MILIsecondes",(double)time/1000000);
			System.out.println("\n================= ARBRE =================");
			System.out.println("Attendre la fenetre d'affichage s'il vous plait");
			System.out.println();
			fen = new Fenetre(abrH1);
			System.out.println();
			System.out.println();
			menuArbreHybride();
			break;
		case 2:
			System.out.println("Entrer un mot :");
			mot=clavier6.nextLine();
			debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
			abrH1.ajout(mot);
			time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
			System.out.println("temps d'execution : "+time+" en NANOsecondes");
			System.out.printf("temps d'execution : %.3f en MILIsecondes",(double)time/1000000);
			System.out.println("\n================= ARBRE =================");
			System.out.println("Attendre la fenetre d'affichage s'il vous plait");
			System.out.println();
			//fen = new Fenetre(abrH1);
			System.out.println();
			System.out.println();
			menuArbreHybride();
			break;
		case 3:
			System.out.println("\n================= ARBRE =================");
			System.out.println("Attendre la fenetre d'affichage s'il vous plait");
			System.out.println();
			if(abrH1==null)
				System.out.println("Votre arbre est vide .");
			else{
				abrH1.equilibrage();
				fen = new Fenetre(abrH1);
			}
			System.out.println();
			System.out.println();
			menuArbreHybride();
			break;
		case 4:
			System.out.println();
			System.out.println("\n====================================================");
			System.out.println("============= Arbre avant suppression : ============");
			System.out.println("====================================================");
			System.out.println("\n================= ARBRE =================");
			System.out.println("Attendre la fenetre d'affichage s'il vous plait");
			fen = new Fenetre(abrH1);
			System.out.println();
			System.out.println("Entrer le mot à supprimmer : ");
			mot=clavier6.nextLine();
			System.out.println("\n==================================================");
			System.out.println("============= Arbre apres suppression : ============");
			System.out.println("\n==================================================");
			System.out.println("\n================= ARBRE =================");
			System.out.println("Attendre la fenetre d'affichage s'il vous plait");
			debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
			abrH1.suppression(mot);
			time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
			System.out.println("temps d'execution : "+time+" en NANOsecondes");
			System.out.printf("temps d'execution : %.3f en MILIsecondes",(double)time/1000000);
			if(abrH1==null)
				System.out.println("Votre arbre est vide .");
			else
				fen = new Fenetre(abrH1);
			System.out.println();
			System.out.println();
			menuArbreHybride();
			break;
		case 5:
			boolean present;
			System.out.println("Entrer le mot à rechercher : ");
			mot=clavier6.nextLine();
			debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
			present=abrH1.recherche(mot);
			time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
			System.out.println("temps d'execution : "+time+" en NANOsecondes");
			System.out.printf("temps d'execution : %.3f en MILIsecondes",(double)time/1000000);
			if(present)
				System.out.println("\nle mot : "+mot+" est bien present dans l'arbre.");
			else
				System.out.println("\nle mot : "+mot+" n'est pas present dans l'arbre.");
			menuArbreHybride();
			break;
		case 6:
			debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
			System.out.println("le nombre mot dans l'arbre est de  : "+abrH1.nombreMots()+" .");
			time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
			System.out.println("temps d'execution : "+time+" en NANOsecondes");
			System.out.printf("temps d'execution : %.3f en MILIsecondes",(double)time/1000000);
			System.out.println();
			menuArbreHybride();
			break;
		case 7:
			ArrayList<String> dico = new ArrayList<String> ();
			debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
			dico=abrH1.listMots();
			time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
			System.out.println("temps d'execution : "+time+" en NANOsecondes");
			System.out.printf("temps d'execution : %.3f en MILIsecondes",(double)time/1000000);
			System.out.println("dictionaire :");
			System.out.println(dico);
			System.out.println();
			menuArbreHybride();
			break;
		case 8:
			System.out.println("Il y a "+abrH1.comptageNil()+" nil(s) (feuille(s)) dans l'arbre.");
			System.out.println();
			menuArbreHybride();
			break;
		case 9:
			debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
			System.out.println("La hauteur de l'arbre est :"+abrH1.hauteur()+" .");
			time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
			System.out.println("temps d'execution : "+time+" en NANOsecondes");
			System.out.printf("temps d'execution : %.3f en MILIsecondes",(double)time/1000000);
			System.out.println();
			System.out.println();
			menuArbreHybride();
			break;
		case 10:
			debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
			System.out.println("La profondeur de l'arbre est :"+abrH1.profondeurMoyenne()+" .");
			time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
			System.out.println("temps d'execution : "+time+" en NANOsecondes");
			System.out.printf("temps d'execution : %.3f en MILIsecondes",(double)time/1000000);
			System.out.println();
			menuArbreHybride();
			break;
		case 11:
			System.out.println("Entrer le mot à rechercher le prefixe: ");
			mot=clavier6.nextLine();
			debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
			System.out.println("ainsi ("+mot+") est prefixe de "+abrH1.prefixe(mot)+" mot(s) de l'arbre Briandais");
			time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
			System.out.println("temps d'execution : "+time+" en NANOsecondes");
			System.out.printf("temps d'execution : %.3f en MILIsecondes",(double)time/1000000);
			System.out.println();
			menuArbreHybride();
			break;
		case 12:
			debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
			abrB1=abrH1.toBriandais();
			time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
			System.out.println("temps d'execution : "+time+" en NANOsecondes");
			System.out.printf("temps d'execution : %.3f en MILIsecondes",(double)time/1000000);
			System.out.println("\n================= ARBRE BRIANDAIS =================");
			System.out.println("legende:    --> : fils,  |: frere");
			System.out.println();
			abrB1.print();
			System.out.println();
			menuArbreHybride();
			break;
		case 13:
			
			ArrayList<Integer> nombreMots= new ArrayList<Integer>();
			ArrayList <Long> temps = new ArrayList<Long>();
			File repertoire=new File("./Text/Shakespeare/");
			String [] files=repertoire.list();
			debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();//TEMPS
			for(int i=0;i<files.length;i++){
				motRecup=cf.recupMotFichierShakespeare(files[i]);
				System.out.println(" texte "+i+" : "+motRecup.size()+ " mots");
				for (int j=0;j<motRecup.size();j++){
					abrH1.ajout(motRecup.get(j));
				}
				nombreMots.add(abrH1.nombreMots());
				sumtime += ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
				temps.add(sumtime);
			}
			time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
			System.out.println("temps d'execution : "+time+" en NANOsecondes");
			System.out.printf("temps d'execution : %.3f en MILIsecondes",(double)time/1000000);
			System.out.println();
			Menu.menu();
			break;
		case 14:
			System.out.println("Entrer le nom du fichier qui contiendra votre arbre : ");
			nomArbre=clavier6.nextLine();
			new StockArbreObjetHybride(nomArbre, abrH1);
			System.out.println("l'arbre a bien ete stocke");
			System.out.println();
			Menu.menu();
			break;
		case 15:
			System.out.println("Entrer le nom du fichier contenant l'arbre que vous voulez charger : ");
			nomArbre=clavier6.nextLine();
			LectureArbreObjetHybride lectureArbre=new LectureArbreObjetHybride();
			try {
				abrH1=lectureArbre.retourneArbreBriandais(nomArbre);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println("================== Voici l'arbre charger : ==================");
			System.out.println("Attendre la fenetre d'affichage s'il vous plait");
			fen = new Fenetre(abrH1);
			System.out.println();
			System.out.println();
			menuArbreHybride();
			break;
		case 16:
			abrH1=new Hybride();
			abrB1=new Briandais();
			System.out.println("La reinitialisation a bien été effectuée.");
			System.out.println();
			menuArbreHybride();
			break;
		case 17:
			System.out.println();
			Menu.menu();
			break;
		default:
			System.out.println("Erreur dans votre choix recommencer : ");
			Menu.menu();
			break;

		}
	}


}
