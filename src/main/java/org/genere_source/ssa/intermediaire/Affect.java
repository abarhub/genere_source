package org.genere_source.ssa.intermediaire;

import java.util.List;

public class Affect extends Instr {

	private Var var;
	private Expr expr;
	
	public Affect(Var var,Expr expr)
	{
		super();
		this.var=var;
		this.expr=expr;
	}

	public Var getVar() {
		return var;
	}

	public void setVar(Var var) {
		this.var = var;
	}

	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
	}
	
	public String toString()
	{
		String s="";
		s+=liste_labels_toString();
		s+=var.toString()+":="+expr.toString();
		return s;
	}

	@Override
	public boolean instr_suivante() {
		return true;
	}

	@Override
	public List<String> saut() {
		return null;
	}

	@Override
	public boolean fin_block() {
		return false;
	}
}
