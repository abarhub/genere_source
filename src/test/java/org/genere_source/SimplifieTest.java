package org.genere_source;

import org.genere_source.programme_src.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Alain
 * Date: 01/11/13
 * Time: 18:29
 * To change this template use File | Settings | File Templates.
 */
public class SimplifieTest {


	@Test
	public void test1()
	{
		ExpProg e1,res;
		e1=plus(donne_entier(5),donne_entier(7));
		res=Simplifie.simplifie(e1);
		Assert.assertEquals("12",res.toString());

		e1=plus(donne_entier(10),donne_var("a"));
		res=Simplifie.simplifie(e1);
		Assert.assertEquals("10+a",res.toString());

		e1=moins(donne_entier(15),donne_entier(15));
		res=Simplifie.simplifie(e1);
		Assert.assertEquals("0",res.toString());

		e1=plus(donne_entier(0),donne_var("a5"));
		res=Simplifie.simplifie(e1);
		Assert.assertEquals("a5",res.toString());

		e1=plus(donne_var("a7"),donne_entier(0));
		res=Simplifie.simplifie(e1);
		Assert.assertEquals("a7",res.toString());

		e1=moins(donne_var("a9"),donne_entier(0));
		res=Simplifie.simplifie(e1);
		Assert.assertEquals("a9",res.toString());

		e1=fois(donne_var("a8"),donne_entier(1));
		res=Simplifie.simplifie(e1);
		Assert.assertEquals("a8",res.toString());

		e1=fois(donne_entier(1), donne_var("a6"));
		res=Simplifie.simplifie(e1);
		Assert.assertEquals("a6",res.toString());

		e1=fois(donne_var("a1"),donne_entier(0));
		res=Simplifie.simplifie(e1);
		Assert.assertEquals("0",res.toString());

		e1=fois(donne_entier(0), donne_var("a2"));
		res=Simplifie.simplifie(e1);
		Assert.assertEquals("0",res.toString());
	}

	@Test
	public void test2()
	{
		ExpProg e1,res;

		e1=plus(donne_entier(5),donne_entier(7));
		res=Simplifie.simplifie(e1);
		Assert.assertEquals("12",res.toString());

		e1=plus(donne_entier(4),plus(donne_entier(9),donne_var("a3")));
		res=Simplifie.simplifie(e1);
		Assert.assertEquals("13+a3",res.toString());

		e1=plus(plus(donne_entier(10),donne_var("a2")),donne_entier(5));
		res=Simplifie.simplifie(e1);
		Assert.assertEquals("15+a2",res.toString());

		e1=plus(plus(plus(donne_var("b1"),donne_entier(6)),donne_var("a4")),donne_entier(7));
		res=Simplifie.simplifie(e1);
		Assert.assertEquals("(13+a4)+b1",res.toString());
	}



	@Test
	public void test3()
	{
		ExpProg e1,res;

		e1=fois(donne_entier(6),donne_entier(3));
		res=Simplifie.simplifie(e1);
		Assert.assertEquals("18",res.toString());

		e1=fois(donne_entier(24),fois(donne_entier(8),donne_var("a3")));
		res=Simplifie.simplifie(e1);
		Assert.assertEquals("192*a3",res.toString());

		e1=fois(fois(donne_entier(16),donne_var("a4")),fois(donne_entier(6),donne_var("a3")));
		res=Simplifie.simplifie(e1);
		Assert.assertEquals("(96*a3)*a4",res.toString());

		e1=div(fois(donne_entier(18),donne_var("a5")),fois(donne_entier(9),donne_var("a5")));
		res=Simplifie.simplifie(e1);
		Assert.assertEquals("2",res.toString());
	}

	public ConstEntierProg donne_entier(int n)
	{
		return new ConstEntierProg(n);
	}

	public VariableProg donne_var(String nom)
	{
		return new VariableProg(nom,TypeSimpleProg.Entier);
	}

	public OperationBinaireProg plus(ExpProg e1,ExpProg e2)
	{
		return new OperationBinaireProg(OperateurProg.Plus, e1,e2);
	}

	public OperationBinaireProg moins(ExpProg e1,ExpProg e2)
	{
		return new OperationBinaireProg(OperateurProg.Moins, e1,e2);
	}

	public OperationBinaireProg fois(ExpProg e1,ExpProg e2)
	{
		return new OperationBinaireProg(OperateurProg.Fois, e1,e2);
	}

	public OperationBinaireProg div(ExpProg e1,ExpProg e2)
	{
		return new OperationBinaireProg(OperateurProg.Div, e1,e2);
	}
}
