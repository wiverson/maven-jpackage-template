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

// Finds file id and component id of file
function FindFileIdentifier(database, fileName) {
    var sql
    var view
    var record

    // First, try to find the exact file name
    sql = "SELECT `File`, `Component_` FROM `File` WHERE `FileName`='" + fileName + "'";
    view = database.OpenView(sql);
    view.Execute();
    record = view.Fetch();
    if (record) {
        var value = record.StringData(1);
        componentId = record.StringData(2)
        view.Close();
        return value;
    }
    view.Close();

    // The file may be in SFN|LFN format.  Look for a filename in this case next
    sql = "SELECT `File`, `Component_`, `FileName` FROM `File`";
    view = database.OpenView(sql);
    view.Execute();
    record = view.Fetch();
    while (record) {
        if (StringEndsWith(record.StringData(3), "|" + fileName)) {
            componentId = record.StringData(2);
            var value = record.StringData(1);
            view.Close();
            return value;
        }

        record = view.Fetch();
    }
    view.Close();

}

function StringEndsWith(str, value) {
    if (str.length < value.length)
        return false;

    return (str.indexOf(value, str.length - value.length) != -1);
}