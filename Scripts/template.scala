import org.warcbase.spark.matchbox._ 
import org.warcbase.spark.rdd.RecordRDD._ 
import org.warcbase.spark.matchbox.{RemoveHTML, RecordLoader, ExtractBoilerpipeText}
val ${COLLECTION} = RecordLoader.loadArchives("/data/${COLLECTION}/*.gz", sc).keepValidPages().map(r => (r.getCrawlMonth, r.getUrl)).saveAsTextFile("/data/derivatives/fullurls/${COLLECTION}")
val ${COLLECTION} = RecordLoader.loadArchives("/data/${COLLECTION}/*.gz", sc).keepValidPages().map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))).countItems().saveAsTextFile("/data/derivatives/urls/${COLLECTION}")
RecordLoader.loadArchives("/data/${COLLECTION}/*.gz", sc).keepValidPages().map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString))).flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", "")))).filter(r => r._2 != "" && r._3 != "").countItems().filter(r => r._2 > 5).saveAsTextFile("/data/derivatives/links/${COLLECTION}")
val ${COLLECTION}gephi = RecordLoader.loadArchives("/data/${COLLECTION}/*.gz", sc) .keepValidPages().map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString))).flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", "")))).filter(r => r._2 != "" && r._3 != "").countItems().filter(r => r._2 > 5).WriteGDF(${COLLECTION}gephi, "/data/derivatives/gephi/${COLLECTION}.gdf")
RecordLoader.loadArchives("/data/${COLLECTION}/*.gz", sc).keepValidPages().map(r => (r.getCrawlMonth, r.getDomain, r.getUrl, ExtractBoilerpipeText(r.getContentString))).saveAsTextFile("/data/derivatives/text/${COLLECTION}")
exit 

