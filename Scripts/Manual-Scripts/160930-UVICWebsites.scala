import org.warcbase.spark.matchbox._ 
import org.warcbase.spark.rdd.RecordRDD._ 
import org.warcbase.spark.matchbox.{RemoveHTML, RecordLoader, ExtractBoilerpipeText}
import StringUtils._

/* linkz */

RecordLoader.loadArchives("/mnt/vol1/data_sets/uvic_websites/*.gz", sc)
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).removePrefixWWW(), ExtractDomain(f._2).removePrefixWWW())))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5)
  .saveAsTextFile("/mnt/vol1/data_sets/uvic_websites/links")

/* gdf */

val gephiu = 
  RecordLoader.loadArchives("/mnt/vol1/data_sets/uvic_websites/*.gz", sc) 
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).removePrefixWWW(), ExtractDomain(f._2).removePrefixWWW())))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5) 
  
WriteGDF(gephiu, "/mnt/vol1/data_sets/uvic_websites/UVIC_University_of_Victoria_Websites.gdf")