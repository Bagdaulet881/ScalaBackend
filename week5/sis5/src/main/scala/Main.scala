import scala.collection.JavaConverters._

object Main extends App {
//  http://jsonviewer.stack.hu/
  var checkData = new CheckClass

  var dataProcessing = new DataProcessing
  var processedDataSet = dataProcessing.processing()

  checkData.setDataBeforeSale(processedDataSet(1),processedDataSet(2),processedDataSet(3),processedDataSet(4),
    processedDataSet(5),processedDataSet(6),processedDataSet(7),processedDataSet(8),processedDataSet(9))

  checkData.setDataAfterSale(processedDataSet(10),processedDataSet(11),processedDataSet(12))
  checkData.fiscalCheck.setFiscalData(processedDataSet(13),
    processedDataSet(14),processedDataSet(15),processedDataSet(16),processedDataSet(17),processedDataSet(18),processedDataSet(19),processedDataSet(20),processedDataSet(21),processedDataSet(22),processedDataSet(23))

  checkData.sale = dataProcessing.saleList.asJava

  //RESULT
  dataProcessing.writeToFile(dataProcessing.toJson(checkData))
}
