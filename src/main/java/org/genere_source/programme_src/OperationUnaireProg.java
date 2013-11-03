package org.genere_source.programme_src;

import com.google.common.base.Preconditions;

/**
 * Created with IntelliJ IDEA.
 * User: Alain
 * Date: 01/11/13
 * Time: 13:43
 * To change this template use File | Settings | File Templates.
 */
public class OperationUnaireProg implements ExpProg {

	private ExpProg val1;
	private OperateurProg op;

	public OperationUnaireProg(OperateurProg op, ExpProg val1) {
		Preconditions.checkArgument(op != null);
		Preconditions.checkArgument(val1 != null);
        Preconditions.checkArgument(op ==OperateurProg.Plus||op ==OperateurProg.Moins);
		this.val1 = val1;
		this.op=op;
	}

	public TypeProg getTypeRetour() {
		return TypeSimpleProg.Entier;
	}

	@Override
	public String toString() {
		String res="";
        switch(op)
        {
            case Plus:
                res="+";
                break;
            case Moins:
                res="-";
                break;
            case Fois:
                res="*";
                break;
            case Div:
                res="/";
                break;
        }
		if(val1 instanceof ConstEntierProg||val1 instanceof VariableProg)
			res+=val1.toString();
		else
			res+="("+val1.toString()+")";

		return res;
	}

	public ExpProg getVal1() {
		return val1;
	}

	public OperateurProg getOp() {
		return op;
	}
}
