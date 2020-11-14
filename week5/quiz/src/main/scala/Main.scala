import java.util
class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}
object Main extends App{
  def task5(): Unit ={
    def subarraySum(nums: Array[Int], k: Int): Int = {
      var cnt = 0
      for(i<- 0 to nums.length-1){
        var sum = 0
        for(j <- i to nums.length-1){
            sum += nums(j)
            if(sum==k)cnt+=1
        }
      }
      cnt
    }
  }
  def task4(): Unit ={
    def longestUnivaluePath(root: TreeNode): Int = {
      var res = 0
      repeat(root)

      def repeat(root: TreeNode): Int = {
        if (root != null) {
          var left = repeat(root.left)
          if ((root.left != null) && root.left.value == root.value) {
            left = left + 1
          } else {
            left = 0
          }

          var right = repeat(root.right)
          if ((root.right != null) && root.right.value == root.value) {
            right = right + 1
          } else {
            right = 0
          }
          res = Math.max(res, left + right)
          Math.max(left,right)
        }else 0

      }
      res
    }
  }
  def task3(): Unit ={
    def minDiffInBST(root: TreeNode): Int = {
      if(root == null) return 0
      var min = Int.MaxValue
      var list = new util.ArrayList[Int]()
      repeat(root,list)
      for(i<-0 to list.size()-2){
        min = Math.min(min,list.get(i+1)-list.get(i))
      }
      min
    }
    def repeat(root2: TreeNode, list: util.ArrayList[Int]): Unit ={
      if(root2 == null) return
      list.add(root2.value)
      repeat(root2.left,list)
      repeat(root2.right,list)

    }
  }
  def task2(): Unit = {
    def tribonacci(n: Int): Int = {
      var t0 = 0
      var t1 = 1
      var t2 = 1
      if (n == 0) return t0
      if (n <= 2) return t1
      for (i <- 3 to n) {
        var temp = t0 + t1 + t2
        t0 = t1
        t1 = t2
        t2 = temp
      }
      t2
    }
  }
  def task1(): Unit ={

    var sum=0
    def rangeSumBST(root: TreeNode, L: Int, R: Int): Int = {
      repeat(root)

      def repeat(root: TreeNode): Unit = {
        if (root != null){
          if((root.value<=R)&&(root.value>=L)){
            sum+=root.value
          }
          if(root.value>L){
            repeat(root.left)
          }
          if(root.value<R){
            repeat(root.right)
          }
        }
      }
      sum
    }
  }
}
