package pl.coderslab.service;

import pl.coderslab.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {

    Tag save(Tag tag);

    Optional<Tag> findByName(String name);
    Optional<Tag> findById(Long id);
    List<Tag> findAll();

    void delete(Tag tag);
}
