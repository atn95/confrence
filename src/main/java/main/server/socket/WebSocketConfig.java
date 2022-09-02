package main.server.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(final MessageBrokerRegistry registry) {
        //prefix server sends to client(client listens to)
        registry.enableSimpleBroker("/topic");

        //prefix the client uses to send to websocket(eg:server listens to)
        registry.setApplicationDestinationPrefixes("/ws");
    }

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        //Where the socket will subscribe to. (let socket = new SockJs('https://localhost:8080/socket')
        registry.addEndpoint("/socket").setAllowedOrigins("*").setHandshakeHandler(new UserHandshakeHandler());
        registry.addEndpoint("/socket").setAllowedOriginPatterns("*").setHandshakeHandler(new UserHandshakeHandler()).withSockJS();
    }
}
