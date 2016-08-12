# Comparing Web Archival Collections with Count

Code primarily developed by [Ryan Deschamps](https://uwaterloo.ca/web-archive-group/people-profiles/ryan-deschamps), a research assistant with the [Web Archives for Historial Research Group(https://uwaterloo.ca/web-archive-group/), in collaboration with [Ian Milligan](https://ianmilligan.ca/).

This is a rough draft of some documentation (we're using this for scholarly research right now, so it'll change as we actually do test cases and beyond). Between this and the well-commented Jupyter notebook file, you should be good. If not, please feel free to open an issue. 

What this allows you to do is take collections and see the overlap between them: what domains do they share, what ones do not. If you use more than three collections, rather than the Venn diagram it generates a correspondence analysis. Our goal is that this might help you in collections development, or just plain ol' figuring out what you have in a collection. 

![Screenshot 1](https://raw.githubusercontent.com/web-archive-group/WAHR/master/images/Compare1.png)

![Screenshot 2](https://raw.githubusercontent.com/web-archive-group/WAHR/master/images/Compare2.png)

![Screenshot 3](https://raw.githubusercontent.com/web-archive-group/WAHR/master/images/Compare3.png)

## Installation

Clone this repo, or simply [download the Jupyter notebook found here](https://github.com/web-archive-group/WALK/tree/master/Scripts/compare_collection). 

This was tested using [anaconda](https://anaconda.org/). Base packages are installed, but you will need to install `mca` and `matplotlib_venn`. On OS X, we used `pip`:

```bash
pip install mca
pip install matplotlib_venn
```

## Generating Input Data

This script currently compares collections by taking their domain coverage and finding overlaps and differences. You will need data. 

By default, the script takes output from [warcbase](http://warcbase.org/). Documentation can be found [here](http://docs.warcbase.org/). We generated domains using a script like so:

```scala
import org.warcbase.spark.matchbox._ 
import org.warcbase.spark.rdd.RecordRDD._ 

val elxn42 = 
  RecordLoader.loadArchives("/mnt/vol1/data_sets/cpp/cpp_warcs_accession_02/*201511*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))) 
  .countItems() 
  .saveAsTextFile("/mnt/vol1/derivative_data/cpp-counted-domains-month-201511")
```

Results will come in a series of `part` files. Join them together:

```bash
cat part* > domains-counted-201511.txt 
```

## Generating Concordances

Open the Jupyter notebook and run the notebook. You should just need to change the following line to point to the directory that has the txt files you generated in the above step. 

```python
#establish the data folder
path = "/Users/ianmilligan1/dropbox/git/WALK/Data/Domains/"
```

For NER overlap, there is another line you will need to change:

```python
#establish the data folder
loc_path = "../../NER/"
```

Change this to the directory with your NER output.

Everything else should be described in comments.

## Acknowlegements

Support for this project comes from an [Ontario Ministry of Research and Innovation Early Researcher Award](https://uwaterloo.ca/web-archive-group/news/ontario-early-researcher-award-announced) and a [Compute Canada Research Platforms and Portals award](https://uwaterloo.ca/web-archive-group/news/compute-canada-grant-web-archives-longitudinal-knowledge).
