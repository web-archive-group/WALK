# Gephi Files

The GDF file format is supported by network analysis software, including Gephi. It was created using [warcbase](http://warcbase.org), [specifically this script run on the WALK server](https://github.com/web-archive-group/WALK/blob/master/Scripts/160711-GephiExtractor.scala). An example of what this code looks like:

```scala
import org.warcbase.spark.matchbox._ 
import org.warcbase.spark.rdd.RecordRDD._ 

val alberta_education_curriculum = 
  RecordLoader.loadArchives("/data/alberta_education_curriculum/*.gz", sc) 
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5) 
  
WriteGDF(alberta_education_curriculum, "/data/derivatives/gephi/alberta_education_curriculum.gdf")
```

The above took the Alberta Education Curriculum, found the links between domains, and wrote them as a GDF file.

Here's a brief walkthrough of how to actually use the files using Gephi.

## Step-by-Step Walkthrough

Once you've downloaded the file, open up [Gephi](http://gephi.github.io/).

On the opening screen, you want to select "Open a Graph File..." and select the `all-links-cpp-link.graphml` file that you downloaded from our Dataverse page.

You then want to click 'ok' on the next page. Create a 'new graph.'

![new graph](https://ianmilli.files.wordpress.com/2015/12/screen-shot-2015-12-10-at-5-27-54-pm.png?w=1024)

You should now see what I (nerdily) call a borg cube. That's good, because it means that the data is in there. We need to make it usable, however.

Click on the "Data Laboratory" tab at the top.

Click on "Nodes" above. When it is shaded behind it, that means that it is selected.

Click on "Copy Data to another Column," select ID, and then select "label" on the drop down menu that appears in the following box.

Now click on "Edges" above.

Click on "Merge Columns," select "timeint -- STRING," and move it into the right-hand column by clicking the arrow. Then under "Merge Strategy" select "Create Time Interval." Click OK.

You now have to tell it how to read the data. Click on "Parse Dates" and enter "yyyymmdd." That corresponds to how the data is laid out in the time intervals column! Press OK.

![](https://ianmilli.files.wordpress.com/2015/12/screen-shot-2015-12-10-at-5-30-45-pm.png?w=1024)

Now click on "overview" above, and let's get our diagram going.

First, we need to filter it down. Right now there are too many nodes for most of our computers to make sense of. In the upper right, you'll see a "filter tab." Let's make it so that we get rid of nodes (or domains) that have less than four inbound links.

To do so, under "Topology" find "In Degree" range and drag it down to the filter list below (Queries). Like so:

![](https://ianmilli.files.wordpress.com/2015/12/screen-shot-2015-12-10-at-5-33-18-pm.png?w=187)

In the slider below, move the lower bound to 4. Then click 'Apply.'

![](https://ianmilli.files.wordpress.com/2015/12/screen-shot-2015-12-10-at-5-34-46-pm.png?w=150)

Then, we need to lay it out. Select the layout tab at left, and fill it out as follows.

![](https://ianmilli.files.wordpress.com/2015/12/screen-shot-2015-12-10-at-5-36-07-pm.png?w=242)

**Note: these are just random example values, that will produce a basic graph quickly. I often spend half an hour or even more tweaking these settings</strong>. In this case, I ran it for a few seconds and then hit stop.** You will have to tweak these depending on the speed of your computer. If you screw up, just run a "Random Layout" and bring it back to the starting point.

Click on the following button to zoom your map out to the extant of the graph:

![](https://ianmilli.files.wordpress.com/2015/12/screen-shot-2015-12-10-at-5-37-20-pm.png)

Now we'll want to do some quick tweaks to make the nodes relative to the size of the number of links inbound. We can do that by setting up some "Rankings." In the upper left, click "Rankings." Select "Nodes." Then let's adjust the size of the node. In Gephi, size is noted by a diamond. Do the following:

![](https://ianmilli.files.wordpress.com/2015/12/screen-shot-2015-12-10-at-5-38-32-pm.png?w=300)

Depending on how big your graph has shaken out, you will have to adjust the min and max size accordingly. Luckily, it's pretty quick: just change the values, click 'apply,' and see if you like it.

Now let's do the same with labels. First, turn them on by clicking this button in the bottom of your window.

![](https://ianmilli.files.wordpress.com/2015/12/screen-shot-2015-12-10-at-5-39-58-pm.png)

Now you'll see either no text, or if you're zoomed in enough, you'll see too much!

![](https://ianmilli.files.wordpress.com/2015/12/screen-shot-2015-12-10-at-5-40-30-pm.png?w=300)

Let's do the same for the labels as we did for size. Go back to your "Ranking" and do the following.

![](https://ianmilli.files.wordpress.com/2015/12/screen-shot-2015-12-10-at-5-41-37-pm.png?w=300)

Note that this is similar to above, but you're now adjusting the "label size" - a letter with a diamond in the upper left corner.

Finally, let's move our labels around like so in our "Layout" tab again:

![](https://ianmilli.files.wordpress.com/2015/12/screen-shot-2015-12-10-at-5-41-45-pm.png?w=300)

Your graph should look like:

![](https://ianmilli.files.wordpress.com/2015/12/screen-shot-2015-12-10-at-5-41-55-pm.png?w=1024)

Now you can do some final steps. You can turn it to a black background with this button:

![](https://ianmilli.files.wordpress.com/2015/12/screen-shot-2015-12-10-at-5-43-47-pm.png)

And to run some rudimentary community detection algorithms, click on "Statistics" in the upper right hand column, click the "Run" button next to modularity, and click through the next few windows by clicking OK.

In the upper left, click on "Partition" (it should be next to "Ranking"). Click the refresh button next to the drop-down menu, and then click "Modularity." Click "Apply."

![](https://ianmilli.files.wordpress.com/2015/12/screen-shot-2015-12-10-at-5-44-55-pm.png?w=272)

Voila! Here you are:

![](https://ianmilli.files.wordpress.com/2015/12/screen-shot-2015-12-10-at-5-46-10-pm.png)
