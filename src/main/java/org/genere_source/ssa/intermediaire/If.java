package org.genere_source.ssa.intermediaire;

import java.util.ArrayList;
import java.util.List;

public class If extends Instr {

	private Expr expr;
	private String label;
	
	public If(Expr expr,String label)
	{
		super();
		this.expr=expr;
		this.label=label;
	}

	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String toString()
	{
		String s="";
		s+=liste_labels_toString();
		s+="if "+expr.toString()+" goto "+label;
		return s;
	}

	@Override
	public boolean instr_suivante() {
		return true;
	}

	@Override
	public List<String> saut() {
		List<String> liste;
		liste=new ArrayList<String>();
		liste.add(label);
		return liste;
	}

	@Override
	public boolean fin_block() {
		// TODO Auto-generated method stub
		return false;
	}
}
