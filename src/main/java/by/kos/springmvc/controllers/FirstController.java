package by.kos.springmvc.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {

  @GetMapping("/hello")
  public String helloPage(@RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "lastName", required = false) String lastName, Model model) {
    model.addAttribute("message", String.format("Hello, %s %s\n", name, lastName));
    System.out.printf("Hello, %s %s\n", name, lastName);
    return "first/hello";
  }

  @GetMapping("/goodbye")
  public String goodbyePage(HttpServletRequest request) {
    String name = request.getParameter("name");
    String lastName = request.getParameter("lastName");
    System.out.printf("Goodbye, %s %s\n", name, lastName);
    return "first/goodbye";
  }

  @GetMapping("/calculator")
  public String calculate(@RequestParam(value = "a") int a, @RequestParam(value = "b") int b,
      @RequestParam(value = "action") String action,
      Model model) {

    double result;
    switch (action) {
      case "multiplication":
        result = a * b;
        break;
      case "addition":
        result = a + b;
        break;
      case "subtraction":
        result = a - b;
        break;
      case "division":
        if (b == 0) {
          result = 0;
        } else {
          result = a / (double) b;
        }
        break;
      default:
        result = 0;
        break;
    }
    model.addAttribute("result", result);

    return "first/calc_result";
  }
}
