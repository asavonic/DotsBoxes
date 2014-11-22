import os
import argparse
import subprocess as proc

parser = argparse.ArgumentParser(description="Launches multiple DotsBoxes instances")
parser.add_argument("ports", metavar="PORT", type=int, nargs="+", help="TCP ports to use")

script_dir = os.path.dirname(os.path.realpath(__file__))
classpath = os.path.join(script_dir, "..", "bin")
classpath = os.path.normpath(classpath)

run_cmdline = "java -Dfile.encoding=UTF-8 -classpath {0} dotsboxes.DotsBoxes".format(classpath).split()

args = parser.parse_args()

ports = [ str(port) for port in args.ports ]
processes = []

for port in ports:
    cmd = run_cmdline + [port]
    processes.append( proc.Popen(cmd) )

for process in processes:
    process.wait()
