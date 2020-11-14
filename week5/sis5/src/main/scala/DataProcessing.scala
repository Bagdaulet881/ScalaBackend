import java.io.PrintWriter

import com.google.gson.Gson
import scala.io.Source
import scala.util.matching.Regex

class DataProcessing {
  var saleList = List[SaleClass]()

  def processing(): List[String] ={
    val normalReg = "(^.* )(.*)$".r
    val specialReg = "(Филиал )(.*)".r
    val specialReg2 = "(Порядковый номер чека )(.*)".r
    val specialReg3 = "(: )(.*)".r
    val specialReg4 = "(сайт:)(.*)".r
    val specialReg5 = "(: )(.*\")".r

    var listOfProducts = false
    var listOfDots = false
    var sale = new SaleClass
    var tempList =  "" :: Nil
    var resList =  "" :: Nil

    for (tempLine <- readFromFile()){
      //----------CENTER------------------------------------------------------------------------------------------------------
      if(tempLine.contains("ПРОДАЖА")){
        listOfProducts = true
      }else
        if(listOfProducts){
          if(tempLine.matches("([0-9]+).$")){
            if(tempList.length>1){
              sale.setData(tempList(1),tempList(2),tempList(3),tempList(4))
              saleList:+=sale
              sale = new SaleClass
              tempList = ""::Nil
            }
          }else
          //      center end
            if(tempLine.contains("Банковская карта:")){
              sale.setData(tempList(1),tempList(2),tempList(3),tempList(4))
              saleList:+=sale
              listOfProducts = false
              listOfDots = true
            }
          tempList:+=tempLine
          //----------------------------------------------------------------------------------------------------------------------
        }else if(listOfDots){//Банковская продажа
          if(tempLine.contains("Для проверки чека")){
            splitData(specialReg5,tempLine)
            splitData(specialReg4,tempLine)
          }else
            if(tempLine.contains(":")){
              splitData(specialReg3,tempLine)
            }else
            {
              //          println(tempLine)
              resList:+=tempLine
            }
        }else
          if(tempLine.contains("Филиал")){
            splitData(specialReg, tempLine)
          }else if( tempLine.contains("Порядковый")){
            splitData(specialReg2, tempLine)
          }else{
            splitData(normalReg, tempLine)
          }
    }
    //    resList.foreach(println)
    //    saleList.foreach(println)
    def splitData(reg: Regex, line:String): Unit ={
      for (patternMatch <- reg.findFirstMatchIn(line)) {
        //      println(s"key: ${patternMatch.group(1)} value: ${patternMatch.group(2)}")
        if(patternMatch.group(2).nonEmpty){
          resList:+=patternMatch.group(2)
        }
      }
    }
    resList
  }
  //----------------------------------------------------------------------------------------------------------------------

  def readFromFile(): List[String] ={
    val source = Source.getClass().getResource("/raw.txt")
    var file = Source.fromURL(source)
      .getLines.toList
      .map(_.split("\\\\\\\\n"))
      .map(_(0))
    file
  }
  def writeToFile(jsonString:String): Unit ={
    new PrintWriter("output.txt") { write(jsonString); close }
    println("File write true")
  }
  //----------------------------------------------------------------------------------------------------------------------
  def toJson(rawObject: CheckClass): String ={
    val gson = new Gson
    val jsonString = gson.toJson(rawObject)
    (jsonString)
  }
}
