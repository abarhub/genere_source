package org.genere_source;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Sets;
/*import org.matheclipse.parser.client.Parser;
import org.matheclipse.parser.client.ast.*;
import org.matheclipse.parser.client.eval.DoubleEvaluator;
import org.matheclipse.parser.client.operator.InfixOperator;*/

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;


/*import org.matheclipse.core.eval.EvalUtilities;
import org.matheclipse.core.expression.F;
import org.matheclipse.core.form.output.OutputFormFactory;
import org.matheclipse.core.form.output.StringBufferWriter;
import org.matheclipse.core.interfaces.IExpr;*/


/**
 * Created with IntelliJ IDEA.
 * User: Alain
 * Date: 07/09/13
 * Time: 16:07
 * To change this template use File | Settings | File Templates.
 */
public class GenereCode {

	private List<List<Object>> parametres;
	private List<Object> resultat;
	private List<String> fonction;
	private final String nom_fonction="myFunction";
	private final int entier_min=-2;
	private final int entier_max=2;
	private final String operateurs[]={"+","-","*","/"};
	private final int nb_min_var=2;

	public GenereCode() {

	}

	public void generation_code2(List<List<Object>> parametres,List<Object> resultat){
		this.parametres=parametres;
		this.resultat=resultat;
		fonction=new ArrayList<String>();
		genere_code2();
	}

	private void genere_code2() {
		int nb_param,nb_var_local;
		String fonction,debut,fin,initialisation,boucle;
		List<String> liste_initialisation,liste_boucle;
		int nb_variables,nb_profondeur;
		GenereExpression gen;
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

		gen=new GenereExpression();
		ArrayList<String> operateurs0 = new ArrayList<String>();
		operateurs0.add("+");
		operateurs0.add("-");
		operateurs0.add("*");
		operateurs0.add("/");
		ArrayList<String> valeurs = new ArrayList<String>();
		/*for(int i=1;i<=nb_variables;i++)
		{
			valeurs.add("v"+i);
		}*/
		valeurs.add("0");
		valeurs.add("1");
		for(int i=0;i<nb_param;i++)
		{
			valeurs.add("p"+(i+1));
		}
		//Stopwatch stopwatch = new Stopwatch().start();

		List<String> res = gen.genere_expression(operateurs0, valeurs, nb_profondeur);

		// début de déclaration de la fonction
		debut="function "+nom_fonction+"(";
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
		fin="return v1;\n}\n";
		fonction=debut+fin;
		this.fonction.add(fonction);
		//liste_initialisation=calcul_initialisation(nb_var_local,nb_param);
		if(res!=null&&!res.isEmpty())
		{
			/*Set<String> set;
			//EvalUtilities util = new EvalUtilities();
			DoubleEvaluator engine = new DoubleEvaluator();
			Parser p = new Parser();
			//p.parse()
			//EvalUtilities util = new EvalUtilities();


			set=new HashSet<String>();
			for(String s:res)
			{
				//for(String v:valeurs)
				{
					String tmp,v,s2;
					v="v1";
					ASTNode tmp2 = p.parse(s);
					//s2=tmp2.toString();
					s2=convert(tmp2);
					System.out.println("sym "+s+":"+s2);
					if(set.contains(s2))
					{
						tmp=v+"="+s2+";\n";
						fonction=debut+tmp+fin;
						this.fonction.add(fonction);
						set.add(s2);
					}
				}
			}*/
		}
	}

	/*private String convert(ASTNode tmp2) {
			if(tmp2 instanceof IntegerNode)
			{
				IntegerNode tmp3=(IntegerNode)tmp2;
				return ""+tmp3;
			}
			else if(tmp2 instanceof FunctionNode)
			{
				FunctionNode tmp3= (FunctionNode) tmp2;
				System.out.println("tmp2_0="+tmp2);
				//int i=1/0;
				ASTNode op = ((FunctionNode) tmp2).get(0);
				String debut,fin,oper;
				if(op.getString().equals("Plus"))
				{
					oper="+";
				}
				else if(op.getString().equals("Times"))
				{
					oper="*";
				}
				else if(op.getString().equals("Minus"))
				{
					oper="-";
				}
				else if(op.getString().equals("Div"))
				{
					oper="/";
				}
				else if(op.getString().equals("Power"))
				{
					oper="^";
				}
				else
				{
					System.out.println("op="+op.getString());
					int i=1/0;
					oper=null;
				}
				debut=convert(tmp3.get(1));
				fin=convert(tmp3.get(2));
				return debut+oper+fin;
			}
			else if(tmp2 instanceof SymbolNode)
			{
				return ""+tmp2;
			}
			else if(tmp2 instanceof FractionNode)
			{
				return ""+tmp2;
			}
			else
			{
				System.out.println("tmp2="+tmp2+";"+tmp2.getClass());
				int i=1/0;
				return null;
			}
		}*/

	public void generation_code(List<List<Object>> parametres,List<Object> resultat){
		this.parametres=parametres;
		this.resultat=resultat;
		fonction=new ArrayList<String>();
		genere_code();
	}

	private void genere_code() {
		int nb_param,nb_var_local;
		String fonction,debut,fin,initialisation,boucle;
		List<String> liste_initialisation,liste_boucle;
		if(!parametres.isEmpty())
		{
			nb_param=parametres.get(0).size();
		}
		else
		{
			nb_param=0;
		}
		nb_var_local=Math.max(nb_min_var,nb_param*2);
		// début de déclaration de la fonction
		debut="function "+nom_fonction+"(";
		for(int i=0;i<nb_param;i++)
		{
			if(i>0)
				debut+=",";
			debut+="p"+(i+1);
		}
		debut+="){\n";
		// initialisation des variables locales
		for(int i=0;i<nb_var_local;i++)
		{
			debut+="v"+(i+1)+"=0;\n";
		}
		// fin de la fonction
		fin="return v1;\n}\n";
		fonction=debut+fin;
		this.fonction.add(fonction);
		liste_initialisation=calcul_initialisation(nb_var_local,nb_param);
		if(liste_initialisation!=null&&!liste_initialisation.isEmpty())
		{
			for(String s:liste_initialisation)
			{
				fonction=debut+s+fin;
				this.fonction.add(fonction);
			}
		}
	}

	private List<String> calcul_initialisation(int nb_var_local, int nb_param) {
		List<String> res,res2;
		String s,s2,s3;
		int tab[],val;
		boolean fin=false;
		Set<Integer> set;
		List<Set<Integer>> liste1;
		res=new ArrayList<String>();

		for(int i=entier_min;i<=entier_max;i++)
		{
			s="";
			for(int i2=0;i2<nb_var_local;i2++)
			{
				s+="v"+(i2+1)+"="+i+";\n";
			}
			res.add(s);
		}

		tab=new int[nb_var_local];
		for(int i2=0;i2<nb_var_local;i2++)
		{
			tab[i2]=entier_min;
		}

		/*set=donne_liste_nombre();
		liste1=new ArrayList<Set<Integer>>();
		for(int i=0;i<nb_var_local;i++)
		{
			liste1.add(set);
		}
		Set<List<Integer>> set2 = Sets.cartesianProduct(liste1);
		for(List<Integer> liste2:set2)
		{
			s="";
			for(int i2=0;i2<nb_var_local;i2++)
			{
				s+="v"+(i2+1)+"="+liste2.get(i2)+";\n";
			}
			res.add(s);
		}*/
		fin=false;
		while(!fin)
		{
			s="";
			for(int i2=0;i2<nb_var_local;i2++)
			{
				val=tab[i2];
				if(val<entier_max)
					s3=""+val;
				else
					s3="p"+(val-entier_max+1);
				s+="v"+(i2+1)+"="+s3+";\n";
			}
			res.add(s);
			res2=donne_iteration(nb_var_local);
			if(res2!=null)
			{
				for(String tmp:res2)
				{
					s2=s+tmp;
					res.add(s2);
				}
			}
			fin=inc(tab,nb_param);
		}

		return res;
	}

	private List<String> donne_iteration(int nb_var_local) {
		List<String> res;
		String s,debut,fin;
		res=new ArrayList<String>();
		debut="for(i=0;i<"+nb_var_local*2+"&&i<v2;i++){\n";
		fin="}\n";

		for(int i=0;i<operateurs.length;i++)
		{
			s="";
			for(int i2=1;i2<nb_var_local;i2++)
			{
				s="v1=v1"+operateurs[i]+"v"+(i2+1)+";\n";
				res.add(debut+s+fin);
			}
			//res.add(debut+s+fin);
		}

		for(int i=0;i<operateurs.length;i++)
		{
			s="";
			for(int i2=1;i2<nb_var_local;i2++)
			{
				for(int i3=1;i3<nb_var_local;i3++)
				{
					s="v1=v"+(i2+1)+operateurs[i]+"v"+(i3+1)+";\n";
					res.add(debut+s+fin);
				}
			}
			//res.add(debut+s+fin);
		}
		/*for(int i=0;i<operateurs.length;i++)
		{
			s="";
			for(int i2=0;i2<nb_var_local;i2++)
			{
				s+="v"+(i2+1)+"=v"+(i2+1)+operateurs[i]+i+";\n";
			}
			res.add(s);
		}*/

		return res;
	}

	/*private Set<Integer> donne_liste_nombre() {
		Set<Integer> set;
		set=new TreeSet<Integer>();
		for(int i=entier_min;i<=entier_max;i++)
		{
			set.add(i);
		}
		return set;
	}*/

	private boolean inc(int[] tab, int nb_param) {
		int pos;
		pos=0;
		while(pos<tab.length)
		{
			if(tab[pos]<entier_max+nb_param-1)
			{
				tab[pos]++;
				break;
			}
			else
			{
				tab[pos]=entier_min;
				pos++;
			}
		}
		if(pos>=tab.length)
			return true;
		else
			return false;
	}

	private boolean est_fin(int[] tab) {
		for(int i2=0;i2<tab.length;i2++)
		{
			if(tab[i2]!=entier_max)
			{
				return false;
			}
		}
		return true;
	}

	public void run() throws ScriptException, NoSuchMethodException {
		String fun;
		List<String> fonctions_ok;
		boolean res,erreur;
		fonctions_ok=new ArrayList<String>();
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
		for(String s:fonctions_ok)
		{
			System.out.println("FonctionProg :"+s);
		}
	}

	private boolean execute(int no_ligne,String code,int no_fonction) throws ScriptException, NoSuchMethodException {

		final List<Object> param;
		final Object res2;
		boolean resultat_fonction;

		param=parametres.get(no_ligne);
		res2=resultat.get(no_ligne);

		System.out.println("Appel " + nom_fonction+no_fonction + "(" + param + ") :");

		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");

        // JavaScript code in a String
        String script = code;
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
			tab=param.toArray();
		}

        // invoke the global function named "hello"
        res= inv.invokeFunction(nom_fonction, tab);

		if(res!=null&&res instanceof Integer)
		{
			res0=(Integer)res;
		}
		else
		{
			m=(Double)res;
			res0=(int)m;
		}
		resultat_fonction=res2.equals(res0);
		System.out.println("res="+res+";"+res0+" (ref="+res2+" : "+((resultat_fonction)?"egal":"diff")+")");
		return resultat_fonction;
	}

	public void affiche_fonctions()
	{
		for(int i=0;i<fonction.size();i++)
		{
			System.out.println("FonctionProg no "+i+" :");
			System.out.println(fonction.get(i));
		}
	}
}
