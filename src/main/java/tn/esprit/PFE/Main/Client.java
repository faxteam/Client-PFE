/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.Main;

import entities.Admin;
import iservice.AdminFacadeRemote;
import iservice.AutheticatorInterfaceRemote;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Tryvl
 */
public class Client {
    public static void main(String[] args) throws NamingException 
    {
        String jndiName = "PFE-ejb/AutheticatorService!iservice.AutheticatorInterfaceRemote";
        String jndiA = "PFE-ejb/AdminFacade!iservice.AdminFacadeRemote";
        Context context = new InitialContext();
        AutheticatorInterfaceRemote proxy = (AutheticatorInterfaceRemote) context.lookup(jndiName);
        AdminFacadeRemote proxyAdmin = (AdminFacadeRemote) context.lookup(jndiA);
        
        Admin admin = new Admin();
        Admin admin2 = new Admin();
        Admin admin3 = new Admin();
        /*admin.setEmail("yassine.sta@esprit.tn");
        admin.setFirstName("Yassine");
        admin.setLastName("STA");
        admin.setLogin("Tryvl");
        admin.setPassword("azerty");
        
        proxy.create(admin);
        */
        admin.setAdmin_id(1L);
        admin2.setAdmin_id(2L);
        admin3.setAdmin_id(3L);
        
        proxyAdmin.remove(admin);
        proxyAdmin.remove(admin2);
        proxyAdmin.remove(admin3);
    }
}
