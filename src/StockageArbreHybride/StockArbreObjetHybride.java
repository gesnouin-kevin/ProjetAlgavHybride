package StockageArbreHybride;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Hybride.Hybride;


public class StockArbreObjetHybride {
	public StockArbreObjetHybride(String nomArbre, Hybride abr) throws FileNotFoundException, IOException{
		ObjectOutputStream sortie = new ObjectOutputStream(new FileOutputStream("./Arbres/Hybride/"+nomArbre+".dat"));
		sortie.writeObject(abr);
		sortie.close();
	}
}
