package org.notifier.sender_management.api;

import org.notifier.sender_management.entity.Sender;
import org.notifier.sender_management.service.ISenderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
public class SenderController {

    private final ISenderService senderService;

    public SenderController(ISenderService senderService) {
        this.senderService = senderService;
    }

    @RequestMapping(value = "/sender/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getSender(@PathVariable("id") long id) {
        Optional<Sender> sOpt = this.senderService.findById(id);
        return sOpt.<ResponseEntity<?>>map(sender ->
                new ResponseEntity<>(sender, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>("User with id " + id
                + " not found", HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/sender/list/", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        Iterable<Sender> all = this.senderService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "/sender/", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Sender sender, UriComponentsBuilder ucBuilder) {

        if (this.senderService.isExist(sender)) {
            return new ResponseEntity<>("Unable to create. A User with name " +
                    sender.getEmail() + " already exist.", HttpStatus.CONFLICT);
        }
        this.senderService.register(sender);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/sender/{id}").buildAndExpand(sender.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/sender/{id}/validate/", method = RequestMethod.POST)
    public ResponseEntity<?> validate(@PathVariable("id") long id) {
        this.senderService.validate(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
