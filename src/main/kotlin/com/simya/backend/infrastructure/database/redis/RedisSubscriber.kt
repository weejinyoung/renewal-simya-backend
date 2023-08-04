package com.simya.backend.infrastructure.database.redis

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Service


//@Service
//class RedisSubscriber (
//    private val objectMapper: ObjectMapper,
//    private val messagingTemplate: SimpMessageSendingOperations
//) {
//
//    private val log = LoggerFactory.getILoggerFactory().getLogger()
//    /**
//     * Redis 에서 pub 으로 메시지가 발행되면 대기하고 있던 onMessage 가 해당 메시지를 받아서 처리한다.
//     */
//    fun sendMessage(publishMessage: String?) {
//        log.info("RedisSubscriber 실행")
//        try {
//            log.info("objectMapper.readValue() 전")
//            // ChatMessage 객체로 매핑
//            val chatMessage: ChatMessageCustom = objectMapper.readValue(publishMessage, ChatMessageCustom::class.java)
//            log.info("objectMapper.readValue() 후")
//
//            // WebSocket 구독자에게 채팅 메시지 Send
//            messagingTemplate.convertAndSend("/sub/simya/chat/room/" + chatMessage.getRoomId(), chatMessage)
//        } catch (e: Exception) {
//            log.error("Exception {}", e)
//        }
//    }
//}