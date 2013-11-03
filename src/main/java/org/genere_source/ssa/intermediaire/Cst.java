package org.genere_source.ssa.intermediaire;

public class Cst extends Expr {

	private String c;
	
	public Cst(String c)
	{
		this.c=c;
	}

	public Cst(int i)
	{
		this.c=""+i;
	}
	
	@Override
	public String toString() {
		return c;
	}

	@Override
	public void remplace(Var x, Var y) {
		
	}

}
