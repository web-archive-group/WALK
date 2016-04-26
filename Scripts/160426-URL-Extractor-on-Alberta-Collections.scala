/* now running on errorlogs branch of warcbase */
/* BROKEN WARC FILE IN GOVERNMENT_INFORMATION - logging enabled */

import org.warcbase.spark.matchbox._ 
import org.warcbase.spark.rdd.RecordRDD._ 

val university_of_alberta_websites = 
  RecordLoader.loadArchives("/data/university_of_alberta_websites/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))) 
  .countItems() 
  .saveAsTextFile("/data/derivatives/urls/university_of_alberta_websites")


val web_archive_general = 
  RecordLoader.loadArchives("/data/web_archive_general/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))) 
  .countItems() 
  .saveAsTextFile("/data/derivatives/urls/web_archive_general")
