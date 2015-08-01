package it.siletto.art.batch;

import io.dropwizard.configuration.ConfigurationException;
import io.dropwizard.configuration.ConfigurationFactory;
import io.dropwizard.configuration.DefaultConfigurationFactoryFactory;
import io.dropwizard.configuration.FileConfigurationSourceProvider;
import io.dropwizard.jackson.Jackson;
import it.siletto.commons.app.BaseAppConfiguration;
import it.siletto.commons.dropwizard.FakeValidator;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class BaseDropwizardBatch {

	protected Injector injector;
	SiteAppConfiguration conf;
	
	public static SiteAppConfiguration parseConfiguration(String path, Class<SiteAppConfiguration> klass) throws IOException, ConfigurationException {
		final ConfigurationFactory<SiteAppConfiguration> configurationFactory = 
					new DefaultConfigurationFactoryFactory<SiteAppConfiguration>().create(klass, new FakeValidator(), Jackson.newObjectMapper(), "dw");
		if (path != null) {
			return configurationFactory.build(new FileConfigurationSourceProvider(), path);
		}
		return configurationFactory.build();
	}
	
	public void setup(String[] args) throws Exception{
		
		Logger root = (Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.WARN);
		
		Logger base = (Logger)LoggerFactory.getLogger("it.siletto");
		base.setLevel(Level.DEBUG);

		String defaultConf = "app.yml";
		if(args.length>0)
			defaultConf = args[0];
		conf = parseConfiguration(defaultConf, SiteAppConfiguration.class);

		setProxy(conf);
        
		System.out.println("loaded configuration: "+ ToStringBuilder.reflectionToString(conf, ToStringStyle.MULTI_LINE_STYLE));
		
		injector = Guice.createInjector(new TestModule(conf));
	}
	
    protected void setProxy(BaseAppConfiguration appConfiguration){
    	if(StringUtils.isNotEmpty(appConfiguration.getProxyHost())){
            System.setProperty("http.proxyHost", appConfiguration.getProxyHost());
            System.setProperty("http.proxyPort", appConfiguration.getProxyPort());
            System.setProperty("https.proxyHost", appConfiguration.getProxyHost());
            System.setProperty("https.proxyPort", appConfiguration.getProxyPort());
    	}
    }
}
