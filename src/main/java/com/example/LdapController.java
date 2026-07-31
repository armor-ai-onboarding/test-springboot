package com.example;

import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import java.util.Properties;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LdapController {

	@RequestMapping("/ldapSearch")
	public String search(@RequestParam("username") String username) throws Exception {
		Properties env = new Properties();
		env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
		env.put("java.naming.provider.url", "ldap://localhost:389");
		DirContext ctx = new InitialDirContext(env);
		String filter = "(uid=" + username + ")";
		SearchControls controls = new SearchControls();
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		ctx.search("dc=example,dc=com", filter, controls);
		return filter;
	}
}
