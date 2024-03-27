# By Thomas Truong
# Quickly compiles every .java file in the src folder,
# and places them into the bin folder.

# All the functions to run.
all: Files

# Files function, uses all .java files in src.
Files: $(wildcard ../src/*.java)
	javac -d ../bin/ $^

# Cleans up every class file in bin.
clean:
	rm -rf ../bin/*.class

# Runs the program with the given data file.
run:
	make clean && make && java -cp ../bin/ AnagramDetector ../data/words.txt

# Runs the program with the small data file.
small:
	make clean && make && java -cp ../bin/ AnagramDetector ../data/small.txt
