package com.example.filmstreamer.controller;

import com.example.filmstreamer.model.View;
import com.example.filmstreamer.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("api/views")
public class ViewRestController {

    @Autowired
    ViewService viewservice;

    //here we are creating our end-point rest API View
    //CRUD: read
    @GetMapping("/views")
    public ResponseEntity<Iterable<View>> getAllViews() {
        Optional<Iterable<View>> viewsRetrieved = viewservice.getAllViews();

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "views");

        if (viewsRetrieved.isPresent()) {
            headers.add("operationStatus", "success");
            return ResponseEntity.accepted().headers(headers).body(viewsRetrieved.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

    //CRUD: read, find one view by id
    @GetMapping(path = "/getView")
    public ResponseEntity<View> findViewById(@RequestParam UUID viewId) {
        Optional<View> viewFound = viewservice.findViewById(viewId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "getView");

        if (viewFound.isPresent()) {
            headers.add("operationStatus", "found");
            return ResponseEntity.accepted().headers(headers).body(viewFound.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

    //CRUD: create view
    @PostMapping(path = "/addView", consumes = "application/JSON")
    public ResponseEntity<View> addView(@RequestBody View view) {
        Optional<View> viewCreated = viewservice.createView(view);

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "addView");

        if (viewCreated.isPresent()) {
            headers.add("operationStatus", "created");
            return ResponseEntity.accepted().headers(headers).body(viewCreated.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

}
