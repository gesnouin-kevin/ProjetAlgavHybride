package Briandais;

import java.io.Serializable;
import java.util.ArrayList;

import Hybride.Hybride;

public class Briandais implements Serializable{

	/**
	 * serialVersionUID permet à la JVM d'identifier les objets lorsqu'il les sérialises/désérialises.
	 */
	private static final long serialVersionUID = 1L;

	//public static final Briandais nil = null;
	private char clef;

	protected int profondeur;
	private Briandais frere;
	private Briandais fils;


	public Briandais(){
		clef=0;
	}

	public Briandais(String word){
		clef=word.charAt(0);
		frere= new Briandais();
		fils=new Briandais();
		profondeur=0;


		StringBuilder ord= new StringBuilder(word);
		ord.deleteCharAt(0);
		this.bloodLine(new String(ord));		
	}

	private Briandais(Briandais pere, char c){ //fils direct
		this.clef=c;
		frere= new Briandais();
		fils= new Briandais();
		profondeur=pere.profondeur+1;
		pere.fils=this;
	}


	/**
	 * Cette methode engendre une lignee de noeud a partir du String en parametre
	 * @param word
	 */
	private void bloodLine(String word){
		if(word.length()!=0){
			StringBuilder ord= new StringBuilder(word);
			ord.deleteCharAt(0);
			char c= word.charAt(0);
			Briandais fils = new Briandais(this,c);
			fils.bloodLine(new String(ord));
		}
	}

	/**
	 * Cette methode permet de normaliser un mot. En d'auutres termes elle verifie que la chaine de caractere est 
	 * bien correcte (et la reduit en minuscule si besoin est) et nous retourn le noeud correspondant au mot parametre 
	 */
	public Briandais Normaliser(String WORD){
		StringBuilder WORDB;

		WORD=WORD.toLowerCase();
		WORD=WORD.replaceAll("[^a-z]", "");
		WORDB= new StringBuilder(WORD);
		/*	if(WORDB.length()==0){
			char c=000;
			WORDB.append(c);
			return new Briandais(new String(WORDB));
		}*/

		return new Briandais(new String(WORDB.append((char)000)));		
	}	

	/**
	 * Cette methode permet d'inserer le mot en parametre dans l'arbre Briandais
	 * @param WORD
	 */

	public void ajout(String WORD){
		Briandais word = Normaliser(WORD);
		if(! word.isNil()){
			this.ajout(word); 
		}
	}

	private void ajout(Briandais Node){
		if(this.profondeur==0 && this.isNil()) this.is(Node); // si l'arbre est vide
		else if(this.clef>Node.clef){
			Node.frere.is(this);
			this.is(Node);
		}else if(this.clef<Node.clef){
			if(!this.frere.isNil()) this.frere.ajout(Node);
			else this.frere=Node;
		}else this.fils.ajout(Node.fils);

	}

	/**
	 * Cette methode permet de rechercher un mot dans l'arbre.
	 * @param WORD
	 */
	public boolean recherche(String WORD){
		Briandais word = Normaliser(WORD);
		return this.recherche(word);
	}

	/**
	 * Cette methode retourne true si le noeud est dans l'arbre, false sinon.
	 * @param Noeud
	 * @return boolean
	 */
	private boolean recherche(Briandais Node){
		if(this.profondeur==0 && this.isNil()) return false;
		if(this.clef!=Node.clef){
			if(this.frere.isNil()) return false;
			return this.frere.recherche(Node);
		}else {
			if(this.isNil()) return true;
			return this.fils.recherche(Node.fils);
		}
	}


	/**
	 * Cette methode prend un mot en argument et dit de combien de mots de l'arbre il est le prÃ©fixe
	 * @param WORD
	 * @return int
	 */
	public int prefixe(String WORD){
		Briandais word = Normaliser(WORD);
		return this.prefixe(word);
	}

	/**
	 * Cette méthode prend un noeud en argument et nous dit de combien de mots de l'arbre il est le préfixe
	 * @param Node
	 * @return int
	 */
	private int prefixe(Briandais Node){
		if(Node.isNil() && Node.profondeur==0) return this.nombreMots();
		if(Node.isNil())System.out.println(this.listMots());
		if(Node.clef<this.clef && !Node.isNil()) return 0;
		if(Node.clef>this.clef){
			if(this.frere.isNil()) return 0;
			return frere.prefixe(Node);
		}
		// si meme clef

		if(Node.fils.isNil())System.out.println("le(s) suffixe(s) :"+this.fils.listMots());
		if(!Node.fils.isNil()) return this.fils.prefixe(Node.fils);
		if(!this.fils.isNil()) return this.fils.nombreMots();
		if(this.fils.frere.isNil()) return 1;
		return this.fils.frere.prefixe(Node.fils)+1;

	}
	/**
	 * Cette methode retourne le nombre de mots de l'arbre
	 * @return int
	 */
	public int nombreMots(){

		if(this.isNil() && this.profondeur==0) return 0;

		int i=0;

		if(this.fils.isNil()) {

			i++;

			if(!this.fils.frere.isNil()) i+=this.fils.frere.nombreMots();

		}else i+=this.fils.nombreMots();

		if(this.frere.isNil()) return i;

		else return i+=this.frere.nombreMots();

	}

	/**
	 * Cette methode retourne le nombre de NIL de l'arbre
	 * @return int
	 */
	public int comptageNil(){
		int i=0;
		if(this.isNil() && this.profondeur==0) return 0;
		if(this.frere.isNil()) i++;
		else i+=this.frere.comptageNil();
		if(this.fils.isNil()){
			if(this.fils.frere.isNil()) i+=2; //le fils +son frere et son fils sont nil
			else i+=this.fils.frere.comptageNil()+1;
		}else i+=this.fils.comptageNil();
		return i;
	}


	/**
	 * Cette methode retourn retourn la hauteur de l'arbre
	 * @return int
	 */
	/**
	 * Cette methode retourn retourn la hauteur de l'arbre
	 * @return int
	 */
	public int hauteur(){
		int i=0;
		if(this.isNil() && this.profondeur==0) return 0;
		if(this.fils.isNil()){
			i++;
			if(!this.fils.frere.isNil()) i+=this.fils.frere.hauteur();
		} else i+=this.fils.hauteur()+1;
		if(!this.frere.isNil()) i=Math.max(i,  this.frere.hauteur());

		return i;
	}


	// profondeur
	public double profondeurMoyenne(){
		int[]T=this.profondeurMots(0);
		return (double)T[0]/T[1];
	}
	private int[] profondeurMots(int j){
		int[] T= new int[2];
		T[0]=0;// represente la somme des profondeurs
		T[1]=0;// represente le nombre de mots
		if(this.fils.isNil()){
			T[0]+=j+1;
			T[1]++;
			if(!this.fils.frere.isNil()){
				int[]R=this.fils.frere.profondeurMots(j+1);
				T[0]+=R[0];
				T[1]+=R[1];
			}
		}else {

			int[]R=this.fils.profondeurMots(j+1);
			T[0]+=R[0];
			T[1]+=R[1];
		}
		if(!this.frere.isNil()){

			int[]R=this.frere.profondeurMots(j+1);
			T[0]+=R[0];
			T[1]+=R[1];

		}	

		return T;
	}
	/**
	 * Cette methode retourne la fusion de deux Briandais 
	 * @param (Briandais, Briandais)
	 * @return Briandais
	 */
	public Briandais fusion(Briandais B1, Briandais B2){
		Briandais b1 = copy(B1);
		Briandais b2 = copy(B2);
		return b1.fusion(b2);
	}

	public Briandais fusion(Briandais B1){
		if(B1.isNil() && B1.profondeur==0) return this;
		if(this.isNil() && this.profondeur==0) return B1;
		Briandais A = new Briandais();
		if(this.clef<B1.clef){
			A.ajout(this);
			A.frere.is(this.frere.fusion(B1));
		}
		if(this.clef>B1.clef){
			A.ajout(B1);
			A.frere.is(this.fusion(B1.frere));
		}
		if(this.clef==B1.clef){
			this.fils.is(B1.fils.fusion(fils));
			A.ajout(this);
			A.frere.is(this.frere.fusion(B1.frere));

		}
		return A;
	}


	/**
	 * Cette methode retourne le dictionnaire de l'arbre
	 * @return ArrayList<String>
	 */
	public ArrayList<String> listMots(){
		return this.listMots(new StringBuilder());
	}

	private ArrayList<String> listMots(StringBuilder W){
		ArrayList<String> list= new ArrayList<String>();
		if(this.isNil() && this.profondeur==0) return new ArrayList<String>();

		if(this.isNil()) list.add(new String(W));
		else {
			W.append(this.clef);
			list.addAll(this.fils.listMots(W));
		}
		if(!this.frere.isNil()) list.addAll(this.frere.listMots(W));
		else if(W.length()!=0) W.deleteCharAt(W.length()-1);

		return list;
	}


	public Briandais copy(Briandais B){
		return B;
	}

	public void print(){
		if(this.isNil())return ;
		StringBuilder S= new StringBuilder();
		System.out.print(this);
		if(!this.fils.isNil())this.fils.print();

		if(this.fils.isNil() && !(this.fils.frere.isNil())) {
			System.out.println(this.fils.clef);
			for(int i=0; i<this.fils.profondeur;i++) S.append("   ");
			System.out.print(S+"|"+"\n"+S);
			this.fils.frere.print();
		}

		if(!(this.frere.isNil())){
			StringBuilder t =new StringBuilder();

			System.out.println();
			for(int i=0; i<this.profondeur;i++) {
				t.append("   ");
			}
			System.out.print(t+"|"+"\n"+t);
			this.frere.print();
		}


	}

	public String toString(){
		if(this.isNil()) return this.clef+"";
		return this.clef+"->";
	}


	public boolean isNil(){
		return this.clef==0 ;
	}
	public void is(Briandais Node){
		this.clef=Node.clef;
		this.frere=Node.frere;
		this.fils=Node.fils;
		this.profondeur=Node.profondeur;
	}



	// SUPPRESSION



	public void suppression(String WORD){
		Briandais word = Normaliser(WORD);
		this.suppression(word, new Briandais(), new Briandais());

	}
	/**
	 * le retour n'est qu'une gestion de la recursivite. La fonction doit toujours retournee vrai 
	 * @param Node
	 * @param frere_gauche (Briandais)
	 * @param pere	(Briandais)
	 * @return boolean
	 */
	private boolean suppression(Briandais Node, Briandais frere_gauche, Briandais pere){
		if(this.clef>Node.clef) return true;
		if(this.clef<Node.clef){
			if(this.frere.isNil()) return true;
			else if(this.frere.suppression(Node, this, new Briandais())) return true;
			else {
				if(this.profondeur==0){
					this.is(frere);
					return true;
				}
				if(pere.isNil()){
					frere_gauche.frere.is(frere);
					return true;
				}
				else{
					pere.fils.is(frere);
					if(this.frere.isNil()) return false;
					else return true;
				}

			}
		}
		// si ils ont la meme clef :
		if(this.isNil()){
			if(this.frere.isNil()) return false;
			else {
				pere.fils.is(frere);
				return true;
			}
		}else if(this.fils.suppression(Node.fils, new Briandais(), this)) return true;
		else {
			if(this.profondeur==0){
				this.is(frere);
				return true;
			}
			if(pere.isNil()){
				frere_gauche.frere.is(frere);
				return true;
			}
			else{

				if(this.frere.isNil()){
					pere.fils.is(frere);
					return false;
				}
				else {
					pere.fils.is(frere);
					return true;
				}
			}

		}

	}

	public Hybride toHybride(){
		return this.toHybride(0,0);
	}

	private Hybride toHybride(int val, int p){
		Hybride Nil= new Hybride();
		Nil.setClef((char)0);
		Nil.setProfondeur(p+1);
		if(this.isNil() && this.profondeur==0) return new Hybride();
		int i=1;
		Hybride A= new Hybride();
		A.setProfondeur(p);
		A.setInf(Nil);
		A.setClef(this.clef);
		if(this.fils.isNil()){
			A.setValeur(val);
			if(this.fils.frere.isNil()) A.setEq(Nil);
			else{
				A.setEq(this.fils.frere.toHybride(val+i, p+1));
				i++;
			}		
		}else {
			A.setValeur(-1);
			A.setEq(this.fils.toHybride(val, p+1));
		}

		if(this.frere.isNil()) A.setSup(Nil);
		else A.setSup(this.frere.toHybride(val+i, p+1));

		return A;
	}


	public Briandais getFils(){
		return this.fils;
	}

	public void setFils(Briandais fils){
		this.fils=fils;
	}

	public Briandais getFrere(){
		return this.frere;
	}

	public void setFrere(Briandais frere){
		this.frere=frere;
	}

	public char getClef(){
		return this.clef;
	}

	public void setClef(char clef){
		this.clef=clef;
	}

	public int getProfondeur(){
		return this.profondeur;
	}

	public void setProfondeur(int profondeur){
		this.profondeur=profondeur;
	}





}