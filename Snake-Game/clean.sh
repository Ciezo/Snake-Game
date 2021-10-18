msg="$ Warning!!! Doing this is only recommended if there are errors in the .class files found in bin"
msg2="$ Proceed with caution!!!"
msg3="$****Running command****"

echo -e "\n" 

echo "$msg"
echo "$msg2"
echo -e "\n" 
echo "$msg3"

echo "Redirecting to bin" 
cd bin
echo "Cleaning bin" 
rm *.class

msg4="$P Please, recompile and build using compile.sh found in /src/"
