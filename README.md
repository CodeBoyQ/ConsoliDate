# ConsoliDate

## Summary
This application groups all the photos and videos in a given folder into subfolders with a [yyyyMMdd] timestamp. 
It can be used to easily arrange photos and videos by date. It uses the Exif original date if possible, otherwise
it uses the filename. Note: Photos or videos created between midnight and 7 o' clock in the morning are still 
part of the previous date. The application can be installed as a MAcOSX Automator service. See below for more information.


## Requirements

macOS High Sierra or later

## Build

Maven build

## Installation

 * Go to the `dist/ConsoliDateSevice` folder
 * Copy the `ConsoliDateSevice.workflow` to `/Users/<User>/Library/Services` (Shift + Command + . to see hidden files)
 * Copy the `_ConsoliDateBinaries` folder to `/Applications`
 * Open `Finder`
 * In the upper menu, choose: `Finder > Services > Services Preferences`
 * Choose `Services` in the left menu and make sure you see the ConsoliDateService
 * Now you can right-click on a folder and choose Services > ConsoliDateService from the context-menu to let the magic happen! :-)
