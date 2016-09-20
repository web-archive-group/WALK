import org.warcbase.spark.matchbox._ 
import org.warcbase.spark.rdd.RecordRDD._ 
import org.warcbase.spark.matchbox.{RemoveHTML, RecordLoader, ExtractBoilerpipeText}

val labour = 
  RecordLoader.loadArchives("/data/TORONTO_canadian_labour_unions/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))) 
  .countItems() 
  .saveAsTextFile("/data/derivatives/urls/TORONTO_canadian_labour_unions")
