package ru.kislyakova.anastasia.channelservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kislyakova.anastasia.channelmodel.dto.ChannelCreationDto;
import ru.kislyakova.anastasia.channelmodel.dto.ChannelUpdatingDto;
import ru.kislyakova.anastasia.channelmodel.entity.Channel;
import ru.kislyakova.anastasia.channelservice.service.ChannelService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/channels")
public class ChannelController {
    private ChannelService channelService;

    @Autowired
    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @PostMapping
    public ResponseEntity<Channel> createChannel(@RequestBody @Valid ChannelCreationDto itemCreationDto) {
        Channel channel = channelService.createChannel(itemCreationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(channel);
    }

    @PutMapping(value = "{channelId}")
    ResponseEntity<Channel> updateChannel(@PathVariable int channelId, @RequestBody ChannelUpdatingDto channelDto) {
        Channel channel = channelService.updateChannel(channelId, channelDto);
        if (channel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(channel);
    }

    @GetMapping(value = "{channelId}")
    ResponseEntity<Channel> getChannelById(@PathVariable int channelId) {
        Channel channelById = channelService.getChannelById(channelId);
        if (channelById == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(channelById);
    }

    @GetMapping
    ResponseEntity<List<Channel>> getChannels() {
        return ResponseEntity.ok(channelService.getChannels());
    }


}
