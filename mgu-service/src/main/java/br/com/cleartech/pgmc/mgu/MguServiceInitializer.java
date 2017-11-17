package br.com.cleartech.pgmc.mgu;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import( MguDomainInitializer.class )
@ComponentScan( "br.com.cleartech.pgmc" )
public class MguServiceInitializer {

}
