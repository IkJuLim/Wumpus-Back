package com.limikju.ai.WumpusWorld.controller;

import com.limikju.ai.WumpusWorld.Return;
import com.limikju.ai.WumpusWorld.wumpus.WumpusWorld;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class MainController {

    @GetMapping("/api")
    public ResponseEntity<Return> main() {
        WumpusWorld wumpusWorld = new WumpusWorld();
        Return returnStatuses = wumpusWorld.get();

        while (returnStatuses == null || returnStatuses.score < 0){

            returnStatuses = wumpusWorld.get();
        }

        return new ResponseEntity(returnStatuses, HttpStatus.OK);
    }
}
