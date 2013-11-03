package org.genere_source;

import com.google.common.base.Stopwatch;
import org.genere_source.programme_src.ConstEntierProg;
import org.genere_source.programme_src.ExpProg;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
	    System.out.println( "Hello World!" );
	    //test1();
	    //test2();
	    //test3();
	    //test4();
	    test5();
    }


	private static void test5() {
		GenereCode2 tmp;
		List<List<ExpProg>> liste;
		List<ExpProg> retour,tmp2;
		tmp=new GenereCode2();
		liste=new ArrayList<List<ExpProg>>();

		if(false)
		{
			tmp2=new ArrayList<ExpProg>();
			tmp2.add(new ConstEntierProg(1));
			tmp2.add(new ConstEntierProg(1));
			liste.add(tmp2);
			tmp2=new ArrayList<ExpProg>();
			tmp2.add(new ConstEntierProg(1));
			tmp2.add(new ConstEntierProg(2));
			liste.add(tmp2);
			tmp2=new ArrayList<ExpProg>();
			tmp2.add(new ConstEntierProg(1));
			tmp2.add(new ConstEntierProg(3));
			liste.add(tmp2);
			tmp2=new ArrayList<ExpProg>();
			tmp2.add(new ConstEntierProg(2));
			tmp2.add(new ConstEntierProg(4));
			liste.add(tmp2);
			tmp2=new ArrayList<ExpProg>();
			tmp2.add(new ConstEntierProg(10));
			tmp2.add(new ConstEntierProg(15));
			liste.add(tmp2);
			retour=new ArrayList<ExpProg>();
			retour.add(new ConstEntierProg(2));
			retour.add(new ConstEntierProg(3));
			retour.add(new ConstEntierProg(4));
			retour.add(new ConstEntierProg(6));
			retour.add(new ConstEntierProg(25));
		}
		else
		{
			tmp2=new ArrayList<ExpProg>();
			tmp2.add(new ConstEntierProg(1));
			tmp2.add(new ConstEntierProg(1));
			liste.add(tmp2);
			tmp2=new ArrayList<ExpProg>();
			tmp2.add(new ConstEntierProg(1));
			tmp2.add(new ConstEntierProg(2));
			liste.add(tmp2);
			tmp2=new ArrayList<ExpProg>();
			tmp2.add(new ConstEntierProg(1));
			tmp2.add(new ConstEntierProg(3));
			liste.add(tmp2);
			tmp2=new ArrayList<ExpProg>();
			tmp2.add(new ConstEntierProg(2));
			tmp2.add(new ConstEntierProg(4));
			liste.add(tmp2);
			tmp2=new ArrayList<ExpProg>();
			tmp2.add(new ConstEntierProg(7));
			tmp2.add(new ConstEntierProg(19));
			liste.add(tmp2);
			retour=new ArrayList<ExpProg>();
			retour.add(new ConstEntierProg(1));
			retour.add(new ConstEntierProg(2));
			retour.add(new ConstEntierProg(3));
			retour.add(new ConstEntierProg(8));
			retour.add(new ConstEntierProg(133));
		}

		Stopwatch stopwatch = new Stopwatch().start();
		Stopwatch stopwatch2 = new Stopwatch().start();

		tmp.generation_code2(liste,retour);

		stopwatch.stop();

		tmp.affiche_fonctions();

		stopwatch2.stop();
		try {
			tmp.run();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("duree : "+stopwatch+";duree2 : "+stopwatch2);
	}

	private static void test4() {
		GenereCode tmp;
		List<List<Object>> liste;
		List<Object> retour,tmp2;
		tmp=new GenereCode();
		liste=new ArrayList<List<Object>>();

		if(false)
		{
			tmp2=new ArrayList<Object>();
			tmp2.add(1);
			tmp2.add(1);
			liste.add(tmp2);
			tmp2=new ArrayList<Object>();
			tmp2.add(1);
			tmp2.add(2);
			liste.add(tmp2);
			tmp2=new ArrayList<Object>();
			tmp2.add(1);
			tmp2.add(3);
			liste.add(tmp2);
			tmp2=new ArrayList<Object>();
			tmp2.add(2);
			tmp2.add(4);
			liste.add(tmp2);
			retour=new ArrayList<Object>();
			retour.add(2);
			retour.add(3);
			retour.add(4);
			retour.add(6);
		}
		else
		{
			tmp2=new ArrayList<Object>();
			tmp2.add(1);
			tmp2.add(1);
			liste.add(tmp2);
			tmp2=new ArrayList<Object>();
			tmp2.add(1);
			tmp2.add(2);
			liste.add(tmp2);
			tmp2=new ArrayList<Object>();
			tmp2.add(1);
			tmp2.add(3);
			liste.add(tmp2);
			tmp2=new ArrayList<Object>();
			tmp2.add(2);
			tmp2.add(4);
			liste.add(tmp2);
			tmp2=new ArrayList<Object>();
			tmp2.add(7);
			tmp2.add(19);
			liste.add(tmp2);
			retour=new ArrayList<Object>();
			retour.add(1);
			retour.add(2);
			retour.add(3);
			retour.add(8);
			retour.add(133);
		}

		Stopwatch stopwatch = new Stopwatch().start();
		Stopwatch stopwatch2 = new Stopwatch().start();

		tmp.generation_code2(liste,retour);

		stopwatch.stop();

		tmp.affiche_fonctions();

		stopwatch2.stop();
		try {
			tmp.run();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("duree : "+stopwatch+";duree2 : "+stopwatch2);
	}

	private static void test3() {
		GenereExpression gen;
		List<String> operateurs,valeurs,res;
		int nb_variables,nb_profondeur;
		GenereCode tmp;

		nb_variables=5;
		nb_profondeur=4;

		nb_variables=5;
		nb_profondeur=3;

		gen=new GenereExpression();
		operateurs=new ArrayList<String>();
		operateurs.add("+");
		operateurs.add("-");
		operateurs.add("*");
		operateurs.add("/");
		valeurs=new ArrayList<String>();
		for(int i=1;i<=nb_variables;i++)
		{
			valeurs.add("v"+i);
		}
		Stopwatch stopwatch = new Stopwatch().start();

		res=gen.genere_expression(operateurs,valeurs,nb_profondeur);

		stopwatch.stop();
		System.out.println("* expressions :");
		if(res!=null&&!res.isEmpty())
		{
			for(String s:res)
			{
				System.out.println(s);
			}
			System.out.println("nb expr:"+res.size());
		}
		System.out.println("duree : "+stopwatch);
	}

	private static void test2() {
		GenereCode tmp;
		List<List<Object>> liste;
		List<Object> retour,tmp2;
		tmp=new GenereCode();
		liste=new ArrayList<List<Object>>();
		tmp2=new ArrayList<Object>();
		tmp2.add(1);
		tmp2.add(1);
		liste.add(tmp2);
		tmp2=new ArrayList<Object>();
		tmp2.add(1);
		tmp2.add(2);
		liste.add(tmp2);
		tmp2=new ArrayList<Object>();
		tmp2.add(1);
		tmp2.add(3);
		liste.add(tmp2);
		tmp2=new ArrayList<Object>();
		tmp2.add(2);
		tmp2.add(4);
		liste.add(tmp2);
		retour=new ArrayList<Object>();
		retour.add(2);
		retour.add(3);
		retour.add(4);
		retour.add(6);

		tmp.generation_code(liste,retour);

		tmp.affiche_fonctions();

		try {
			tmp.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void test1() {
		GenereCode tmp;
		tmp=new GenereCode();
		try {
			tmp.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
