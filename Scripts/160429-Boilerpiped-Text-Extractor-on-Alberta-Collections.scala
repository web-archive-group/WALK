import org.warcbase.spark.matchbox.{RemoveHTML, RecordLoader, ExtractBoilerpipeText}
import org.warcbase.spark.rdd.RecordRDD._

RecordLoader.loadArchives("/data/prarie_provinces/*.gz", sc)
  .keepValidPages()
  .map(r => (r.getCrawlMonth, r.getDomain, r.getUrl, ExtractBoilerpipeText(r.getContentString)))
  .saveAsTextFile("/data/derivatives/text/prarie_provinces")

RecordLoader.loadArchives("/data/web_archive_general/*.gz", sc)
  .keepValidPages()
  .map(r => (r.getCrawlMonth, r.getDomain, r.getUrl, ExtractBoilerpipeText(r.getContentString)))
  .saveAsTextFile("/data/derivatives/text/web_archive_general")