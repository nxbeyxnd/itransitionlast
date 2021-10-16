package ru.alexeyshekhnov.lastproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexeyshekhnov.lastproject.entities.Tag;
import ru.alexeyshekhnov.lastproject.repositories.TagRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;

    public List<Tag> getAllTags(){
        return tagRepository.findAll();
    }

    public void saveOrUpdate(Tag tag){
        if(!findTagByName(tag.getName()).isPresent()){
            tagRepository.save(tag);
        }
    }

    public Optional<Tag> findTagByName(String name){
        return tagRepository.findByName(name);
    }

    public Tag getTagByName(String name){
        return tagRepository.findTagByName(name);
    }

    public void deleteAll(){
        tagRepository.deleteAll();
    }
}
