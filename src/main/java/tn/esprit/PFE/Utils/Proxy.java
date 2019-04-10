/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.Utils;

import iservice.AdminFacadeRemote;
import iservice.AutheticatorInterfaceRemote;
import iservice.ClassFacadeRemote;
import iservice.DepartementFacadeRemote;
import iservice.EmployeeFacadeRemote;
import iservice.FormFacadeRemote;
import iservice.MailServerFacadeLocal;
import iservice.OptFacadeRemote;
import iservice.PFE_FormFacadeRemote;
import iservice.SchoolFacadeRemote;
import iservice.SiteFacadeRemote;
import iservice.StudentFacadeRemote;
import tn.esprit.PFE.Utils.Cache.ContextCache;

/**
 *
 * @author Tryvl
 */
public class Proxy {
    String jndiAdmin ="PFE-ear/PFE-ejb/AdminFacade!iservice.AdminFacadeRemote";
    String jndiSchool="PFE-ear/PFE-ejb/SchoolFacade!iservice.SchoolFacadeRemote";
    String jndiEmploye ="PFE-ear/PFE-ejb/EmployeeFacade!iservice.EmployeeFacadeRemote";
    String jndiAuthent ="PFE-ear/PFE-ejb/AutheticatorService!iservice.AutheticatorInterfaceRemote";
    String jndiDepartement ="PFE-ear/PFE-ejb/DepartementFacade!iservice.DepartementFacadeRemote";
    String jndiOption ="PFE-ear/PFE-ejb/OptFacade!iservice.OptFacadeRemote";
    String jndiSite ="PFE-ear/PFE-ejb/SiteFacade!iservice.SiteFacadeRemote";
    String jndiMailer ="PFE-ear/PFE-ejb/MailServerFacade!iservice.MailServerFacadeLocal";
    String jndiClasse ="PFE-ear/PFE-ejb/ClassFacade!iservice.ClassFacadeRemote";
    String jndiStudent ="PFE-ear/PFE-ejb/StudentFacade!iservice.StudentFacadeRemote";
    String jndiPFEForm ="PFE-ear/PFE-ejb/PFE_FormFacade!iservice.PFE_FormFacadeRemote";
    String jndiForm ="PFE-ear/PFE-ejb/FormFacade!iservice.FormFacadeRemote";

    public Proxy() {
    }
    
    public AdminFacadeRemote getAdminProxy()
    {
        return (AdminFacadeRemote) ContextCache
                .getInstance()
                .getProxy(jndiAdmin);
    }
    
    public SchoolFacadeRemote getSchoolProxy()
    {
        return (SchoolFacadeRemote) ContextCache
                .getInstance()
                .getProxy(jndiSchool);
    }
    
    public EmployeeFacadeRemote getEmployeeProxy()
    {
        return (EmployeeFacadeRemote) ContextCache
                .getInstance()
                .getProxy(jndiEmploye);
    }
    
   public AutheticatorInterfaceRemote getAuthenticated()
   {
       return (AutheticatorInterfaceRemote) ContextCache
                .getInstance()
                .getProxy(jndiAuthent);
   }
   
   public DepartementFacadeRemote getDepartement()
   {
       return (DepartementFacadeRemote) ContextCache
                .getInstance()
                .getProxy(jndiDepartement);
   }
   
   
   public SiteFacadeRemote getSite()
   {
       return (SiteFacadeRemote) ContextCache
                .getInstance()
                .getProxy(jndiSite);
   }
   
   
   public OptFacadeRemote getOption()
   {
       return (OptFacadeRemote) ContextCache
                .getInstance()
                .getProxy(jndiOption);
   }
    
   
   public MailServerFacadeLocal getMailServer()
   {
       return (MailServerFacadeLocal) ContextCache
                .getInstance()
                .getProxy(jndiMailer);
   }
    
   
   public ClassFacadeRemote getClasse()
   {
       return (ClassFacadeRemote) ContextCache
                .getInstance()
                .getProxy(jndiClasse);
   }
    
   
   public StudentFacadeRemote getStudent()
   {
       return (StudentFacadeRemote) ContextCache
                .getInstance()
                .getProxy(jndiStudent);
   }
    
   
   public PFE_FormFacadeRemote getPFEFormProxy()
   {
       return (PFE_FormFacadeRemote) ContextCache
                .getInstance()
                .getProxy(jndiPFEForm);
   }
    
   
   public FormFacadeRemote getFormProxy()
   {
       return (FormFacadeRemote) ContextCache
                .getInstance()
                .getProxy(jndiForm);
   }
    
}
