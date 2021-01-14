package ru.kislyakova.anastasia.channelclient;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kislyakova.anastasia.channelmodel.dto.ChannelCreationDto;
import ru.kislyakova.anastasia.channelmodel.dto.ChannelUpdatingDto;
import ru.kislyakova.anastasia.channelmodel.entity.Channel;

@Headers({ "Accept: application/json" })
@FeignClient(name = "api/channels")
public interface ChannelServiceClient {

    @RequestLine("POST /")
    @Headers("Content-Type: application/json")
    Mono<Channel> createChannel(ChannelCreationDto channelCreationDto);

    @RequestLine("GET /")
    Flux<Channel> getChannels();

    @RequestLine("GET /{channelId}")
    Mono<Channel> getChannelById(@Param("channelId") int channelId);

    @RequestLine("PUT /{channelId}")
    @Headers("Content-Type: application/json")
    Mono<Channel> updateChannel(@Param("channelId") int channelId, ChannelUpdatingDto channelDto);
}
