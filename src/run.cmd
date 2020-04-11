@echo off

REM Use the following line to set a proxy server
set PROXY_SERVER=proxy.domain.com

REM Uncomment the following two lines to use a proxy or to debug grape downloads
REM set PROXY_OPTS=-Dhttp.proxyHost=%PROXY_SERVER% -Dhttp.proxyPort=80 -Dhttps.proxyHost=%PROXY_SERVER% -Dhttps.proxyPort=80
REM set GRAPE_OPTS=-Dgroovy.grape.report.downloads=true

set JAVA_OPTS=%PROXY_OPTS% %GRAPE_OPTS%

REM -cp . puts the current directory on the classpath
groovy -cp . myscript.groovy %*

timeout 5
