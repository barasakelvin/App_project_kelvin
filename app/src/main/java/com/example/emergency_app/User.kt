package com.example.emergency_app

class User {
    var name:String =""
    var number:String =""
    var email:String =""
    var locate:String =""
    var des:String =""
    var id: String =""

    constructor(
        name: String,
        number: String,
        email: String,
        locate: String,
        des: String,
        id: String,
    )
    {
        this.name = name
        this.email = email
        this.number = number
        this.locate = locate
        this.des = des
        this.id = id

    }
constructor()
}