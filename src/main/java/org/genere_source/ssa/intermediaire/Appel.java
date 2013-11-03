package org.genere_source.ssa.intermediaire;

import java.util.List;

public class Appel extends Instr {

	private String nom;
	private List<Expr> param;
	
	public Appel(String nom,List<Expr> param) {
		this.nom=nom;
		this.param=param;
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
	public String toString() {
		String s="";
		s+=liste_labels_toString();
		s+=nom;
		if(param!=null&&!param.isEmpty())
		{
			boolean debut=true;
			for(Expr e:param)
			{
				if(debut)
					s+=",";
				s+=e.toString();
				debut=false;
			}
		}
		return s;
	}

	@Override
	public boolean fin_block() {
		return true;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Expr> getParam() {
		return param;
	}

	public void setParam(List<Expr> param) {
		this.param = param;
	}

}
