package com.space.service;

import com.space.model.Ship;
import com.space.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class ServiceImpl implements ShipService {
    private CustomerRepository customerRepository;

    @Autowired

    public ServiceImpl(CustomerRepository customerRepository) {

        super();
        this.customerRepository = customerRepository;
    }

    public ServiceImpl() {
    }

    @Override
    public Ship saveShip(Ship ship) {
        return customerRepository.save(ship);
    }

    @Override
    public List<Ship> getShips() {
        System.out.println("!!!!getShips!!!!");
        //Pageable pageable = PageRequest.of(1, 5);
        List<Ship> ships = customerRepository.findAll();

        return ships;
    }

    @Override
    public Ship getShip(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Ship editShip(Long id, Ship ship) {

        Ship editedShip = customerRepository.findById(id).get();

        if (ship.getName() != null && !ship.getName().isEmpty())
            editedShip.setName(ship.getName());

        if (ship.getPlanet() != null)
            editedShip.setPlanet(ship.getPlanet());

        if (ship.getShipType() != null)
            editedShip.setShipType(ship.getShipType());

        if (ship.getProdDate() != null)
            editedShip.setProdDate(ship.getProdDate());

        if (ship.getSpeed() != null)
            editedShip.setSpeed(ship.getSpeed());

        if (ship.getUsed() != null)
            editedShip.setUsed(ship.getUsed());

        if (ship.getCrewSize() != null)
            editedShip.setCrewSize(ship.getCrewSize());
        return customerRepository.save(editedShip);
    }

    @Override
    public void shipDel(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Ship> pagination(List<Ship> ships,String pageSize, String pageNumber) {
        Integer number=Integer.parseInt(pageNumber);
        Integer size=Integer.parseInt(pageSize);

        Integer first=size*number;
        int last=first+size;
        return ships.subList(first,last);
    }
}
