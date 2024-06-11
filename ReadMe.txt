Game Bundle

This project is a collection of 3 game variants.

------------------------------------------------------------------------------

Compiling/Execution of Source code in command prompt:

1. Navigate to the SourceCode, type "cmd" in the address bar and press ENTER to open the command prompt directed to the folder.

2. Then, type the following command in the cmd to compile the source code. 

    javac --module-path "GameBundleLib\lib" --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.graphics,poi.ooxml,poi src/*.java -d classes

3. Next, type the following command in the cmd to run the program.

    java --module-path "GameBundleLib\lib" --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.graphics,poi.ooxml,poi  -cp classes GameBundle




