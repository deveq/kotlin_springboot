package com.hi.udemy

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.security.MessageDigest
import javax.servlet.http.HttpSession

@Controller //controller 어노테이션으로 스프링부트와 연결됨.
class HtmlController {

    @Autowired
    lateinit var repository:UserRepository
    //지금 바로 초기화 하지 않고 autowired를 통해 나중에 초기화하겠다는 표시

    //메소드에 URL을 맵핑 localhost:8080/URI
    //해당하는 URL이 입력될 경우 아래의 index함수가 호출됨
    //아래 index 함수의 경우 RequestMapping이 "/"이므로 메인페이지가 됨
    @GetMapping("/") //RequestMapping("URL")
    fun index(model : Model) : String {
        model.addAttribute("title","HOME")
        return "index"
    }

    fun crypto(ss: String) : String {
        val sha = MessageDigest.getInstance("SHA-256")
        val hexa = sha.digest(ss.toByteArray())
        val cryptoStr = hexa.fold("",{str,it -> str + "%02x".format(it)})

        return cryptoStr
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

    @PostMapping("/sign")
    fun postSign(model:Model,
                @RequestParam(value="id") userId:String,
                @RequestParam(value="password") password:String) : String {

        try{
            val cryptoPass = crypto(password)
            val user = repository.save(User(userId,cryptoPass))  //save : CRUD에서 create에 해당
            println(user.toString())
        }catch (e : Exception){
            e.printStackTrace()
        }

        model.addAttribute("title","sign success")

        return "login"

    }

    @PostMapping("/login")
    fun postLogin(model : Model,
                  session : HttpSession,
                @RequestParam("id") userId: String,
                @RequestParam("password") password : String) : String {
        var pageName = ""

        try{
            val cryptoPass = crypto(password)
            val dbUser = repository.findByUserId(userId)

            if(dbUser != null) {
                val dbPass = dbUser.password

                if(cryptoPass.equals(dbPass)){
                    session.setAttribute("userId",dbUser.userId)
                    model.addAttribute("title","welcome")
                    model.addAttribute("userId",userId)
                    pageName =  "welcome"
                }else{
                    model.addAttribute("title","login")
                    pageName =  "login"
                }
            }
        } catch (e : Exception){
            e.printStackTrace()
        }
        return pageName
    }


}

