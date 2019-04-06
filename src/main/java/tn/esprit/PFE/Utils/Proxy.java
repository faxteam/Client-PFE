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
import iservice.MailServerFacadeLocal;
import iservice.OptFacadeRemote;
import iservice.SchoolFacadeRemote;
import iservice.SiteFacadeRemote;
import iservice.StudentFacadeRemote;
import tn.esprit.PFE.Utils.Cache.ContextCache;

/**
 *
 * @author Tryvl
 */
public class Proxy {
    String jndiAdmin ="PFE-ejb/AdminFacade!iservice.AdminFacadeRemote";
    String jndiSchool="PFE-ejb/SchoolFacade!iservice.SchoolFacadeRemote";
    String jndiEmploye ="PFE-ear/PFE-ejb/EmployeeFacade!iservice.EmployeeFacadeRemote";
    String jndiAuthent ="PFE-ear/PFE-ejb/AutheticatorService!iservice.AutheticatorInterfaceRemote";
    String jndiDepartement ="PFE-ejb/DepartementFacade!iservice.DepartementFacadeRemote";
    String jndiOption ="PFE-ear/PFE-ejb/OptFacade!iservice.OptFacadeRemote";
    String jndiSite ="PFE-ejb/SiteFacade!iservice.SiteFacadeRemote";
    String jndiMailer ="PFE-ejb/MailServerFacade!iservice.MailServerFacadeLocal";
    String jndiClasse ="PFE-ejb/ClassFacade!iservice.ClassFacadeRemote";
    String jndiStudent ="PFE-ejb/StudentFacade!iservice.StudentFacadeRemote";

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
    
}
