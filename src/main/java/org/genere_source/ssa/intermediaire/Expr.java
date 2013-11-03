package org.genere_source.ssa.intermediaire;

public abstract class Expr {

	public abstract String toString();

	public abstract void remplace(Var x, Var y);
}
