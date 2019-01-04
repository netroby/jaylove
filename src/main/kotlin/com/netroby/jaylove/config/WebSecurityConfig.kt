package com.netroby.jaylove.config


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity(debug=false)
class WebSecurityConfig(@Autowired val accountConfig: AccountConfig) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
                //.csrf().disable() // TODO  处理csrf
                .authorizeRequests()
                .antMatchers(
                        HttpMethod.GET,
                        "/assets/**",
                        "/*.html",
                        "/favicon.ico",
                        "/assets/**",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/webjars/**",
                        "/login",
                        "/logout",
                        "/api/**"
                ).permitAll()
                .antMatchers("/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        val passwordEncoder = BCryptPasswordEncoder() // 定义默认的密码加密器为bcrypt
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
                //  .withUser("user").password(passwordEncoder.encode("user")).roles("USER") // 创建一个账号，密码用bcrypt加密
                //.and()
                .withUser(accountConfig.username).password(passwordEncoder.encode(accountConfig.password)).roles("ADMIN")
    }
}