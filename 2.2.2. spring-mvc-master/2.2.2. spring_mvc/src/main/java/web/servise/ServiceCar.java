package web.servise;

import web.model.Car;

import java.util.List;

public interface ServiceCar {
    List<Car> listCar(Integer count);
}
