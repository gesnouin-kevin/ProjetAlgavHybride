package StockageArbreBriandais;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Briandais.Briandais;


public class StockArbreObjetBriandais {
	public StockArbreObjetBriandais(String nomArbre, Briandais abr) throws FileNotFoundException, IOException{
		ObjectOutputStream sortie = new ObjectOutputStream(new FileOutputStream("./Arbres/Briandais/"+nomArbre+".dat"));
		sortie.writeObject(abr);
		sortie.close();
	}
}
