package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.Car;
import web.servise.ServiceCarImp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class CarController {
    private static int count;
    private static List<Car> cars = new ServiceCarImp().listCar(count);

    @GetMapping("/cars")
    public String carList(ModelMap model) {

        model.addAttribute("cars", cars);

        return "cars";
    }

    @GetMapping("/cars/{count}")
    public String carList2(@PathVariable(name = "count") int count, ModelMap model) {

        model.addAttribute("cars", new ServiceCarImp().listCar(count));

        return "cars";
    }

}
