package br.edu.ifpi.catce.sistemareserva.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//ESPECIFICO QUE É UMA CLASSE DE CONFIGURAÇÃO
@Configuration
// ATIVANDO A SEGURANÇA WEB
@EnableWebSecurity
//CLASSE DE CONFIURAÇÃO DO SECURITY;
public class SecurityConfig {
    //DECLARA QUE ESTE MÉTODO PRODUZ UM BEAN QUE SERÁ GERENCIADO PELO SPRING.
    @Bean
    //UM FILTRO QUE DEFINE A SEGURANÇA DA APLICAÇÃO. O MÉTODO RECEBE UM OBJETO HTTPSECURITY, QUE PERMITE CONFIGURAR A SEGURANÇA.
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //AQUI EU FAÇO AS AUTORIZAÇÃO DE REQUISIÇÕES
        http
                //METODO QUE PERMITE DEFINIR QUAIS REQUISIÇÕES SÃO AUTORIZADAS.
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                //AQUI EU TO PERMITINDO ACESSO A QUALQUER URL QUE CORRESPONDA AO PADRÃO STATIC.
                                .requestMatchers("/static/**").permitAll()
                                //INDICA QUE TODAS AS OUTRAS REQUISIÇÕES PRECISAM DE AUTORIZAÇÃO.
                                .anyRequest().authenticated()
                )
                // AUTENTICAÇÃO VIA FORMULÁRIO.
                .formLogin(formLogin ->
                        formLogin
                                //DEFINI A URL DA PÁGINA DE LOGIN PERSONALIZADA.
                                .loginPage("/login")
                                //APÓS UM LOGIN BEM-SUCEDIDO, O  USUÁRIO SERÁ REDIRECIONADO PARA UMA PÁGINA
                                .defaultSuccessUrl("/Paginaprincipal",true)
                                //PERMITE QUE QUALQUER USUARIO ACESSE A PÁGINA DE LOGIN, MESMO QUE NÃO ESTEJA AUTENTICADO.
                                .permitAll()
                )
                // CONFIGURA AS OPÇÕES DE LOGOUT.
                .logout(logout ->
                        logout
                                //DEFINE A URL PARA O LOGOUT.
                                .logoutUrl("/logout")
                                //APÓS UM LOGOUT BEM-SUCEDIDO, O USUÁRIO É REDIRECIONADO PARA A PÁGINA DE LOGIN COM A QUERY ?logout PARA INDICAR QUE A SAÍDA FOI BEM-SUCEDIDA.
                                .logoutSuccessUrl("/login?logout")
                                //INVALIDA A SESSÃO HYTTP AO FAZER LOGOUT.
                                .invalidateHttpSession(true)
                                //REMOVE O COOKIE DA SESSÃO (JSESSIONID) QUANDO O USUÁRIO FAZ O LOGOUT.
                                .deleteCookies("JSESSIONID")
                                // PERMITE QUE QUALQUER USUARIO ACESSE A URL DE LOGOUT.
                                .permitAll())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }


    @Bean
    //INTERFACE USADA PARA CARREGAR OS DADOS DO USUARIO
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password(passwordEncoder().encode("12345"))
                        .roles("ADMIN")
                        .build()
        );
    }
    @Bean
    // INTERFACE USADA PARA CODIFICAÇÃO DE SENHAS.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //INTERFACE USADA PARA DEFINIR UM MANIPULADOR PARA FALHAS DE AUTENTICAÇÃO.
    public AuthenticationFailureHandler authenticationFailureHandler() {
        SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler("/login?error");
        failureHandler.setUseForward(true);
        return failureHandler;
    }
}

