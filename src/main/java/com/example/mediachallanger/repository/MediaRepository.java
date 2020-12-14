package com.example.mediachallanger.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mediachallanger.model.Media;

public interface MediaRepository extends JpaRepository<Media, Long>{

	Optional<Media> findByNome(String nome);

}
