package StockageArbreHybride;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import Hybride.Fenetre;
import Hybride.Hybride;



public class LectureArbreObjetHybride {


	public Hybride retourneArbreBriandais(String nomArbre) throws  IOException, ClassNotFoundException{
		ObjectInputStream entree = new ObjectInputStream(new FileInputStream("./Arbres/Hybride/"+nomArbre+".dat"));
		boolean eof = false;
		Hybride abr= null;
		while(!eof){
			try{

				abr=(Hybride) entree.readObject();
				if(abr!=null){
					System.out.println("============ voici l'arbre recuperé : ============");
					Fenetre fen= new Fenetre(abr);

				}
			}catch(EOFException  e){
				eof=true;
			}
		}
		entree.close();
		return abr;
	}

}
