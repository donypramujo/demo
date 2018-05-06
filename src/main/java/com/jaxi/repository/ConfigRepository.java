package com.jaxi.repository;

import com.jaxi.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository  extends JpaRepository<Config,String> {
}
