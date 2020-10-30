package clearmap.backend.util.tileSlicer

import rb.glow.Color
import rb.glow.Colors
import rb.glow.gl.GLImage
import rb.glow.img.IImage
import rb.vectrix.linear.Vec2i
import rb.vectrix.shapes.RectI
import spirite.specialRendering.fill.toIntArray


interface ILineTileSlicer {
    fun slice(image: GLImage, color: Color = Colors.RED) : Collection<RectI>
}

object LineTileSlicer : ILineTileSlicer {
    /**
     * Conditions for a top-left corner:
     * 1. (X,Y) is Red
     * 2. (X+1, Y) is Red
     * 3. (X, Y+1) is Red
     * 4. (X+1, Y+1) is not red.
     *
     * Once it finds a Top-Left corner starting at (X+1, Y), it traverse the top (over X):
     * 1. If below is red, it has found its top-right corner
     * 2. If the traversed pixel is not red then region detection has failed and moves on to the next to-left corner.
     *
     * Once it finds a Top-Right corner, it starts at (X, Y+1), traversing down:
     * 1. If to the right is red, it has found the bottom-left corner
     * 2. If the traversed pixel is not red, region detection has failed
     *
     * Once it has a Bottom-Left corner, it will crawl the bottom edge to make sure it's valid, starting at (blX+1, blY):
     * 1. If it has reached x = trX, it passes
     * 2. If the traversed pixel is not red, it fails
     * 3. If the above pixel is red, it fails
     *
     * And then it does the same for the Right wall (if above succeeded).  Starting at (trX, trY+1), going down, looking left
     */
    override fun slice(image: GLImage, color: Color): Collection<RectI> {
        val argb = color.argb32

        // Step 1: Store all pixels of image of the given color into a hashset
        val i = image.graphics.toIntArray()
        val redPositions = hashSetOf<Vec2i>()
        for (x in 0 until image.width) {
            for( y in 0 until image.height) {
                if(i[x + y*image.width] == argb)
                    redPositions.add(Vec2i(x,y))
            }
        }

        // Step 2: Find top-left pixels.
        val topLeftRegions = redPositions

            .filter { (x,y) ->
                redPositions.contains(Vec2i(x+1, y))  &&
                redPositions.contains(Vec2i(x, y+1)) &&
                !redPositions.contains(Vec2i(x+1,y+1))
            }

        // Step 3: Convert Top-Left regions into Regions (or null if failed)
        fun regionFind(tl: Vec2i) : RectI?
        {
            // Top Travel (to find Right edge)
            var topX = tl.xi + 1
            while(true) {
                if( !redPositions.contains(Vec2i(topX,tl.yi)))
                    return null
                if( redPositions.contains(Vec2i(topX, tl.yi+1)))
                    break
                ++topX
            }

            // Left Travel (to find bottom)
            var leftY = tl.yi + 1
            while (true) {
                if( !redPositions.contains(Vec2i(tl.xi, leftY)))
                    return null
                if( redPositions.contains(Vec2i(tl.xi+1, leftY)))
                    break
                ++leftY
            }

            // Bottom Travel (to verify)
            var bottomX = tl.xi + 1
            while( true) {
                if( bottomX == topX)
                    break
                if( !redPositions.contains(Vec2i(bottomX, leftY)))
                    return null
                if( redPositions.contains(Vec2i(bottomX, leftY - 1)))
                    return null
               ++ bottomX
            }

            // Right Travel (to verify)
            var rightY = tl.yi + 1
            while (true) {
                if( rightY == leftY)
                    break
                if( !redPositions.contains(Vec2i(topX, rightY)))
                    return null
                if( redPositions.contains(Vec2i(topX - 1, rightY)))
                    return null
                ++ rightY
            }
            return RectI(tl.xi+1, tl.yi+1, topX - tl.xi - 1, leftY - tl.yi - 1)
        }
        return topLeftRegions
            .mapNotNull { regionFind(it) }
    }
}