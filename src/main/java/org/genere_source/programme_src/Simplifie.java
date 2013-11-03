package org.genere_source.programme_src;

import com.google.common.base.Preconditions;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alain
 * Date: 01/11/13
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
public class Simplifie {


	public static ExpProg simplifie(ExpProg e)
	{
		boolean simpl,est_simplifie=false;
		ExpProg res=null;
		ExpProg enfant1,enfant2,exp;
		OperationBinaireProg tmp;
		String s1,s2;
		Preconditions.checkNotNull(e);
		//if(!est_simplifie)
		{
			if(e instanceof OperationBinaireProg)
			{
				exp=e;
				do{
					simpl=false;
					if(exp instanceof OperationBinaireProg)
					{
						tmp=(OperationBinaireProg) exp;
						enfant1=tmp.getVal1();
						enfant2=tmp.getVal2();
						enfant1=simplifie(enfant1);
						enfant2=simplifie(enfant2);
						tmp=new OperationBinaireProg(tmp.getOp(),enfant1,enfant2);
						if(tmp.getOp()==OperateurProg.Plus)
						{
							s1=enfant1.toString();
							s2=enfant2.toString();
							if(s1.equals("0"))
							{
								res=enfant2;
								simpl=true;
							}
							else if(s2.equals("0"))
							{
								res=enfant1;
								simpl=true;
							}
							else if(enfant1 instanceof ConstEntierProg&&enfant2 instanceof ConstEntierProg)
							{
								res=new ConstEntierProg(((ConstEntierProg) enfant1).getN()+((ConstEntierProg) enfant2).getN());
								simpl=true;
							}
							else if(enfant1 instanceof OperationBinaireProg||enfant2 instanceof OperationBinaireProg)
							{
								ExpProg tmp2;
								tmp2=regroupe_addition(tmp);
								if(tmp2!=null)
								{
									res=tmp2;
								}
								else
								{
									res=tmp;
								}
							}
							else
							{
								res=tmp;
							}
						}
						else if(tmp.getOp()==OperateurProg.Moins)
						{
							s1=enfant1.toString();
							s2=enfant2.toString();
							if(s1.equals(s2))
							{
								res=new ConstEntierProg(0);
								simpl=true;
							}
							else if(s2.equals("0"))
							{
								res=enfant1;
								simpl=true;
							}
							else if(enfant1 instanceof ConstEntierProg&&enfant2 instanceof ConstEntierProg)
							{
								res=new ConstEntierProg(((ConstEntierProg) enfant1).getN()-((ConstEntierProg) enfant2).getN());
								simpl=true;
							}
							else if(enfant2 instanceof ConstEntierProg)
							{
								res=new OperationBinaireProg(OperateurProg.Plus,enfant1,new ConstEntierProg(-((ConstEntierProg) enfant2).getN()));
								simpl=true;
							}
							else if(enfant1 instanceof OperationBinaireProg||enfant2 instanceof OperationBinaireProg)
							{
								ExpProg tmp2;
								tmp2=regroupe_addition(tmp);
								if(tmp2!=null)
								{
									res=tmp2;
								}
								else
								{
									res=tmp;
								}
							}
							else
							{
								res=tmp;
							}
						}
						else if(tmp.getOp()==OperateurProg.Fois)
						{
							s1=enfant1.toString();
							s2=enfant2.toString();
							if(s1.equals("0")||s2.equals("0"))
							{
								res=new ConstEntierProg(0);
								simpl=true;
							}
							else if(s1.equals("1")||s2.equals("1"))
							{
								if(s1.equals("1"))
								{
									res=enfant2;
								}
								else
								{
									res=enfant1;
								}
							}
							else if(enfant1 instanceof ConstEntierProg&&enfant2 instanceof ConstEntierProg)
							{
								res=new ConstEntierProg(((ConstEntierProg) enfant1).getN()*((ConstEntierProg) enfant2).getN());
								simpl=true;
							}
							else if(enfant1 instanceof OperationBinaireProg||enfant2 instanceof OperationBinaireProg)
							{
								ExpProg tmp2;
								tmp2=regroupe_multiplication(tmp);
								if(tmp2!=null)
								{
									res=tmp2;
								}
								else
								{
									res=tmp;
								}
							}
							else
							{
								res=tmp;
							}
						}
						else if(tmp.getOp()==OperateurProg.Div)
						{
							s1=enfant1.toString();
							s2=enfant2.toString();
							if(s1.equals("0"))
							{
								res=new ConstEntierProg(1);
								simpl=true;
							}
							else if(s2.equals("0"))
							{
								int i=1/0;
								res=new ConstEntierProg(1);
								simpl=true;
							}
							else if(s1.equals(s2))
							{
								res=new ConstEntierProg(1);
								simpl=true;
							}
							else if(s2.equals("1"))
							{
								res=enfant1;
								simpl=true;
							}
							else if(enfant1 instanceof ConstEntierProg&&enfant2 instanceof ConstEntierProg)
							{
								//if(((ConstEntierProg) enfant2).getN()!=0)
								{
									res=new ConstEntierProg(((ConstEntierProg) enfant1).getN()/((ConstEntierProg) enfant2).getN());
									simpl=true;
								}
								/*else
								{

								}*/
							}
							else if(enfant1 instanceof OperationBinaireProg||enfant2 instanceof OperationBinaireProg)
							{
								ExpProg tmp2;
								tmp2=regroupe_multiplication(tmp);
								if(tmp2!=null)
								{
									res=tmp2;
								}
								else
								{
									res=tmp;
								}
							}
							else
							{
								res=tmp;
							}
						}
						else
						{
							Preconditions.checkState(false);
						}
					}
					Preconditions.checkNotNull(res);
					exp=res;
				}while(simpl);
			}
			else
			{
				res=e;
			}
		}
		Preconditions.checkNotNull(res);
		return res;
	}

	private static ExpProg regroupe_addition(OperationBinaireProg tmp) {
		Preconditions.checkNotNull(tmp);
		if(tmp.getOp()==OperateurProg.Plus||tmp.getOp()==OperateurProg.Moins)
		{
			List<ExpProg> liste_plus=null,liste_autre,liste2,liste_moins,liste_autre_moins,liste3;
			List<VariableProg> liste_variables,liste_variables_moins;
			int nb;
			ExpProg enfant1 = tmp.getVal1();
			ExpProg enfant2 = tmp.getVal2();
			if((enfant1 instanceof OperationBinaireProg&&
					(( ((OperationBinaireProg) enfant1).getOp()==OperateurProg.Plus)
						|| ((OperationBinaireProg) enfant1).getOp()==OperateurProg.Moins)
				||(enfant2 instanceof OperationBinaireProg&&
					(( ((OperationBinaireProg) enfant2).getOp()==OperateurProg.Plus)
						|| ((OperationBinaireProg) enfant2).getOp()==OperateurProg.Moins)) ))
			{
				liste_plus=new ArrayList<ExpProg>();
				liste_moins=new ArrayList<ExpProg>();
				ajoute_exp(liste_plus,liste_moins,enfant1);
				if(tmp.getOp()==OperateurProg.Plus)
				{
					ajoute_exp(liste_plus,liste_moins,enfant2);
				}
				else
				{
					ajoute_exp(liste_moins,liste_plus,enfant2);
				}
				nb=0;
				liste_variables=new ArrayList<VariableProg>();
				liste_autre=new ArrayList<ExpProg>();
				liste_variables_moins=new ArrayList<VariableProg>();
				liste_autre_moins=new ArrayList<ExpProg>();
				for(ExpProg e:liste_plus)
				{
					if(e instanceof ConstEntierProg)
					{
						nb+=((ConstEntierProg) e).getN();
					}
					else if(e instanceof VariableProg)
					{
						liste_variables.add((VariableProg) e);
					}
					else
					{
						liste_autre.add(e);
					}
				}
				for(ExpProg e:liste_moins)
				{
					if(e instanceof ConstEntierProg)
					{
						nb-=((ConstEntierProg) e).getN();
					}
					else if(e instanceof VariableProg)
					{
						boolean trouve=false;
						Iterator<VariableProg> iter = liste_variables.iterator();
						while(iter.hasNext())
						{
							VariableProg tmp2 = iter.next();
							if(tmp2.getNom().equals(((VariableProg) e).getNom()))
							{
								trouve=true;
								iter.remove();
								break;
							}
						}
						if(!trouve)
						{
							liste_variables_moins.add((VariableProg) e);
						}
					}
					else
					{
						liste_autre_moins.add(e);
					}
				}
				if(liste_variables.size()>1||liste_variables_moins.size()>1)
				{
					Comparator<VariableProg> tmp2;
					tmp2=new Comparator<VariableProg>() {
							@Override
							public int compare(VariableProg o1, VariableProg o2) {
								return o1.getNom().compareTo(o2.getNom());
							}
						};
					if(liste_variables.size()>1)
						Collections.sort(liste_variables,tmp2);
					if(liste_variables_moins.size()>1)
						Collections.sort(liste_variables_moins,tmp2);
				}
				liste2=new ArrayList<ExpProg>();
				if(nb!=0)
				{
					liste2.add(new ConstEntierProg(nb));
				}
				liste2.addAll(liste_variables);
				liste2.addAll(liste_autre);
				liste3=new ArrayList<ExpProg>();
				liste3.addAll(liste_variables_moins);
				liste3.addAll(liste_autre_moins);
				if(liste2.size()==0&&liste3.size()==0)
				{
					return new ConstEntierProg(0);
				}
				else if(liste2.size()==1&&liste3.size()==0)
				{
					return liste2.get(0);
				}
				else if(liste2.size()==0&&liste3.size()>0)
				{
					OperationBinaireProg tmp2;
					tmp2=new OperationBinaireProg(OperateurProg.Moins,new ConstEntierProg(0),liste3.get(0));
					for(int i=1;i<liste3.size();i++)
					{
						tmp2=new OperationBinaireProg(OperateurProg.Moins,tmp2,liste3.get(i));
					}
					return tmp2;
				}
				else
				{
					ExpProg tmp2,tmp3;
					Preconditions.checkState(liste2.size()>=1);
					tmp2=liste2.get(0);
					for(int i=1;i<liste2.size();i++)
					{
						tmp2=new OperationBinaireProg(OperateurProg.Plus,tmp2,liste2.get(i));
					}
					if(liste3.size()>0)
					{
						tmp3=liste3.get(0);
						for(int i=1;i<liste3.size();i++)
						{
							tmp3=new OperationBinaireProg(OperateurProg.Plus,tmp3,liste3.get(i));
						}
						tmp2=new OperationBinaireProg(OperateurProg.Moins,tmp2,tmp3);
					}
					return tmp2;
				}
			}
		}
		return null;
	}

	private static void ajoute_exp(List<ExpProg> liste, List<ExpProg> liste_moins, ExpProg enfant1) {
		Preconditions.checkNotNull(liste);
		Preconditions.checkNotNull(liste_moins);
		Preconditions.checkNotNull(enfant1);
		if(enfant1 instanceof OperationBinaireProg&&((OperationBinaireProg) enfant1).getOp()==OperateurProg.Plus)
		{
			ajoute_exp(liste, liste_moins, ((OperationBinaireProg) enfant1).getVal1());
			ajoute_exp(liste, liste_moins, ((OperationBinaireProg) enfant1).getVal2());
		}
		else if(enfant1 instanceof OperationBinaireProg&&((OperationBinaireProg) enfant1).getOp()==OperateurProg.Moins)
		{
			ajoute_exp(liste, liste_moins, ((OperationBinaireProg) enfant1).getVal1());
			ajoute_exp(liste_moins, liste, ((OperationBinaireProg) enfant1).getVal2());
		}
		else
		{
			liste.add(enfant1);
		}
	}

	private static ExpProg regroupe_multiplication(OperationBinaireProg tmp) {
		Preconditions.checkNotNull(tmp);
		if(tmp.getOp()==OperateurProg.Fois||tmp.getOp()==OperateurProg.Div)
		{
			List<ExpProg> liste_plus=null,liste_autre,liste2,liste_moins,liste_autre_moins,liste3;
			List<VariableProg> liste_variables,liste_variables_moins;
			int nb;
			boolean contient_nombre;
			ExpProg enfant1 = tmp.getVal1();
			ExpProg enfant2 = tmp.getVal2();
			if((enfant1 instanceof OperationBinaireProg&&
					(( ((OperationBinaireProg) enfant1).getOp()==OperateurProg.Fois)
						|| ((OperationBinaireProg) enfant1).getOp()==OperateurProg.Div)
				||(enfant2 instanceof OperationBinaireProg&&
					(( ((OperationBinaireProg) enfant2).getOp()==OperateurProg.Fois)
						|| ((OperationBinaireProg) enfant2).getOp()==OperateurProg.Div)) ))
			{
				liste_plus=new ArrayList<ExpProg>();
				liste_moins=new ArrayList<ExpProg>();
				multi_exp(liste_plus,liste_moins,enfant1);
				if(tmp.getOp()==OperateurProg.Fois)
				{
					multi_exp(liste_plus,liste_moins,enfant2);
				}
				else
				{
					multi_exp(liste_moins,liste_plus,enfant2);
				}
				nb=1;
				contient_nombre=false;
				liste_variables=new ArrayList<VariableProg>();
				liste_autre=new ArrayList<ExpProg>();
				liste_variables_moins=new ArrayList<VariableProg>();
				liste_autre_moins=new ArrayList<ExpProg>();
				for(ExpProg e:liste_plus)
				{
					if(e instanceof ConstEntierProg)
					{
						nb*=((ConstEntierProg) e).getN();
						contient_nombre=true;
					}
					else if(e instanceof VariableProg)
					{
						liste_variables.add((VariableProg) e);
					}
					else
					{
						liste_autre.add(e);
					}
				}
				for(ExpProg e:liste_moins)
				{
					if(e instanceof ConstEntierProg)
					{
						nb/=((ConstEntierProg) e).getN();
						contient_nombre=true;
					}
					else if(e instanceof VariableProg)
					{
						boolean trouve=false;
						Iterator<VariableProg> iter = liste_variables.iterator();
						while(iter.hasNext())
						{
							VariableProg tmp2 = iter.next();
							if(tmp2.getNom().equals(((VariableProg) e).getNom()))
							{
								trouve=true;
								iter.remove();
								break;
							}
						}
						if(!trouve)
						{
							liste_variables_moins.add((VariableProg) e);
						}
					}
					else
					{
						liste_autre_moins.add(e);
					}
				}
				if(liste_variables.size()>1||liste_variables_moins.size()>1)
				{
					Comparator<VariableProg> tmp2;
					tmp2=new Comparator<VariableProg>() {
							@Override
							public int compare(VariableProg o1, VariableProg o2) {
								return o1.getNom().compareTo(o2.getNom());
							}
						};
					if(liste_variables.size()>1)
						Collections.sort(liste_variables,tmp2);
					if(liste_variables_moins.size()>1)
						Collections.sort(liste_variables_moins,tmp2);
				}
				liste2=new ArrayList<ExpProg>();
				if(nb!=1)
				{
					liste2.add(new ConstEntierProg(nb));
				}
				liste2.addAll(liste_variables);
				liste2.addAll(liste_autre);
				liste3=new ArrayList<ExpProg>();
				liste3.addAll(liste_variables_moins);
				liste3.addAll(liste_autre_moins);
				if(liste2.size()==0&&liste3.size()==0||nb==0)
				{
					return new ConstEntierProg(0);
				}
				else if(liste2.size()==1&&liste3.size()==0)
				{
					return liste2.get(0);
				}
				else if(liste2.size()==0&&liste3.size()>0)
				{
					OperationBinaireProg tmp2;
					tmp2=new OperationBinaireProg(OperateurProg.Div,new ConstEntierProg(1),liste3.get(0));
					for(int i=1;i<liste3.size();i++)
					{
						tmp2=new OperationBinaireProg(OperateurProg.Div,tmp2,liste3.get(i));
					}
					return tmp2;
				}
				else
				{
					ExpProg tmp2,tmp3;
					Preconditions.checkState(liste2.size()>=1);
					tmp2=liste2.get(0);
					for(int i=1;i<liste2.size();i++)
					{
						tmp2=new OperationBinaireProg(OperateurProg.Fois,tmp2,liste2.get(i));
					}
					if(liste3.size()>0)
					{
						tmp3=liste3.get(0);
						for(int i=1;i<liste3.size();i++)
						{
							tmp3=new OperationBinaireProg(OperateurProg.Fois,tmp3,liste3.get(i));
						}
						tmp2=new OperationBinaireProg(OperateurProg.Div,tmp2,tmp3);
					}
					return tmp2;
				}
			}
		}
		return null;
	}

	private static void multi_exp(List<ExpProg> liste, List<ExpProg> liste_moins, ExpProg enfant1) {
		Preconditions.checkNotNull(liste);
		Preconditions.checkNotNull(liste_moins);
		Preconditions.checkNotNull(enfant1);
		if(enfant1 instanceof OperationBinaireProg&&((OperationBinaireProg) enfant1).getOp()==OperateurProg.Fois)
		{
			multi_exp(liste, liste_moins, ((OperationBinaireProg) enfant1).getVal1());
			multi_exp(liste, liste_moins, ((OperationBinaireProg) enfant1).getVal2());
		}
		else if(enfant1 instanceof OperationBinaireProg&&((OperationBinaireProg) enfant1).getOp()==OperateurProg.Div)
		{
			multi_exp(liste, liste_moins, ((OperationBinaireProg) enfant1).getVal1());
			multi_exp(liste_moins, liste, ((OperationBinaireProg) enfant1).getVal2());
		}
		else
		{
			liste.add(enfant1);
		}
	}
}
