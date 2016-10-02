import org.warcbase.spark.matchbox._ 
import org.warcbase.spark.rdd.RecordRDD._ 
import org.warcbase.spark.matchbox.{RemoveHTML, RecordLoader, ExtractBoilerpipeText}

/* linkz */

RecordLoader.loadArchives("/data/UVIC_University_of_Victoria_Websites/*.gz", sc)
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5)
  .saveAsTextFile("/data/derivatives/links/UVIC_University_of_Victoria_Websites")

/* gdf */

val gephiu = 
  RecordLoader.loadArchives("/data/UVIC_University_of_Victoria_Websites/*.gz", sc) 
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5) 
  
WriteGDF(gephiu, "/data/derivatives/gephi/UVIC_University_of_Victoria_Websites.gdf")
