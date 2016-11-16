package StockageArbreBriandais;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import Briandais.Briandais;

public class LectureArbreObjetBriandais {


	public Briandais retourneArbreBriandais(String nomArbre) throws  IOException, ClassNotFoundException{
		ObjectInputStream entree = new ObjectInputStream(new FileInputStream("./Arbres/Briandais/"+nomArbre+".dat"));
		boolean eof = false;
		Briandais abr= null;
		while(!eof){
			try{

				abr=(Briandais) entree.readObject();
				if(abr!=null){
					System.out.println("============ voici l'arbre recuperé : ============");
					abr.print();

				}
			}catch(EOFException  e){
				eof=true;
			}
		}
		entree.close();
		return abr;
	}

}
