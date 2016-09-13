# Scripts

These scripts are the warcbase processing scripts. They are currently designed to run on the WALK server, but they would work elsewhere (you would need to change paths in both files).

`template.scala` is the scala template, can be modified based on use. Right now, it generates the following derivatives:
- domains by count;
- URL dump;
- plain text, minus boilerplate;
- link diagram;
- link diagram in Gephi format.

`warcbase-to-process.sh` is what you use to call the template, modify it based on collection. Usage is as follows:

```
sh warcbase-to-process.sh TORONTO_Snowden_Archive
```

Would find the data in `/data/TORONTO_Snowden_Archive`, ingest it, and generate derivatives in the `/data/derivatives` directories. 

Part files are combined but remain. They should be manually deleted on a regular basis.
