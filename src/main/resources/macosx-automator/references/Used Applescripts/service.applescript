-- This Applescript is used in the ConsoliDate Automator Service and is only here for reference
on run {input, parameters}

	-- Get the directory of the script
	tell application "Finder"
		set currentDir to POSIX path of (path to applications folder as text)
	end tell

	-- Run the Shell script with the Automator FolderPath variable as parameter
	tell application "Terminal"
		activate
		set folderPath to POSIX path of (item 1 of input)
		do script "cd '" & currentDir & "/_ConsoliDateBinaries' && ./run.sh '" & folderPath & "'"
	end tell

end run