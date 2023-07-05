package poly.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("SELECT TENTAIKHOAN, MATKHAU, TRANGTHAI FROM TAIKHOAN WHERE TENTAIKHOAN = ?")
				.authoritiesByUsernameQuery("SELECT TENTAIKHOAN, QUYEN FROM TAIKHOAN WHERE TENTAIKHOAN = ?").passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/admin/**.htm", "/admin.htm").hasRole("ADMIN").and().authorizeRequests()
				.antMatchers("/user/**.htm").hasRole("USER").and().authorizeRequests().antMatchers("/shipper.htm").hasRole("SHIPPER")
				.anyRequest().permitAll().and().formLogin()
				.loginPage("/login.htm").loginProcessingUrl("/login.htm").usernameParameter("tentaikhoan")
				.passwordParameter("matkhau").successHandler(myAuthenticationSuccessHandler())
				.failureUrl("/login.htm?error=failed").and().exceptionHandling()
				.accessDeniedPage("/login.htm?error=deny");
	}
	
	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MyCustomLoginSuccessHandler();
    }
}
