run: compile
	java Main

compile: clean
	javac *.java

clean:
	rm -f *~
	rm -f *#
	rm -f *.class