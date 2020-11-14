object Main {

  def task4(): Unit ={
    import scala.collection.mutable.HashSet
    def findPairs(nums: Array[Int], k: Int): Int = {
      var sorted = nums.sorted
      var hs = HashSet[(Int,Int)]()
      for(i<-0 until sorted.length){
        for (j<-i until sorted.length){
          if(i!=j && sorted(j)-sorted(i)==k){
            hs += sorted(j)->sorted(i)
          }
        }
      }
      hs.size
    }
  }
  def task3(): Unit ={
    def dayOfTheWeek(day: Int, month: Int, year: Int): String = {
      val week = Array("Sunday", "Monday", "Tuesday", "Wednesday","Thursday", "Friday", "Saturday")
      val monthDay = Array(0,31,28,31,30,31,30,31,31,30,31,30,31)
      var cnt = 4
      for(i<-1971 until year){
        cnt+= (if(leapCheck(i))366 else 365)
      }
      for(i<-1 until month){
        if(leapCheck(year)&&i==2){
          cnt+=1
        }
        cnt+=monthDay(i)
      }
      cnt +=day
      cnt %=7
      week(cnt)

    }
    def leapCheck(year: Int): Boolean ={
      if((year%4==0&&year%100!=0)|| year%400==0){
        true

      }else
        false
    }

  }
  def task2(): Unit ={
    def average(salary: Array[Int]): Double = {
      salary.foldLeft((0, Int.MaxValue, Int.MinValue,0)){
        (memo,next)=>
          memo match {
            case (sum, min, max,count)=>
              (sum+next,math.min(next,min), math.max(max, next), count + 1)
          }
      } match {
        case (sum, min, max,count)=> ((sum-min-max)/ (count-2).toDouble)
      }
    }
  }

  def task1(): Unit ={
    def maxProduct(nums: Array[Int]): Int = {
      nums.sorted.slice(nums.length-2, nums.length).reduce((a, b) => (a-1)*(b-1))
    }
  }
}
