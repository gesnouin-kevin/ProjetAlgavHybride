package Hybride;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

//CTRL + SHIFT + O pour g�n�rer les imports n�cessaires

public class Fenetre extends JFrame {
	private JTree arbre;   

	public Fenetre(Hybride abrH){
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Les arbres");
		//On invoque la m�thode de construction de notre arbre
		buildTree(abrH);

		this.setVisible(true);
	}

	 private MutableTreeNode buildTree(Hybride abrH){
		  DefaultMutableTreeNode racine;
		  if(abrH.getValeur()==-1) racine = new DefaultMutableTreeNode(abrH.getClef());
		  else  racine = new DefaultMutableTreeNode(abrH.getClef()+" ("+abrH.getValeur()+")");
		  if(!abrH.getSup().isNil()) racine.add(buildTree(abrH.getSup()));
		  if(!abrH.getEq().isNil())racine.add(buildTree(abrH.getEq()));   
		  if(!abrH.getInf().isNil()) racine.add(buildTree(abrH.getInf()));
		  
		  
		  
		 arbre = new JTree(racine);

		 this.getContentPane().add(new JScrollPane(arbre));
		 return racine;
	}
  
}
