package com.eoptech.shopdoda.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import com.eoptech.shopdoda.services.fb.FacebookConnectionSignup;
import com.eoptech.shopdoda.services.fb.FacebookSignInAdapter;

@Configuration
@EnableWebSecurity
public class SecureConf extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private FacebookConnectionSignup facebookConnectionSignup;

	@Value("${spring.social.facebook.appSecret}")
	String appSecret;

	@Value("${spring.social.facebook.appId}")
	String appId;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()

				.antMatchers("/css/**", "/js/**", "/vendor/**", "/images/**", "/img/**", "/fonts/**", "/upload/**",
						"/summernote/**" /* , "/files/**" */)
				.permitAll()

				.antMatchers("/admin/**").hasAnyAuthority("ADMIN")

				.and()

				.formLogin().loginPage("/login").loginProcessingUrl("/perform_login").defaultSuccessUrl("/success", true)
				.failureUrl("/login?login_error=true").permitAll()

				.and()

				.logout().logoutUrl("/logout").logoutSuccessUrl("/home").invalidateHttpSession(true)
				.deleteCookies("JSESSIONID").permitAll();

		// configure remember me, time remain is 1296000s = 15 days.
		http.rememberMe().key("uniqueAndSecretOfMe").tokenValiditySeconds(1296000);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(4));
	}

	// kích hoạt xác thực qua Facebook
	@Bean
	public ProviderSignInController providerSignInController() {
		ConnectionFactoryLocator connectionFactoryLocator = connectionFactoryLocator();
		UsersConnectionRepository usersConnectionRepository = getUsersConnectionRepository(connectionFactoryLocator);
		((InMemoryUsersConnectionRepository) usersConnectionRepository).setConnectionSignUp(facebookConnectionSignup);
		return new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository,
				new FacebookSignInAdapter());
	}

	// đăng ký FacebookConnectionFactory với những thuộc tính trong
	// appilcation.properties
	private ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new FacebookConnectionFactory(appId, appSecret));
		return registry;
	}

	private UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		return new InMemoryUsersConnectionRepository(connectionFactoryLocator);
	}

}
