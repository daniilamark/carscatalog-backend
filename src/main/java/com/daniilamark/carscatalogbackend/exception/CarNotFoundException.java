package com.daniilamark.carscatalogbackend.exception;

public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException(Long id){
        super("Не удалось найти пользователя с id: " + id);
    }
}
