package ru.alexeyshekhnov.lastproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexeyshekhnov.lastproject.entities.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
