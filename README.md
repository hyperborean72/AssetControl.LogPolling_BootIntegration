# AssetControl.LogPolling_BootIntegration

Probation project for Asset Control, Netherlands

TECHOLOGIES USED
In addition to Spring DI project employs following Spring modules: MVC, Boot, Integration
Periodic polling is implemented by Poller class from the Integration
Front-end is built with Apache FreeMarker and some Javascript and jQuery for the view to be consistent with the updates of the backend model

GOAL OF THE PROJECT
"The test requires you to write a simple application with Java back-end and HTML5 user
interface. You may use any IDE, library, framework or any other tooling you desire.
It is not a test of how quickly you can put the program together, but rather to gauge the
way in which you would address the problem.

Web interface does not require any authentication.
We expect you to deliver fully working and deployable package (possibly with build, setup
and deployment instructions).

Test
Assume there is a service running on a back end that produces some log file in format:
<datetime> <severity> <some message>
Example:
2016-09-20 16:23:10,994 INFO Some info message
2016-09-20 16:23:11,994 INFO Some other info message
2016-09-20 16:23:12,994 WARNING Some warning message
2016-09-20 16:23:13,994 WARNING Some other warning message
2016-09-20 16:23:14,994 ERROR Some error message

Your task is to write a simple dashboard with one simple widget. Application should
monitor the changes to a given file and display distinct number of ERROR, WARNING and
INFO messages for a last number of seconds (so called monitoring interval).

Considering example given above with monitoring interval equals to 5 seconds (back from
current system time):
given system time: 2016-09-20 16:23:15,994
the following is expected to be displayed
INFO: 1
WARNING: 2
ERROR: 1

given system time: 2016-09-20 16:23:16,994
the following is expected to be displayed
INFO: 0
WARNING: 2
ERROR: 1

Please be aware that new records are constantly being added to the log file. Widget should
display actual information without manual page reload.
The application has to have a property file with at least two configuration records:
• default monitoring interval in seconds
• path to the monitoring file"
