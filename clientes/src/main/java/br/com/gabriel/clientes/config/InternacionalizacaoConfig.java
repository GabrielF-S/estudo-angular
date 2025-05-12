package br.com.gabriel.clientes.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Configuration
public class InternacionalizacaoConfig {
    @Bean
    public MessageSource messageSource() { //configruação para difinir o arquivo de mensagem
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages"); //define o nome do arquivo que contem as mensagens
        messageSource.setDefaultEncoding("ISO-8859-1"); // define o encode para acnetuação
        messageSource.setDefaultLocale(Locale.getDefault()); //define a localização para a do notebook
        return messageSource;

    }

    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){ //validador do serviço de mensagem
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return  bean;
    }
}
