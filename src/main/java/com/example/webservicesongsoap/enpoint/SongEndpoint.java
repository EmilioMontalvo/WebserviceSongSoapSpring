package com.example.webservicesongsoap.enpoint;

import com.example.webservicesongsoap.converter.SongConverter;
import com.example.webservicesongsoap.gen.*;
import com.example.webservicesongsoap.model.SongModel;
import com.example.webservicesongsoap.repository.SongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class SongEndpoint {

    private static final String NAMESPACE_URI = "http://www.example.com/webservicesongsoap/gen";

    private SongRepository songRepository;
    private SongConverter songConverter;

    @Autowired
    public SongEndpoint(SongRepository songRepository, SongConverter songConverter) {
        this.songRepository = songRepository;
        this.songConverter = songConverter;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSongByIdRequest")
    @ResponsePayload
    public GetSongByIdResponse getSongById(@RequestPayload GetSongByIdRequest request) {
        GetSongByIdResponse response = new GetSongByIdResponse();
        SongModel songModel = songRepository.findById(request.getId());
        response.setSong(songConverter.convertSongModelToSong(songModel));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSongsRequest")
    @ResponsePayload
    public GetSongsResponse getSongs(@RequestPayload GetSongsRequest request) {
        GetSongsResponse response = new GetSongsResponse();
        List<SongModel> songModels = songRepository.findAll();
        List<Song> songs = songConverter.convertSongModelsToSongs(songModels);
        response.getSongs().addAll(songs);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postSongRequest")
    @ResponsePayload
    public PostSongResponse postSongs(@RequestPayload PostSongRequest request) {
        PostSongResponse response = new PostSongResponse();
        SongModel songModel = songConverter.convertSongToSongModel(request.getSong());
        Song song = songConverter.convertSongModelToSong(songRepository.save(songModel));
        response.setSong(song);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteSongRequest")
    @ResponsePayload
    public DeleteSongResponse deleteSong(@RequestPayload DeleteSongRequest request) {
        DeleteSongResponse response = new DeleteSongResponse();
        try {
            songRepository.deleteById(request.getId());
            response.setMessage("Song with ID " + request.getId() + " deleted successfully.");
        } catch (Exception e) {
            response.setMessage("Failed to delete song with ID " + request.getId() + ". Error: " + e.getMessage());
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateSongRequest")
    @ResponsePayload
    public UpdateSongResponse updateSong(@RequestPayload UpdateSongRequest request) {
        UpdateSongResponse response = new UpdateSongResponse();
        SongModel existingSongModel = songRepository.findById(request.getSong().getId());
        if (existingSongModel != null) {
            SongModel updatedSongModel = songConverter.convertSongToSongModel(request.getSong());
            songRepository.save(updatedSongModel);
            response.setMessage("Song with ID " + request.getSong().getId() + " updated successfully.");
        } else {
            response.setMessage("Song with ID " + request.getSong().getId() + " does not exist.");
        }
        return response;
    }



}
