# Jira-Login-Add-Work-Log
This project add work log to jira:

It uses selenium so you have to first download selenium. For ubuntu users in resources file there is 2 ready .sh file. Ubuntu users first should run [install.sh](https://github.com/ilkerbfl/Jira-Login-Add-Work-Log/blob/master/src/main/resources/install_selenium.sh)  then [selenium.sh](https://github.com/ilkerbfl/Jira-Login-Add-Work-Log/blob/master/src/main/resources/start_chrome_for_selenium.sh).

There is also one crontab example. I use ubuntu crontabs for daily running this jar by this. you should locate this [crontab.sh](https://github.com/ilkerbfl/Jira-Login-Add-Work-Log/blob/master/src/main/resources/crontab.sh) to your computer then from terminal type crontab -e , open it with nano or vim and add --->>   15 16 * * *  /path/to/crontab/sh . With this setting it will run every day at 16:15 so if you want to change the time which jar runs you must make necessary changes

![](https://github.com/ilkerbfl/Jira-Login-Add-Work-Log/blob/master/src/main/resources/work-log.gif)
