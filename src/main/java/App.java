import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args){
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/output", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/output.vtl");
      String input = request.queryParams("input-number");
      Integer inputInteger = Integer.parseInt(input);
      model.put("number-input", inputInteger);
      PingPong pingPong = new PingPong();
      ArrayList<Object> results = pingPong.runPingPong(inputInteger);
      model.put("results", results);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
