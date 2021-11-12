echo -e "**** Redirecting to src ****"
cd src/
echo -e "**** Recompiling source files ****"
sh ./compile.sh
echo -e "**** Redirecting to / ****"
cd ../

echo -e "\n \n"
echo -e "██████████████████████████████████████████████████████████████████"
echo -e "Attempting to build an executable file.    ::      Format ==> .jar"
echo -e "██████████████████████████████████████████████████████████████████"
jar -cvfe mmmmmSnekkk_v0.1.jar src/Main.java src bin bin/Main*.class assets sfxpack 