/*
 *
 *  7272   72   72  72   72
 * 72  72  72   72  727 727
 * 72      72 7 72  72 2 72
 * 72  72  7272772  72   72
 *  7272    72 72   72   72
 *
 * @author Aradam
 */

package com.aradam.catwar.graphics;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class CwTextureAtlas extends TextureAtlas
{
  /* Словарь со спрайтами с атласа */
  private HashMap<String, AtlasRegion> mSprites = new HashMap<String, AtlasRegion> ();

  public CwTextureAtlas (String internalPackFile)
  {
    super (internalPackFile);

    //Делаем словарь по типу Name = AtlasRegion
    for (AtlasRegion region : getRegions ())
    {
      mSprites.put (region.name + "_" + region.index, region);
    }
  }

  public AtlasRegion getSprite (String name, int index)
  {
    return mSprites.get (name + "_" + index);
  }

  public AtlasRegion getSprite (String name)
  {
    return mSprites.get (name + "_-1");
  }

  @Override
  public void dispose ()
  {
    mSprites.clear ();
    super.dispose ();
  }
}