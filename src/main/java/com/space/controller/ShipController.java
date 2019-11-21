package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class ShipController {
    private ShipService service;

    @Autowired

    public void setService(ShipService service) {
        this.service = service;
    }

    @GetMapping(value = "/ships")
    @ResponseStatus(HttpStatus.OK)
    public List<Ship> getAllShips() {
        System.out.println("@@@@@@ShipController@@@@@@@@");

        return service.getShips();
    }

    @GetMapping(value = "/ships/count")
    @ResponseStatus(HttpStatus.OK)
    public Integer getCount() {
        return service.getShips().size();
    }

    @PostMapping(value = "/ships")
    @ResponseStatus(HttpStatus.OK)
    public Ship creatShip() {
        System.out.println("post");
        Ship ship = new Ship("Test", "testPlanet", ShipType.MILITARY, new Date(), true, 444.0, 11, 323.22);
        service.saveShip(ship);
        return ship;
    }

    @GetMapping(value = "/ships/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ship getShip(@PathVariable(value = "id") String pathId) {
        Long id = Long.parseLong(pathId);
        return service.getShip(id);
    }

    @PostMapping(value = "/ships/{id}")
    @ResponseBody
    public Ship editShip(@PathVariable(value = "id") String pathId, @RequestBody Ship ship) {
        System.out.println(ship);
        Long id = Long.parseLong(pathId);


        return service.editShip(id,ship);

    }

    @DeleteMapping(value = "/ships/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void shipDel(@PathVariable (value = "id") String pathId){
        Long id = Long.parseLong(pathId);
        service.shipDel(id);

    }

}
