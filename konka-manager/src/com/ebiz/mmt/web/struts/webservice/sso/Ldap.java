package com.ebiz.mmt.web.struts.webservice.sso;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;


public class Ldap {

	private LdapContext ctx = null;
	private String searchBase = "DC=ad,DC=konka,DC=com";
	private String returnedAtts[] = {"displayName","sAMAccountName","userPassword","distinguishedName" };

	public boolean initial_Ldap() {
		Properties env = new Properties();//ekpadmin   konkatest1234~
		String adminName = "CN=qdgladmin,CN=Users,DC=ad,DC=konka,DC=com";
		String adminPassword = "QDGL1q2w3e";
		String ldapURL = "LDAP://192.168.2.27:389";
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, adminName);
		env.put(Context.SECURITY_CREDENTIALS, adminPassword);
		env.put(Context.PROVIDER_URL, ldapURL);

		try {
			//System.out.println("Start InitialLdapContext");
			ctx = new InitialLdapContext(env, null);
			//System.out.println("InitialLdapContext succeed");
		} catch (NamingException e) {
			//System.out.println("Problem initial_Ldap NamingException: " + e);
			return false;
		}

		return true;
	}

	public boolean close_Ldap() {
		//System.out.println("Close Ldap");
		try {
			ctx.close();
		} catch (NamingException e) {
			//System.out.println("Problem close_Ldap NamingException: " + e);
			return false;
		}
		return true;
	}

	public void search_distinguishedName(String searchFilter) {
		try {
			//System.out.println("Start search " + searchFilter + "'s distinguishedName");
			SearchControls searchCtls = new SearchControls();
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			searchCtls.setReturningAttributes(returnedAtts);
			NamingEnumeration answer = ctx.search(searchBase, searchFilter,searchCtls);
			int totalResults = 0;
			int rows = 0;
			while (answer.hasMoreElements()) {// 遍历结果集
				SearchResult sr = (SearchResult) answer.next();// 得到符合搜索条件的DN 
				////System.out.println(sr.getName());
				Attributes Attrs = sr.getAttributes();// 得到符合条件的属性集
				if (Attrs != null) {

					StringBuffer str= new StringBuffer();
					for (NamingEnumeration ne = Attrs.getAll(); ne.hasMore();) {
						Attribute Attr = (Attribute) ne.next();// 得到下一个属性
						str.append(Attr.getID().toString()+":");
						////System.out.print(Attr.getID().toString());
						// 读取属性值
						for (NamingEnumeration e = Attr.getAll(); e.hasMore(); totalResults++) {
							str.append(e.next().toString()+",");
							////System.out.print(":"+ e.next().toString()+",");
						} 
					}
					//System.out.println(str);
				}
			}
			
		} catch (NamingException e) {
			//System.out.println("Problem search_distinguishedName NamingException: "+ e);
		}
	}
	
	public void search_distinguishedName(String searchFilter,List<String> list) {
		try { 
			SearchControls searchCtls = new SearchControls();
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			searchCtls.setReturningAttributes(returnedAtts);
			NamingEnumeration answer = ctx.search(searchBase, searchFilter,searchCtls);
			int totalResults = 0;
			int rows = 0;
			while (answer.hasMoreElements()) {// 遍历结果集
				SearchResult sr = (SearchResult) answer.next();// 得到符合搜索条件的DN 
				////System.out.println(sr.getName());
				Attributes Attrs = sr.getAttributes();// 得到符合条件的属性集
				if (Attrs != null) { 
					StringBuffer str= new StringBuffer();
					for (NamingEnumeration ne = Attrs.getAll(); ne.hasMore();) {
						Attribute Attr = (Attribute) ne.next();// 得到下一个属性
						str.append(Attr.getID().toString()+":");
						////System.out.print(Attr.getID().toString());
						// 读取属性值
						for (NamingEnumeration e = Attr.getAll(); e.hasMore(); totalResults++) {
							str.append(e.next().toString()+",");
							////System.out.print(":"+ e.next().toString()+",");
						} 
					}
					list.add(str.toString()); 
				}
			}
			
		} catch (NamingException e) {
			//System.out.println("Problem search_distinguishedName NamingException: "+ e);
		}
	}
	
	public static void main(String args[]) {
		Ldap inst = new Ldap();
		inst.initial_Ldap();
		String where = "(objectClass=organizationalPerson)";
		//String where = "(objectClass=organizationalPerson)";
		//String where = "(objectClass=organizationalUnit)";
		inst.search_distinguishedName(where);
		inst.close_Ldap();
	}
}
