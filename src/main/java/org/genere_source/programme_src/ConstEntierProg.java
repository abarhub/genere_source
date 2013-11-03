package org.genere_source.programme_src;

/**
 * Created with IntelliJ IDEA.
 * User: Alain
 * Date: 01/11/13
 * Time: 13:41
 * To change this template use File | Settings | File Templates.
 */
public class ConstEntierProg implements ExpProg {

	private int n;

	public ConstEntierProg(int n)
	{
		this.n=n;
	}


	public TypeProg getTypeRetour() {
		return TypeSimpleProg.Entier;
	}

	public int getN() {
		return n;
	}

	@Override
	public String toString() {
		/*if(n<0)
			return " "+n;*/
		return ""+n;

	}
}
