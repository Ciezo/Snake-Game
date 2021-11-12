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
mkdir "release"
echo -e " \t ==> Folder created!"
jar -cvfe release/mmmmmSnekkk_v0.1.jar src/Main bin/Main.class bin/*.class src bin assets sfxpack
jar -cvfe mmmmmSnekkk_v0.1.jar src/Main bin/Main.class bin/*.class src bin assets sfxpack
echo -e "\n \n A copy of generated executable file is in ==>   release folder!"
echo -e "---------------------------------------------------------------------"
echo -e "\n \n Generated generated executable file is in ==>   / folder!"
echo -e "---------------------------------------------------------------------"