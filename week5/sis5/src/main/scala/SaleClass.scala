class SaleClass {
  var id = ""
  var name = ""
  var costCalc = ""
  var totalCost = ""

  def setData(id:String, name: String, costCalc: String, totalCost: String): Unit ={
    this.id = ("""\d+""".r findAllIn id).toList(0)
    this.name = name
    this.costCalc = costCalc
    this.totalCost = totalCost
  }

  override def toString: String = {
    val res = id.toString + "\n" + "" +
      name + "\n" + "" +
      costCalc + "\n" + "" +
      totalCost + "\n" + "" +
      "Стоимость" + "\n" + "" +
      totalCost + ""
    res
  }
}
