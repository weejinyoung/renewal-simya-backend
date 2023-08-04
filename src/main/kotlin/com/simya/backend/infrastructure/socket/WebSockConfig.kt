package com.simya.backend.infrastructure.socket

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.ChannelRegistration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer


//@Configuration
//@EnableWebSocketMessageBroker
//class WebSockConfig (
//    private val stompHandler: StompHandler
//) : WebSocketMessageBrokerConfigurer{
//
//    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
//        registry.addEndpoint("/simya/ws-stomp").setAllowedOriginPatterns("*")
//            .withSockJS() // sockJs() => 낮은 브라우저에서도 웹 소켓 지원
//    }
//
//    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
//        registry.enableSimpleBroker("/sub")
//        registry.setApplicationDestinationPrefixes("/pub")
//    }
//
//    override fun configureClientInboundChannel(registration: ChannelRegistration) {
//        registration.interceptors(stompHandler) // StompHandler 가 WebSocket 앞단에서 Token 체크할 수 있도록 인터셉터로 설정
//    }
//}