JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	MatrixOps.java\
	RandomGraph.java \
	strassen.java

classes: $(CLASSES:.java=.class)

default: classes

clean:
	$(RM) *.class