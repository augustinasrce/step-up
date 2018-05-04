package com.haka.stepup;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;

@EnableAutoConfiguration
@SpringBootApplication
@EnableOAuth2Client
@Configuration
@EnableWebSecurity
public class StepupApplication extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("oauth2ClientContext")
	OAuth2ClientContext oauth2ClientContext;
	@Autowired
	AuthorizationCodeResourceDetails fitBitApi;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.antMatcher("/**")
				.authorizeRequests()
				.antMatchers("/", "/login**", "/webjars/**", "/error**")
				.permitAll()
				.and()
				.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
	}

	private Filter ssoFilter() {
		OAuth2ClientAuthenticationProcessingFilter facebookFilter = new OAuth2ClientAuthenticationProcessingFilter("/login");
		OAuth2RestTemplate facebookTemplate = new OAuth2RestTemplate(fitBitApi, oauth2ClientContext);
		facebookFilter.setRestTemplate(facebookTemplate);
		UserInfoTokenServices tokenServices = new UserInfoTokenServices(fitBitApiResource().getUserInfoUri(), fitBitApi.getClientId());
		tokenServices.setRestTemplate(facebookTemplate);
		facebookFilter.setTokenServices(tokenServices);
		return facebookFilter;
	}

//
//	@Bean
//	@ConfigurationProperties("fitbitapi.client")
//	public AuthorizationCodeResourceDetails fitBitApi() {
//		return new AuthorizationCodeResourceDetails();
//	}

	@Bean
	@ConfigurationProperties("fitbitapi.resource")
	public ResourceServerProperties fitBitApiResource() {
		return new ResourceServerProperties();
	}

	@Bean
	public FilterRegistrationBean oauth2ClientFilterRegistration(
			OAuth2ClientContextFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}

//	private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(StepupApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(StepupApplication.class, args);

	}

}
