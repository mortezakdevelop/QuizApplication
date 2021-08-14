package com.example.quizapplication

object setData {
    const val name:String="name"
    const val score:String="score"

    fun getQuestion(): ArrayList<QuestionData> {
        var que: ArrayList<QuestionData> = arrayListOf()

        var question1 = QuestionData(
            1,

            "what is capital of tehren?",

            "dehli",
            "kabol",
            "tehran",
            "moscow",
            3
        )
        var question2 = QuestionData(
            2,

        "what is name of iranian boy?",
            "ali",
        "fatemeh",
            "sara",
            "maryam",
            1
        )

     var question3 = QuestionData(
            3,

        "what is largest country in the world?",
            "USA",
        "russia",
            "china",
            "mexico",
            2
        )

      var question4 = QuestionData(
            4,

        "what the world largest ocean?",
            "atlas",
        "india",
            "pacific ocean",
            "none",
            3
        )

       var question5 = QuestionData(
            5,

        "what the smallest country in the world?",
            "england",
        "france",
            "german",
            "vatican",
            4
        )

        que.add(question1)
        que.add(question2)
        que.add(question3)
        que.add(question4)
        que.add(question5)

        return que
    }

}