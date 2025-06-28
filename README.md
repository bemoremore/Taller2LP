**Para poder correr el analizador sintactico se puede ejecutar de las siguientes maneras:**

Corriendo el main. (Mayús + F10)

O ejecutando el siguiente comando:
> java ./src/Main.java

Y va analizar el contenido del archivo 'programa.cmm' y a su vez generara el siguiente archivo "programa_ast.dot".

Para poder generar directamente la imagen con graphviz si se cuenta con las dependencias de esta:

dot -Tpng programa_ast.dot -o ast.png

Y lo guardara en una imagen .png que se puede visualizar para ver el árbol generado.

**En el caso de que no estén los archivos generados por JavaCC, en el package parser:**

> javacc .\parser\SyntaxChecker.jj

Y en el root del proyecto ósea en el inicio:

> javac *.java

Esto generara un conjunto de archivos para el analizador, luego se debe ejecutar inicialmente el primer comando.
