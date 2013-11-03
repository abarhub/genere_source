package org.genere_source.ssa.intermediaire;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BasicBloc {

	private int no;
	private List<Instr> liste_instr;
	private List<BasicBloc> liste_bloc_suivant;
	private List<BasicBloc> liste_bloc_precedant;
	private List<BasicBloc> fils_dom;
	private BasicBloc pere_dom;
	private Set<BasicBloc> dominance_frontiere;
	
	public BasicBloc(int no)
	{
		this.no=no;
		liste_instr=new ArrayList<Instr>();
		liste_bloc_suivant=new ArrayList<BasicBloc>();
		liste_bloc_precedant=new ArrayList<BasicBloc>();
		fils_dom=new ArrayList<BasicBloc>();
		dominance_frontiere=new HashSet<BasicBloc>();
	}

	public List<Instr> getListe_instr() {
		return liste_instr;
	}

	public void setListe_instr(List<Instr> liste_instr) {
		this.liste_instr = liste_instr;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public List<BasicBloc> getListe_bloc_suivant() {
		return liste_bloc_suivant;
	}

	public void setListe_bloc_suivant(List<BasicBloc> liste_bloc_suivant) {
		this.liste_bloc_suivant = liste_bloc_suivant;
	}
	
	public String toString()
	{
		String s="";
		boolean debut=true;
		s+="Bloc "+no+":\n";
		for(Instr ins:liste_instr)
		{
			s+=ins.toString()+"\n";
		}
		s+="Bloc suivant :";
		debut=true;
		for(BasicBloc b:liste_bloc_suivant)
		{
			if(!debut)
				s+=",";
			s+=b.getNo();
			debut=false;
		}
		s+="\n";
		s+="Bloc pr�c�dant :";
		debut=true;
		for(BasicBloc b:liste_bloc_precedant)
		{
			if(!debut)
				s+=",";
			s+=b.getNo();
			debut=false;
		}
		s+="\n";
		s+="Pere dominateur :";
		if(pere_dom!=null)
		{
			s+=pere_dom.getNo();
		}
		s+="\n";
		s+="Fils domin�es :";
		debut=true;
		for(BasicBloc b:fils_dom)
		{
			if(!debut)
				s+=",";
			s+=b.getNo();
			debut=false;
		}
		s+="\n";
		s+="Dominance fronti�re :";
		debut=true;
		for(BasicBloc b:dominance_frontiere)
		{
			if(!debut)
				s+=",";
			s+=b.getNo();
			debut=false;
		}
		s+="\n";
		return s;
	}

	public List<BasicBloc> getFils_dominee() {
		return fils_dom;
	}

	public void setFils_dominee(List<BasicBloc> fils_dominee) {
		this.fils_dom = fils_dominee;
	}

	public List<BasicBloc> getListe_bloc_precedant() {
		return liste_bloc_precedant;
	}

	public void setListe_bloc_precedant(List<BasicBloc> liste_bloc_precedant) {
		this.liste_bloc_precedant = liste_bloc_precedant;
	}
	
	public void defini_bloc_fils(BasicBloc b)
	{
		assert(b!=null);
		this.liste_bloc_suivant.add(b);
		b.getListe_bloc_precedant().add(this);
	}
	
	public void defini_bloc_fils_dom(BasicBloc b)
	{
		fils_dom.add(b);
		b.setPere_dom(this);
	}

	public BasicBloc getPere_dom() {
		return pere_dom;
	}

	public void setPere_dom(BasicBloc pere_dom) {
		this.pere_dom = pere_dom;
	}

	public List<BasicBloc> getFils_dom() {
		return fils_dom;
	}

	public void setFils_dom(List<BasicBloc> fils_dom) {
		this.fils_dom = fils_dom;
	}

	public Set<BasicBloc> getDominance_frontiere() {
		return dominance_frontiere;
	}

	public void setDominance_frontiere(Set<BasicBloc> dominance_frontiere) {
		this.dominance_frontiere = dominance_frontiere;
	}
}
