package com.devpro.shopdoda.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecureConf extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

//	@Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter() {
//        return new JwtAuthenticationFilter();
//    }
	
//	@Bean(BeanIds.AUTHENTICATION_MANAGER)
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        // Get AuthenticationManager bean
//        return super.authenticationManagerBean();
//    }
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()

				.antMatchers("/css/**", "/js/**", "/vendor/**", "/images/**", "/img/**", "/fonts/**", "/upload/**",
						"/summernote/**" /* , "/files/**" */)
				.permitAll()

				.antMatchers("/admin/**").hasAnyAuthority("ADMIN")
				
				.and()
				
				.formLogin().loginPage("/login").loginProcessingUrl("/perform_login").defaultSuccessUrl("/home", true)
				.failureUrl("/login?login_error=true").permitAll()

				.and()

				.logout().logoutUrl("/logout").logoutSuccessUrl("/home").invalidateHttpSession(true)
				.deleteCookies("JSESSIONID").permitAll();
		
		// Cấu hình remember me, thời gian là 1296000 giây
//	    http.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(1296000);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(4));
	}

}
