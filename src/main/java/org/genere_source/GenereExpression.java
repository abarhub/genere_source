package org.genere_source;

import com.google.common.base.Stopwatch;
import org.genere_source.programme_src.ExpProg;
import org.genere_source.programme_src.OperateurProg;
import org.genere_source.programme_src.OperationBinaireProg;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Alain
 * Date: 08/09/13
 * Time: 19:26
 * To change this template use File | Settings | File Templates.
 */
public class GenereExpression {

	public GenereExpression(){

	}

	public List<String> genere_expression(List<String> operateurs,List<String> valeurs,int profondeur)
	{
		List<String> res;
		List<Feuille> liste;
		Set<String> set;

		res=new ArrayList<String>();

		liste=genere_expression2(operateurs, valeurs, profondeur);

		if(liste!=null&&!liste.isEmpty())
		{
			set=new LinkedHashSet<String>();
			for(Feuille f:liste)
			{
				set.add(f.toString());
			}
			res.addAll(set);
			set=null;
		}

		return res;
	}

	private List<Feuille> genere_expression2(List<String> operateurs, List<String> valeurs, int profondeur) {
		List<Feuille> res,liste1;
		Feuille tmp;
		res=new ArrayList<Feuille>();
		if(profondeur<=1)
		{
			for(String v:valeurs)
			{
				tmp=new Feuille(v);
				res.add(tmp);
			}
		}
		else
		{
			liste1=genere_expression2(operateurs,valeurs,profondeur-1);
			for(int i=0;i<liste1.size();i++)
			{
				for(int j=0;j<liste1.size();j++)
				{
					for(String op:operateurs)
					{
						tmp=new Feuille(op,liste1.get(i),liste1.get(j));
						tmp.simplifie();
						res.add(tmp);
					}
				}
			}
		}
		return res;
	}

}
