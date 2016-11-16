package Fichier;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;




public class CreationFichier {


	public  void menuCreationFichier() throws IOException{
		int i=1;
		Scanner clavierFich= new Scanner(System.in);
		System.out.println("Donner le nom du fichier a creer : ");
		String nomFichier = clavierFich.nextLine();
		File fichier= new File("./Text/"+nomFichier+".txt");
		if(fichier.exists() ){// on verifie qu'il existe pas deja
			choice(fichier);
		}
		else{
			PrintWriter sortie =new PrintWriter(fichier);
			do{
				System.out.println("(TAPE 0 pour finir) donner Ligne n°"+i+" :" );
				String ligne = clavierFich.nextLine();
				i++;
				if(ligne.equals("0")){i=0;}
				else{
					sortie.write(""+ligne+"\n"); 
				}
			}while(i!=0);
			sortie.close();
			System.out.println("fin");
		}
	}


	public  void choice(File fichier) throws IOException{
		Scanner clavierFich2= new Scanner(System.in);
		System.out.println("|------------------- ATTENTION FICHIER EXIST DEJA -------------------|");
		System.out.println("|-------------------- 0 °) Quitter ----------------------------------|");
		System.out.println("|-------------------- 1 °) Ecrire a la suite ? ----------------------|");
		System.out.println("|-------------------- 2 °) Ecrire dessus (ecraser contenu) ? --------|");
		System.out.println("|-------------------- 3 °) Choisir un autre nom de dossier ? --------|");
		System.out.println("|--------------------------------------------------------------------|");
		int choix = clavierFich2.nextInt();
		choice2(choix, fichier);
		//clavierFich2.close();
	}
	public  void choice2(int choix, File fichier) throws IOException{
		Scanner clavierFich3= new Scanner(System.in);
		int i=1;
		switch(choix){
		case 0:
			System.out.println("Merci d'avoir utilisé notre application, Au revoir.");
			System.exit(0);
			break;

		case 1:
			// ici si on veut ecrire a la suite on est obligé de mettre un Filwriter avec true en 2 ieme parametres
			FileWriter f= new FileWriter(fichier, true);
			PrintWriter sortie1 =new PrintWriter(f);
			do{
				System.out.println("(TAPE 0 pour finir) donner Ligne n°"+i+" :" );
				String ligne = clavierFich3.nextLine();
				i++;
				if(ligne.equals("0")){i=0;}
				else{
					sortie1.write(""+ligne+"\n"); 
				}
			}while(i!=0);
			sortie1.close();
			System.out.println("fin");
			break;

		case 2:

			PrintWriter sortie =new PrintWriter(fichier);
			do{
				System.out.println("(TAPE 0 pour finir) donner Ligne n°"+i+" :" );
				String ligne = clavierFich3.nextLine();
				i++;
				if(ligne.equals("0")){i=0;}
				else{
					sortie.write(""+ligne+"\n"); 
				}
			}while(i!=0);
			sortie.close();
			System.out.println("fin");

			break;

		case 3:
			menuCreationFichier();

			break;

		default:
			System.out.println("Erreur dans votre choix recommencer : ");
			choice(fichier);
			break;
		}
		//	clavierFich3.close();

	}




	// Recupere un mot du fichier
	public ArrayList<String> recupMotFichier() throws IOException{


		String ligne;
		ArrayList<String> mot = new ArrayList<String>();
		int nbToken= 0;

		String nomFichier=new String("");
		Scanner sousClavierFich =new Scanner(System.in);
		System.out.println("Entrer un nom de fichier");
		nomFichier = sousClavierFich.nextLine();

		BufferedReader entree = new BufferedReader(new FileReader("./Text/"+nomFichier+".txt"));
		do{
			ligne=entree.readLine();// on recupere ligne par ligne de notre fichier .
			if(ligne==null)
				break;
			else{
				//StringTokenizer va permettre de recuperer les differents mot d'un ligne selon 1 separateur
				StringTokenizer tok =  new StringTokenizer(ligne, " ");
				nbToken = tok.countTokens();
				for(int i=0;i<nbToken;i++){
					mot.add(tok.nextToken());
				}

			}
		}while(ligne != null);
		String mote = null;
		int max=0;
		//affiche tous les mots recupere
		for(int i =0; i<mot.size(); i++){
			System.out.println("mot hello "+i+" : "+mot.get(i));
			if(mot.get(i).length()>mot.get(max).length()){
				max=i;
			}
			mote=mot.get(max);
		}
		System.out.println("mot le plus grand :"+mote);
		entree.close();
		return mot;
	}

	// Recupere un mot du fichier
	public ArrayList<String> recupMotFichierShakespeare(String nomFichier) throws IOException{
		String ligne;
		ArrayList<String> mot = new ArrayList<String>();
		int nbToken= 0;
		BufferedReader entree = new BufferedReader(new FileReader("./Text/Shakespeare/"+nomFichier));
		do{
			ligne=entree.readLine();// on recupere ligne par ligne de notre fichier .
			if(ligne==null)
				break;
			else{
				//StringTokenizer va permettre de recuperer les differents mot d'un ligne selon 1 separateur
				StringTokenizer tok =  new StringTokenizer(ligne, " ");
				nbToken = tok.countTokens();

				for(int i=0;i<nbToken;i++){
					mot.add(tok.nextToken());
				}

			}
		}while(ligne != null);
		entree.close();
		return mot;
	}




	public void fichierAjoutGnuplot(ArrayList<Long> temps, ArrayList<Long> temps2, ArrayList<Integer> nbMots) throws IOException{
		//----- VARIABLE LOCAL -----//
		long i=0;
		String nomFichier ;

		Scanner sousClavier= new Scanner(System.in);

		//----- CODE -----//	 
		System.out.println("Donner le nom du fichier gnuplot a creer : ");
		nomFichier= sousClavier.nextLine();
		File fichier= new File("./Gnuplot/"+nomFichier+".txt");
		PrintWriter sortie =new PrintWriter(fichier);
		while(i< nbMots.size()){
			
			String ligneB = Long.toString(temps.get((int) i));
			String ligneH = Long.toString(temps2.get((int) i));
			sortie.write(nbMots.get((int) i)+" "+ligneB+" "+ligneH+"\n");
			i++;

		}
		sortie.close();
		System.out.println("fin");
	}






}
