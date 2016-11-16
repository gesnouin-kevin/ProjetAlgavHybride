package Menu;

import java.lang.management.ManagementFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Briandais.Briandais;
import Fichier.CreationFichier;
import Hybride.Fenetre;
import Hybride.Hybride;
import StockageArbreBriandais.LectureArbreObjetBriandais;
import StockageArbreBriandais.StockArbreObjetBriandais;

public class MenuBriandais {

	private static Briandais abrB1 =new Briandais();

	public static void menuArbreBriandais() throws IOException { // pas oublier temps execution

		Scanner clavier2 = new Scanner(System.in);
		Scanner clavier3 = new Scanner(System.in);
		Fenetre fen;
		ArrayList<String> motRecup = new ArrayList<String>();
		CreationFichier cf = new CreationFichier();
		Hybride abrH1= new Hybride();
		
		int choix2=0;
		String mot;
		String nomArbre;
		long debutTime;
		long time;
		long debut2Time;
		long sumtime = 0;
		long sumtime2 = 0;
		
		System.out.println("|-------------------------------- MENU BRIANDAIS ------------------------------------------|");
		System.out.println("|-------------------- 0 °) Quitter --------------------------------------------------------|");
		System.out.println("|-------------------- 1 °) Créer l'Arbre à partir d'un Fichier ----------------------------|");
		System.out.println("|-------------------- 2 °) Ajout d'un nouveau mot -----------------------------------------|");
		System.out.println("|-------------------- 3 °) Afficher Arbre Briandais ---------------------------------------|");
		System.out.println("|-------------------- 4 °) Supprimer un mot -----------------------------------------------|");
		System.out.println("|-------------------- 5 °) Rechercher mot -------------------------------------------------|");
		System.out.println("|-------------------- 6 °) Compter mot(s) -------------------------------------------------|");
		System.out.println("|-------------------- 7 °) lister mot(s) (par ordre alphabetique) -------------------------|");
		System.out.println("|-------------------- 8 °) Compter nil(s) -------------------------------------------------|");
		System.out.println("|-------------------- 9 °) Hauteur arbre Briandais ----------------------------------------|");
		System.out.println("|-------------------- 10 °) Profondeur Moyenne --------------------------------------------|");
		System.out.println("|-------------------- 11 °) Prefixe -------------------------------------------------------|");
		System.out.println("|-------------------- 12 °) Fusion  -------------------------------------------------------|");
		System.out.println("|-------------------- 13 °) Conversion en Briandais -> Hybride ----------------------------|");
		System.out.println("|-------------------- 14 °) Oeuvres Shakespeare -------------------------------------------|");
		System.out.println("|-------------------- 15 °) Sauvegarder Arbre Briandais -----------------------------------|");
		System.out.println("|-------------------- 16 °) Charger Arbre Briandais ---------------------------------------|");
		System.out.println("|-------------------- 17 °) Reinitialisation ----------------------------------------------|");
		System.out.println("|-------------------- 18 °) Retour Menu General -------------------------------------------|");
		System.out.println("|------------------------------------------------------------------------------------------|");
		choix2 = clavier2.nextInt();
		
		switch (choix2) {
		case 0:
			System.out.println("Merci d'avoir utilisé notre application, Au revoir.");
			System.exit(0);
			break;
		case 1:
			motRecup=cf.recupMotFichier(); // recuperation de ts les mot dans une arraylist
			debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();//TEMPS
			for (int i=0;i<motRecup.size();i++){
				abrB1.ajout(motRecup.get(i));
			}
			time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
			System.out.println("temps d'execution : "+time+" en NANOsecondes");
			System.out.printf("temps d'execution : %.3f en MILIsecondes",(double)time/1000000);
			System.out.println("\n================= ARBRE =================");
			System.out.println("legende:    --> : fils,  |: frere");
			System.out.println();
			abrB1.print();
			System.out.println();
			System.out.println();
			menuArbreBriandais();
			break;
		case 2:
			System.out.println("Entrer un mot :");
			mot=clavier3.nextLine();
			debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();//TEMPS
			abrB1.ajout(mot);
			time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
			System.out.println("temps d'execution : "+time+" en NANOsecondes");
			System.out.printf("temps d'execution : %.3f en MILIsecondes",(double)time/1000000);
			System.out.println("\n================= ARBRE =================");
			System.out.println("legende:    --> : fils,  |: frere");
			System.out.println();
			abrB1.print();
			System.out.println();
			System.out.println();
			menuArbreBriandais();
			break;
		case 3:
			System.out.println("\n================= ARBRE =================");
			System.out.println("legende:    --> : fils,  |: frere");
			System.out.println();
			if(abrB1==null)
				System.out.println("Votre arbre est vide .");
			else
			abrB1.print();
			System.out.println();
			System.out.println();
			menuArbreBriandais();
			break;
		case 4:
			System.out.println("\n================= ARBRE =================");
			System.out.println("legende:    --> : fils,  |: frere");
			System.out.println();
			System.out.println("\n====================================================");
			System.out.println("============= Arbre avant suppression : ============");
			System.out.println("====================================================");
			System.out.println();
			abrB1.print();
			System.out.println();
			System.out.println("Entrer le mot à supprimmer : ");
			mot=clavier3.nextLine();
			System.out.println("\n====================================================");
			System.out.println("============= Arbre apres suppression : ============");
			System.out.println("====================================================");
			System.out.println();
			debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();//TEMPS
			abrB1.suppression(mot);
			time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
			System.out.println("temps d'execution : "+time+" en NANOsecondes");
			System.out.printf("temps d'execution : %.3f en MILIsecondes",(double)time/1000000);
			abrB1.print();
			System.out.println();
			System.out.println();
			menuArbreBriandais();
			break;
		case 5:
			boolean present;
			System.out.println("Entrer le mot à rechercher : ");
			mot=clavier3.nextLine();
			debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
			present=abrB1.recherche(mot);
			time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
			System.out.println("temps d'execution : "+time+" en NANOsecondes");
			System.out.printf("temps d'execution : %.3f en MILIsecondes",(double)time/1000000);
			System.out.println();
			if(present)
				System.out.println("le mot : "+mot+" est bien present dans l'arbre.");
			else
				System.out.println("le mot : "+mot+" n'est pas present dans l'arbre.");
			System.out.println();
			menuArbreBriandais();
			break;
		case 6:
			debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
			System.out.println("le nombre mot dans l'arbre est de  : "+abrB1.nombreMots()+" .");
			time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
			System.out.println("temps d'execution : "+time+" en NANOsecondes");
			System.out.printf("temps d'execution : %.3f en MILIsecondes",(double)time/1000000);
			System.out.println();
			menuArbreBriandais();
			break;
		case 7:
			ArrayList<String> dico = new ArrayList<String> ();
			debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
			dico=abrB1.listMots();
			time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
			System.out.println("temps d'execution : "+time+" en NANOsecondes");
			System.out.printf("temps d'execution : %.3f en MILIsecondes",(double)time/1000000);
			System.out.println("dictionaire :");
			System.out.println(dico);
			System.out.println();
			menuArbreBriandais();
			break;
		case 8:
			System.out.println("Il y a "+abrB1.comptageNil()+" nil(s) (feuille(s)) dans l'arbre.");
			System.out.println();
			menuArbreBriandais();
			break;
		case 9:
			debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
			System.out.println("La hauteur de l'arbre est :"+abrB1.hauteur()+" .");
			time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
			System.out.println("temps d'execution : "+time+" en NANOsecondes");
			System.out.printf("temps d'execution : %.3f en MILIsecondes",(double)time/1000000);
			System.out.println();
			menuArbreBriandais();
			break;
		case 10:
			debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
			System.out.println("La profondeur de l'arbre est :"+abrB1.profondeurMoyenne()+" .");
			time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
			System.out.println("temps d'execution : "+time+" en NANOsecondes");
			System.out.printf("temps d'execution : %.3f en MILIsecondes",(double)time/1000000);
			System.out.println();
			menuArbreBriandais();
			break;
		case 11:
			System.out.println("Entrer le mot à rechercher le prefixe: ");
			mot=clavier3.nextLine();
			debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
			System.out.println("ainsi ("+mot+") est prefixe de "+abrB1.prefixe(mot)+" mot(s) de l'arbre Briandais");
			time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
			System.out.println("temps d'execution : "+time+" en NANOsecondes");
			System.out.printf("temps d'execution : %.3f en MILIsecondes",(double)time/1000000);
			System.out.println();
			menuArbreBriandais();
			break;
		case 12:
			menuFusion();
			break;
		case 13:
			debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
			abrH1=abrB1.toHybride();
			time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime;
			System.out.println("temps d'execution : "+time+" en NANOsecondes");
			System.out.printf("temps d'execution : %.3f en MILIsecondes",(double)time/1000000);
			System.out.println("\n================= ARBRE HYBRIDE =================");
			System.out.println("Attendre la fenetre d'affichage s'il vous plait");
			System.out.println();
			fen = new Fenetre(abrH1);
			System.out.println();
			menuArbreBriandais();
			break;
		case 14:
			File repertoire=new File("./Text/Shakespeare/");
			String [] files=repertoire.list();
			ArrayList <String> liste =new ArrayList<String>();
			ArrayList <Long> temps = new ArrayList<Long>();
			ArrayList <Long> temps2 = new ArrayList<Long>();
			ArrayList<Integer> nombreMots= new ArrayList<Integer>();
			for(int i=0;i<files.length;i++){

				motRecup=cf.recupMotFichierShakespeare(files[i]);
				System.out.println(" texte "+i+" : "+motRecup.size()+ " mots");

				debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
				for (int j=0;j<motRecup.size();j++){
					abrB1.ajout(motRecup.get(j));
				}
				sumtime += ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime; // - more on retranche le temps de calcule de nombre de mots
				temps.add(sumtime);
			}
			
			System.out.println("temps d'execution ajout briandais: "+sumtime+" en NANOsecondes");
			System.out.printf("temps d'execution ajout Briandais : %.3f en MILIsecondes",(double)sumtime/1000000);
			System.out.println();
			System.out.println();
			Menu.menu();
			break;
		case 15:
			System.out.println("Entrer le nom du fichier qui contiendra votre arbre (prealablement cree avec les options 1 et 2 du menu ) : ");
			nomArbre=clavier3.nextLine();
			new StockArbreObjetBriandais(nomArbre, abrB1);
			System.out.println("l'arbre a bien ete stocke");
			System.out.println();
			Menu.menu();
			break;
		case 16:
			System.out.println("Entrer le nom du fichier contenant l'arbre que vous voulez charger : ");
			nomArbre=clavier3.nextLine();
			LectureArbreObjetBriandais lectureArbre=new LectureArbreObjetBriandais();
			try {
				abrB1=lectureArbre.retourneArbreBriandais(nomArbre);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println("================== Voici l'arbre charger : ==================");
			abrB1.print();
			System.out.println();
			System.out.println();
			menuArbreBriandais();
			break;
		case 17:
			abrB1=new Briandais();
			abrH1=new Hybride();
			System.out.println("La reinitialisation a bien été effectuée.");
			System.out.println();
			menuArbreBriandais();
			break;
		case 18:
			System.out.println();
			Menu.menu();
			break;
		default:
			System.out.println("Erreur dans votre choix recommencer : ");
			Menu.menu();
			break;

		}
	}
	private static void menuFusion() throws IOException {
		int choix = 0;
		Scanner clavier4 = new Scanner(System.in);
		String mot;
		System.out.println("|------------------------- MENU FUSION ------------------------------------------------------------------|");
		System.out.println("|-------------------- 0 °) Quitter ----------------------------------------------------------------------|");
		System.out.println("|-------------------- 1 °) Fusion avec arbre prealablement charge et un 2 ieme arbre a inserer ----------|");
		System.out.println("|-------------------- 2 °) Fusion en inserant 2 arbres  -------------------------------------------------|");
		System.out.println("|-------------------- 3 °) Retour Menu Arbre Briandais  -------------------------------------------------|");
		System.out.println("|--------------------------------------------------------------------------------------------------------|");
		choix = clavier4.nextInt();
		String nomArbre;
		Briandais abrFusion =new Briandais();
		Briandais abrB2 =new Briandais();
		Briandais abrB3 =new Briandais();
		boolean arret=false;
		ArrayList<String> mots = new ArrayList<String>();
		ArrayList<String> mots2 = new ArrayList<String>();
		switch(choix){
		case 0:
			System.out.println("Merci d'avoir utilisé notre application, Au revoir.");
			System.exit(0);
			break;
		case 1:
			while(!arret){
				System.out.println("(tapez finarbre pour finir) Entrer un mot :");
				mot=clavier4.nextLine();
				if(mot.equals("finarbre")){
					arret=true;
				}else{
					arret=false;
					mots.add(mot);
				}
			}
			int i=0;
			while(i<mots.size()){
				abrB2.ajout(mots.get(i));
				i++;
			}
			System.out.println("\n================================================");
			System.out.println("\n================= Fusion Arbre =================");
			System.out.println("\n================================================");
			System.out.println("legende:    --> : fils,  |: frere");
			abrB1.print();
			System.out.println("\n\n + \n\n");
			abrB2.print();
			System.out.println("\n\n = \n\n");
			abrFusion=abrFusion.fusion(abrB1,abrB2);
			abrFusion.print();
			System.out.println();
			System.out.println();
			menuFusion();
			break;
		
		case 2:
			arret=true;
			while(arret){
				System.out.println("================= INSERTION 1 er arbre =================");
				System.out.println("(tapez finarbre pour finir) Entrer un mot :");
				mot=clavier4.nextLine();
				
				if(mot.equals("finarbre")){
					arret=false;
				}else{
					mots.add(mot);
				}
			}
			int j=0;
			while(j<mots.size()){
				abrB2.ajout(mots.get(j));
				j++;
			}
			while(!arret){
				
				System.out.println("================= INSERTION 2 eme arbre =================");
				System.out.println("(tapez finarbre pour finir) Entrer un mot :");
				mot=clavier4.nextLine();
				if(mot.equals("finarbre")){
					arret=true;
				}else{
					mots2.add(mot);
				}
			}
			int z=0;
			while(z<mots2.size()){
				abrB3.ajout(mots2.get(z));
				z++;
			}
			System.out.println("\n================================================");
			System.out.println("\n================= Fusion Arbre =================");
			System.out.println("\n================================================");
			System.out.println("legende:    --> : fils,  |: frere");
			abrB2.print();
			System.out.println("\n\n + \n\n");
			abrB3.print();
			System.out.println("\n\n = \n\n");
			abrFusion=abrFusion.fusion(abrB2,abrB3);
			abrFusion.print();
			System.out.println();
			System.out.println();
			menuFusion();
			break;
		case 3:
			menuArbreBriandais();
			break;
		default:
			System.out.println("Erreur dans votre choix recommencer : ");
			System.out.println();
			menuFusion();
			break;
		}
		clavier4.close();
	}
}
