# 0.81a - 2020-05-23
- Added Korge 1.12.7
    - This version includes a lot of optimizations, specially for Kotlin/Native
    - Allow to have TileSets with several textures
    - Allows to update Textures from Bitmaps without creating newbitmaps
    
# 0.81 - 2020-05-20
- Added Korge 1.12.5 with several bugfixes and optimizations 
    - includes some shader optimizations
    - Adds a BlurFilter
    - Fixes a few issues including: with unsigned integer literals on tilemaps, and wrong stage size on the first frame
    - Adds a new SpriteAnimation @Nico Emig
    - Adds a RoundRect and relative positioning utilities for views @RezMike
    - Some SVG fixes (though you have to manually include korimVersion=1.12.15 that korim version will be included in the next plugins version)
    - Includes lots of fixes and optimizations to the software rasterizer (there is still a pending issue related to end edges, but now should be much better than before)
    - Supports both winding modes on rasterizer and polygon point checking
    - Initial support for CompositeOperation on rasterizer (JS and software rasterizer for now) and jus a few operations supported
    - Some general optimizations
- Sprite and SpriteAnimation moved from EngineEmi to Korge (new imports necessary)
    - Slightly changed API
    - Bugfixes and optimizations

   