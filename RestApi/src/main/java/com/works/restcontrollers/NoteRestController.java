package com.works.restcontrollers;

import com.works.entities.Note;
import com.works.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteRestController {

    final NoteService noteService;

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody Note note, @RequestParam(defaultValue = "newsLine") String data) {
        System.out.println(data);
        return noteService.save(note);
    }

    @GetMapping("/list")
    public ResponseEntity list( @RequestParam(defaultValue = "0") int pageCount ) {
        return noteService.list(pageCount);
    }

}
