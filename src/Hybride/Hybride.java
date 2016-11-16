package Hybride;

import java.io.Serializable;
import java.util.ArrayList;

import Briandais.Briandais;

public class Hybride implements Serializable {



	/**
	 * serialVersionUID permet à la JVM d'identifier les objets lorsqu'il les sérialises/désérialises.
	 */
	private static final long serialVersionUID = 1L; 
	private char clef;
	private int valeur;
	private int profondeur;
	private Hybride inf;
	private Hybride eq;
	private Hybride sup;


	public static ArrayList<Hybride> FinsDeMots= new ArrayList<Hybride>(); // represente la liste des noeuds fin de mot

	public Hybride (){

		this.valeur =-1;


	}

	public Hybride(String word){
		clef=word.charAt(0);
		inf=new Hybride();
		eq=	new Hybride();
		sup=new Hybride();
		profondeur=0;
		if(word.length()==1){
			FinsDeMots.add(this);
			this.valeur=FinsDeMots.size()-1;				
		}
		else {
			this.valeur=-1;
			StringBuilder ord= new StringBuilder(word);
			ord.deleteCharAt(0);
			this.bloodLine(new String(ord));
		}
	}

	private Hybride(Hybride pere, char c, int v){ //fils direct
		clef=c;
		valeur=v; 
		inf= new Hybride();
		eq= new Hybride();
		sup= new Hybride();
		profondeur=pere.profondeur+1;
	}

	/**
	 * Cette methode engendre une lignee de noeud a partir du String en parametre
	 * @param word
	 */

	private void bloodLine(String word){
		if(word.length()>0){

			StringBuilder ord= new StringBuilder(word);
			ord.deleteCharAt(0);
			char c= word.charAt(0);
			if(word.length()==1) {
				this.eq = new Hybride(this,c,FinsDeMots.size());
				FinsDeMots.add(this.eq);

			}
			else this.eq = new Hybride(this,c,-1);

			eq.bloodLine(new String(ord));
		}
	}	

	/**
	 * Cette methode retourne la hauteur de l'arbre
	 * @return
	 */

	public int hauteur(){

		if(this.isNil()) return -1;
		return Math.max(Math.max(inf.hauteur()+1,eq.hauteur()+1), sup.hauteur()+1);

	}

	/**
	 * Cette methode compte le nombre de NIL dans l'arbre
	 * @return int
	 */

	public int comptageNil(){

		int i=0;
		if(this.isNil()) return 0;
		if(this.eq.isNil()) i=1;
		if(this.inf.isNil() && this.sup.isNil()) i+=2;
		if(this.inf.isNil() && !this.sup.isNil() || !this.inf.isNil() && this.sup.isNil()) i+=1;

		return i+inf.comptageNil()+eq.comptageNil()+sup.comptageNil();

	}

	/**
	 * Cette methode compte le nombre de mots dans l'arbre
	 * @return int
	 */
	public int nombreMots(){

		int i=0;
		if(this.isNil()) return 0;
		if(this.valeur!=-1) i=1;
		return i+ eq.nombreMots()+inf.nombreMots()+sup.nombreMots();
	}


	public String toString(){
		if(this.clef!=0){
			if(this.valeur!=-1){
				return this.clef+"("+this.valeur+")"+"->";
			}else return this.clef+"->";
		}return null;
	}

	/**
	 * Cette methode permet d'ajouter le mot WORD dans l'arbre
	 * @param WORD
	 */
	public void ajout(String WORD){

		Hybride word= normaliser(WORD);
		this.ajout(word);
	}


	private void ajout(Hybride Node){

		if(this.clef==0) this.is(Node);

		else if(this.clef==Node.clef) {
			if(Node.eq.isNil()){
				if(this.valeur==-1) {
					this.valeur=FinsDeMots.size()-1;
					FinsDeMots.remove(Node);
					FinsDeMots.add(this);
				}
				else FinsDeMots.remove(Node);
			}
			else this.eq.ajout(Node.eq);
		}

		else if(this.clef<Node.clef) {
			this.sup.ajout(Node);
			this.sup.profondeur=this.profondeur+1;
		}

		else {
			this.inf.ajout(Node);
			this.inf.profondeur=this.profondeur+1;
		}	

	}

	/**
	 * Cette methode permet de supprimer le mot WORD de l'arbre
	 * @param WORD
	 */

	public void suppression(String WORD){
		Hybride word= normaliser(WORD);
		FinsDeMots.remove(FinsDeMots.size()-1);
		this.suppression(word);
	}

	public boolean suppression(Hybride Node){

		if(this.isNil()) return true;
		if(this.clef<Node.clef){
			if(this.sup.suppression(Node)) return true;
			else {
				this.sup= new Hybride();
				return true;
			}
		}
		if(this.clef>Node.clef) {
			if(this.inf.suppression(Node)) return true;
			else{
				this.inf= new Hybride();
				return true;
			}
		}
		// les clef sont les memes
		if(Node.eq.isNil()){
			if(!this.eq.isNil()){
				if(this.valeur!=-1){
					this.miseAjourValeurs();
					this.valeur=-1;
				}
				return true;
			}else{

				this.miseAjourValeurs();
				if(this.inf.isNil() && this.sup.isNil()) {
					if(this.valeur!=-1 && this.profondeur==0){
						this.is(new Hybride());
						return true;
					}
					return false;
				}
				this.is(this.newEq());
				return true;
			}
		}else{
			if(this.eq.isNil()) return true;
			else{
				if(this.eq.suppression(Node.eq)) return true;
				else{
					if(this.valeur!=-1){
						this.eq=new Hybride();
						return true;
					}
					if(this.inf.isNil() && this.sup.isNil()) {
						if(this.profondeur!=0) return false;
						else {
							this.is(new Hybride());
							return true;
						}
					}
					this.is(this.newEq());
					return true;
				}
			}
		}
	}

	/**
	 * Cette fonction return un nouveu eq qui sera soit sup soit inf, selon leur ordre d'entree
	 * @return
	 */
	private Hybride newEq(){
		if(this.sup.min()<this.inf.min()){
			this.sup.ajout(inf);
			this.is(sup);

		}else{
			this.inf.ajout(sup);
			this.is(inf);
		}
		return this;
	}

	public void miseAjourValeurs(){
		int i=FinsDeMots.indexOf(this);
		if(i!=-1){
			for(int k=i; k<FinsDeMots.size(); k++){
				FinsDeMots.get(k).valeur--;
			}
			FinsDeMots.remove(this);
		}
	}

	public void is(Hybride Node){
		this.clef=Node.clef;
		this.eq=Node.eq;
		this.inf=Node.inf;
		this.sup=Node.sup;
		this.valeur=Node.valeur;
	}


	/**
	 * Cette methode permet de normaliser un mot. En d'auutres termes elle verifie que la chaine de caractere est 
	 * bien correcte (et la reduit en minuscule si besoin est) et nous retourn le noeud correspondant au mot parametre 
	 */

	public Hybride normaliser(String WORD){
		WORD=WORD.toLowerCase();
		WORD=WORD.replaceAll("[^a-z]", "");
		StringBuilder WORDB = new StringBuilder(WORD);
		if(WORD.length()==0){
			return new Hybride();
		}

		return new Hybride(new String(WORDB));		
	}

	public boolean isNil(){
		return this.clef==0;
	}


	public boolean recherche(String WORD){
		Hybride abrH = normaliser(WORD);
		return recherche(abrH);
	}


	public boolean recherche(Hybride WORD){
		if(this.clef==WORD.clef ){
			if((WORD.eq.isNil())){ 
				if( ((this.valeur==-1)))
					return false;
				else
					return true;	
			}
			if(this.eq.isNil()) return false;
			return this.eq.recherche(WORD.eq);
		}

		if(this.clef>WORD.clef){
			if(this.inf.isNil()) return false;
			return this.inf.recherche(WORD);
		}
		if(this.sup.isNil()) return false;
		return this.sup.recherche(WORD);

	}




	/**
	 * Cette methode retourne la profondeur moyenne de l'arbre
	 * @return double
	 */

	public double profondeurMoyenne(){

		int[] T = this.profondeurMoyenne(0);
		return (double)T[0]/T[1];

	}

	private int[] profondeurMoyenne(int j){ // j= profondeur de this
		int[] T= new int[2];
		T[0]=0; // somme des profondeurs
		T[1]=0;// nombres de eq nils
		if(this.eq.isNil() && this.profondeur==0) {
			if(!this.isNil()) T[0]++;
			T[1]=1;
			return T;

		}
		if(this.eq.isNil()) {
			T[0]+=(j+1);
			T[1]++;
		}

		else {
			int[] R=this.eq.profondeurMoyenne(j+1);
			T[0]+=R[0];
			T[1]+=R[1];
		}

		if(!this.inf.isNil()) {
			int[] R=this.inf.profondeurMoyenne(j+1);
			T[0]+=R[0];
			T[1]+=R[1];
		}

		if(!this.sup.isNil()){
			int[] R=this.sup.profondeurMoyenne(j+1);
			T[0]+=R[0];
			T[1]+=R[1];
		}

		return T;
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
		if(!this.inf.isNil()) {
			list.addAll(inf.listMots(W));
			W.deleteCharAt(W.length()-1);
		}

		if(!this.isNil()){
			W.append(this.clef);
			if(this.valeur!=-1) list.add(new String(W));
		}

		if(!this.eq.isNil()) {
			list.addAll(eq.listMots(W));
			W.deleteCharAt(W.length()-1);
		}

		if(!this.sup.isNil()) {
			W.deleteCharAt(W.length()-1);
			list.addAll(sup.listMots(W));
		}

		return list;
	}

	/**
	 * Cette methode nous donne le nombre de mots de l'arbre dont le mot en marametre est le prefixe	
	 * @param WORD
	 * @return int
	 */

	public int prefixe(String WORD){

		Hybride word= normaliser(WORD);
		return this.prefixe(word);

	}

	private int prefixe(Hybride Node){

		if(this.eq.isNil() && this.profondeur==0) return 0;

		if(this.clef==Node.clef){
			// le mot de this est fini
			if(this.eq.isNil() && this.sup.isNil() && this.inf.isNil()) {
				if(Node.eq.isNil()) return 1 ;
				else return 0;
			}
			//le mot de this n'est pas fini
			if(this.valeur!=-1 && Node.eq.isNil()) return this.eq.nombreMots()+1;
			if(Node.eq.isNil()) return this.eq.nombreMots();
			return this.eq.prefixe(Node.eq);

		}

		if(this.clef<Node.clef) return this.sup.prefixe(Node);

		return this.inf.prefixe(Node);
	}


	/**
	 * Pertmet de convertir un Hybride en un Briandais
	 * @return Briandais
	 */
	public Briandais toBriandais(){
		return this.toBriandais(0, new Briandais());
	}

	private Briandais toBriandais(int p, Briandais D){ // le Briandais D mis en param�tre est celui qui se trouve � droite de this

		if(this.isNil()) return new Briandais();

		Briandais A= new Briandais(); // se chargera de l'inf
		Briandais B= new Briandais(); // se chargera du tronc (this + eq)
		Briandais C= new Briandais();// se chargera du sup

		// PARTIE EQ
		B.setClef(this.clef);
		B.setProfondeur(p);

		if(this.eq.isNil()){	
			B.setFils(new Briandais());	
			B.getFils().setFrere(new Briandais());
			B.getFils().setFils(new Briandais());
			B.getFils().setProfondeur(p+1);
		}else {
			if(this.valeur!=-1){
				B.setFils(new Briandais());
				B.getFils().setProfondeur(p+1);
				B.getFils().setFrere(this.eq.toBriandais(p+1, new Briandais()));
			}else{
				B.setFils(this.eq.toBriandais(p+1, new Briandais()));
			}
		}

		//PARTIE SUP ET INF
		if(this.sup.isNil()) B.setFrere(D);
		else{
			C=sup.toBriandais(p,D);
			B.setFrere(C);
		}

		if(this.inf.isNil()) return B;
		else{
			A=inf.toBriandais(p, B);
			return A;
		}

	}

	

	/**
	 * Cette methode retourne la plus petite valeur que possede l'arbre. 
	 * Cette valeur est forcement au niveau de son EQ.
	 * @return int
	 */
	public int min(){
		int min;
		min=this.nombreMots()-1;
		if(this.isNil()) return min;
		if(this.valeur!=-1 && this.valeur<min) min=this.valeur;
		min=Math.min(this.eq.min(), min);
		return min;
	}


	public void equilibrage(){
		
		if(!this.isNil()){
			int hS=this.sup.hauteur();
			int hI=this.inf.hauteur();
			
			if(hS>hI+2) this.rotationG();
			if(hI>hS+2)this.rotationD();
			
			this.sup.equilibrage();
			this.inf.equilibrage();
			this.eq.equilibrage();
			
			
				
		}
		
	}
	
	private void rotationD(){ // permet de faire une rotation Droite analogue a l'AVL
		Hybride cp = new Hybride();
		cp.is(this.inf);
		this.inf=new Hybride();
		cp.ajout(this);
		this.is(cp);
	}
	
	private void rotationG(){ // permet de faire une rotation Gauche analogue a l'AVL
		Hybride cp = new Hybride();
		cp.is(this.sup);
		this.sup=new Hybride();
		cp.ajout(this);
		this.is(cp);
	}
	

	// GET & SET function
	public Hybride getInf(){
		return this.inf;
	}

	public void setInf(Hybride inf){
		this.inf=inf;
	}

	public Hybride getSup(){
		return this.sup;
	}

	public void setSup(Hybride sup){
		this.sup=sup;
	}

	public Hybride getEq(){
		return this.eq;
	}

	public void setEq(Hybride eq){
		this.eq=eq;
	}

	public int getProfondeur(){
		return this.profondeur;
	}

	public void setProfondeur(int profondeur){
		this.profondeur=profondeur;
	}

	public int getValeur(){
		return this.valeur;
	}

	public void setValeur(int valeur){
		this.valeur=valeur;
	}

	public char getClef(){
		return this.clef;
	}

	public void setClef(char clef){
		this.clef=clef;

	}


}






