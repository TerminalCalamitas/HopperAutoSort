
# Hopper Sorting Plugin
A simple spigot based plugin to sort items using hoppers


## Acknowledgements

 - Much of this code was adapted from the example plugin made by LiveOverflow. 
    (If he made a repo for it I could not find it)

## Usage/Examples
To sort items rename a hopper with an anvil.

Sorting information:
- The names needed to sort items can be found in the minecraft advanced tool-tips. (f3+h)
    Example: to sort grass blocks you would rename the hopper to `grass_block`
-  Multiple items can be sorted by seperating them with a commands.
    Example: to sort grass blocks and diamonds the hopper would be named `grass_block,diamond` 
- `*` is a wild card character. This means that adding * to the name will allow text to be less specific.
    Example: to sort grass blocks and anything that starts with diamond the name would be `grass_block,diamond*`
- * can be used at the beginning, end, or both. `*block` will accept any item ending with "block", `grass*` will allow anything that begins with "grass", and `*grass*` will accept any item that contains "grass".

