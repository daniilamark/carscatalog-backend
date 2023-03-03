package com.daniilamark.carscatalogbackend.controller;

import com.daniilamark.carscatalogbackend.exception.CarNotFoundException;
import com.daniilamark.carscatalogbackend.model.Car;
import com.daniilamark.carscatalogbackend.repository.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class CarController {
    @Autowired
    private CarRepository carRepository;

    @PostMapping("/car")
    Car newCar(@RequestBody Car newCar){
        return carRepository.save(newCar);
    }

    @GetMapping("/cars")
    List<Car> getAllCars(){
        return carRepository.findAll();
    }


    @GetMapping("/car/{id}")
    Car getCarById(@PathVariable Long id){
        return carRepository.findById(id)
                .orElseThrow(()->new CarNotFoundException(id));
    }

    @PutMapping("/car/{id}")
    Car updateCar(@RequestBody Car newCar, @PathVariable Long id){
        return carRepository.findById(id)
                .map(car -> {
                    car.setNumber(newCar.getNumber());
                    car.setMark(newCar.getMark());
                    car.setColor(newCar.getColor());
                    car.setYearRelease(newCar.getYearRelease());
                    car.setBodyType(newCar.getBodyType());
                    car.setGearboxType(newCar.getGearboxType());
                    car.setMaxSpeed(newCar.getMaxSpeed());
                    car.setDriveUnit(newCar.getDriveUnit());

                    return carRepository.save(car);
                }).orElseThrow(()->new CarNotFoundException(id));
    }


    @DeleteMapping("/car/{id}")
    String deleteCar(@PathVariable Long id){
        if(!carRepository.existsById(id)){
            throw new CarNotFoundException(id);
        }
        carRepository.deleteById(id);
        return "Автомобиль с id " + id + " успешно удален!";
    }

    @GetMapping("/stat")
    String getStat(){
        return "Всего автомобилей: " + carRepository.count();
    }
}