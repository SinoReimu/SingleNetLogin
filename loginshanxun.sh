#!/bin/bash


getchar() {
    stty cbreak -echo
    dd if=/dev/tty bs=1 count=1 2> /dev/null
    stty -cbreak echo
}
attrget()  
{  
        ATTR_PAIR=${1#*$2=\"}  
        echo "${ATTR_PAIR%%\"*}"  
}  
ShowLoginUrl="http://115.239.134.163:8080/showlogin.do"

echo "UserName:"
read UserName
echo "PassWord:"
while : ; do
    ret=`getchar`
    if [ x$ret =  x ]; then
        echo
        break
    fi
    PassWord="$PassWord$ret"
done
EPass=$(java EncryptPassword PassWord)
ipaddress=$(sudo ifconfig wlan0|grep "inet addr"|cut -d: -f2|awk '{print $1}')
result1=$(curl -H "User-Agent:China Telecom Client" -d "wlanuserip=$ipaddress" $ShowLoginUrl)
echo "[Handling Return content]"
UserIP=$(echo $result1|awk -vFS="<UserIP>" '{print $2}'|awk -vFS="</UserIP>" '{print $1}')
Uuid=$(echo $result1|awk -vFS="<Uuid>" '{print $2}'|awk -vFS="</Uuid>" '{print $1}')
LoginURL=$(echo $result1|awk -vFS="<LoginURL>" '{print $2}'|awk -vFS="</LoginURL>" '{print $1}')
if [[ x$UserIP != x$ipaddress ]]||[[ x$Uuid = x ]]||[[ x$LoginURL == x ]]; then
	echo returnValueError , Please Login Again Later.
	exit 1
fi
echo UserName is $UserName , IpAddress is $ipaddress Logining
result2=$(curl -H "User-Agent:China Telecom Client" -d \
	"username=$UserName\
	&&password=$EPass\
	&&userip=$ipaddress\
	&&ratingtype=1\
	&&uuid=$Uuid"\
	 $LoginURL)
resp=$(echo $result2|awk -vFS="<ResponseCode>" '{print $2}'|awk -vFS="</ResponseCode>" '{print $1}')
if [ x$resp != x"200" ]; then
	echo Login Success
	exit 0
fi
    echo Login Finish please Check you netWork Statue
exit 1