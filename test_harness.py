
import itertools
import os.path

from threading import Timer

import subprocess
from subprocess import Popen, PIPE

total_score = 0
total_possible = 0

try:
    st = subprocess.check_output(["javac", "-version"])
except:
    print "Unable to find javac compiler. Make sure it is added to your system path"
    exit(1)
print "Found javac."

#----------------------

#Range search testing
print "\nCompiling code..."
try:
    process = Popen(["javac", "Main.java"], stdout=PIPE, stderr=PIPE)
    st, err = process.communicate()
    
    if len(err) > 0:
        print "The compilation process had the following output:"
        print err
        if " error:" in err:
            print "A compile error was detected when executing javac. Exiting..."
            exit(0)
except:
    "An unknown error occurred invoking javac. Exiting..."
    exit(0)

print "Executing Java code..."

num_tests = 0
num_correct_tests = 0
#Kruskal testing
for test in itertools.count(1):
    file_name = 'tests/test{}.in'.format(test)
    out_file_name = 'tests/test{}.out'.format(test)
    if not os.path.isfile(file_name):
        break #no more tests
    num_tests += 1
    TIMEOUT = 16 #TIMEOUT for this problem
    VERDICT = "ACCEPTED"
    
    st = ""
    err = ""
    
    with open(file_name, 'r') as in_file:
        try:

            process = Popen(["java", "-Xmx512m", "Main"], stdin=in_file, stdout = PIPE, stderr = PIPE)
 
            def on_timer_finish(process):
                process.kill()
                global VERDICT
                VERDICT = "REJECTED, TIMED OUT"
 
            my_timer = Timer(TIMEOUT, on_timer_finish, [process])
 
            try:
                my_timer.start()
                st, err = process.communicate()
            finally:
                my_timer.cancel()
            
        except: 
            print "Java code encountered a runtime error or returned a non-zero exit code."
            VERDICT = "REJECTED, RUNTIME ERROR"
            
        if "Could not find or load main class" in err:
            print "Could not find or load main class. Exiting..."
            exit(1)
        elif "java.lang.OutOfMemoryError:" in err:
            VERDICT = "REJECTED, OUT OF MEMORY"
        elif "Exception in thread" in err:
            VERDICT = "REJECTED, RUNTIME ERROR"
    
    #Load sample correct output:
    correct_output = ""
    with open(out_file_name.format(test), 'r') as sample_file:
        correct_output = sample_file.read()

    st = st.replace('\r', '')
    st = st.replace('\t', ' ')

    #Test Correctness
    if VERDICT == "ACCEPTED":
        lines1 = [x.strip() for x in st.split("\n") if x]
        lines2 = [x.strip() for x in correct_output.split("\n") if x]
        
        if len(lines1) != len(lines2):
            VERDICT = "REJECTED, NUMBER OF NON-EMPTY LINES DIFFERS FROM SOLUTION"
        else:
            for line_num in range(len(lines1)):
                try:
                    nums1 = [int(x) for x in lines1[line_num].split(" ") if x]
                    nums2 = [int(x) for x in lines2[line_num].split(" ") if x]
                    if nums1 != nums2:
                        VERDICT = "REJECTED, WRONG ANSWER"
                        break
                except:
                    VERDICT = "REJECTED, EXPECTED INTEGER AND FOUND NON-INTEGER"
                    break
    if VERDICT == "ACCEPTED":
        num_correct_tests += 1
    print "Test {} verdict: {}".format(test, VERDICT)
print "Score: {} / {}".format(num_correct_tests * 1, num_tests * 1)
if num_correct_tests == num_tests:
    print "All tests passed."
else:
    print "Code failed one or more tests."
