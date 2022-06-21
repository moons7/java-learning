package io.reimu.webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class WebFluxController {

    @GetMapping("/mono/{user}")
    public Mono<Integer> getUser(@PathVariable Integer user) {
        return Mono.just(user);
    }

    @GetMapping("/flux/{user}")
    public Flux<Integer> getUser() {
        return Flux.just(1,2,3,4,5);
    }
}
