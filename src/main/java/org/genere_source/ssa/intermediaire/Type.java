package org.genere_source.ssa.intermediaire;

public class Type {

	private String nom;
	
	public Type(String nom)
	{
		this.nom=nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String toString()
	{
		return nom;
	}
}
