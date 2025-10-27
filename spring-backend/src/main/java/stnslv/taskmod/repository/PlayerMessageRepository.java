package stnslv.taskmod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stnslv.taskmod.entity.PlayerMessage;

@Repository
public interface PlayerMessageRepository extends JpaRepository<PlayerMessage, Long> {
}
