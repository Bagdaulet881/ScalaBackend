import java.util

class CheckClass {
  var branch = ""
  var BIN = ""
  var vatSeries = ""
  var room = ""
  var cashBox = ""
  var shift = ""
  var orderNumberOfCheck = ""
  var check = ""
  var cashierPharmacy = ""
//ПРОДАЖА
  var sale:java.util.List[SaleClass] = new java.util.ArrayList[SaleClass]()
//
  var bankCard = ""
  var total = ""
  var vat = "0.00" //в т.ч. НДС 12%:
//ФИСКАЛЬНЫЙ ЧЕК
  var fiscalCheck = new FiscalClass

//1-9
  def setDataBeforeSale(branch:String,BIN:String,vatSeries:String,room:String,
                        cashBox:String,shift:String,orderNumberOfCheck:String,check:String,cashierPharmacy:String): Unit ={
    this.branch = branch
    this.BIN = BIN
    this.vatSeries = vatSeries
    this.room = room
    this.cashBox = cashBox
    this.shift = shift
    this.orderNumberOfCheck = orderNumberOfCheck
    this.check = check
    this.cashierPharmacy = cashierPharmacy
  }
//  10-17
  def setDataAfterSale(bankCard:String,total:String,vat:String): Unit ={
    this.bankCard = bankCard
    this.total = total
    this.vat = vat

  }

  override def toString: String = {
    val res = branch + "\n" + "" +
      BIN + "\n" + "" +
      vatSeries + "\n" + "" +
      room + "\n" + "" +
      cashBox + "" +
      shift + "\n" + "" +
      orderNumberOfCheck + "\n" + "" +
      check + "\n" + "" +
      cashierPharmacy + "" +
      sale.toString() + "\n" + "" +
      bankCard + "\n" + "" +
      total + "\n" + "" +
      vat + "" + "\n" +
      fiscalCheck.toString
    res
  }
}
