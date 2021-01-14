package ru.kislyakova.anastasia.channelservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.kislyakova.anastasia.channelmodel.dto.ChannelCreationDto;
import ru.kislyakova.anastasia.channelmodel.dto.ChannelUpdatingDto;
import ru.kislyakova.anastasia.channelmodel.entity.Channel;
import ru.kislyakova.anastasia.channelservice.exception.DuplicateChannelException;
import ru.kislyakova.anastasia.channelservice.repository.ChannelRepository;
import ru.kislyakova.anastasia.channelservice.service.ChannelService;

import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {

    private ChannelRepository channelRepository;

    @Autowired
    public ChannelServiceImpl(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @Override
    public Channel createChannel(ChannelCreationDto channelCreationDto) {
        Channel channel = new Channel(channelCreationDto);
        try {
            channel = channelRepository.save(channel);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateChannelException(channelCreationDto.getName());
        }
        return channel;
    }

    @Override
    public List<Channel> getChannels() {
        return channelRepository.findAll();
    }

    @Cacheable(value = "channels")
    @Override
    public Channel getChannelById(int channelId) {
        return channelRepository.findById(channelId).orElse(null);
    }

    @CachePut(value = "channels", key = "#channelId")
    @Override
    public Channel updateChannel(int channelId, ChannelUpdatingDto channelDto) {
        Channel channel = channelRepository.findById(channelId)
                .orElse(null);
        if (channel == null) {
            return null;
        }
        channel.setDescription(channelDto.getDescription());
        channel.setRecipients(channelDto.getRecipients());
        channelRepository.save(channel);
        return channel;
    }
}
