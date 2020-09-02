package com.hi.udemy

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller //controller 어노테이션으로 스프링부트와 연결됨.
class HtmlController {

    //메소드에 URL을 맵핑 localhost:8080/URI
    //해당하는 URL이 입력될 경우 아래의 index함수가 호출됨
    //아래 index 함수의 경우 RequestMapping이 "/"이므로 메인페이지가 됨
    @GetMapping("/") //RequestMapping("URL")
    fun index(model : Model) : String {
        model.addAttribute("title","HOME")
        return "index"
    }



    @GetMapping("/{formType}")
    fun htmlForm(model : Model, @PathVariable formType : String) : String {

        var response = ""

        if(formType.equals("sign")){
            response = "sign"
        } else if(formType.equals("login")) {
            response = "login"
        }

        model.addAttribute("title",response.toUpperCase())

        return response
    }


}

