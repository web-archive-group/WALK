import org.warcbase.spark.matchbox._ 
import org.warcbase.spark.rdd.RecordRDD._ 
import org.warcbase.spark.matchbox.{RemoveHTML, RecordLoader, ExtractBoilerpipeText}

/* Full URL */

val ymmfire = 
  RecordLoader.loadArchives("/data/ymmfire/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  .saveAsTextFile("/data/derivatives/fullurls/ymmfire")

/* lil' URL */

val ymmfirelil = 
  RecordLoader.loadArchives("/data/ymmfire/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))) 
  .countItems() 
  .saveAsTextFile("/data/derivatives/urls/ymmfire")

/* linkz */

RecordLoader.loadArchives("/data/ymmfire/*.gz", sc)
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5)
  .saveAsTextFile("/data/derivatives/links/ymmfire")

/* gdf */

val ymmfiregephi = 
  RecordLoader.loadArchives("/data/ymmfire/*.gz", sc) 
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5) 
  
WriteGDF(ymmfiregephi, "/data/derivatives/gephi/ymmfire.gdf")

/* text */

RecordLoader.loadArchives("/data/ymmfire/*.gz", sc)
  .keepValidPages()
  .map(r => (r.getCrawlMonth, r.getDomain, r.getUrl, ExtractBoilerpipeText(r.getContentString)))
  .saveAsTextFile("/data/derivatives/text/ymmfire")

