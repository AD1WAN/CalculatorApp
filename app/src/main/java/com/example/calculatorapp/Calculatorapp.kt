package com.example.calculatorapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class Calculatorapp : AppCompatActivity() {
 lateinit var zero: TextView
 lateinit var one: TextView
 lateinit var two: TextView
 lateinit var three: TextView
 lateinit var four: TextView
 lateinit var five: TextView
 lateinit var six: TextView
 lateinit var seven: TextView
 lateinit var eight: TextView
 lateinit var nine: TextView


 lateinit var plus: TextView
 lateinit var minus: TextView
 lateinit var multiply: TextView
 lateinit var divide: TextView

 lateinit var ac: TextView
 lateinit var backspace: ImageView
 lateinit var modulo: TextView
 lateinit var changesign: TextView
 lateinit var decimal: TextView
 lateinit var equals: TextView

 lateinit var  expression: TextView
 lateinit var  result: TextView


     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_calculatorapp)

         zero = findViewById(R.id.zero)
         one = findViewById(R.id.one)
         two = findViewById(R.id.two)
         three = findViewById(R.id.three)
         four = findViewById(R.id.four)
         five = findViewById(R.id.five)
         six = findViewById(R.id.six)
         seven = findViewById(R.id.seven)
         eight = findViewById(R.id.eight)
         nine = findViewById(R.id.nine)


         plus = findViewById(R.id.add)
         minus = findViewById(R.id.minus)
         multiply = findViewById(R.id.multiply)
         divide = findViewById(R.id.divide)


         ac = findViewById(R.id.ac)
         backspace = findViewById(R.id.backspace)
         modulo = findViewById(R.id.modulo)
         changesign = findViewById(R.id.changesign)
         decimal = findViewById(R.id.decimal)
         equals = findViewById(R.id.equals)

         expression = findViewById(R.id.expression)
         result = findViewById(R.id.result)

         appendHelper(zero, "0", false)
         appendHelper(one, "1", false)
         appendHelper(two, "2", false)
         appendHelper(three, "3", false)
         appendHelper(four, "4", false)
         appendHelper(five, "5", false)
         appendHelper(six, "6", false)
         appendHelper(seven, "7", false)
         appendHelper(eight, "8", false)
         appendHelper(nine, "9", false)

         appendHelper(plus, "+", false)
         appendHelper(minus, "-", false)
         appendHelper(multiply, "*", false)
         appendHelper(divide, "/", false)
         appendHelper(modulo, "%", false)

         appendHelper(decimal, ".", false)

         backspace.setOnClickListener {
            result.text = ""
            result.hint = ""

             if (expression.text.isNotBlank()){
                 expression.text = expression.text.dropLast(1)
                 //expression.text = expression.text.substring(0..(expression.text.length-2))
                 calculate(isAfterBacksapce = true)

             }

             }
         ac.setOnClickListener {
             result.hint = ""
             result.text = ""
             expression.text = ""

             changesign.setOnClickListener {
                 if (expression.text.isNotBlank() && expression.text[0]== '-'){
                     expression.text= expression.text.substring(1)
                 } else if (expression.text.isNotBlank()) {
                     expression.text = "-(${expression.text})"

                 }
             }

         }
         listenToClicksOnEqualsOption()

     }
       private fun calculate(isAfterBacksapce: Boolean= false){

           if (expression.text.isBlank()) return
           try {
               val expression = ExpressionBuilder (expression.text.toString()).build()
               val answer = expression.evaluate()
               val answerInt = answer.toInt()
               val diff = answer - answerInt
               if (diff == 0.0) {
                   result.text = answerInt.toString()
               } else {
                   result.text = answer.toString()
               }
           } catch (exception: Exception) {
               if (isAfterBacksapce) {
                   result.text = ""
                   result.hint = ""
               } else {
                   result.text = exception.message
               }
           }
       }




     private fun listenToClicksOnEqualsOption() {
         equals.setOnClickListener {
            calculate()
         }
     }

     private fun appendHelper(View: TextView, value: String, toBeCleared: Boolean) {
         View.setOnClickListener {
             appendText(value, toBeCleared)
         }
     }

     private fun appendText(value: String, toBeCleared: Boolean) {

         if (result.text != "") {
             expression.text = ""
         }


          if (toBeCleared) {
             result.text = ""
             expression.append(value)


         }  else {
             expression.append(result.text)
             expression.append(value)
             result.text = ""


}
      result.hint = expression.text
}
}







