@echo off
setlocal

REM Use the following lines to set a proxy server
REM set PROXY=www-proxy.domain.com
REM set PROXY_OPTS=-Dhttp.proxyHost=%PROXY% -Dhttp.proxyPort=80 -Dhttps.proxyHost=%PROXY% -Dhttps.proxyPort=80
REM set NONPROXY_OPTS=-Dhttp.nonProxyHosts="localhost|127.0.0.1|*.domain.com"

REM Uncomment the following line to debug grape downloads
REM set GRAPE_OPTS=-Dgroovy.grape.report.downloads=true

set JAVA_OPTS=%PROXY_OPTS% %NONPROXY_OPTS% %GRAPE_OPTS%

REM -cp . puts the current directory on the classpath
groovy -cp . myscript.groovy %*

REM Pause for 5 seconds to you have a chance to see any error messages before the window closes
timeout 5
