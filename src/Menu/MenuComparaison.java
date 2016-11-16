
package Menu;

import java.lang.management.ManagementFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Briandais.Briandais;
import Fichier.CreationFichier;
import Hybride.Hybride;


public class MenuComparaison {

	private static Briandais abrB =new Briandais();
	private static Hybride abrH =new Hybride();



	public static void menuComparaison() throws IOException { // pas oublier temps execution
		// pour chaque fonctionnalite.

		Scanner clavier7 = new Scanner(System.in);
		Scanner clavier8 = new Scanner(System.in);
		ArrayList<String> motRecup = new ArrayList<String>();
		ArrayList<Integer> nombreMots= new ArrayList<Integer>();
		ArrayList <Long> temps = new ArrayList<Long>();
		ArrayList <Long> temps2 = new ArrayList<Long>();

		File repertoire=new File("./Text/Shakespeare/");
		String [] files=repertoire.list();

		int choix;
		long debutTime;
		long debut2Time;
		long sumtime = 0;
		long sumtime2 = 0;

		CreationFichier creafich =new CreationFichier();
		System.out.println("|-------------------------------- MENU COMPARAISON ----------------------------------------|");
		System.out.println("|-------------------- 0 °) QUITTER --------------------------------------------------------|");
		System.out.println("|-------------------- 1 °) AJOUT  ---------------------------------------------------------|");
		System.out.println("|-------------------- 2 °) SUPPRESSION  ---------------------------------------------------|");
		System.out.println("|-------------------- 3 °) RECHERCHE ------------------------------------------------------|");
		System.out.println("|-------------------- 4 °) PROFONDEUR -----------------------------------------------------|");
		System.out.println("|-------------------- 5 °) LISTER mot(s) (par ordre alphabetique) -------------------------|");
		System.out.println("|-------------------- 6 °) COMPTER mot(s) -------------------------------------------------|");
		System.out.println("|-------------------- 7 °) HAUTEUR --------------------------------------------------------|");
		System.out.println("|------------------------------------------------------------------------------------------|");
		choix = clavier7.nextInt();
		int tape;
		int tape2;


		switch (choix) {
		case 0:
			System.out.println("Merci d'avoir utilisé notre application, Au revoir.");
			System.exit(0);
			break;
		case 1:
			System.out.println("|------------------------------------------------------------------------------------------|");
			System.out.println("|-------------------- 1 °) AJOUT TOUTES OEUVRES SHAKESPEARE -------------------------------|");
			System.out.println("|-------------------- 2 °) AJOUT UNE OEUVRE SHAKESPEARE------------------------------------|");
			System.out.println("|------------------------------------------------------------------------------------------|");
			tape= clavier8.nextInt();
			if(tape==1){
				ArrayList <String> liste =new ArrayList<String>();
				for(int i=0;i<files.length;i++){

					motRecup=creafich.recupMotFichierShakespeare(files[i]);
					System.out.println(" texte "+i+" : "+motRecup.size()+ " mots");

					debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					for (int j=0;j<motRecup.size();j++){
						abrB.ajout(motRecup.get(j));
					}
					sumtime += ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime; // - more on retranche le temps de calcule de nombre de mots
					temps.add(sumtime);

					nombreMots.add(abrB.nombreMots());
					System.out.println(abrB.nombreMots());

					liste=abrB.listMots();
					System.out.println(" liste: "+liste.size()+ " = nbmots"+abrB.nombreMots());

					debut2Time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					for (int j=0;j<motRecup.size();j++){
						abrH.ajout(motRecup.get(j));
					}
					sumtime2 += ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debut2Time;
					temps2.add(sumtime2);

				}
				creafich.fichierAjoutGnuplot(temps, temps2, nombreMots );
				System.out.println("Chargement effectue");
			}else if(tape==2){
				CreationFichier cf = new CreationFichier();
				motRecup=cf.recupMotFichier();
				// on ajoute les mots dans l'arbre Briandais
				for (int j=0;j<motRecup.size();j++){
					nombreMots.add(j+1);
					debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					abrB.ajout(motRecup.get(j));
					sumtime += ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime; // - more on retranche le temps de calcule de nombre de mots
					temps.add(sumtime);
					debut2Time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					abrH.ajout(motRecup.get(j));
					sumtime2 += ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debut2Time;
					temps2.add(sumtime2);
				}
				creafich.fichierAjoutGnuplot(temps, temps2, nombreMots );
				System.out.println("Chargement effectue");
			}
			menuComparaison();
			break;
		case 2:
			System.out.println("|----------------------------------------------------------------------------------------------|");
			System.out.println("|-------------------- 1 °) SUPPRIMER TOUTES OEUVRES SHAKESPEARE -------------------------------|");
			System.out.println("|-------------------- 2 °) SUPPRIMER UNE OEUVRE SHAKESPEARE------------------------------------|");
			System.out.println("|----------------------------------------------------------------------------------------------|");
			tape2= clavier8.nextInt();
			if(tape2==1){
				abrB= new Briandais();
				abrH= new Hybride();

				ArrayList <String> liste =new ArrayList<String>();
				for(int i=0;i<files.length;i++){

					motRecup=creafich.recupMotFichierShakespeare(files[i]);
					System.out.println("fichier"+i+" : "+files[i]+" "+motRecup.size()+ " mots");
					for (int z=0;z<motRecup.size();z++){
						abrB.ajout(motRecup.get(z));
						abrH.ajout(motRecup.get(z));
					}
					liste=abrB.listMots();

					nombreMots.add(i);
					debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					for(int e=liste.size()-1;e>=0;e--){
						abrB.suppression(liste.get(e));
					}
					sumtime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime; // - more on retranche le temps de calcule de nombre de mots
					temps.add(sumtime);
					debut2Time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					for (int j=liste.size()-1;j>=0;j--){
						abrH.suppression(liste.get(j));
					}
					sumtime2 = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debut2Time;
					temps2.add(sumtime2);

				}

				System.out.println("abrB:"+abrB.listMots());
				System.out.println("abrH:"+abrH.listMots());

			}else if(tape2==2){
				abrB= new Briandais();
				abrH= new Hybride();
				Hybride h = new Hybride();
				ArrayList <String> liste =new ArrayList<String>();
				motRecup=creafich.recupMotFichier();
				System.out.println("Nombre de mot(s) :"+motRecup.size());
				for (int z=0;z<motRecup.size();z++){
					abrB.ajout(motRecup.get(z));
					h.ajout(motRecup.get(z));
					//abrH.ajout(motRecup.get(z));
				}
				liste=abrB.listMots(); // ICI REMETTTRERERETRE liste=h.listMots();
				debut2Time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
				Briandais b =h.toBriandais();
				sumtime2 = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debut2Time; // - more on retranche le temps de calcule de nombre de mots
				for(int e=liste.size()-1;e>=0;e--){
					debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					abrB.suppression(liste.get(e));
					//b.suppression(liste.get(e));
					sumtime += ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime; // - more on retranche le temps de calcule de nombre de mots
					temps.add(sumtime);
				}

				for (int j=liste.size()-1;j>=0;j--){
					debut2Time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					//abrH.suppression(liste.get(j));
					b.suppression(liste.get(j));
					sumtime2 += ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debut2Time;
					temps2.add(sumtime2);
					nombreMots.add(liste.size()-j);
				}

			}
			creafich.fichierAjoutGnuplot(temps, temps2, nombreMots );
			menuComparaison();
			break;
		case 3:
			System.out.println("|----------------------------------------------------------------------------------------------|");
			System.out.println("|-------------------- 1 °) RECHERCHE TOUTES OEUVRES SHAKESPEARE -------------------------------|");
			System.out.println("|-------------------- 2 °) RECHERCHE TOUTES OEUVRES SHAKESPEARE (somme)------------------------|");
			System.out.println("|----------------------------------------------------------------------------------------------|");
			tape2= clavier8.nextInt();
			if(tape2==1){
				abrB= new Briandais();
				abrH= new Hybride();

				ArrayList <String> liste2 =new ArrayList<String>();
				for(int i=0;i<files.length;i++){

					abrB= new Briandais();
					abrH= new Hybride();
					motRecup=creafich.recupMotFichierShakespeare(files[i]);
					System.out.println("fichier"+i+" : "+files[i]+" "+motRecup.size()+ " mots");

					for (int z=0;z<motRecup.size();z++){
						abrB.ajout(motRecup.get(z));
						abrH.ajout(motRecup.get(z));
					}
					nombreMots.add(i);
					liste2=(abrB.listMots());

					debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					for(int f=0;f<liste2.size();f++){
						abrB.recherche(liste2.get(f));
					}
					sumtime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime; // - more on retranche le temps de calcule de nombre de mots
					temps.add(sumtime);

					debut2Time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					for(int f=0;f<liste2.size();f++){
						abrH.recherche(liste2.get(f));
					}
					sumtime2 = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debut2Time;
					temps2.add(sumtime2);
				}


			}else if(tape2==2){
				abrB= new Briandais();
				abrH= new Hybride();
				ArrayList <String> liste2 =new ArrayList<String>();
				for(int i=0;i<files.length;i++){

					motRecup=creafich.recupMotFichierShakespeare(files[i]);
					System.out.println("fichier"+i+" : "+files[i]+" "+motRecup.size()+ " mots");
					for (int z=0;z<motRecup.size();z++){
						abrB.ajout(motRecup.get(z));
						abrH.ajout(motRecup.get(z));
					}

					liste2=abrB.listMots();

					debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					for(int f=0;f<liste2.size();f++){
						abrB.recherche(liste2.get(f));
					}
					sumtime += ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime; // - more on retranche le temps de calcule de nombre de mots
					temps.add(sumtime);

					debut2Time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					for(int f=0;f<liste2.size();f++){
						abrH.recherche(liste2.get(f));
					}
					sumtime2 += ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debut2Time;
					temps2.add(sumtime2);

					nombreMots.add(i);
				}
			}
			creafich.fichierAjoutGnuplot(temps, temps2, nombreMots );
			menuComparaison();
			break;
		case 4:
			ArrayList <Long> profB =new ArrayList<Long>();
			ArrayList <Long> profH =new ArrayList<Long>();
			System.out.println("|------------------------------------------------------------------------------------------------------------------------|");
			System.out.println("|-------------------- 1 °) PROFONDEUR MOYENNE TOUTES OEUVRES SHAKESPEARE (temps) ----------------------------------------|");
			System.out.println("|-------------------- 2 °) PROFONDEUR MOYENNE TOUTES OEUVRES SHAKESPEARE (profondeur) -----------------------------------|");
			System.out.println("|-------------------- 3 °) PROFONDEUR MOYENNE D' UNE OEUVRE SHAKESPEARE (somme temps) -----------------------------------|");
			System.out.println("|-------------------- 4 °) PROFONDEUR MOYENNE TOUTES OEUVRES SHAKESPEARE avec equilibrage (temps) -----------------------|");
			System.out.println("|-------------------- 5 °) PROFONDEUR MOYENNE TOUTES OEUVRES SHAKESPEARE avec equilibrage (profondeur) ------------------|");
			System.out.println("|-------------------- 6 °) PROFONDEUR MOYENNE D' UNE OEUVRE SHAKESPEARE avec equilibrage (somme temps) ------------------|");
			System.out.println("|------------------------------------------------------------------------------------------------------------------------|");
			tape2= clavier8.nextInt();
			if(tape2==1){
				abrB= new Briandais();
				abrH= new Hybride();

				ArrayList <String> liste =new ArrayList<String>();
				for(int i=0;i<files.length;i++){
					abrB= new Briandais();
					abrH= new Hybride();
					motRecup=creafich.recupMotFichierShakespeare(files[i]);
					System.out.println("fichier"+i+" : "+files[i]+" "+motRecup.size()+ " mots");
					for (int z=0;z<motRecup.size();z++){
						abrB.ajout(motRecup.get(z));
						abrH.ajout(motRecup.get(z));
					}
					liste=abrB.listMots();

					nombreMots.add(i);
					debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					abrB.profondeurMoyenne();
					sumtime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime; // - more on retranche le temps de calcule de nombre de mots
					temps.add(sumtime);

					debut2Time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					abrH.profondeurMoyenne();
					sumtime2 = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debut2Time;
					temps2.add(sumtime2);
				}

			}
			else if(tape2==2){
				abrB= new Briandais();
				abrH= new Hybride();
				double prof1 = 0.0;
				double prof2 = 0.0;

				ArrayList <String> liste =new ArrayList<String>();
				for(int i=0;i<files.length;i++){
					abrB= new Briandais();
					abrH= new Hybride();
					motRecup=creafich.recupMotFichierShakespeare(files[i]);
					System.out.println("fichier"+i+" : "+files[i]+" "+motRecup.size()+ " mots");
					for (int z=0;z<motRecup.size();z++){
						abrB.ajout(motRecup.get(z));
						abrH.ajout(motRecup.get(z));
					}
					liste=abrB.listMots();

					nombreMots.add(i);
					prof1 = abrB.profondeurMoyenne();
					prof2 = abrH.profondeurMoyenne();

					profB.add(Long.parseLong(new String(""+Math.round(prof1))));
					profH.add(Long.parseLong(new String(""+Math.round(prof2))));

					prof1=0;
					prof2=0;
				}
				creafich.fichierAjoutGnuplot(profB, profH, nombreMots );
				menuComparaison();
			}
			else if(tape2==3){

				abrB= new Briandais();
				abrH= new Hybride();
				ArrayList <String> liste =new ArrayList<String>();
				for(int i=0;i<files.length;i++){
					motRecup=creafich.recupMotFichierShakespeare(files[i]);
					System.out.println("fichier"+i+" : "+files[i]+" "+motRecup.size()+ " mots");
					System.out.println("Nombre de mot(s) :"+motRecup.size());
					for (int z=0;z<motRecup.size();z++){
						abrB.ajout(motRecup.get(z));
						abrH.ajout(motRecup.get(z));
					}
					liste=abrB.listMots();

					debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					abrB.profondeurMoyenne();
					sumtime += ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime; // - more on retranche le temps de calcule de nombre de mots
					temps.add(sumtime);

					debut2Time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					abrH.profondeurMoyenne();
					sumtime2 += ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debut2Time;
					temps2.add(sumtime2);
					nombreMots.add(i);
				}
			}
			else if(tape2==4){
				abrB= new Briandais();
				abrH= new Hybride();

				ArrayList <String> liste =new ArrayList<String>();
				for(int i=0;i<files.length;i++){
					abrB= new Briandais();
					abrH= new Hybride();
					motRecup=creafich.recupMotFichierShakespeare(files[i]);
					System.out.println("fichier"+i+" : "+files[i]+" "+motRecup.size()+ " mots");
					for (int z=0;z<motRecup.size();z++){
						abrB.ajout(motRecup.get(z));
						abrH.ajout(motRecup.get(z));
					}
					
					liste=abrB.listMots();

					nombreMots.add(i);
					debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					abrB.profondeurMoyenne();
					sumtime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime; // - more on retranche le temps de calcule de nombre de mots
					temps.add(sumtime);

					debut2Time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					abrH.equilibrage();
					abrH.profondeurMoyenne();
					sumtime2 = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debut2Time;
					temps2.add(sumtime2);
				}

			}
			else if(tape2==5){
				abrB= new Briandais();
				abrH= new Hybride();
				double prof1 = 0.0;
				double prof2 = 0.0;

				ArrayList <String> liste =new ArrayList<String>();
				for(int i=0;i<files.length;i++){
					abrB= new Briandais();
					abrH= new Hybride();
					motRecup=creafich.recupMotFichierShakespeare(files[i]);
					System.out.println("fichier"+i+" : "+files[i]+" "+motRecup.size()+ " mots");
					for (int z=0;z<motRecup.size();z++){
						abrB.ajout(motRecup.get(z));
						abrH.ajout(motRecup.get(z));
					}
					abrH.equilibrage();
					liste=abrB.listMots();

					nombreMots.add(i);
					prof1 = abrB.profondeurMoyenne();
					prof2 = abrH.profondeurMoyenne();

					profB.add(Long.parseLong(new String(""+Math.round(prof1))));
					profH.add(Long.parseLong(new String(""+Math.round(prof2))));

					prof1=0;
					prof2=0;
				}
				creafich.fichierAjoutGnuplot(profB, profH, nombreMots );
				menuComparaison();
			}
			else if(tape2==6){

				abrB= new Briandais();
				abrH= new Hybride();
				ArrayList <String> liste =new ArrayList<String>();
				for(int i=0;i<files.length;i++){
					motRecup=creafich.recupMotFichierShakespeare(files[i]);
					System.out.println("fichier"+i+" : "+files[i]+" "+motRecup.size()+ " mots");
					System.out.println("Nombre de mot(s) :"+motRecup.size());
					for (int z=0;z<motRecup.size();z++){
						abrB.ajout(motRecup.get(z));
						abrH.ajout(motRecup.get(z));
					}
					
					liste=abrB.listMots();

					debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					abrB.profondeurMoyenne();
					sumtime += ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime; // - more on retranche le temps de calcule de nombre de mots
					temps.add(sumtime);

					debut2Time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					abrH.equilibrage();
					abrH.profondeurMoyenne();
					sumtime2 += ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debut2Time;
					temps2.add(sumtime2);
					nombreMots.add(i);
				}
			}
			System.out.println();
			creafich.fichierAjoutGnuplot(temps, temps2, nombreMots );
			menuComparaison();
			break;
		case 5:
			System.out.println("|----------------------------------------------------------------------------------------------|");
			System.out.println("|-------------------- 1 °) LISTER MOT(S) TOUTES OEUVRES SHAKESPEARE ---------------------------|");
			System.out.println("|-------------------- 2 °) LISTER MOT(S) TOUTES OEUVRES SHAKESPEARE (somme) -------------------|");
			System.out.println("|----------------------------------------------------------------------------------------------|");
			tape2= clavier8.nextInt();
			if(tape2==1){
				abrB= new Briandais();
				abrH= new Hybride();

				ArrayList <String> liste =new ArrayList<String>();
				for(int i=0;i<files.length;i++){
					abrB= new Briandais();
					abrH= new Hybride();
					motRecup=creafich.recupMotFichierShakespeare(files[i]);
					System.out.println("fichier"+i+" : "+files[i]+" "+motRecup.size()+ " mots");
					for (int z=0;z<motRecup.size();z++){// abre du fichier pour chaque fichier
						abrB.ajout(motRecup.get(z));
						abrH.ajout(motRecup.get(z));
					}
					debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					System.out.println("abrB"+abrB.listMots());
					sumtime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime; // - more on retranche le temps de calcule de nombre de mots
					temps.add(sumtime);

					nombreMots.add(i);

					debut2Time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					System.out.println("abrH"+abrH.listMots());
					sumtime2 = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debut2Time;
					temps2.add(sumtime2);
				}

			}else if(tape2==2){
				abrB= new Briandais();
				abrH= new Hybride();

				ArrayList <String> liste =new ArrayList<String>();
				for(int i=0;i<files.length;i++){
					motRecup=creafich.recupMotFichierShakespeare(files[i]);
					System.out.println("fichier"+i+" : "+files[i]+" "+motRecup.size()+ " mots");
					for (int z=0;z<motRecup.size();z++){// abre du fichier pour chaque fichier
						abrB.ajout(motRecup.get(z));
						abrH.ajout(motRecup.get(z));
					}
					debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					System.out.println("abrB"+abrB.listMots());
					sumtime += ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime; // - more on retranche le temps de calcule de nombre de mots
					temps.add(sumtime);

					nombreMots.add(i);

					debut2Time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					System.out.println("abrH"+abrH.listMots());
					sumtime2 += ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debut2Time;
					temps2.add(sumtime2);
				}
			}
			creafich.fichierAjoutGnuplot(temps, temps2, nombreMots );
			menuComparaison();
			break;
		case 6:
			System.out.println("|----------------------------------------------------------------------------------------------|");
			System.out.println("|-------------------- 1 °) COMPTER MOT(S) TOUTES OEUVRES SHAKESPEARE --------------------------|");
			System.out.println("|-------------------- 2 °) COMPTER MOT(S) TOUTES OEUVRES SHAKESPEARE (somme) ------------------|");
			System.out.println("|----------------------------------------------------------------------------------------------|");
			tape2= clavier8.nextInt();
			if(tape2==1){
				abrB= new Briandais();
				abrH= new Hybride();

				ArrayList <String> liste =new ArrayList<String>();
				for(int i=0;i<files.length;i++){
					abrB= new Briandais();
					abrH= new Hybride();
					motRecup=creafich.recupMotFichierShakespeare(files[i]);
					System.out.println("fichier"+i+" : "+files[i]+" "+motRecup.size()+ " mots");
					for (int z=0;z<motRecup.size();z++){// abre du fichier pour chaque fichier
						abrB.ajout(motRecup.get(z));
						abrH.ajout(motRecup.get(z));
					}
					debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					abrB.nombreMots();
					sumtime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime; // - more on retranche le temps de calcule de nombre de mots
					temps.add(sumtime);

					nombreMots.add(i);

					debut2Time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					abrH.nombreMots();
					sumtime2 = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debut2Time;
					temps2.add(sumtime2);
				}

			}else if (tape2==2){
				abrB= new Briandais();
				abrH= new Hybride();

				ArrayList <String> liste =new ArrayList<String>();
				for(int i=0;i<files.length;i++){
					motRecup=creafich.recupMotFichierShakespeare(files[i]);
					System.out.println("fichier"+i+" : "+files[i]+" "+motRecup.size()+ " mots");
					for (int z=0;z<motRecup.size();z++){// abre du fichier pour chaque fichier
						abrB.ajout(motRecup.get(z));
						abrH.ajout(motRecup.get(z));
					}
					debutTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					abrB.nombreMots();
					sumtime += ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debutTime; // - more on retranche le temps de calcule de nombre de mots
					temps.add(sumtime);

					nombreMots.add(i);

					debut2Time = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
					abrH.nombreMots();
					sumtime2 += ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - debut2Time;
					temps2.add(sumtime2);
				}
			}
			creafich.fichierAjoutGnuplot(temps, temps2, nombreMots );
			menuComparaison();
			break;
		case 7: 

			ArrayList<Long> hautB= new ArrayList<Long>();
			ArrayList<Long> hautH= new ArrayList<Long>();
			abrB= new Briandais();
			abrH= new Hybride();
			double haut1 = 0.0;
			double haut2 = 0.0;

			ArrayList <String> liste =new ArrayList<String>();
			for(int i=0;i<files.length;i++){

				motRecup=creafich.recupMotFichierShakespeare(files[i]);
				System.out.println("fichier"+i+" : "+files[i]+" "+motRecup.size()+ " mots");
				for (int z=0;z<motRecup.size();z++){
					abrB.ajout(motRecup.get(z));
					abrH.ajout(motRecup.get(z));
				}
				liste=abrB.listMots();

				nombreMots.add(i);
				haut1 = abrB.hauteur();
				haut2 = abrH.hauteur();

				hautB.add(Long.parseLong(new String(""+Math.round(haut1))));
				hautH.add(Long.parseLong(new String(""+Math.round(haut2))));

				haut1=0;
				haut2=0;
			}
			creafich.fichierAjoutGnuplot(hautB, hautH, nombreMots );
			menuComparaison();


			break;

		default:
			System.out.println("Erreur dans votre choix recommencer : ");
			System.out.println();

			break;
		}

	}

}
