package com.space.service;

import com.space.model.Ship;

import java.util.List;

public interface ShipService {
   List<Ship> getShips();
  Ship saveShip(Ship ship);
  Ship getShip(Long id);
  Ship editShip(Long id,Ship ship);
  void shipDel(Long id);
}
