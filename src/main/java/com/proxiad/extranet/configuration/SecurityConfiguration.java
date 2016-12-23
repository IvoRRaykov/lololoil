package com.proxiad.extranet.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Spring Security configuration class
 * 
 * @author Emil Doychev (e.doychev@proxiad.com)
 * @author Mihail Merkov
 */

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.httpBasic().authenticationEntryPoint(createBasicAuthenticationEntry());
		addPermittedAccessToEmployee(http);
		http.authorizeRequests().antMatchers("/api/**").authenticated()
			.anyRequest().permitAll();
	}
	
	/**
	 * Add permitted access to specific Employee API for all users
	 * @param http
	 * @throws Exception
	 */
	private void addPermittedAccessToEmployee(final HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.regexMatchers(HttpMethod.GET, "\\A/api/employee\\?withMainDataOnly=true\\Z").permitAll()
			.antMatchers(HttpMethod.GET, "/api/employee/*/profilePicture", "/api/employee/*",
				"/api/employee/*/skills", "/api/employee/*/interests", "/api/employee/*/expertises",
				"/api/employee/*/languages", "/api/employee/*/educations", "/api/employee/*/certifications").permitAll();
	}
	
	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	@Bean
	public ProxiadExtranetBasicAuthenticationEntryPoint createBasicAuthenticationEntry() {
		return new ProxiadExtranetBasicAuthenticationEntryPoint();
	}

}
