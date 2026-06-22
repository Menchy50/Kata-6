package software.ulpgc.kata6;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;
import software.ulpgc.kata2.Factorial;

public class Main {
  public static void main(String[] args) {
    Javalin app = Javalin.create();
    app.get("/", Main::hello);
    app.get("/factorial", Main::factorial);
    app.get("/movies", Main::movies);
    app.get("/movies/{id}", Main::movie);
    app.start(8080);
  }

  private static void movie(Context context) {
    String id = context.pathParam("id");
    context.cookie("last", id);
    String cookies = context.header("Cookie");
    System.out.println(context.header("USER-AGENT"));
    System.out.println(cookies);
    context.result(id);
  }

  private static void movies(Context context) {
    context.json(new RemoteStore().movies().toList());
  }

  private static void factorial(Context context) {
    String value = context.queryParam("n");
    try {
      int n = Integer.parseInt(value);
      int f = 1;
      for (int i = 2; i <= n; i++) {
        f *= i;
      }
      context.status(200);
      context.json(new Factorial(n, f));
    }
    catch (NumberFormatException e) {
      context.status(400);
    }
  }

  private static void hello(Context context) {
    context.status(200);
    context.result("Hello World");
  }
}
