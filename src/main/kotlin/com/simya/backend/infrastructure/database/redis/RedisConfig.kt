package com.simya.backend.infrastructure.database.redis

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
@EnableRedisRepositories
class RedisConfig (
    @Value("\${spring.data.redis.host}")
    private val host: String,
    @Value("\${spring.data.redis.port}")
    private val port: Int
){
    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory =
        LettuceConnectionFactory(host, port)

    @Bean
    fun redisTemplate(): RedisTemplate<String, String> =
        RedisTemplate<String, String>()
            .apply {
                keySerializer = StringRedisSerializer()
                valueSerializer = StringRedisSerializer()
                connectionFactory = redisConnectionFactory()
            }

//    @Bean
//    fun channelTopic(): ChannelTopic {
//        return ChannelTopic("chatroom")
//    }
//
//    @Bean
//    fun redisMessageListenerContainer(
//        connectionFactory: RedisConnectionFactory,
//        listenerAdapter: MessageListenerAdapter,
//        channelTopic: ChannelTopic?
//    ): RedisMessageListenerContainer? {
//        val container = RedisMessageListenerContainer()
//        container.setConnectionFactory(connectionFactory!!)
//        container.addMessageListener(listenerAdapter!!, channelTopic!!)
//        return container
//    }
//
//    @Bean
//    fun listenerAdapter(subscriber: RedisSubscriber): MessageListenerAdapter {
//        return MessageListenerAdapter(subscriber, "sendMessage")
//    }
//
//    @Bean
//    fun redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
//        val redisTemplate = RedisTemplate<String, Any>()
//        redisTemplate.connectionFactory = connectionFactory
//        redisTemplate.keySerializer = StringRedisSerializer()
//        redisTemplate.valueSerializer = Jackson2JsonRedisSerializer(String::class.java)
//        return redisTemplate
//    }
//
//    @Bean
//    fun profileRedisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<String, ProfileResponseDto> {
//        val profileRedisTemplate: RedisTemplate<String, ProfileResponseDto> =
//            RedisTemplate<String, ProfileResponseDto>()
//        profileRedisTemplate.setConnectionFactory(connectionFactory)
//        profileRedisTemplate.setKeySerializer(StringRedisSerializer())
//        profileRedisTemplate.setValueSerializer(Jackson2JsonRedisSerializer<Any>(ProfileResponseDto::class.java))
//        return profileRedisTemplate
//    }
//
//    @Bean
//    fun byteRedisTemplate(connectionFactory: RedisConnectionFactory?): RedisTemplate<*, *>? {
//        val redisTemplate = RedisTemplate<ByteArray, ByteArray>()
//        redisTemplate.connectionFactory = connectionFactory
//        return redisTemplate
//    }
}