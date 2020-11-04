package web.servise;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.*;

@Service
public class ServiceCarImp implements ServiceCar {

    @Override
    public List<Car> listCar(int count) {
        List<Car> cars = new ArrayList<>();

        Car ferrary = new Car("Ferrary", 123, 5.7);
        Car bugatti = new Car("Bugatti", 456, 4.9);
        Car belaz = new Car("Belaz", 789, 20.5);
        Car lada = new Car("Lada", 2105, 1.8);
        Car volkswagen = new Car("Volkswagen", 189, 3.7);

        cars.add(ferrary);
        cars.add(bugatti);
        cars.add(belaz);
        cars.add(lada);
        cars.add(volkswagen);

        List<Car> cars2 = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cars2.add(cars.get(i));
        }
        return count == 0 ? cars : cars2;
    }
}
