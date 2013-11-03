package org.genere_source.programme_src;

import com.google.common.base.Preconditions;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alain
 * Date: 01/11/13
 * Time: 13:34
 * To change this template use File | Settings | File Templates.
 */
public class FonctionProg {

	private List<InstructionProg> instructions;
	private LinkedHashMap<VariableProg,TypeProg> parametres;
	private TypeProg type_retour;
	private LinkedHashMap<VariableProg,TypeProg> variables_locales;
	private String nom;

	public FonctionProg(String nom,List<InstructionProg> instructions, LinkedHashMap<VariableProg, TypeProg> parametres,
	                    TypeProg type_retour, LinkedHashMap<VariableProg, TypeProg> variables_locales) {
		Preconditions.checkArgument(nom != null);
		Preconditions.checkArgument(instructions!=null);
		Preconditions.checkArgument(parametres!=null);
		Preconditions.checkArgument(type_retour!=null);
		this.instructions = instructions;
		this.parametres = parametres;
		this.type_retour = type_retour;
		this.variables_locales = variables_locales;
		this.nom=nom;
	}

	public List<InstructionProg> getInstructions() {
		return instructions;
	}

	public Map<VariableProg, TypeProg> getParametres() {
		return parametres;
	}

	public TypeProg getType_retour() {
		return type_retour;
	}

	public String getNom() {
		return nom;
	}

	public Map<VariableProg, TypeProg> getVariables_locales() {
		return variables_locales;

	}

	public String toString()
	{
		String res;
		boolean debut;

		res=type_retour.getNom()+" "+nom+"(";
		if(parametres!=null&&!parametres.isEmpty())
		{
			debut=true;
			for(Map.Entry<VariableProg, TypeProg> tmp:parametres.entrySet())
			{
				if(!debut)
				{
					res+=",";
				}
				res+=tmp.getValue().getNom()+" "+tmp.getKey().toString();
				debut=false;
			}
		}
		res+=")\n{\n";
		if(variables_locales!=null&&!variables_locales.isEmpty())
		{
			String decl,init;
			//debut=true;
			decl="";
			init="";
			for(Map.Entry<VariableProg, TypeProg> tmp:variables_locales.entrySet())
			{
				//if(!debut)
				{
					//res+=",";
				}
				decl+=tmp.getValue().getNom()+" "+tmp.getKey().toString()+";\n";
				init+=tmp.getKey().toString()+"=0;\n";
				//debut=false;
			}
			res+=decl+init;
		}
		if(instructions!=null&&!instructions.isEmpty())
		{
			for(InstructionProg tmp:instructions)
			{
				res+=tmp+";\n";
			}
		}
		if(variables_locales!=null&&!variables_locales.isEmpty())
		{
			Map.Entry<VariableProg, TypeProg> tmp2=null;
			for(Map.Entry<VariableProg, TypeProg> tmp:variables_locales.entrySet())
			{
				tmp2=tmp;
				break;
			}
			res+="return "+tmp2.getKey()+";\n";
		}
		res+="}";
		return res;
	}

	public String toJavascript()
	{
		String res;
		boolean debut;

		res="function "+nom+"(";
		if(parametres!=null&&!parametres.isEmpty())
		{
			debut=true;
			for(Map.Entry<VariableProg, TypeProg> tmp:parametres.entrySet())
			{
				if(!debut)
				{
					res+=",";
				}
				res+=tmp.getKey().toString();
				debut=false;
			}
		}
		res+=")\n{\n";
		if(variables_locales!=null&&!variables_locales.isEmpty())
		{
			String decl,init;
			//debut=true;
			decl="";
			init="";
			for(Map.Entry<VariableProg, TypeProg> tmp:variables_locales.entrySet())
			{
				//if(!debut)
				{
					//res+=",";
				}
				//decl+=tmp.getValue().getNom()+" "+tmp.getKey().toString()+";\n";
				init+=tmp.getKey().toString()+"=0;\n";
				//debut=false;
			}
			res+=decl+init;
		}
		if(instructions!=null&&!instructions.isEmpty())
		{
			for(InstructionProg tmp:instructions)
			{
				res+=tmp+";\n";
			}
		}
		if(variables_locales!=null&&!variables_locales.isEmpty())
		{
			Map.Entry<VariableProg, TypeProg> tmp2=null;
			for(Map.Entry<VariableProg, TypeProg> tmp:variables_locales.entrySet())
			{
				tmp2=tmp;
				break;
			}
			res+="return "+tmp2.getKey()+";\n";
		}
		res+="}";
		return res;
	}
}
