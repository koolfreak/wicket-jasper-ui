# wicket-jasper-core #

In this branch http://wicket-jasper-ui.googlecode.com/svn/branches/wicket-jasper-core were file virtualizer implemented. Instead storing datas in memory, datas stored in tmp files.

configuration before running this wicket-jasper-core branch version:
  * set java property by adding the specific directory where the tmp files to be stored:
> > -Dorg.jasper.cache.dir=c:\jaspercahce or /home/{user.dir}/jaspercache (note: directory should have write & read permission)
  * if no property set it will create at {user.home}/jasperCache

additional wicket component added: JRExportLinkPanel

```
public JRExportLinkPanel(String id,boolean hasPdflink,boolean hasCsvlink,boolean hasImagelink,
							 boolean hasRtflink,boolean hasXlslink,boolean hasTxtlink) {
  .............
}
```