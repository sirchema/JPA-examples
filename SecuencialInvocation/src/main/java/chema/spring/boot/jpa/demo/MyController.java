package chema.spring.boot.jpa.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @Autowired
    private MyServiceImpl myService;

    @Value("${spring.jpa.open-in-view}")
    private String openInView;

    @RequestMapping(path = "/query")
    @ResponseBody
    public boolean query(long id) {
        log();
        People first = myService.query(id);
        People second = myService.query(id);

        return first == second;
    }

    private void log() {
        System.out.println("------ using: spring.jpa.open-in-view=" + openInView);
    }




}
