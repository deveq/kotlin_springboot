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
    @RequestMapping("/in") //RequestMapping("URL")
    fun index(model : Model) : String {
        println("index function")
        return "index"
    }
    //mustache를 사용하여 index메소드에서 return된 문자열이름을 가진 html파일(template폴더 안에 있는)을 연결

    //GetMapping("value") get방식 요청에만 작동하는 매핑 어노테이션
    //PathVariable

    @GetMapping("/post/{num}")
    //중괄호{ } 사이에는 변수로 취급
    fun post(model: Model, @PathVariable num : Int) {
    //PathVariable : 매핑 시 value값에 있는 { } 사이의 변수
        println("num : $num")
    }


}

// xxx.mustache 확장자 형식으로 파일 작성.