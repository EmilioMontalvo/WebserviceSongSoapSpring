package com.example.webservicesongsoap.config;

import com.example.webservicesongsoap.converter.SongConverter;
import com.example.webservicesongsoap.enpoint.SongEndpoint;
import com.example.webservicesongsoap.repository.SongRepository;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.core.io.ClassPathResource;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "song")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema songsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("songPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.example.com/webservicesongsoap/gen");
        wsdl11Definition.setSchema(songsSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema songsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/song.xsd"));
    }

    @Bean
    public SongConverter songConverter() {
        return new SongConverter();
    }

    @Bean
    public SongEndpoint songEndpoint(SongRepository songRepository, SongConverter songConverter) {
        return new SongEndpoint(songRepository, songConverter);
    }
}
