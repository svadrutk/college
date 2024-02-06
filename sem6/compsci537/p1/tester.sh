cd /Users/arambam/Documents/college/sem6/compsci537/p1/
gcc -g -Wall -Werror MADCounter.c -o MADCounter
# echo '-------------------------------'
# echo 'Should print USAGE: flag'
# ./MADCounter # should print USAGE: flag
# echo '-------------------------------'
# echo 'Should have no errors'
# ./MADCounter -f welcome.txt -o output.txt # should have no errors
# echo '-------------------------------'
# echo 'Should have invalid flag error'
# ./MADCounter -f welcome.txt -o output.txt -m # should have invalid flag error
# echo '-------------------------------'
# echo "Can't open batch file error"
# ./MADCounter -B error.txt
# echo '-------------------------------'
# echo 'Empty batch file error'
# ./MADCounter -B empty.txt
# echo '-------------------------------'
# echo 'No input file provided' 
# ./MADCounter -f -o output.txt # should have no input file provided error
# ./MADCounter -o output.txt # should have no input file provided error
# echo '-------------------------------'
# echo "Can't open input file error"
# ./MADCounter -f error.txt -o output.txt # should have can't open input file error
# echo '-------------------------------'
# echo 'Should have output file error'
# ./MADCounter -f welcome.txt -o -output.txt # should have no output file error
# echo '-------------------------------'
# echo 'Input file empty' 
# ./MADCounter -f empty.txt -o output.txt -LMNop # should have invalid flag error
# ./MADCounter -f empty.txt -o output.txt 

./MADCounter -f welcome.txt -o output.txt -c -w -l -Lw -Ll # should have no errors




