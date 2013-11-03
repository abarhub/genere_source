package org.genere_source.programme_src;

import com.google.common.base.Preconditions;

/**
 * Created with IntelliJ IDEA.
 * User: Alain
 * Date: 01/11/13
 * Time: 13:39
 * To change this template use File | Settings | File Templates.
 */
public enum TypeSimpleProg implements TypeProg {
	Entier("int");

	private TypeSimpleProg(String nom)
	{
		Preconditions.checkArgument(nom != null);
		Preconditions.checkArgument(nom.length()>0);
		this.nom=nom;
	}

	private final String nom;

	public String getNom()
	{
		return nom;
	}
}
