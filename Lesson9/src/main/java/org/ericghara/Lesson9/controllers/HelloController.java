package org.ericghara.Lesson9.controllers;

import org.ericghara.Lesson9.dtos.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController // meta-annotation for Controller and ResponseBody
public class HelloController {
    // Only specifying controller would require methods to return STATIC endpoints

    //@RequestMapping(method = RequestMethod.GET, path = "/hello/{name}")
    @GetMapping(path = "/hello/{name}")   // equivalent to above
    public String hello(@PathVariable String name) {
        return String.format("Hello, %s!%n", name);
    }

    @PostMapping(path = "/goodbye")
    public String goodbye(@RequestBody Person person) {

        return String.format("Goodbye, %s!%n", person);
    }

    @GetMapping(path= "/get")
    public Person getPerson() {
        Person p = new Person();
        p.setName("Mr. Cool Guy");
        return p;
    }

    @GetMapping(path= "/getAll")
    public Stream<Person> getPeople() {
        return IntStream.range(0,10)
                        .boxed()
                        .map( (i) -> "Mr. Cool Guy " + i )
                        .map(Person::new);
    }

    @GetMapping(path = "/statusTest")
    public void statusTest(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

}
