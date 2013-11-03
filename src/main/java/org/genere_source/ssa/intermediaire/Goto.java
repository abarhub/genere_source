package org.genere_source.ssa.intermediaire;

import java.util.ArrayList;
import java.util.List;

public class Goto extends Instr {

	private String label;
	
	public Goto(String label)
	{
		super();
		this.label=label;
	}
	
	@Override
	public String toString() {
		String s="";
		s+=liste_labels_toString();
		s+="goto "+label;
		return s;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public boolean instr_suivante() {
		return false;
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
