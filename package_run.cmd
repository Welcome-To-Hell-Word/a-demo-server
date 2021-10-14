echo off
chcp 65001
set app=server-1.0.0-Beta.jar
if not exist "jar/" (
md jar
)
cd jar
if exist %app% (
jps -l | findstr %app%
for /f %%a in ('jps -l ^| findstr %app%') do (taskkill -f -pid %%a)
java -jar %app%
) else (
echo please 先运行 package.cmd 生成 %app%
)
pause
