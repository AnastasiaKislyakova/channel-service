package ru.kislyakova.anastasia.channelservice.service;

import ru.kislyakova.anastasia.channelmodel.dto.ChannelCreationDto;
import ru.kislyakova.anastasia.channelmodel.dto.ChannelUpdatingDto;
import ru.kislyakova.anastasia.channelmodel.entity.Channel;

import java.util.List;

public interface ChannelService {
    Channel createChannel(ChannelCreationDto channelCreationDto);
    List<Channel> getChannels();
    Channel getChannelById(int channelId);
    Channel updateChannel(int channelId, ChannelUpdatingDto channelDto);
}

