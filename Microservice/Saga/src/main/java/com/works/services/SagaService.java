package com.works.services;

import com.works.entities.Note;
import com.works.entities.Person;
import com.works.props.Products;
import com.works.repositories.NoteRepository;
import com.works.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class SagaService {


    final NoteRepository noteRepository;
    final PersonRepository personRepository;

    public Map save() {
        Map map = new HashMap();
        RestTemplate template = new RestTemplate();

        Note note = new Note();
        note.setTitle("New Note");

        Person person = new Person();
        person.setName("Veli Bilirim");

        noteRepository.save(note);
        personRepository.save(person);

        String url = "https://dummyjson.com/products";
        Products products = template.getForObject(url, Products.class);

        if ( products.getTotal() < 100 ) {
            throw new DataIntegrityViolationException("");
        }

        map.put("status", true);
        return map;
    }

}
