package com.ConsoleRPGGame.application.repository;


import com.ConsoleRPGGame.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository <Item, Long>{

}
