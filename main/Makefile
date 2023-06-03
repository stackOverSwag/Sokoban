JFLAGS = -g

JC = javac



.SUFFIXES: .java .class



.java.class:

	$(JC) $(JFLAGS) $*.java



CLASSES = Mur.java Boite.java Chemin.java Cible.java Joueur.java Matrice.java Graphic.java KeyHandler.java Direction.java Main.java DirectoryReader.java



default: classes



classes: $(CLASSES:.java=.class)



clean:

	$(RM) *.class
