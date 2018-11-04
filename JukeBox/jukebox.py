import os   # Used to play songs mainly
import glob # Used to easily find all mp3s in a file
import sys  # Used to exit the program
import random   # Needed to easily generate a random number

# A class to to define what a song is
# We don't need getters and setters because in python class members are public
class Song:

    def __init__(self, artist, songName, length, path):
        self.artist = artist
        self.songName = songName
        self.length = length
        self.songPath = path


# Method that I use to initalize the song list and create a dictionary to hold all of the songs that will be used
def updateSongDict(songDict):
    songList = glob.glob(r"C:\Users\Lawrence\Desktop\Python_Workspace\JukeBox\Music\*.mp3") # Please change the path for where ever your mp3s are located

    for song in songList:
        index = song.find("\\")
        songName = song[index + 1:-4]
        try:
            exists = songDict[songName]
        except KeyError:
            newSong = Song("", songName, -1, song)
            songDict[songName] = newSong

# A method that checks the user's input to make sure that they are selecting a valid option
def readInput(maxRange):
    convert = str(maxRange - 1) 
    errorString = "Please select an option between 1 - " + convert
    while True:
        try:
            userInput = int(input("\nChoice an option: "))
            if userInput > 0 and userInput < maxRange:  # Checking if the option the user picked was valid
                return userInput
            else:
                print(errorString)  # Error if the user did not choose a valid option
        except ValueError:
            print(errorString)  # Error if the user did not pick a valid option

# Method to print a the menu that gives the user thier options
def printMenu():
    print("\n1 : Play a song by choice")
    print("2 : Play a random song")
    print("3 : Look at a list of all songs")
    print("4 : Refresh song list")
    print("5 : Remove a song")
    print("6 : Quit\n")

# Method use to play a specified song by the user, also used to play a random song
def playThisSong(song):
    print("Currently playing", song.songName)
    os.startfile(song.songPath) # This line specifically plays the music

# Method that prints the list of the songs, the information is gathered form the file
def printSongList(songDict):
    songList = list(songDict.keys())    # A list to hold the songs
    if len(songList) == 0:  # In case thier are no songs in the directory
        print("(There are no songs in the directory)")
        return None
    count = 1
    for i in songList:  # Loops printing a numbered list to the user of each song found in the directory
        print(count, i)
        count = count + 1
    return songList

if __name__ == "__main__":  # In case this is imported as a module
    songDict = dict()   # Initialize the dictionary
    updateSongDict(songDict)    # Add the songs to the dictionary

    while True:
        printMenu()
        userInput = readInput(7)    # Take in user choice for menu
        if (userInput == 1):    # User wants to play a specific song
            songList = printSongList(songDict)
            if (songList != None):
                userChoice = readInput(len(songList) + 1)   # Take in which song the user wants to play
                currentSong = songDict[songList[userChoice - 1]]    # Make current song the user's choice
                playThisSong(currentSong)   # Play the song choice
        elif userInput == 2:    # User wants to play a random song
            songList = list(songDict.keys())
            if len(songList) == 0:
                continue
            index = random.randint(0, len(songList) - 1) # Select a random song
            playThisSong(songDict[songList[index - 1]]) # Play the randomly selected song
        elif userInput == 3:    # User wants to see a list of songs
            songList = printSongList(songDict)  # Calls my print method
        elif userInput == 4:    # The user wants to refresh the song list, most likely because they deleted a song
            updateSongDict(songDict)    # update the song dictionary
        elif userInput == 5:    # The user wants to remove a song
            songList = printSongList(songDict)  # Print the list of songs currently in the dictionary
            if (songList != None):
                userChoice = readInput(len(songList) + 1)   # Take in the song that the user wants to remove
                currentSong = songDict[songList[userChoice - 1]]    # Set the current song to the user choice
                if currentSong.songName in songDict:    # Check to see if the song the user picked is in the dictionary
                    del songDict[currentSong.songName]  # Delete the song
                    os.remove(currentSong.songPath)     # Delete the song from the folder
        elif userInput == 6:    # The user wants to quit the program
            print("Bye!")
            sys.exit(0) # Call system to close the program