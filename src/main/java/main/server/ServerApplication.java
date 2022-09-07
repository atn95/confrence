package main.server;

import main.server.account.Account;
import main.server.account.AccountRepository;
import main.server.friends.Room;
import main.server.friends.RoomRepository;
import main.server.friends.UserRelationship;
import main.server.friends.UserRelationshipRepository;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	public ServletWebServerFactory servletContainer() {
		// Enable SSL Trafic
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};

		// Add HTTP to HTTPS redirect
		tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());

		return tomcat;
	}

	/*
    We need to redirect from HTTP to HTTPS. Without SSL, this application used
    port 8082. With SSL it will use port 8443. So, any request for 8082 needs to be
    redirected to HTTPS on 8443.
     */
	private Connector httpToHttpsRedirectConnector() {
		Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
		connector.setScheme("http");
		connector.setPort(8080);
		connector.setSecure(false);
		connector.setRedirectPort(8443);
		return connector;
	}

	@Bean
	CommandLineRunner commandLineRunner(RoomRepository roomRepository, UserRelationshipRepository userRelationshipRepository, AccountRepository accountRepository) {
		return (args ) -> {
			Account an = new Account("atn95@gmail.com", "asdf1234", "an", "an", "nguyen");
			accountRepository.save(an);
			Account atn = new Account("atn955@gmail.com", "asdf1234", "an2", "an2", "nguyen");
			accountRepository.save(atn);
			Account tran = new Account("JTran@gmail.com", "asdf1234", "an2", "an2", "nguyen");
			accountRepository.save(tran);
			Room room1 = new Room();
			roomRepository.save(room1);
			Room room2 = new Room();
			roomRepository.save(room2);
			Room room3 = new Room();
			roomRepository.save(room3);
			UserRelationship friendSelf = new UserRelationship(an,atn,room3);
			userRelationshipRepository.save(friendSelf);
			UserRelationship tr = new UserRelationship(an,tran, room2);
			userRelationshipRepository.save(tr);
		};
	}

	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
