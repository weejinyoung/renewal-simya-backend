package com.simya.backend.infrastructure.database.redis

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Service


//@Service
//class RedisPublisher (
//    private val redisTemplate: RedisTemplate<String, Any>
//){
//    fun publish(topic: ChannelTopic, message: ChatMessage) {
//        redisTemplate.convertAndSend(topic.topic, message)
//    }
//}
