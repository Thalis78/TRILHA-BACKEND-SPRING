package br.edu.ifpi.catce.sistemareserva.config;

import br.edu.ifpi.catce.sistemareserva.controller.EquipamentoController;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.ISpringTemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
@Configuration
//ESCANEAMENTO DE COMPONENTES EM UM PACOTE COM BASE EM UMA CLASSE ESPECIFICADA;
@ComponentScan(basePackageClasses = EquipamentoController.class)
@EnableWebMvc
// HABILITA O SUPORTE A RECURSOS DA WEB PARA CONSULTAS E PAGINAÇÃO EM APLICAÇÕES SPRING MVC.
@EnableSpringDataWebSupport
public class WebConfig implements WebMvcConfigurer {
    // É UM OBJETO QUE O SPRING GERENCIA, PROPORCIONANDO FUNCIONALIDADES COMO INJEÇÃO DE DEPEMDÊNCIAS E GERENCIAMENTO DE CICLO DE VIDA;
    @Bean
    // RESPONSÁVEL POR MAPEAR NOMES DE VIEWS (COMO PAGINAS HTML) PARA OS TEMPLATES REAIS QUE DEVEM SER RENDERIZADPS;
    // QUANDO UMA SOLICITAÇÃO É FEITA, O VIEWRESOLVER DECIDE QUAL TEMPLATE USAR COM BASE NO NOME DA VIEW RETORNADA PELO CONTROLADOR;
    public ViewResolver viewResolver() {
        // CRIANDO UMA NOVA INSTÂNCIA DO THYMELEAFVIEWRESOLVER
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        // RETORNA UMA INSTÂNCIA DO SPRINGTEMPLATEENGINE, QUE É O NÚCLEO DO PROCESSAMENTO DE TEMPLATES DO THYMLEAF.
        // ISSO VAI PERMITIR QUE O THYMELEAFVIEWRESOLVE INTERPRETE OS TEMPLATES CORRETAMENTE
        resolver.setTemplateEngine(templateEngine());
        // USADO PARA GARANTIR A REDENRIZAÇÃO DE CARACTERE ESPECIAIS
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    @Bean
    // MÉTODO USADO PARA CONFIGURAR A UTILIZAÇÃO DO THYMELEAF, POSSIBILITANDO A CRIAÇÃO DE APLICAÇÕES WEB DINAMICAS, APROVEITANDO AS FUNCIONALIDADES MAXIMA DO THYMELEAF E A INTEGRAÇÃO COM SPRING
    public ISpringTemplateEngine templateEngine() {
        // CRIANDO UMA NOVA INSTÂNCIA DO SPRINGTEMPLATEENGINE
        // VAI SER O MOTOR DE TEMPLATES DP THYMELEAF QUE PROCESSA OS TEMPLATES HTML E SUBSTITUI AS EPRESSÕES THYMELEAF POR DADOS REAIS.
        SpringTemplateEngine engine = new SpringTemplateEngine();
        // VAI PERMITIR QUE O THYMELEAF COMPILE ESSAS EXPRESSÕES EM CÓDIGO EXECUTÁVEL.
        engine.setEnableSpringELCompiler(true);
        // RETORNA UMA NOVA INSTÂNCIA DE UM ITEMPLATERESOLVER QUE DEFINE COMO O THYMLEAF LOCALIZARÁ E RESOLVERÁ OS TEMPLATES
        // ISSO INCLUE A DEFINIÇÃO DE CAMINHOS PARA ARQUIVOS DE TEMPLATES E OUTRAS CONFIGURAÇÕES RELEVANTES PARA A RESOLUÇÃO DE TEMPLATES.
        engine.setTemplateResolver(iTemplateResolver());
        //ADIÇÃO DE DIALETOS
        engine.addDialect(new LayoutDialect());
        return engine;
    }

    @Bean
    // METODO QUE VAI PERMITIR QUE SEJA UTILIZADO A INTERFACE PARA MAIOR FLEXIBILIDADE E SUBSTITUIÇÃO FUTURA.
    public ITemplateResolver iTemplateResolver() {
        // USADO PARA PROCURAR LOCALIZAR TEMPLATES EM RECURSOS DO SPRING.
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        // INDICA ONDE O THYMLEAF DEVE PROCURAR OS ARQUIVOS DE TEMPLATE;
        resolver.setPrefix("classpath:/templates/");
        // INDICA QUE TODOS OS TEMPLATES TERÃO A EXTENSÃO .HTML.;
        // ISSO QUE SIGNIFICA QUE NÃO SERÁ NECESSÁRIOS INCLUIR .HTML AO FINAL DO NOME DA VIEW;
        resolver.setSuffix(".html");
        // INDICA QUE OS TEMPLATES ESTÃO NO FORMATO HTML
        resolver.setTemplateMode(TemplateMode.HTML);
        // ISSO VAI GARANTIR QUE OS CARACTERES ESPECIAIS SEJAM CORRETAMENTE INTERPRETADOS;
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }
    // ESSA ANOTAÇÃO INDICA QUE O MÉTODO ESTÁ SOBRESCREVENDO UM MÉTODO DA CLASSE PAI OU DE UMA INTERFACE.
    @Override
    // UM OBJETO QUE PERMITE REGISTRAR MANIPULADORES DE RECURSOS. ESTE OBJETO É FORNECIDO PELO SPRING QUANDO O MÉTODO É CHAMADO.
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // ESSE MÉRODO DEFINE UM PADRÃO DE URL QUE O SPRING DEVE RECONHECER COMO UM CAMINHO PARA RECURSOS ESTÁTICOS.
        // O PADRÃO /STATIC/** SIGNIFICA QUE QUALQUER URL QUE COMECE COM /STATIC; SERÁ TRATADO POR ESTE MANIPULADOR. 
        registry.addResourceHandler("/static/**")
        // INDICA QUE O SPRING DEVE PROCURAR OS ARQUIVOS NA PASTA STATIC DENTRO DO CLASPATH DA APLICAÇÃO.
                .addResourceLocations("classpath:/static/");
    }
}
