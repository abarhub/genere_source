package org.genere_source.programme_src;

import com.google.common.base.Preconditions;

/**
 * Created with IntelliJ IDEA.
 * User: Alain
 * Date: 01/11/13
 * Time: 13:34
 * To change this template use File | Settings | File Templates.
 */
public class VariableProg implements ExpProg {

	private String nom;
	private TypeProg typeVar;

	public VariableProg(String nom,TypeProg typeVar) {
		Preconditions.checkArgument(nom!=null);
		Preconditions.checkArgument(nom.length()>0);
		Preconditions.checkArgument(nom.matches("^[a-zA-Z][a-zA-Z0-9_]*$"));
		Preconditions.checkArgument(typeVar!=null);
		this.nom = nom;
		this.typeVar=typeVar;
	}

	public String getNom() {
		return nom;
	}

	public TypeProg getTypeRetour() {
		return typeVar;
	}

	public String toString()
	{
		return nom;
	}
}
