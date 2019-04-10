package pidev_client;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import Interfaces.ICategorieRemote;
import Interfaces.IEmployeRemote;
import Interfaces.IFormsRemote;
import Interfaces.IpfeformRemote;
import entities.Category;
import entities.Form;

public class client {

	public static void main(String[] args)throws InterruptedException, NamingException {
		 String jndiName="pidev-ear/pidev-ejb/ServiceForms!Interfaces.IFormsRemote";
		    Context context=new InitialContext();
		    IFormsRemote proxy =(IFormsRemote) context.lookup(jndiName);
		  //  List<Form> l=proxy.findForms();
		//    for (Form form : l) {
				//System.out.println(form);
			//}
		    String jndiName1="pidev-ear/pidev-ejb/pfe_form_service!Interfaces.IpfeformRemote";
		    IpfeformRemote proxy1=(IpfeformRemote) context.lookup(jndiName1);
		   //System.out.println(proxy1.sans_superviseure());
		    //System.out.println(proxy1.form_pfe_accepted());
		    
		    String jndiName2="pidev-ear/pidev-ejb/EmployeService!Interfaces.IEmployeRemote";
		   IEmployeRemote proxy2=(IEmployeRemote) context.lookup(jndiName2);
		   //System.out.println(proxy2.orderby_supervisons());
		   
		   
		    String jndiName3="pidev-ear/pidev-ejb/Categorie_service!Interfaces.ICategorieRemote";
		   ICategorieRemote proxy3=(ICategorieRemote) context.lookup(jndiName3);
		   //System.out.println(proxy3.categorie_non_accepter());
		 //  proxy3.accepter(1);
		  //System.out.println(proxy3.categorie_accepter());
		 //  System.out.println(proxy1.afficher_supervision(2));
		System.out.println(  proxy3.existe("HTML"));
	}
	

}
