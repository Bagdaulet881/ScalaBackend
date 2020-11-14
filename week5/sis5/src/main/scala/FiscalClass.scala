class FiscalClass {
  var fiscalSign = ""
  var time = ""
  var location = ""
  var fiscalDataOperator = ""
  var checkInfo = ""
  var fiscalCheck = "Фискальный чек"
  var fiscalType = "ФП"
  var INK_OFD = ""
  var code = ""
  var ZNM = ""
  var cashUrl = ""

  def setFiscalData(fiscalSign:String,time:String,
                    location:String,fiscalDataOperator:String,checkInfo:String,fiscalCheck:String, fiscalType:String,INK_OFD:String,code:String,
                    ZNM:String,cashUrl:String): Unit ={
    this.fiscalSign = fiscalSign
    this.time = time
    this.location = location
    this.fiscalDataOperator = fiscalDataOperator
    this.checkInfo = checkInfo
    this.fiscalCheck = fiscalCheck
    this.fiscalType = fiscalType
    this.INK_OFD = INK_OFD
    this.code = code
    this.ZNM = ZNM
    this.cashUrl = cashUrl
  }

  override def toString: String = {
    val res =
      fiscalSign + "\n" + "" +
        time + "\n" + "" +
        location + "\n" + "" +
        fiscalDataOperator + "\n" + "" +
        checkInfo + "\n" +
        fiscalCheck + "\n" +
      fiscalType + "\n" + "" +
      INK_OFD + "\n" + "" +
      code + "\n" + "" +
      ZNM + "\n" + "" +
      cashUrl + ""
    res
  }
}
