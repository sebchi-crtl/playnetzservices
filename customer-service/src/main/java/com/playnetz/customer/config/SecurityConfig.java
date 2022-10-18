package com.playnetz.customer.config;

import com.playnetz.customer.config.jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

//    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors();
        http
                .csrf()
                .disable();
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


       http
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()//for login and registration pre-path
                .anyRequest()
                .authenticated();
       http
               .addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

//                .antMatchers("api/admin/**").hasAnyRole(USER.name())
    }


    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter()
    {
        return  new JwtAuthorizationFilter();
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry)
            {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*");
            }
        };
    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        return userDetailsService;
//        UserDetails linkCustomer = User.builder()
//                .username("linda")
//                .password(passwordEncoder.encode("password"))
////                .roles(USER.name()) //ROLE_USER
//                .authorities(USER.getGrantedAuthorities())
//                .build();
//
//        UserDetails sebCustomer = User.builder()
//                .username("seb")
//                .password(passwordEncoder.encode("password123"))
//                .roles(ADMIN.name()) //ROLE_ADMIN
//                .build();
//
//        UserDetails sebProducer = User.builder()
//                .username("seba")
//                .password(passwordEncoder.encode("password123"))
//                    .roles(ADMIN.name()) //ROLE_PRODUCER
//                .build();
//
//        return new InMemoryUserDetailsManager(
//                linkCustomer,
//                sebCustomer,
//                sebProducer
//        );
//    }
}
