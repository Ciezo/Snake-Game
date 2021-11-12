echo -e "Compiling Main class with main()"
javac -d ../bin Main.java

echo -e "Compiling SnakeProto class with main()"
javac -d ../bin SnakeProto.java

echo -e "Setting classpath"
javac -cp ../bin Main.java

# 1
echo -e "Compiling *SnakeGame* class AND setting classpath to bin directory"
javac -cp ../bin SnakeGame.java

# 2 
echo -e "Compiling *EventMaster* class AND setting classpath to bin directory"
javac -cp ../bin EventMaster.java

# 3
echo -e "Compiling *Snake* class AND setting classpath to bin directory"
javac -cp ../bin Snake.java 

# 4
echo -e "Compiling *SnakeProto* class AND setting classpath to bin directory"
javac -cp ../bin SnakeProto.java

# 5
echo -e "Compiling *Worker* class AND setting classpath to bin directory"
javac -cp ../bin Worker.java

# 6
echo -e "Compiling *Main* class AND setting classpath to bin directory"
javac -cp ../bin Main.java

mv *.class ../bin 

# -------------------------------------------------------------------------
echo -e "Removing unnecessary artifact"
cd ../bin
rm "compile.sh"