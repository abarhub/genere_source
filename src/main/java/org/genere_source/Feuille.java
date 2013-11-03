package org.genere_source;

import com.google.common.base.Preconditions;

/**
 * Created with IntelliJ IDEA.
 * User: Alain
 * Date: 08/09/13
 * Time: 19:28
 * To change this template use File | Settings | File Templates.
 */
public class Feuille {

	private boolean est_valeur;
	private Feuille enfant1,enfant2;
	private String operateur;
	private String valeur;
	private boolean est_simplifie;

	public Feuille(String operateur,Feuille enfant1,Feuille enfant2) {

		Preconditions.checkNotNull(operateur);
		Preconditions.checkNotNull(enfant1);
		Preconditions.checkNotNull(enfant2);
		this.enfant1=enfant1;
		this.enfant2=enfant2;
		this.operateur=operateur;
		est_valeur=false;
	}


	public Feuille(String valeur) {
		Preconditions.checkNotNull(valeur);
		this.valeur=valeur;
		est_valeur=true;
	}

	@Override
	public String toString() {
		if(est_valeur)
		{
			return valeur;
		}
		else
		{
			String enf1,enf2;
			if(enfant1.est_valeur)
				enf1=enfant1.toString();
			else
				enf1="("+enfant1.toString()+")";
			if(enfant2.est_valeur)
				enf2=enfant2.toString();
			else
				enf2="("+enfant2.toString()+")";
			return enf1+operateur+enf2;
		}
	}

	public void simplifie()
	{
		boolean simpl;
		if(!est_simplifie)
		{
			if(!est_valeur)
			{
				Preconditions.checkNotNull(enfant1);
				Preconditions.checkNotNull(enfant2);
				enfant1.simplifie();
				enfant2.simplifie();
				Preconditions.checkNotNull(enfant1);
				Preconditions.checkNotNull(enfant2);
			}
			do{
				simpl=false;
				if(!est_valeur)
				{
					Preconditions.checkNotNull(enfant1);
					Preconditions.checkNotNull(enfant2);
					Preconditions.checkNotNull(operateur);
					if(operateur.equals("-"))
					{
						if(enfant1.est_valeur&&enfant2.est_valeur&&enfant1.valeur.equals(enfant2.valeur))
						{
							est_valeur=true;
							valeur="0";
							enfant1=null;
							enfant2=null;
							simpl=true;
							operateur=null;
						}
						else if(enfant1.est_valeur==enfant2.est_valeur&&enfant1.toString().equals(enfant2.toString()))
						{
							est_valeur=true;
							valeur="0";
							enfant1=null;
							enfant2=null;
							simpl=true;
							operateur=null;
						}
						else if(enfant2.est_valeur&&enfant2.est_nombre(0))
						{
							est_valeur=enfant1.est_valeur;
							valeur=enfant1.valeur;
							operateur=enfant1.operateur;
							enfant2=enfant1.enfant2;
							enfant1=enfant1.enfant1;
							simpl=true;
						}
					}
					else if(operateur.equals("*"))
					{
						if(enfant1.est_valeur&&enfant2.est_valeur&&(enfant1.est_nombre(0)||enfant2.est_nombre(0)))
						{
							est_valeur=true;
							valeur="0";
							enfant1=null;
							enfant2=null;
							simpl=true;
							operateur=null;
						}
						else if(enfant1.est_nombre(1))
						{
							est_valeur=enfant2.est_valeur;
							valeur=enfant2.valeur;
							operateur=enfant2.operateur;
							enfant1=enfant2.enfant1;
							enfant2=enfant2.enfant2;
							simpl=true;
						}
						else if(enfant2.est_nombre(1))
						{
							est_valeur=enfant1.est_valeur;
							valeur=enfant1.valeur;
							operateur=enfant1.operateur;
							enfant2=enfant1.enfant2;
							enfant1=enfant1.enfant1;
							simpl=true;
						}
					}
					else if(operateur.equals("/"))
					{
						if(enfant1.est_valeur&&enfant2.est_valeur&&enfant1.valeur.equals(enfant2.valeur))
						{
							est_valeur=true;
							valeur="1";
							enfant1=null;
							enfant2=null;
							simpl=true;
							operateur=null;
						}
						else if(enfant2.est_nombre(1))
						{
							est_valeur=enfant1.est_valeur;
							valeur=enfant1.valeur;
							operateur=enfant1.operateur;
							enfant2=enfant1.enfant2;
							enfant1=enfant1.enfant1;
							simpl=true;
						}
						else if(enfant1.est_nombre(0))
						{
							est_valeur=true;
							valeur="0";
							enfant1=null;
							enfant2=null;
							simpl=true;
							operateur=null;
						}
					}
					else if(operateur.equals("+"))
					{
						if(enfant1.est_nombre(0)||enfant2.est_nombre(0))
						{
							if(enfant1.est_nombre(0))
							{
								est_valeur=enfant2.est_valeur;
								valeur=enfant2.valeur;
								operateur=enfant2.operateur;
								enfant1=enfant2.enfant1;
								enfant2=enfant2.enfant2;
								simpl=true;
								Preconditions.checkState(valide());
							}
							else if(enfant2.est_nombre(0))
							{
								est_valeur=enfant1.est_valeur;
								valeur=enfant1.valeur;
								operateur=enfant1.operateur;
								enfant2=enfant1.enfant2;
								enfant1=enfant1.enfant1;
								simpl=true;
								/*if(!enfant1.est_valeur)
								{
									Preconditions.checkNotNull(enfant1.operateur);
								}
								else
								{
									Preconditions.checkState(enfant1.operateur==null);
								}
								Preconditions.checkState(enfant1.operateur==operateur);
								Preconditions.checkState(enfant1.est_valeur==est_valeur);
								Preconditions.checkState(enfant1.est_valeur||enfant1.operateur!=null);
								Preconditions.checkState(est_valeur||operateur!=null);*/
								Preconditions.checkState(valide());
							}
						}
						else
						{
							//if()
						}
					}
				}
			}while(simpl);
			est_simplifie=true;
		}
	}

	private boolean valide() {
		if(est_valeur)
		{
			if(valeur==null)
			{
				System.out.println("feuille:"+toString());
			}
			return valeur!=null;
		}
		else
		{
			if(operateur==null)
			{
				System.out.println("feuille:"+toString());
			}
			return operateur!=null&&enfant1!=null&&enfant2!=null;
		}
	}

	public boolean est_nombre(int n)
	{
		if(est_valeur)
		{
			Preconditions.checkNotNull(valeur,this.toString());
			if(Character.isDigit(valeur.charAt(0)))
			{
				return (""+n).equals(valeur);
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
}
