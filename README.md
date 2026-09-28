Intro:
=======
Welcome to DragonLib, my own custom library mod to add a few new functions to make our lives
as mod devs just a tad bit easer.

Currently the features this library mod adds are:

-NBT TimerTags that can count up or down (depending on a boolean parameter) each time the entity it's attached to ticks, with accompanying functions.

-A scare entity function to make the target entity run in the opposite direction of a different entity (this uses a separate nbt data system then the timer tags, but this might change in the future).

-A list contained in a custom '.dat' file inside the world save (saves/your_world_name_here/dimensions/minecraft/overworld/dragon_lib) that contains a list of entities that the game will 
not render (this uses a different method then the vanilla provided options) as an alternative to other more common methods.

Developer guide (how to import into mod-dev workspace):
=======

For the time being this section of the read.me file is going to be rather spars as I'm not sure how intuitive my code will be to other developers and how much of it I wall have to explain in more detail.

Until I have a better understanding of what I'll have to explain I'll just say that the methode for installing is to put the following code into your "build.gradle" file:

    repositories {
        maven { 
            url 'https://jitpack.io' 
        }
    }
    
    dependencies {
        implementation 'com.github.Gamerdragon0095:DragonLib-NeoForge:v26-1.0.0'
    }

Note that this code is an example and to get the latest version of the mod or the version your looking for you might have to change the "26-1.0.0" to something else.

And that you should only copy over the maven: 

    maven {
        url 'https://jitpack.io'
    }

And the implementation: 

    implementation 'com.github.Gamerdragon0095:DragonLib-NeoForge:v26-1.0.0'

And place them in the "repository" and "dependencies" sections as shown above instead of completely replacing the contents of the file itself.

Mod template stuff
============

Installation information
============

This template repository can be directly cloned to get you started with a new
mod. Simply create a new repository cloned from this one, by following the
instructions provided by [GitHub](https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template).

Once you have your clone, simply open the repository in the IDE of your choice. The usual recommendation for an IDE is either IntelliJ IDEA or Eclipse.

If at any point you are missing libraries in your IDE, or you've run into problems you can
run `gradlew --refresh-dependencies` to refresh the local cache. `gradlew clean` to reset everything 
{this does not affect your code} and then start the process again.

Mapping Names:
============
By default, the MDK is configured to use the official mapping names from Mojang for methods and fields 
in the Minecraft codebase. These names are covered by a specific license. All modders should be aware of this
license. For the latest license text, refer to the mapping file itself, or the reference copy here:
https://github.com/NeoForged/NeoForm/blob/main/Mojang.md

Additional Resources: 
==========
Community Documentation: https://docs.neoforged.net/  
NeoForged Discord: https://discord.neoforged.net/
