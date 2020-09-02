package com.hi.udemy

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

//Entity어노테이션 : 해당 클래스가 application.properties 내의
//spring.datasource.name의 value값인 udemy 데이터베이스의 테이블이라는 의미
@Entity
class User (
        var userId:String,
        var password:String,
        @Id @GeneratedValue var id:Long?=null
        //GeneratedValue : 자동으로 생성되는 값
)
//위의 클래스가 하나의 DB 테이블이 되고, 하나의 변수가 컬럼이됨.

//primary key라는 어노테이션 : @Id

/*
* CREATE TABLE user (
*   userId varchar2(50) not null,
*   password varchar2(50) not null,
*   id long primary key,
* */
