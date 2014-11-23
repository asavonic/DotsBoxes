import os
import argparse
import subprocess as proc

parser = argparse.ArgumentParser(description="Launches multiple DotsBoxes instances")
parser.add_argument("ports", metavar="PORT", type=int, nargs="+", help="TCP ports to use")

script_dir = os.path.dirname(os.path.realpath(__file__))
root_dir = os.path.normpath( os.path.join( script_dir, ".." ) )
classpath = os.path.join(root_dir, "bin")

run_cmdline = "java -Dfile.encoding=UTF-8 -classpath {0} dotsboxes.DotsBoxes".format(classpath).split()

args = parser.parse_args()

ports = [ str(port) for port in args.ports ]


class Player:
    def __init__(self, name, ip, port, hash):
        self.name = name
        self.ip = ip
        self.port = port
        self.hash = hash
        self.known_players = []

    def __str__(self):
        return "{0} {1} {2} {3}".format(self.name, self.ip, self.port, self.hash)

    def __eq__(self, other):
        return ( (self.name, self.ip, self.port, self.hash) == (other.name, other.ip, other.port, other.hash) )

    def connect_to(self, player):
        self.known_players.append(player)


class KnownPlayersConfig:
    def __init__(self, players):
        self.players = players

    def __str__(self):
        players_str = [ str(player) for player in self.players ]
        return "\n".join(players_str)

players = [ Player( "Tester" + str(port), "127.0.0.1", port, "000000" + port ) for port in ports ]

# all to all connection
for player in players:
    for other in players:
        if other != player:
            player.connect_to(other)

processes = []
for player in players:
    cmd = run_cmdline + [player.port]

    known_players_file = open("known_players.conf", "w" )
    conf = KnownPlayersConfig(player.known_players)
    known_players_file.write( str(conf) )

    output_file = open( "{0}.log".format(player.name), "w")
    processes.append( proc.Popen(cmd, stdout=output_file, stderr=output_file) )

    os.remove("known_players.conf")

retcode = 0
for process in processes:
    retcode += process.wait()

if retcode != 0:
    print "FAILED"

exit(retcode)
