package com.works.services;

import com.works.entities.Note;
import com.works.repositories.NoteRepository;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NoteService {

    final NoteRepository noteRepository;

    public ResponseEntity save(Note note) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            noteRepository.save(note);
            hm.put(REnum.status, true);
            hm.put(REnum.result, note);
            return new ResponseEntity(hm, HttpStatus.OK);
        }catch (Exception ex) {
            hm.put(REnum.status, false);
            hm.put(REnum.errors, ex.getMessage());
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity list( int pageCount ) {
        Pageable pageable = PageRequest.of(pageCount, 5);
        Page<Note> notePage = noteRepository.findAll(pageable);
        return new ResponseEntity(notePage, HttpStatus.OK);
    }

}
