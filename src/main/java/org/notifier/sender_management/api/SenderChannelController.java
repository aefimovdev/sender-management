package org.notifier.sender_management.api;

import org.notifier.sender_management.entity.Sender;
import org.notifier.sender_management.entity.SenderChannel;
import org.notifier.sender_management.service.ISenderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
public class SenderChannelController {

    private final ISenderService senderService;

    public SenderChannelController(ISenderService senderService) {
        this.senderService = senderService;
    }

    @RequestMapping(value = "/sender/channel/", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody SenderChannel senderChannel, UriComponentsBuilder ucBuilder) {

        senderChannel.setSender(getCurrentSender());
        this.senderService.createChannel(senderChannel);

        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/sender/{id}").buildAndExpand(sender.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }

    private Sender getCurrentSender(){
        Optional<Sender> byId = this.senderService.findById(1840);
        return byId.get();
    }

}
