// run with command
// cscript add-change.js
var installer = WScript.CreateObject("WindowsInstaller.Installer");
var database = installer.OpenDatabase("${app.name}-${app.version}.msi", 1);
var sql
var view

var file = FindFileIdentifier(database, "${app.name}.exe");

try {
    sql = "INSERT INTO `CustomAction` (`Action`,`Type`,`Source`) VALUES ('ExecuteAfterFinalize','2258','" + file + "')"
    WScript.StdErr.WriteLine(sql);
    view = database.OpenView(sql);
    view.Execute();
    view.Close();

    sql = "INSERT INTO `InstallExecuteSequence` (`Action`,`Condition`,`Sequence`) VALUES ('ExecuteAfterFinalize','NOT Installed','6700')"
    WScript.StdErr.WriteLine(sql);
    view = database.OpenView(sql);
    view.Execute();
    view.Close();
    WScript.StdErr.WriteLine("Committing changes");
    database.Commit();
} catch (e) {
    WScript.StdErr.WriteLine(e);
    WScript.Quit(1);
}
