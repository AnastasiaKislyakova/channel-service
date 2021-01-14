package ru.kislyakova.anastasia.channelservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kislyakova.anastasia.channelmodel.entity.Channel;

import java.util.List;

@Repository
public interface ChannelRepository extends CrudRepository<Channel, Integer> {
    @Override
    List<Channel> findAll();
}
