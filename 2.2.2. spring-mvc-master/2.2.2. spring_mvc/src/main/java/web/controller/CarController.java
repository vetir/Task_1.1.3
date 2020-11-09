package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import web.model.Car;
import web.servise.ServiceCarImp;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CarController {

    /* Решение через аннотацию @PathVariable

    private int count;
    private List<Car> cars = new ServiceCarImp().listCar(count);

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
    */

    @GetMapping("/cars")
    public String carList(@RequestParam(value = "count", defaultValue = "5") Integer count, ModelMap model) {
        model.addAttribute("cars", new ServiceCarImp().listCar(count));
        return "cars";
    }

//    @GetMapping("/cars")
//    public String carList(HttpServletRequest request, ModelMap model) {
//        String name = request.getParameter("count");
//        model.addAttribute("cars", new ServiceCarImp().listCar(Integer.parseInt(name)));
//        return "cars";
//    }
}
