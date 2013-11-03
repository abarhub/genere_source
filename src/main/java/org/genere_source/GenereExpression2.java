package org.genere_source;

import com.google.common.base.Preconditions;
import org.genere_source.programme_src.*;

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
public class GenereExpression2 {

	public GenereExpression2(){

	}

	public List<ExpProg> genere_expression(List<OperateurProg> operateurs,
	                                        List<ExpProg> valeurs, int nb_profondeur) {
		List<ExpProg> res;
		List<ExpProg> liste;
		Set<String> set;
		String code;

		res=new ArrayList<ExpProg>();

		liste=genere_expression2(operateurs, valeurs, nb_profondeur);

		if(liste!=null&&!liste.isEmpty())
		{
			set=new LinkedHashSet<String>();
			for(ExpProg f:liste)
			{
				//set.add(f.toString());
				code=f.toString();
				if(!set.contains(code))
				{
					set.add(code);
					res.add(f);
				}
			}
			//res.addAll(set);
			set=null;
		}

		return res;
	}

	private List<ExpProg> genere_expression2(List<OperateurProg> operateurs, List<ExpProg> valeurs, int profondeur) {
		List<ExpProg> res,liste1;
		ExpProg tmp;
		res=new ArrayList<ExpProg>();
		if(profondeur<=1)
		{
			for(ExpProg v:valeurs)
			{
				res.add(v);
			}
		}
		else
		{
			liste1=genere_expression2(operateurs,valeurs,profondeur-1);
			for(int i=0;i<liste1.size();i++)
			{
				for(int j=0;j<liste1.size();j++)
				{
					for(OperateurProg op:operateurs)
					{
						tmp=new OperationBinaireProg(op,liste1.get(i),liste1.get(j));
						//tmp.simplifie();
						try{
							tmp=Simplifie.simplifie(tmp);
							res.add(tmp);
						}catch(ArithmeticException e)
						{
							System.err.println("Expression invalide:"+e.getLocalizedMessage());
						}
					}
				}
			}
		}
		return res;
	}


}
