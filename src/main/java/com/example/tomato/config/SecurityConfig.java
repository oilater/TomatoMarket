package com.example.tomato.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.tomato.service.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	MemberService memberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //·Î±×ÀÎ¿¡ ´ëÇÑ ¼³Á¤
        http.formLogin()
                .loginPage("/members/login") //·Î±×ÀÎ ÆäÀÌÁö url¼³Á¤
                .defaultSuccessUrl("/") //·Î±×ÀÎ ¼º°ø½Ã ÀÌµ¿ÇÒ ÆäÀÌÁö
                .usernameParameter("email") //·Î±×ÀÎ½Ã »ç¿ëÇÒ ÆÄ¶ó¸ÞÅÍ ÀÌ¸§
                .failureUrl("/members/login/error") //·Î±×ÀÎ ½ÇÆÐ½Ã ÀÌµ¿ÇÒ url
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) //·Î±×¾Æ¿ô url
                .logoutSuccessUrl("/"); //·Î±×¾Æ¿ô ¼º°ø½Ã ÀÌµ¿ÇÒ url
        
        //ÆäÀÌÁöÀÇ Á¢±Ù¿¡ °üÇÑ ¼³Á¤
        http.authorizeRequests()
                .mvcMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .mvcMatchers("/", "/members/**", "/item/**", "/images/**").permitAll() //¸ðµç »ç¿ëÀÚ°¡ ·Î±×ÀÎ(ÀÎÁõ) ¾øÀÌ Á¢±ÙÇÒ ¼ö ÀÖµµ·Ï ¼³Á¤
                .mvcMatchers("/admin/**").hasRole("ADMIN") // '/admin' À¸·Î ½ÃÀÛÇÏ´Â °æ·Î´Â °èÁ¤ÀÌ ADMIN roleÀÏ °æ¿ì¿¡¸¸ Á¢±Ù °¡´ÉÇÏµµ·Ï ¼³Á¤
                .anyRequest().authenticated(); //±× ¿Ü¿¡ ÆäÀÌÁö´Â ¸ðµÎ ·Î±×ÀÎ(ÀÎÁõ)À» ¹Þ¾Æ¾ß ÇÑ´Ù.
        
        //ÀÎÁõµÇÁö ¾ÊÀº »ç¿ëÀÚ°¡ ¸®¼Ò½º(ÆäÀÌÁö, ÀÌ¹ÌÁö µî..)¿¡ Á¢±ÙÇßÀ»¶§ ¼³Á¤
        http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());

        return http.build();
    }
	
	//비밀번호를 암호화 해서 저장
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
}
