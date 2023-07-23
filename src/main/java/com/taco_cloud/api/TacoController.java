package com.taco_cloud.api;

import com.taco_cloud.data.TacoRepository;
import com.taco_cloud.domain.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tacos")
@CrossOrigin("/**")
public class TacoController {
    private TacoRepository tacoRepository;

    @Autowired
    public TacoController(TacoRepository tacoRepository){
        this.tacoRepository = tacoRepository;
    }

    @GetMapping("recent")
    public Iterable<Taco> getRecentTacos(){
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").ascending());
        return tacoRepository.findAll(page).getContent();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Taco addNewTacos(@RequestBody Taco taco){
        return tacoRepository.save(taco);
    }
}
