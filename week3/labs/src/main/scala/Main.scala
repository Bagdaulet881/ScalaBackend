import scala.+:
import scala.collection.immutable.ListMap
import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks.{break, breakable}

object Main extends App {
  problem10()
  def problem10(): Unit ={
    def buildArray(target: Array[Int], n: Int): List[String] = {
      var res: List[String] = List()
      var inp = 1
      for(i <- target){
        while (inp < i){
          res :+= "Push"
          res :+= "Pop"
          inp = inp + 1
        }
        res :+= "Push"
        inp = inp + 1
      }
      res
    }
    def test1() = {
      val nums = Array(1,3)
      val num = 3
      println(buildArray(nums, num).foreach(x => print(x + " ")))
    }

    def test2() = {
      val nums = Array(1,2,3)
      val num = 3
      println(buildArray(nums, num).foreach(x => print(x + " ")))
    }

    def test3() = {
      val nums = Array(1,2)
      val num = 4
      println(buildArray(nums, num).foreach(x => print(x + " ")))
    }
    test1()
    test2()
    test3()
  }
  def problem9(): Unit ={
    def intersection(nums1: Array[Int], nums2: Array[Int]): Array[Int] = {
      var size = 0

      def intersec(bigger: Array[Int], smaller: Array[Int]): Array[Int] = {
        var res: List[Int] = List()
        for(i <- 0 to size-1){
          breakable{
            for(j <- 0 to bigger.length-1){
              if(bigger(j) == smaller(i)) {
                res :+= bigger(j)
                break
              }
            }
          }
        }
        res.toArray.distinct
      }
      if(nums1.length>nums2.length){
        size = nums2.length
       intersec(nums1, nums2)
      }else{
        size = nums1.length
        intersec(nums2, nums1)
      }

    }

    def test1() = {
      val nums1 = Array(1,2,2,1)
      val nums2 = Array(2,2)
      println(intersection(nums1, nums2).foreach(x => print(x + " ")))
    }

    def test2() = {
      val nums1 = Array(4,9,5)
      val nums2 = Array(9,4,9,8,4)
      println(intersection(nums1, nums2).foreach(x => print(x + " ")))
    }
    test1()
    test2()
  }
  def problem8(): Unit ={
    //tested on leetcode
    class CustomFunction {
          def f(x: Int, y: Int): Int = {
            1
          }
       }
    def findSolution(customfunction: CustomFunction, z: Int): List[List[Int]] = {
      var res: List[List[Int]] = List()
      for(i <- 1 to 1001){
        breakable{
          for(j <- 1 to 1001){
            if(customfunction.f(i,j)==z){
              res :+= List(i,j)
            }else
              if(customfunction.f(i,j)>z){
                break
              }
          }
        }
      }
      res
    }
  }
  def problem7(): Unit ={
    def kWeakestRows(mat: Array[Array[Int]], k: Int): Array[Int] = {
      val temp = mat.map(x => x.count(y => 1 == y))
      var res = Array.ofDim[Int](k)
      val indecies = temp
      val sorted = temp.sortWith(_ < _)
      println(sorted.foreach(x => print(x + " ")))
      for (i <- 0 to k-1) {
        breakable{
          for (j <- 0 to indecies.length-1){
            if(sorted(i) == indecies(j)) {
              res(i) = j
              indecies(j) = -1
              break
            }
          }
        }
      }
      res
    }
    def test1(): Unit ={
      val arr : Array[Array[Int]] = Array(Array(1,1,0,0,0),Array(1,1,1,1,0),Array(1,0,0,0,0),Array(1,1,0,0,0),Array(1,1,1,1,1))
      val k = 3
      println(kWeakestRows(arr, k).foreach(x => print(x + " ")))
    }
    def test2(): Unit ={
      val arr : Array[Array[Int]] = Array(Array(1,1,0),Array(1,1,0),Array(1,1,1),Array(1,1,1),Array(0,0,0),Array(1,1,1),Array(1,0,0))
      val k = 6
      println(kWeakestRows(arr, k).foreach(x => print(x + " ")))
    }
    test1()
    test2()
  }
  def problem6(): Unit ={
    def sumZero(n: Int): Array[Int] = {
      var a = Array.ofDim[Int](n)
      if(n%2==0){
        for (i <- 0 to n/2){
          a(i) =  -(i+1)
          a(n-i-1) = (i+1)
        }
      }else{
        for (i <- 0 to n/2){
          a(i) =  -(i+1) //0 1
          a(n-i-1) = (i+1) // 2 3 4
        }
        a(n/2) = 0
      }
      a
    }
    def test1(): Unit ={
      println(sumZero(5).foreach(x => print(x + " ")))
    }
    def test2(): Unit ={
      println(sumZero(3).foreach(x => print(x + " ")))
    }
    def test3(): Unit ={
      println(sumZero(6).foreach(x => print(x + " ")))
    }
    test1()
    test2()
    test3()
  }
  def problem5() = {
    def decompressRLElist(nums: Array[Int]): Array[Int] = {
      (for (i <- nums.indices by 2) yield List.fill(nums(i))(nums(i + 1))).flatten.toArray
    }

    def test1() = {
      val nums = Array(1,2,3,4)
      println(decompressRLElist(nums).foreach(x => print(x + " ")))
    }

    def test2() = {
      val nums = Array(1,1,2,3)
      println(decompressRLElist(nums).foreach(x => print(x + " ")))
    }

    def test3() = {
      val nums = Array(1, 2)
      println(decompressRLElist(nums).foreach(x => print(x + " ")))
    }

    test1()
    test2()
    test3()
  }

  def problem4() = {

    def repeatedNTimes(A: Array[Int]): Int = {
      val res = A.map(x => A.count(y => x == y))
      for((value, index) <- res.zipWithIndex)
        if (value == A.length / 2) return  A(index)
      -1
    }

    def test1() = {
      val nums = Array(1,3,3,3)
      println(repeatedNTimes(nums))
    }

    def test2() = {
      val nums = Array(2,1,2,5,3,2)
      println(repeatedNTimes(nums))
    }

    def test3() = {
      val nums = Array(5,1,5,2,5,3,5,4)
      println(repeatedNTimes(nums))
    }

    test1()
    test2()
    test3()
  }
  def problem3() = {
//    def smallerNumbersThanCurrent(nums: Array[Int]): Array[Int] = {
//      nums.map(x => nums.count(y => x > y))
//    }

    def smallerNumbersThanCurrent(nums: Array[Int]): Array[Int] = {
      val res = Array.ofDim[Int](nums.length)
      for ((value, index) <- nums.zipWithIndex) {
        var cnt = 0
        nums.foreach(x => if (x < value)
          cnt = cnt + 1
        )
        res(index) = cnt
      }
      res
    }

    def test1() = {
      val nums = Array(8, 1, 2, 2, 3)
      println(smallerNumbersThanCurrent(nums).foreach(x => print(x + " ")))
    }

    def test2() = {
      val nums = Array(6,5,4,8)
      println(smallerNumbersThanCurrent(nums).foreach(x => print(x + " ")))
    }

    def test3() = {
      val nums = Array(7, 7, 7, 7)
      println(smallerNumbersThanCurrent(nums).foreach(x => print(x + " ")))
    }

    test1()
    test2()
    test3()
  }

  def problem2(): Unit ={
    //----------tested on leetcode--------
    class ListNode(_x: Int = 0, _next: ListNode = null) {
      var next: ListNode = _next
      var x: Int = _x
    }
    def getDecimalValue(head: ListNode): Int = {
      var text = ""
      var last: ListNode = head
      do{
        text += last.x.toString()
        last = last.next
      }
      while (last !=null)
      val x = Integer.parseInt(text, 2)
      return x
    }

  }
  def problem1(): Unit ={
    def kidsWithCandies(candies: Array[Int], extraCandies: Int): Array[Boolean] = {
      var resArray = ArrayBuffer[Boolean]()

      for(candy <- candies){
        var maxBool = true;
        var current = candy + extraCandies

        for(next<-candies){
          if(next>current){
            maxBool = false;
          }
        }
        resArray += (maxBool)

      }
      return resArray.toArray[Boolean]
    }

    def test1(): Unit ={
      val candies: Array[Int] = Array(2,3,5,1,3)
      val extra = 3
      println(kidsWithCandies(candies, extra).foreach(bl => println(bl)))
    }
    test1()
  }
}
