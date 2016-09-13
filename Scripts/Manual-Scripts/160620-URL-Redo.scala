import org.warcbase.spark.matchbox._ 
import org.warcbase.spark.rdd.RecordRDD._ 

val circumpolar = 
  RecordLoader.loadArchives("/data/circumpolar/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))) 
  .countItems() 
  .saveAsTextFile("/data/derivatives/urls/circumpolar")

val cpp = 
  RecordLoader.loadArchives("/data/cpp/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))) 
  .countItems() 
  .saveAsTextFile("/data/derivatives/urls/cpp")

val government_information = 
  RecordLoader.loadArchives("/data/government_information/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))) 
  .countItems() 
  .saveAsTextFile("/data/derivatives/urls/government_information")

val panamapapers = 
  RecordLoader.loadArchives("/data/panamapapers/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))) 
  .countItems() 
  .saveAsTextFile("/data/derivatives/urls/panamapapers")

val university_of_alberta_websites = 
  RecordLoader.loadArchives("/data/university_of_alberta_websites/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))) 
  .countItems() 
  .saveAsTextFile("/data/derivatives/urls/university_of_alberta_websites")
