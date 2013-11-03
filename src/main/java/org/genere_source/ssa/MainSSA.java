package org.genere_source.ssa;

import org.genere_source.ssa.intermediaire.Affect;
import org.genere_source.ssa.intermediaire.Cst;
import org.genere_source.ssa.intermediaire.Program;
import org.genere_source.ssa.intermediaire.Type;
import org.genere_source.ssa.intermediaire.Var;

public class MainSSA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Program p;
		Var v1,v2,v3;
		Type t;
		p=new Program();
		
		t=new Type("int");
		v1=new Var("a",t);
		v2=new Var("b",t);
		v3=new Var("c",t);
		
		p.getListe_variables().add(v1);
		p.getListe_variables().add(v2);
		p.getListe_variables().add(v3);
		
		p.getListe_instructions().add(new Affect(v1,new Cst(1)));
		p.getListe_instructions().add(new Affect(v2,new Cst(50)));
		p.getListe_instructions().add(new Affect(v3,new Cst(86)));
		System.out.println("res="+p.toString());
	}

}
