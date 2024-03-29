package web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import web.security.handler.LoginSuccessHandler;
import web.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/login").anonymous()
                                .antMatchers("/admin").hasAuthority("ADMIN")
                                .antMatchers("/users").hasAnyAuthority("ADMIN", "USER")
                                .anyRequest().authenticated().and().formLogin().successHandler(new LoginSuccessHandler());
        http.logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").and().csrf().disable();
    }

//@Override
//protected void configure(HttpSecurity http) throws Exception {
//    http.formLogin()
//            // указываем страницу с формой логина
//            .loginPage("/login")
//            //указываем логику обработки при логине
//            .successHandler(new LoginSuccessHandler())
//            // указываем action с формы логина
//            .loginProcessingUrl("/login")
//            // Указываем параметры логина и пароля с формы логина
//                .usernameParameter("j_username")
//                .passwordParameter("j_password")
//            // даем доступ к форме логина всем
//            .permitAll();
//
//    http.formLogin().successHandler(new LoginSuccessHandler());
//
//    http.logout()
//            // разрешаем делать логаут всем
//            .permitAll()
//            // указываем URL логаута
//            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//            // указываем URL при удачном логауте
//            .logoutSuccessUrl("/login?logout")
//            //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
//            .and()
//            .csrf()
//            .disable();
//
//    http
//            // делаем страницу регистрации недоступной для авторизированных пользователей
//            .authorizeRequests()
//            //страницы аутентификаци доступна всем
//            .antMatchers("/login")
//            .anonymous()
//            // защищенные URL
//            .antMatchers("/admin").hasAuthority("ADMIN")
//            .antMatchers("/users").hasAuthority("USER")
//            .anyRequest()
//            .authenticated();
//    }
}
