package main.server.repository;

import main.server.model.entity.UserRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRelationshipRepository extends JpaRepository<UserRelationship, Long> {
}
