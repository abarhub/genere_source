package org.genere_source.ssa.intermediaire;

import javax.management.InvalidApplicationException;

public class Var extends Expr {

	private String nom;
	private Type type;
	
	public Var(String nom,Type type)
	{
		this.nom=nom;
		this.type=type;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public String toString()
	{
		return nom;
	}
	
	public boolean equals(Var v)
	{
		if(v==null)
			return false;
		if(v.getNom()==null)
			return nom==null;
		if(nom==null)
			return false;
		return v.getNom().equalsIgnoreCase(nom);
		
	}

	@Override
	public void remplace(Var x, Var y)  {
		throw new RuntimeException("Operation non permise");
	}
}
