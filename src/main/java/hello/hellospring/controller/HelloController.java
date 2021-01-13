package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("hello", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    /**
     * http 통신 String 형식으로 반환
     * @param name : 이름(required)
     * @return : 'hello. ' + name
     */
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam(value = "name") String name) {
        return "hello. " + name;
    }

    /**
     * http 통신 json 형식으로 반환
     * 객체로 return 하는경우 자동으로 json형태로 변환
     * @param name
     * @return
     */
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam(value = "name") String name){
        return new Hello(name);
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }
        void setName(String name) {
            this.name = name;
        }
        Hello(String name){
            this.name = name;
        }
    }
}
