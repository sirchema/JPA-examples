package chema.spring.boot.jpa.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @Autowired
    private MyServiceImpl myService;

    public MyController(){

    }

    @RequestMapping(path = "/saluda")
    @ResponseBody
    public String hello() {
        return "Hello world";
    }

    @RequestMapping(path = "/helloName")
    @ResponseBody
    public String hello(String name) {
        return "Hello " + name;
    }

    @RequestMapping(path = "/add")
    @ResponseBody
    public String add(int a, int b) {
        return "Result of operation is: " + myService.add(a, b);
    }

    @RequestMapping(path = "/create")
    @ResponseBody
    public People create(String name, String town) {
        return myService.create(name, town);
    }

    @RequestMapping(path = "/query")
    @ResponseBody
    public String query(String name){
        //With spring.jpa.open-in-view=true working
        People people = myService.query(name);
        System.out.println("Get town");
        return people.getTown();
    }
}
