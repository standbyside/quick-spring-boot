package quick.boot.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class TestController {

  @GetMapping("/test1")
  public Mono<String> test1(String param) {
    Mono<String> mono = Mono.just(param)
           .filter(o -> o.equals("a"))
           .switchIfEmpty(Mono.just("b"));
    return mono;
  }
}
