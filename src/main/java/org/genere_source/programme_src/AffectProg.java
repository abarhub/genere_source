package org.genere_source.programme_src;

import com.google.common.base.Preconditions;

/**
 * Created with IntelliJ IDEA.
 * User: Alain
 * Date: 01/11/13
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 */
public class AffectProg implements InstructionProg {

	private VariableProg var_affecte;
	private ExpProg valeur_affecte;

	public AffectProg(VariableProg var_affecte,ExpProg valeur_affecte) {
		Preconditions.checkArgument(valeur_affecte != null);
		Preconditions.checkArgument(var_affecte!=null);
		this.valeur_affecte = valeur_affecte;
		this.var_affecte = var_affecte;
	}

	public VariableProg getVar_affecte() {
		return var_affecte;
	}

	public ExpProg getValeur_affecte() {
		return valeur_affecte;
	}

	public String toString()
	{
		return var_affecte+" = "+valeur_affecte;

	}
}
