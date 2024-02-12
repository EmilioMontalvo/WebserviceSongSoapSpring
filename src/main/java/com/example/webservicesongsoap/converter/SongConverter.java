package com.example.webservicesongsoap.converter;


import com.example.webservicesongsoap.gen.Song;
import com.example.webservicesongsoap.model.SongModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SongConverter {

    public SongModel convertSongToSongModel(Song song) {
        SongModel songModel = new SongModel();
        songModel.setId(song.getId());
        songModel.setName(song.getName());
        songModel.setPath(song.getPath());
        songModel.setPlays(song.getPlays());
        return songModel;
    }

    public Song convertSongModelToSong(SongModel songModel) {
        Song song = new Song();
        song.setId(songModel.getId());
        song.setName(songModel.getName());
        song.setPath(songModel.getPath());
        song.setPlays(songModel.getPlays());
        return song;
    }

    public List<SongModel> convertSongsToSongModels(List<Song> songs) {
        List<SongModel> songModels = new ArrayList<>();
        for (Song song : songs) {
            songModels.add(convertSongToSongModel(song));
        }
        return songModels;
    }

    public List<Song> convertSongModelsToSongs(List<SongModel> songModels) {
        List<Song> songs = new ArrayList<>();
        for (SongModel songModel : songModels) {
            songs.add(convertSongModelToSong(songModel));
        }
        return songs;
    }
}
