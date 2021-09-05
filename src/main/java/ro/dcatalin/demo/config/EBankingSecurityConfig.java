package ro.dcatalin.demo.config;

import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;

@Configuration
@EnableWebSecurity
public class EBankingSecurityConfig extends WebSecurityConfigurerAdapter {

	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	private DataSource securityDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// use jdbc authentication ... oh yeah!!!
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/").hasRole("CLIENT")
			.antMatchers("/").hasRole("EMPLOYEE")
			.antMatchers("/").hasRole("MANAGER")
			.antMatchers("/").hasRole("ADMIN")
			.antMatchers("/css/**").permitAll()
			.antMatchers("bank/manageAccts").hasRole("CLIENT")
			.antMatchers("bank/sendNewAcctCreation").hasRole("CLIENT")
			.antMatchers("bank/showAccounts").hasRole("EMPLOYEE")
			.antMatchers("/leaders/**").hasRole("MANAGER")
			.antMatchers("/systems/**").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");

		SecurityContextConfigurer<HttpSecurity> x = http.securityContext();
		logger.info("EBANKING Security configuration in progress. AUTH - " + x.toString());
	}

}
