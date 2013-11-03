package org.genere_source;

import org.genere_source.programme_src.*;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alain
 * Date: 01/11/13
 * Time: 13:46
 * To change this template use File | Settings | File Templates.
 */
public class GenereCode2 {

	private List<List<ExpProg>> parametres;
	private List<ExpProg> resultat;
	private ArrayList<FonctionProg> fonction;

	public void generation_code2(List<List<ExpProg>> parametres, List<ExpProg> resultat) {
		this.parametres=parametres;
		this.resultat=resultat;
		fonction=new ArrayList<FonctionProg>();
		genere_code2();
	}

	private void genere_code2() {
		int nb_param,nb_var_local;
		String fonction,debut,fin,initialisation,boucle;
		List<String> liste_initialisation,liste_boucle;
		int nb_variables,nb_profondeur;
		GenereExpression2 gen;
		FonctionProg fonction2;
		String nom;
		List<InstructionProg > instructions;
		LinkedHashMap< VariableProg, TypeProg > parametres2;
		TypeProg type_retour;
		LinkedHashMap< VariableProg, TypeProg > variables_locales;
		if(!parametres.isEmpty())
		{
			nb_param=parametres.get(0).size();
		}
		else
		{
			nb_param=0;
		}
		//nb_var_local=Math.max(nb_min_var,nb_param*2);
		// génération des expressions possibles
		nb_variables=2;
		nb_profondeur=3;
		nb_var_local=1;

		gen=new GenereExpression2();
		ArrayList<OperateurProg> operateurs0 = new ArrayList<OperateurProg>();
		operateurs0.add(OperateurProg.Plus);
		operateurs0.add(OperateurProg.Moins);
		operateurs0.add(OperateurProg.Fois);
		operateurs0.add(OperateurProg.Div);
		ArrayList<ExpProg> valeurs = new ArrayList<ExpProg>();
		/*for(int i=1;i<=nb_variables;i++)
		{
			valeurs.add("v"+i);
		}*/
		valeurs.add(new ConstEntierProg(0));
		valeurs.add(new ConstEntierProg(1));
		for(int i=0;i<nb_param;i++)
		{
			valeurs.add(new VariableProg("p"+(i+1),TypeSimpleProg.Entier));
		}
		//Stopwatch stopwatch = new Stopwatch().start();

		//List<String> res = gen.genere_expression(operateurs0, valeurs, nb_profondeur);

		// début de déclaration de la fonction
		nom="myFunction";
		instructions=new ArrayList<InstructionProg>();
		parametres2=new LinkedHashMap<VariableProg, TypeProg>();
		for(int i=0;i<nb_param;i++)
		{
			parametres2.put(new VariableProg("p"+(i+1),TypeSimpleProg.Entier),TypeSimpleProg.Entier);
		}
		variables_locales=new LinkedHashMap<VariableProg, TypeProg>();
		for(int i=0;i<nb_var_local;i++)
		{
			variables_locales.put(new VariableProg("v"+(i+1),TypeSimpleProg.Entier),TypeSimpleProg.Entier);
		}

		/*debut="function "+nom_fonction+"(";
		for(int i=0;i<nb_param;i++)
		{
			if(i>0)
				debut+=",";
			debut+="p"+(i+1);
		}
		debut+="){\n";
		// initialisation des variables locales
		debut+="v1=0;\n";
		/*for(int i=0;i<valeurs.size();i++)
		{
			if(!valeurs.get(i).startsWith("p"))
			{
				debut+=valeurs.get(i)+"=0;\n";
			}
		}*/
		// fin de la fonction
		//fin="return v1;\n}\n";
		//fonction=debut+fin;
		type_retour=TypeSimpleProg.Entier;
		fonction2=new FonctionProg(nom,instructions,parametres2,type_retour,variables_locales);
		this.fonction.add(fonction2);

		List<ExpProg> res = gen.genere_expression(operateurs0, valeurs, nb_profondeur);

		//liste_initialisation=calcul_initialisation(nb_var_local,nb_param);
		if(res!=null&&!res.isEmpty())
		{
			Set<String> set;
			VariableProg v1;
			v1=new VariableProg("v1",TypeSimpleProg.Entier);
			//EvalUtilities util = new EvalUtilities();
			//DoubleEvaluator engine = new DoubleEvaluator();
			//Parser p = new Parser();
			//p.parse()
			//EvalUtilities util = new EvalUtilities();


			set=new HashSet<String>();
			for(ExpProg e:res)
			{
				//for(String v:valeurs)
				{
					String tmp,v,s2;
					ExpProg e2;
					//v="v1";
					//ASTNode tmp2 = p.parse(s);
					//s2=tmp2.toString();
					e2=simplifie(e);
					System.out.println("sym "+e+":"+e2);
					s2=e2.toString();
					if(!set.contains(s2))
					{
						instructions=new ArrayList<InstructionProg>();
						instructions.add(new AffectProg(v1,e2));
						//tmp=v+"="+s2+";\n";
						//fonction=debut+tmp+fin;
						//this.fonction.add(fonction);
						fonction2=new FonctionProg(nom,instructions,parametres2,type_retour,variables_locales);
						this.fonction.add(fonction2);
						set.add(s2);
					}
				}
			}
		}
	}

	private ExpProg simplifie(ExpProg e) {
		return Simplifie.simplifie(e);
	}

	public void affiche_fonctions() {
		for(int i=0;i<fonction.size();i++)
		{
			System.out.println("FonctionProg no "+i+" :");
			System.out.println(fonction.get(i));
		}
	}

	public void run() throws ScriptException, NoSuchMethodException {
		FonctionProg fun;
		List<FonctionProg> fonctions_ok;
		boolean res,erreur;
		fonctions_ok=new ArrayList<FonctionProg>();
		if(!fonction.isEmpty())
		{
			for(int i=0;i<fonction.size();i++)
			{
				fun=fonction.get(i);
				erreur=false;
				for(int j=0;j<parametres.size();j++)
				{
					res=execute(j,fun,i);
					if(res)
					{
						//fonctions_ok.add(fun);
					}
					else
					{
						erreur=true;
						break;
					}
				}
				if(!erreur)
				{
					fonctions_ok.add(fun);
				}
			}
		}
		System.out.println("Fonctions Ok :");
		for(FonctionProg s:fonctions_ok)
		{
			System.out.println("FonctionProg :"+s);
		}
	}

	private boolean execute(int no_ligne, FonctionProg code,int no_fonction) throws ScriptException, NoSuchMethodException {

		final List<ExpProg> param;
		final Object res2;
		final int res3;
		boolean resultat_fonction;

		param=parametres.get(no_ligne);
		res2=resultat.get(no_ligne);
		res3=((ConstEntierProg)res2).getN();

		System.out.println("Appel " + code.getNom()+no_fonction + "(" + param + ") :");


		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");

        // JavaScript code in a String
        String script = code.toJavascript();
		System.out.println("f : "+script);
        // evaluate script
        engine.eval(script);

        // javax.script.Invocable is an optional interface.
        // Check whether your script engine implements or not!
        // Note that the JavaScript engine implements Invocable interface.
        Invocable inv = (Invocable) engine;

		Object tab[]=null,res;
		double m;
		int res0;

		if(param!=null&&!param.isEmpty())
		{
			//tab=param.toArray();
			tab=new Object[param.size()];
			for(int i=0;i<param.size();i++)
			{
				ExpProg tmp = param.get(i);
				tab[i]=((ConstEntierProg)tmp).getN();
			}
		}

        // invoke the global function named "hello"
        res= inv.invokeFunction(code.getNom(), tab);

		if(res!=null&&res instanceof Integer)
		{
			res0=(Integer)res;
		}
		else if(res!=null&&res instanceof Double)
		{
			m=(Double)res;
			res0=(int)m;
		}
		else
		{
			String s0;
			s0= (String) res;
			res0=Integer.parseInt(s0);
		}
		//resultat_fonction=res2.equals(res0);
		resultat_fonction=res0==res3;
		System.out.println("res="+res+";"+res0+" (ref="+res2+" : "+((resultat_fonction)?"egal":"diff")+")");
		return resultat_fonction;
	}
}
