package com.example.webservicesongsoap.repository;

import com.example.webservicesongsoap.model.SongModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SongRepository extends JpaRepository<SongModel, Integer> {
    SongModel findById(int id);
}
